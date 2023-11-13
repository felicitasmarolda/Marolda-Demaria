package linea;

public class TypeA extends TriumphType {

	public boolean canHandle(char type) {
		return type == 'A';
	}

	public FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine) {
		fourInLine.establishTriumphType(new TypeA());
		return fourInLine;
	}

	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(FourInLine fourInLine, char color, int column) {
		return currentPieceHorizontalTriumph(fourInLine, color, column)         ||
			   currentPieceVerticalTriumph(fourInLine, color, column);	
	}
}