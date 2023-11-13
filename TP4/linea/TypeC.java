package linea;

public class TypeC extends TriumphType {

	public boolean canHandle(char type) {
		return type == 'C';
	}

	public FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine) {
		fourInLine.establishTriumphType(new TypeC());
		return fourInLine;
	}
	
	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(FourInLine fourInLine, char color, int column) {
		return currentPieceHorizontalTriumph(fourInLine, color, column)         ||
			   currentPieceVerticalTriumph(fourInLine, color, column)   		  ||
			   currentPieceDiagonalTriumph(color, column, fourInLine);	
	}
}