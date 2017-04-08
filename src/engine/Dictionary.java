package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.apache.commons.io.input.BOMInputStream;

public class Dictionary {
	private int totalWords;
	private static String INVALID_CHARS = "[!?,.:;\\-_=+\\*\"'\\(\\){}\\[\\]%$]";
	HashMap<String, Integer> words;
	
	public boolean isValidWord(String word) {
		return words.containsKey(word);
	}
	
	public Dictionary(String filePath) throws FileNotFoundException, IOException {
		words = new HashMap<String, Integer>();
		loadDictionary(filePath);
		
		if (totalWords < 1) {
			throw new IOException("Dictionary file doesnt contain any words");
		}
	}
	
	private void loadDictionary(String filePath) throws FileNotFoundException, IOException {
		BufferedReader br = null;
		FileReader fr = null;
		
		File file = new File(filePath);
		FileInputStream fs = new FileInputStream(file);
		
		// byte order mark (BOM) is a Unicode character used to signal the endianness 
		// (byte order) of a text file or stream. we don't want to add this as a word.
		BOMInputStream bmr = new BOMInputStream(fs);
		
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			
			br.skip(bmr.getBOM().length());
			String currentLine = br.readLine();
			
			while (currentLine != null) {
				currentLine = currentLine.toUpperCase();
				currentLine = currentLine.replaceAll(INVALID_CHARS, "");
				String[] lineWords = currentLine.split(" ");
				
				for (String word : lineWords) {
					if (word.length() > 1) {
						totalWords++;
						if (words.containsKey(word)) {
							words.put(word, words.get(word).intValue() + 1);
						}
						else {
							words.put(word, 1);
						}
					}
				}
				
				currentLine = br.readLine();
			}
		}
		finally {
			if (br != null) {
				br.close();
			}
			
			if (fr != null) {
				fr.close();
			}
			
			if (bmr != null) {
				bmr.close();
			}
			
			if (fs != null) {
				fs.close();
			}
		}
	}
}
