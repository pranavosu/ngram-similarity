package implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimilarityChecker {

	
	private Set<String> tuplesToCheck;
	private int equalTuples;
	private Map<String, Set<String>> synonymMap;
	
	public SimilarityChecker() {
		super();
		tuplesToCheck = new HashSet<String>();
		equalTuples = 0;
		synonymMap = new HashMap<String, Set<String>>();
		
	}
	
	
	public void addSynonyms(ArrayList<String> synonymList){
		
		
		for(String synonyms: synonymList){
			
			
			String[] synList = synonyms.split("\\s");
			
			Set<String> synSet =  new HashSet<String>();
			
			for(String synonym: synList){
				
				synSet.add(synonym);
				
			}
			
			for(String synonym: synList){
				
				synonymMap.put(synonym, synSet);
				
			}
			
			
		}
		
	}
	public void generateTuplesFromSentence(int n,String line){
		
		NgramIterator ngramIter = new NgramIterator(n, line);
		
		while(ngramIter.hasNext()){
			
			tuplesToCheck.add(ngramIter.next());
		}
	}
	
	public int checkSentenceForExistingTuples(int n, String line){
		
		NgramIterator ngramIter = new NgramIterator(n, line);
		
		while(ngramIter.hasNext()){
			
			String tuple = ngramIter.next();
			
			if(matchTuple(tuple)){
				equalTuples++;
			}
		}
				
		return equalTuples;
	}


	private boolean matchTuple(String tuple) {
		
		if(tuplesToCheck.contains(tuple)) 
			return true;
		else
			return fuzzyMatch(tuple);
		
	}


	private boolean fuzzyMatch(String tuple) {
	
		String[] words = tuple.split("\\s");
		
		for(String word: words){

			if(!synonymMap.containsKey(word)) continue;
				
			Set<String> synSet = synonymMap.get(word);
					
			for(String synonym: synSet){
				
				if(!synonym.equals(word)){
					
					StringBuffer mTuple = new StringBuffer(tuple);
					
					int start = tuple.indexOf(word);
					int end = start+word.length();
					
					mTuple.replace(start, end, synonym);
					
					if(tuplesToCheck.contains(mTuple.toString()))
						return true;
					
				}
				
			}
			
		}
		
		return false;
	}
	
	/**
	 * Generates a similarity measure from the sentences(original and checked)
	 * @return current percentage of similar tuples among the sentences seen.
	 */
	public double getSimilarityMeasure(){
		
		if(tuplesToCheck.size() == 0) return 0.0;
		
		return ((double) equalTuples/tuplesToCheck.size())*100;
		
	}
	 

}
