/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {
	ArrayList <String> wordList = new ArrayList<>();

	public HangmanLexicon() {


		//adds the individual words in the file to the array list
		try {
			BufferedReader hangmanWords = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true) {
				String line = hangmanWords.readLine();
				if(line == null) break;
				wordList.add(line);
			}
			hangmanWords.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	};
}
