package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class QuestionControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new QuestionController(null, null, null));
 	}
}
