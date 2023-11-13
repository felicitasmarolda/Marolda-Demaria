package linea;

public class TypeNull extends TriumphType {

	public boolean canHandle(char type) {
		return type != 'A' && 
			   type != 'B' &&
			   type != 'C';
	}

	public FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine) {
		throw new RuntimeException("Invalid triumph type parameter.");
	}
	
	public boolean verifyTriumphAsTypeInGameWithColorAndColumn(FourInLine fourInLine, char color, int column) {
		return false;	
	}
}