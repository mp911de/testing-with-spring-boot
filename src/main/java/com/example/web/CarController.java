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

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.domain.Car;
import com.example.domain.Money;
import com.example.usecase.FindCar;
import com.example.usecase.SellCar;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mark Paluch
 */
@RestController
public class CarController {

    private final FindCar findCar;
    private final SellCar sellCar;

    public CarController(FindCar findCar, SellCar sellCar) {
        this.findCar = findCar;
        this.sellCar = sellCar;
    }

    @GetMapping(value = "/cars/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car findCarJson(@PathVariable String name) {
        return getCar(name);
    }

    private Car getCar(String name) {
        return findCar.findCar(name).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/cars/{name}.html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> findCarHtml(@PathVariable String name) {

        Optional<Car> car = findCar.findCar(name);

        return car //
                .map((body) -> ResponseEntity.ok(String.format("<html><h1>Car</h1><p>Found %s</p></html>", body.getName()))) //
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("<html><h1>Not Found</h1><p>Cannot find %s</p></html>", name)));
    }

    @DeleteMapping("/cars/{name}")
    public Money sellCar(@PathVariable String name) {
        return sellCar.sellCar(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleNotFoundException(NotFoundException e) {

    }
}
