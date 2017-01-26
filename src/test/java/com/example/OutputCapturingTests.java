package com.example;

import static org.hamcrest.Matchers.containsString;

import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.test.rule.OutputCapture;

/**
 * Test expecting some output on sysout.
 *
 * @author Mark Paluch
 */
public class OutputCapturingTests {

	@Rule public final OutputCapture outputCapture = new OutputCapture();

	@Test
	public void shouldOutputAString() {

		outputCapture.expect(containsString("expected on sysout"));

		System.out.println("Hello, I'm expected on sysout");
	}
}
