package implementation.utils;

import implementation.core.SimilarityChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PlagiarismDetector {

	
	static SimilarityChecker similarityChecker;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		CommandLineParser clp = new CommandLineParser();
		clp.parseArguments(args);
		
		similarityChecker = new SimilarityChecker();
		
		
		ArrayList<String> synonymList = new ArrayList<String>();
		
		synonymList.add("run sprint jog");
		
		similarityChecker.addSynonyms(synonymList);
		
		findPercentPlagiarised(null, "in1.txt", "in2.txt", 3);
		
	}

	private static void findPercentPlagiarised(Object object, String originalFilePath,
			String fileToCheckPath, int n) {
		
			
		File inputFile = new File(originalFilePath);
		
		try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
			
			
			String line; 
			
			while((line = reader.readLine()) != null){
				
				line = sanitizeInput(line);
				similarityChecker.generateTuplesFromSentence(n, line);
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		File fileToCheck = new File(fileToCheckPath);
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileToCheck))){
			
			String line; 
			
			while((line = reader.readLine()) != null){
				
				line = sanitizeInput(line);
				similarityChecker.checkSentenceForExistingTuples(n, line);
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(similarityChecker.getSimilarityMeasure());
		
		
		
		
		
	}

	public static String sanitizeInput(String line) {
		
		line = line.replaceAll("[\\.,!\\?:;\\-_]", "").toLowerCase();
		
		return line;
	}
	
	
	
	
	
}
