/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domain.Car;
import com.example.usecase.FindCar;
import com.example.usecase.SellCar;

/**
 * Tests for {@link CarController}.
 * 
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FindCar findCar;

    @MockBean
    private SellCar sellCar;

    @Test
    public void findCarShouldReturnCar() throws Exception {

        when(findCar.findCar("Honda")).thenReturn(Optional.of(new Car("Honda")));

        mvc.perform(get("/cars/Honda").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json("{ 'name': 'Honda' }"));
    }

    @Test
    public void findCarShouldReturn404WhenNoCarIsFound() throws Exception {

    	when(findCar.findCar(anyString())).thenReturn(Optional.empty());

        mvc.perform(get("/cars/Unknown").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}