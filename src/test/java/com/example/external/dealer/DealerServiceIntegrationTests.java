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

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.example.domain.Car;
import com.example.domain.Money;

/**
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@RestClientTest(DealerService.class)
@DealerServiceEndpoint("http://localhost:8081")
public class DealerServiceIntegrationTests {

    @Autowired
    private DealerService sut;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails() throws Exception {

        this.server.expect(requestTo("http://localhost:8081/offers/Honda"))
                .andRespond(withSuccess("{\"amount\": 42, \"currency\": \"USD\"}", MediaType.APPLICATION_JSON));

        Money bucks = sut.sell(new Car("Honda"));

        assertThat(bucks.getValue()).isEqualTo(BigDecimal.valueOf(42));
        assertThat(bucks.getCurrency()).isEqualTo(Currency.getInstance("USD"));
    }
}