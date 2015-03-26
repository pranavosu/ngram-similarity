package tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import implementation.core.NtupleIterator;

import org.junit.Test;

public class NgramIteratorTests {

	@Test
	public void testGetFirstN() {
		
		NtupleIterator iter = new NtupleIterator(3, "go for a jog");
		
		String actual = iter.next();
		
		String expected = "go for a";
		
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testGetNextN() {
		
		NtupleIterator iter = new NtupleIterator(3, "go for a jog");
		
		iter.next();
		
		String actual = iter.next();
		
		String expected = "for a jog";
		
		assertEquals(expected,actual);
		
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testNoMoreTuples() {
		
		NtupleIterator iter = new NtupleIterator(3, "go for a jog");
		
		iter.next();
		iter.next();
		iter.next();
		
		
	}
	
	@Test
	public void testEmptyString() {
		
		NtupleIterator iter = new NtupleIterator(3, "");
		
		boolean actual = iter.hasNext();
		boolean expected = false;
		
		
		assertEquals(expected, actual);
		
	}

}
