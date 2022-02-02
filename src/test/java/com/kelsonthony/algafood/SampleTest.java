package com.kelsonthony.algafood;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({"/application-test.properties"})
public class SampleTest {

	@Value("${data.name}")
	private String name;
	
	@Test
	public void Test() {
		System.out.println("Test " + name);
	}
}
