
package wordsearch;

import java.util.List;

import wordsearch.NoWrapSearch;
import wordsearch.WordSearch;
import wordsearch.WrapSearch;

public class WordGridBuilder {
	private char[][] WordGrid;
	private boolean Wrap;
	// N * M WordGrid(r*c)
	private int r;
	private int c;
	private List<String> searchWords;

	/*justifying the wrap search or no_wrap search*/
	public WordSearch builder() {
		WordSearch word = null;
		if (this.Wrap) {
			word = new WrapSearch(this.WordGrid, this.r, this.c,
					this.searchWords);
		}else{
			word = new NoWrapSearch(this.WordGrid, this.r, this.c,
					this.searchWords);
		}

		return word;
	}

	public char[][] getWordGrid() {
		return WordGrid;
	}

	public void setWordGrid(char[][] wordGrid) {
		WordGrid = wordGrid;
	}

	public boolean isWrap() {
		return Wrap;
	}

	public void setWrap(boolean wrap) {
		Wrap = wrap;
	}

	public int getRow() {
		return r;
	}

	public void setRow(int row) {
		this.r = row;
	}

	public int getColumn() {
		return c;
	}

	public void setColumn(int column) {
		this.c = column;
	}

	public List<String> getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(List<String> searchWords) {
		this.searchWords = searchWords;
	}

}
