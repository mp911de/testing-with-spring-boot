# Testing with Spring Boot 1.5

This repository contains code to demonstrate the use of the new Spring Boot 1.5 testing support.

In particular it shows the usage of:

* AssertJ                     
* `@LocalServerPort` through [TestingWithSpringBootApplicationTests.java](src/test/java/com/example/TestingWithSpringBootApplicationTests.java)
* `@RestClientTest` through [DealerServiceIntegrationTests.java](src/test/java/com/example/external/dealer/DealerServiceIntegrationTests.java) 
* `@JsonTest` through [TransactionJsonTests.java](src/test/java/com/example/external/dealer/TransactionJsonTests.java) 
* `@DataJpaTest` through [CarRepositoryIntegrationTests.java](src/test/java/com/example/external/data/CarRepositoryIntegrationTests.java) 
* `@MockBean` and `@WebMvcTest` through [CarControllerTests.java](src/test/java/com/example/web/CarControllerTests.java) 
* `@WebMvcTest` with Selenium and HtmlUnit through [CarControllerSeleniumTests.java](src/test/java/com/example/web/CarControllerSeleniumTests.java) and [CarControllerHtmlUnitTests.java](src/test/java/com/example/web/CarControllerHtmlUnitTests.java) 
* `TestRestTemplate` through [CarControllerRestTests.java](src/test/java/com/example/web/CarControllerSeleniumTests.java)
* `@Rule OutputCapture` through through [OutputCapturingTests.java](src/test/java/com/example/OutputCapturingTests.java)