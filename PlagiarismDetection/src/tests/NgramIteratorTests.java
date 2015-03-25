package tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import implementation.NgramIterator;

import org.junit.Test;

public class NgramIteratorTests {

	@Test
	public void testGetFirstN() {
		
		NgramIterator iter = new NgramIterator(3, "go for a jog");
		
		String actual = iter.next();
		
		String expected = "go for a";
		
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testGetNextN() {
		
		NgramIterator iter = new NgramIterator(3, "go for a jog");
		
		iter.next();
		
		String actual = iter.next();
		
		String expected = "for a jog";
		
		assertEquals(expected,actual);
		
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testNoMoreTuples() {
		
		NgramIterator iter = new NgramIterator(3, "go for a jog");
		
		iter.next();
		iter.next();
		iter.next();
		
		
	}
	
	@Test
	public void testEmptyString() {
		
		NgramIterator iter = new NgramIterator(3, "");
		
		boolean actual = iter.hasNext();
		boolean expected = false;
		
		
		assertEquals(expected, actual);
		
	}

}
