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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JSON representation test for {@link com.example.domain.Money}.
 *
 * @author Mark Paluch
 */
@RunWith(SpringRunner.class)
@JsonTest
public class TransactionJsonTests {

    @Autowired
    private JacksonTester<Transaction> json;

    @Test
    public void serializeJson() throws Exception {

        Transaction transaction = new Transaction(42, "USD");

        assertThat(this.json.write(transaction)).isEqualTo("{\"amount\":42, \"currency\":\"USD\"}");
    }

    @Test
    public void deserializeJson() throws Exception {

        String content = "{\"amount\":42, \"currency\":\"USD\"}";

        assertThat(this.json.parse(content)).isEqualTo(new Transaction(42, "USD"));
        assertThat(this.json.parseObject(content).getAmount()).isEqualTo(42);
    }
}
