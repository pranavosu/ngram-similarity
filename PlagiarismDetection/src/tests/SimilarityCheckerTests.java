package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import implementation.core.SimilarityChecker;

import org.junit.Test;

public class SimilarityCheckerTests {

	@Test
	public void testInitialMeasure() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		double expected = 0.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}
	
	@Test
	public void testFullMatch() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		int n = 3;
		
		sc.generateTuplesFromSentence(n, "go for a jog");
		sc.checkSentenceForExistingTuples(n, "go for a jog");
		
		double expected = 100.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}
	
	@Test
	public void testPartialMatch() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		int n = 3;
		
		sc.generateTuplesFromSentence(n, "go for a jog");
		sc.checkSentenceForExistingTuples(n, "went for a jog");
		
		double expected = 50.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}

	@Test
	public void testSynonymMatch() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		int n = 3;
		
		ArrayList<String> synonymList = new ArrayList<String>();
		synonymList.add("run sprint jog");
		sc.addSynonyms(synonymList);
		
		sc.generateTuplesFromSentence(n, "go for a run");
		sc.checkSentenceForExistingTuples(n, "go for a jog");
		
		double expected = 100.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}
	
	@Test
	public void testMultipleMatch() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		int n = 3;
		
		ArrayList<String> synonymList = new ArrayList<String>();
		synonymList.add("run sprint jog");
		sc.addSynonyms(synonymList);
		
		sc.generateTuplesFromSentence(n, "go for a run go for a jog");
		sc.checkSentenceForExistingTuples(n, "go for a jog");
		
		double expected = 50.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}
	
	@Test
	public void testOppMatch() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		int n = 3;
		
		ArrayList<String> synonymList = new ArrayList<String>();
		synonymList.add("run sprint jog");
		sc.addSynonyms(synonymList);
		
		sc.generateTuplesFromSentence(n, "go for a run");
		sc.checkSentenceForExistingTuples(n, "go for a jog  go for a run");
		
		double expected = 100.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}
	
	@Test
	public void testMultipleSynonymMatch() {
		
		SimilarityChecker sc = new SimilarityChecker();
		
		int n = 3;
		
		ArrayList<String> synonymList = new ArrayList<String>();
		synonymList.add("run sprint jog");
		synonymList.add("go went");
		synonymList.add("for fah");
		sc.addSynonyms(synonymList);
		
		sc.generateTuplesFromSentence(n, "go for run");
		sc.checkSentenceForExistingTuples(n, "went fah jog");
		
		double expected = 100.0;
		double actual = sc.getSimilarityMeasure();
		
		assertEquals(expected, actual,0.0001);
		
	}
	
	
	
}
