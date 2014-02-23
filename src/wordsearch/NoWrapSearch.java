/* No wrap search class*/


package wordsearch;

import java.util.List;

import wordsearch.Coordinate;

public class NoWrapSearch extends WordSearch {

	public NoWrapSearch(char[][] wordGrid, int row, int column,
			List<String> searchWords) {
		super(wordGrid, row, column, searchWords);
	}

	@Override
	public Coordinate adjustPosition(int row, int column) {
		if (row >= 0 && row < this.getRow() && column >= 0 && column < this.getColumn()) {
			return new Coordinate(row, column);
		} else {
			return null;
		}
	}

}
