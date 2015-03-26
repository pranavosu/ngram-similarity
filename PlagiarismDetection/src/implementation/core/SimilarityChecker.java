package implementation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimilarityChecker {

	
	private Map<String,String> tuplesMap;
	private int equalTuples;
	private Map<String, List<String>> synonymMap;
	
	public SimilarityChecker() {
		
		super();
		tuplesMap = new HashMap<String,String>();
		equalTuples = 0;
		synonymMap = new HashMap<String, List<String>>();
		
	}
	
	public void addSynonyms(ArrayList<String> synonymList){
		
		for(String synonyms: synonymList){
			
			String[] synList = synonyms.split("\\s");
			List<String> synSet =  new ArrayList<String>();
		
			for(String synonym: synList){
				
				synSet.add(synonym);
			}
			
			for(String synonym: synList){
				
				synonymMap.put(synonym, synSet);
				
			}
		}
	}
	
	
	public void generateTuplesFromSentence(int n,String line){
		
		NtupleIterator ngramIter = new NtupleIterator(n, line);
		
		while(ngramIter.hasNext()){
			
			String tuple = ngramIter.next();
			
			String key = getTupleSynonymKey(tuple);
			
			tuplesMap.put(key,tuple);
		}
	}

	/**
	 * 
	 * @param n
	 * @param line
	 * @return
	 */

	public int checkSentenceForExistingTuples(int n, String line){
		
		NtupleIterator ngramIter = new NtupleIterator(n, line);
		
		while(ngramIter.hasNext()){
			
			String tuple = ngramIter.next();
			
			if(fuzzyMatch(tuple)){
				equalTuples++;
			}
		}
				
		return equalTuples;
	}

	
	private boolean fuzzyMatch(String tuple){
		
		String key = getTupleSynonymKey(tuple);
		
		return tuplesMap.containsKey(key.toString());
		
	}
	
	/**
	 * Generates the key used for hashing a tuple. 
	 * Concatenates all synonyms and words in the tuple to generate the key.
	 * @param tuple
	 * @return key for hashing a tuple.
	 */
	private String getTupleSynonymKey(String tuple) {
		
		
		String[] words = tuple.split("\\s");
		
		StringBuffer key = new StringBuffer();
		
		
		for(String word: words){
			
			if(!synonymMap.containsKey(word)) 
				key.append(word);
			else
			{
				List<String> synSet = synonymMap.get(word);
				
				for(String syn: synSet){
					
					key.append(syn);
				}
			}
			
		}
		
		return key.toString();
	}
	
	
	/**
	 * Generates a similarity measure from the sentences(original and checked)
	 * @return current percentage of similar tuples among the sentences seen.
	 */
	public double getSimilarityMeasure(){
		
		if(tuplesMap.size() == 0) return 0.0;
		
		return ((double) equalTuples/tuplesMap.size())*100;
		
	}
	 

}
