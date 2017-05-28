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

package com.example.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.domain.Car;
import com.example.external.data.CarEntity;
import com.example.external.data.CarRepository;

/**
 * Unit test for {@link FindCar}.
 *
 * @author Mark Paluch
 */
public class FindCarTests {

    private FindCar sut;

    @Mock
    private CarRepository carRepositoryMock;

    @Before
    public void before() throws Exception {

        MockitoAnnotations.initMocks(this);

        sut = new FindCar(carRepositoryMock);
    }

    @Test
    public void shouldNotFindCar() throws Exception {

        Optional<Car> absent = sut.findCar("absent");

        assertThat(absent).isNotPresent();
    }

    @Test
    public void shouldFindCar() throws Exception {

        when(carRepositoryMock.findByName("Honda")).thenReturn(new CarEntity("Honda"));

        Optional<Car> present = sut.findCar("Honda");

        assertThat(present).isPresent().hasValueSatisfying(car -> {
            assertThat(car.getName()).isEqualTo("Honda");
        });
    }
}
