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

package com.example.external.dealer;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Car;
import com.example.domain.Money;

/**
 * Sells a car.
 * 
 * @author Mark Paluch
 */
@Service
public class DealerService {

    private final RestTemplate restTemplate;
    @Value("${dealer.service.value:http://my.dealer.service:9999}")
    private String endpoint;

    public DealerService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Money sell(Car car) {

        ResponseEntity<Transaction> response = restTemplate.postForEntity(String.format("%s/offers/{name}", endpoint), car,
                Transaction.class, car.getName());

        Transaction body = response.getBody();

        return new Money(new BigDecimal(body.getAmount()), Currency.getInstance(body.getCurrency()));
    }
}
