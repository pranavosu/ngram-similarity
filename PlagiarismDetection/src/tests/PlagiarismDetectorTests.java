package tests;

import static org.junit.Assert.*;
import implementation.utils.PlagiarismDetector;

import org.junit.Test;

public class PlagiarismDetectorTests {

	@Test
	public void testSanitizeInput() {
		
		String line = "hello!, Go for:- a run?.";
		
		String actual = PlagiarismDetector.sanitizeInput(line);
		String expected = "hello go for a run";
		
		assertEquals(expected, actual);
		
		
	}

}
