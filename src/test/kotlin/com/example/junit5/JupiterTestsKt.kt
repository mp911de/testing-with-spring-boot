package com.example.junit5

import com.example.domain.Car
import com.example.domain.Money
import com.example.external.dealer.DealerService
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.math.BigDecimal
import java.util.*

/**
 * @author Mark Paluch
 */
@SpringJUnitConfig(TestAppConfiguration::class)
class JupiterTestsKt(@Autowired val dealerService: DealerService) {

	@Test
	fun testDealerService() {
		assertThat(dealerService.sell(Car())).isEqualTo(Money(BigDecimal.ONE, Currency.getInstance("EUR")))
	}
}
