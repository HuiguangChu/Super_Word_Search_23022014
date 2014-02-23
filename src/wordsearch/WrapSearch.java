package wordsearch;

import java.util.List;
import wordsearch.Coordinate;

public class WrapSearch extends WordSearch {

	public WrapSearch(char[][] wordGrid, int row, int column,
			List<String> searchWords) {
		super(wordGrid, row, column, searchWords);
	}

	@Override
	public Coordinate adjustPosition(int row, int column) {
		if(row<0) {
			row += this.getRow();
			}
		if(column<0) {
			column += this.getColumn();
			}
		row %= this.getRow();
		column %= this.getColumn();
		return new Coordinate(row, column);
	}

}
