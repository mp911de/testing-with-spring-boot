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

import com.example.annotation.UseCase;
import com.example.domain.Car;
import com.example.domain.Money;
import com.example.external.dealer.DealerService;

/**
 * @author Mark Paluch
 */
@UseCase
public class SellCar {

    private final DealerService dealerService;
    private final FindCar findCar;

    public SellCar(DealerService dealerService, FindCar findCar) {
        this.dealerService = dealerService;
        this.findCar = findCar;
    }

    public Money sellCar(String name) {

        Car car = findCar.findCar(name).orElseThrow(() -> new IllegalStateException(String.format("Car %s not found", name)));

        return dealerService.sell(car);
    }
}
