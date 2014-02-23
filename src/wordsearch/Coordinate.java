package wordsearch;

import wordsearch.Direction;
import wordsearch.WordSearch;

public class Coordinate {
	private int r; //row
	private int c;//column

	public Coordinate(int row, int column) {
		super();
		this.r = row;
		this.c = column;
	}

	public Coordinate getPosition(Direction dir, int i, WordSearch  word) {
		Coordinate p = null;
		int row =this.r;
		int column = this.c;
		switch (dir) {
		case RL:
			column -= i;
			break;
		case BT:
			row -= i;
			break;
		case LR:
			column += i;
			break;
		case TB:
			row += i;
			break;
		case BLTR:
			row -= i;
			column += i;
			break;
		case BRTL:
			row -= i;
			column -= i;
			break;
		case TLBR:
			row += i;
			column += i;
			break;
		case TRBL:
			row += i;
			column -= i;
			break;
		
		}
		p = word.adjustPosition(row, column);
		return p;
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

	public String toString() {
		String result = "";
		result += "( " + r + "," + c + " )";
		return result;
	}
	
/*justify whether the begining letter and the ending letter is the same or not*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (c != other.c)
			return false;
		if (r != other.r)
			return false;
		return true;
	}
	
	
}
