
package wordsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import wordsearch.WordsearchGame;

public class WordFileReader{
	private WordGridBuilder words;

	public WordFileReader(WordGridBuilder builder) {
		this.words = builder;
	}

	/*
	 * Read the context content from the Java application Textbox
	 * 
	 */
	public void readInput() {
		StringReader sr=new StringReader(WordsearchGame.text.getText());
		BufferedReader reader = null;
		String in = null;
		try {
			reader = new BufferedReader(sr);
			int count = 0;
			String gridtmp = "";
			while ((in = reader.readLine()) != null) {
				if (count == 0) {
					//deal with the row and column.
					int sep = in.indexOf(' ');
					words.setRow(Integer.parseInt(in.substring(0, sep)));
					words.setColumn(Integer.parseInt(in.substring(sep + 1,
							in.length())));
					count++;
				} else if (count != 0 && count < words.getRow() + 1) {
					gridtmp += in;
					count++;
				} else if (count == words.getRow() + 1) {
					//deal with the WRAP status
					words.setWrap(in.equals("WRAP") ? true : false);
					count++;
				} else if (count == words.getRow() + 2){
					//deal with the search words number
					List<String> list = new ArrayList<String>(Integer.parseInt(in));
					words.setSearchWords(list);
					count++;
				} else{
					//deal with the search words
					words.getSearchWords().add(in);
					count ++;
				}
			}
			words.setWordGrid(transferToGrid(gridtmp));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/*set up the grid*/
	private char[][] transferToGrid(String in){
		char[][] result = new char[words.getRow()][words.getColumn()];
		for(int i = 0; i < words.getRow(); i ++){
			for(int j = 0; j< words.getColumn(); j ++){
				result[i][j] = in.toCharArray()[i*words.getColumn() + j];
			}
		}
		return result;
	}

}
