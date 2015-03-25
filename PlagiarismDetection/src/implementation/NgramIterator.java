package implementation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NgramIterator implements Iterator<String> {
	
	 String[] words;
	 int currentPosition = 0;
	 int n;

	    
	public NgramIterator(int n, String str) {
	        this.n = n;
	        words = str.split(" ");
	}
	 
	@Override
	public boolean hasNext() {
		
		return currentPosition < words.length - n + 1;
		
	}

	@Override
	public String next() {
	
		StringBuilder sb = new StringBuilder();
      
		int end = currentPosition + n;
		
		if(end > words.length) throw new NoSuchElementException();
		
		for (int i = currentPosition; i < end; i++)
            sb.append((i > currentPosition ? " " : "") + words[i]);
        
		currentPosition++;
        
        return sb.toString();
	}
	

}
