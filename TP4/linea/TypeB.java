package linea;

public class TypeB extends TriumphType {

	public boolean canHandle(char type) {
		return type == 'B';
	}
	
	public FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine) {
		fourInLine.establishTriumphType(new TypeB());
		return fourInLine;
	}
	
	public boolean verifyTriumphAsTypeInGameWithColorAndColumn(FourInLine fourInLine, char color, int column) {
		return currentPieceDiagonalTriumph(fourInLine, color, column);	
	}
}