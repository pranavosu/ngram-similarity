package implementation.utils;

import java.text.ParseException;
import java.util.HashMap;


public class CommandLineParser {
	
	
	HashMap<String, String> parseMap;

	public HashMap<String, String> parseArguments(String args[]){
		
		parseMap = new HashMap<String, String>();
//		
//		if(args.length % 2 !=0){
//			
//			throw new ParseException("Invalid Number of Arguments", 0);
//		}
//	
		for(int i = 0; i < args.length-1 ; i++){
			
			parseMap.put(args[i], args[i+1]);
			
		}
		
		return parseMap;
		
		
	}
	
	public String getArgForOption(String option) throws ParseException{
		
		
		if(parseMap.containsKey(option))
			return parseMap.get(option);
		else
			throw new ParseException("Invalid option: "+option, 0);
		
	}
	
	public boolean isArgPresent(String option){
		return parseMap.containsKey(option);
	}
	
}
