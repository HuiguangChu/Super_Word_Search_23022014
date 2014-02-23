
package wordsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import wordsearch.Coordinate;
import wordsearch.Direction;

public abstract class WordSearch {
	protected char[][] wordGrid;
	// row * column WordGrid
	protected int r;
	protected int c;
	private List<String> searchWords;
	private List<Result> searchResults;

	public WordSearch(char[][] wordGrid, int row, int column,
			List<String> searchWords) {
		super();
		this.wordGrid = wordGrid;
		this.r = row;
		this.c = column;
		this.searchWords = searchWords;
		this.searchResults = new ArrayList<Result>(searchWords.size());
	}

	@SuppressWarnings("rawtypes")
	public List search() {
		// TODO Auto-generated method stub
		List<Coordinate> result = new LinkedList<Coordinate>();
		for (String word : searchWords) {

			result = searchWord(word);
			if (result != null) {
				searchResults.add (searchWords.indexOf(word),
						new Result(result.get(0), result.get(result.size() - 1)));
			}else{
				searchResults.add(searchWords.indexOf(word), null);
			}
		}
		return result;
	}

	public List<Result> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<Result> searchResults) {
		this.searchResults = searchResults;
	}

	public List<Coordinate> searchWord(String word) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				Coordinate pp = new Coordinate(i, j);

				for (Direction dir : Direction.values()) {
					List<Coordinate> pos = new LinkedList<Coordinate>();
					char[] ch = word.toCharArray();
					for (int m = 0; m < ch.length; m++) {

						Coordinate p = pp.getPosition(dir, m, this);
						if(p == null)break;
						char now = getChar(p);
						if (pos.contains(p)) {
							break;
						}
						if (now == ch[m]) {
							pos.add(p);
						} else {
							break;
						}

					}
					if (pos.size() == ch.length) {
						return pos;
					}
				}
			}
		}
		return null;

	}

	public abstract Coordinate adjustPosition(int row, int column);

	public char getChar(Coordinate po) {
		return wordGrid[po.getRow()][po.getColumn()];
	}

	
	
	public String toString() {
		String result = "";
		result += r + "row " + c + "column\n";

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				result += wordGrid[i][j] + " ";
			}
			result += "\n";
		}
		result += searchWords.toString();

		return result;
	}

	public char[][] getWordGrid() {
		return wordGrid;
	}

	public void setWordGrid(char[][] wordGrid) {
		this.wordGrid = wordGrid;
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

	private class Result {
		private Coordinate start;
		private Coordinate end;

		public Result(Coordinate start, Coordinate end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return start.toString() + " " + end.toString();
		}

	}
}
