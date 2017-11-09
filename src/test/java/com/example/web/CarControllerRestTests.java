/*
 * Copyright 2016-2017 the original author or authors.
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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Car;
import com.example.usecase.FindCar;
import com.example.usecase.SellCar;

/**
 * Tests for {@link CarController}.
 *
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarControllerRestTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FindCar findCar;

    @MockBean
    private SellCar sellCar;

    @Test
    public void findCarShouldReturnCar() {

        when(findCar.findCar("Honda")).thenReturn(Optional.of(new Car("Honda")));

        ResponseEntity<Car> honda = restTemplate.getForEntity("/cars/{car}", Car.class, "Honda");

        assertThat(honda.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(honda.getBody().getName()).isEqualTo("Honda");
    }
}
