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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Car;
import com.example.usecase.FindCar;
import com.example.usecase.SellCar;

/**
 * Selenium Tests for {@link CarController}.
 *
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerSeleniumTests {

    @Autowired
    private WebDriver webDriver;

    @MockBean
    private FindCar findCar;

	@MockBean
    private SellCar sellCar;

    @Test
    public void findCarShouldReturnCar() {

        when(findCar.findCar("Honda")).thenReturn(Optional.of(new Car("Honda")));

        this.webDriver.get("/cars/Honda.html");
        assertThat(webDriver.findElement(By.tagName("h1")).getText()).isEqualTo("Car");
        assertThat(webDriver.findElement(By.tagName("p")).getText()).isEqualTo("Found Honda");
    }

}
