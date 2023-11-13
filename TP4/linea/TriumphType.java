package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public abstract class TriumphType {

	private static List<TriumphType> possibleModes = new ArrayList<TriumphType>( Arrays.asList(new TypeA(),
			  																		   new TypeB(),
			  																		   new TypeC(),
			   																		   new TypeNull()) );
	
	public static TriumphType typeFor(Character typeChar) {
		List<TriumphType> typeForTypeChar = new ArrayList();
		possibleModes.stream()
					 .filter( typeInstance -> typeInstance.canHandle(typeChar) )
					 .forEach( typeInstance -> typeForTypeChar.add(typeInstance) );
		return typeForTypeChar.get(0);
	}
	
	protected abstract FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine);
	protected abstract boolean canHandle(char typeChar);
	protected abstract boolean verifyTriumphInGameAsTypeWithColorAndColumn(FourInLine fourInLine, char color, int column);
	
	
	protected boolean currentPieceVerticalTriumph(FourInLine fourInLine, char color, int column) {
		return 4 <= IntStream.range(0, fourInLine.gameHeight)
							 .takeWhile(y -> fourInLine.getColorAt(column - 1, y) == color)
							 .count() ;
	}
	
	protected boolean currentPieceHorizontalTriumph(FourInLine fourInLine, char color, int column) {
		return 4 <= IntStream.range(0, fourInLine.gameBase)
							 .takeWhile(xCoord -> fourInLine.getColorAt(xCoord, fourInLine.getRow(column) - 1) == color)
							 .count() ;
	}
	
	protected boolean currentPieceIncreasingDiagonalTriumph(FourInLine fourInLine, char color, int column) {
		return 4 <= IntStream.range(0, fourInLine.gameHeight)
							 .takeWhile(y -> fourInLine.getColorAt(column - fourInLine.getRow(column) + y, y) == color)
							 .count() ;
	}
	
	protected boolean currentPieceDecreasingDiagonalTriumph(FourInLine fourInLine, char color, int column) {
		return 4 <= IntStream.range(0, fourInLine.gameHeight)
							 .takeWhile(y -> fourInLine.getColorAt(column - 1 + fourInLine.getRow(column) - 1 - y, y) == color)
							 .count() ;
	}
	
	public boolean currentPieceDiagonalTriumph(char color, int column, FourInLine fourInLine) {
		return currentPieceIncreasingDiagonalTriumph(fourInLine, color, column) ||
			   currentPieceDecreasingDiagonalTriumph(fourInLine, color, column);
	}
	
}
