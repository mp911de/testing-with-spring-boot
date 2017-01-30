package com.example.web;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.RequestDispatcher;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.TestingWithSpringBootApplication;
import com.example.external.data.CarEntity;
import com.example.external.data.CarRepository;

/**
 * API Documentation with Spring REST Docs.
 *
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs("target/generated-snippets")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = TestingWithSpringBootApplication.class)
@AutoConfigureMockMvc
public class ApiDocumentation {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void errorExample() throws Exception {

        this.mockMvc
                .perform(
                        get("/error")
                                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
                                .requestAttr(RequestDispatcher.ERROR_REQUEST_URI, "/cars")
                                .requestAttr(RequestDispatcher.ERROR_MESSAGE,
                                        "The car 'http://localhost:8080/cars/123' does not exist"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error", is("Bad Request")))
                .andExpect(jsonPath("timestamp", is(notNullValue())))
                .andExpect(jsonPath("status", is(400)))
                .andExpect(jsonPath("path", is(notNullValue())))
                .andDo(document(
                        "error-example",
                        responseFields(fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
                                fieldWithPath("message").description("A description of the cause of the error"),
                                fieldWithPath("path").description("The path to which the request was made"),
                                fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
                                fieldWithPath("timestamp")
                                        .description("The time, in milliseconds, at which the error occurred"))));
    }

    @Test
    public void indexExample() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andDo(document(
                        "index-example",
                        links(linkWithRel("cars").description("The <<resources-cars,Cars resource>>"), linkWithRel("profile")
                                .description("The ALPS profile for the service")), responseFields(fieldWithPath("_links")
                                .description("<<resources-index-links,Links>> to other resources"))));

    }

    @Test
    public void carRepositoryListExample() throws Exception {

        this.carRepository.deleteAll();

        carRepository.save(new CarEntity("Audi"));
        carRepository.save(new CarEntity("Honda"));

        this.mockMvc
                .perform(get("/cars"))
                .andExpect(status().isOk())
                .andDo(document(
                        "cars-list-example",
                        links(linkWithRel("self").description("Canonical link for this resource"), linkWithRel("search")
                                .description("Search link for this resource"),
                                linkWithRel("profile").description("The ALPS profile for this resource")),
                        responseFields(
                                fieldWithPath("_embedded.cars").description("An array of <<resources-car, Car resources>>"),
                                fieldWithPath("_links").description("<<resources-cars-list-links, Links>> to other resources"))));
    }

}
