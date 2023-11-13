package linea;

public class TypeNull extends TriumphType {

	public boolean canHandle(char type) {
		return type != 'A' && 
			   type != 'B' &&
			   type != 'C';
	}

	public FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine) {
		throw new RuntimeException("Parámetro de tipo de estrategia inválido.");
	}
	
	public boolean verifyTriumphInGameAsTypeWithColorAndColumn(FourInLine fourInLine, char color, int column) {
		return false;	
	}
}