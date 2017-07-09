package com.example.junit5;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.domain.Car;
import com.example.domain.Money;
import com.example.external.dealer.DealerService;

/**
 * @author Mark Paluch
 */
@SpringJUnitConfig(TestAppConfiguration.class)
class JupiterTests {

    @Test
    void testDealerService(@Autowired DealerService dealerService) {
        assertThat(dealerService.sell(new Car())).isEqualTo(new Money(BigDecimal.ONE, Currency.getInstance("EUR")));
    }
}
