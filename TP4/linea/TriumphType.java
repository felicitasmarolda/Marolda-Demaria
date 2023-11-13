package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public abstract class TriumphType {

	private static List<TriumphType> possibleModes = new ArrayList<TriumphType>( Arrays.asList(new TypeA(),
			  																		   		   new TypeB(),
			  																		   		   new TypeC(),
			  																		   		   new TypeNull() ) );
	
	public static TriumphType typeFor(Character typeChar) {
		List<TriumphType> typeForTypeChar = new ArrayList();
		possibleModes.stream()
					 .filter( typeInstance -> typeInstance.canHandle(typeChar) )
					 .forEach( typeInstance -> typeForTypeChar.add(typeInstance) );
		return typeForTypeChar.get(0);
	}
	
	protected abstract FourInLine checkMeAsTriumphTypeFor(FourInLine fourInLine);
	protected abstract boolean canHandle(char typeChar);
	protected abstract boolean verifyTriumphAsTypeInGameWithColorAndColumn(FourInLine fourInLine, char color, int column);
	
	
	protected boolean currentPieceVerticalTriumph(FourInLine fourInLine, char color, int column) {
		boolean downwardsVertical = 4 <= IntStream.range(0, fourInLine.getRow(column) )
												  .takeWhile(yCoord -> fourInLine.getColorAt(column - 1, fourInLine.getRow(column) - 1 - yCoord) == color)
												  .count() ;
		boolean upwardsVertical   = 4 <= IntStream.range(fourInLine.getRow(column) - 1, fourInLine.gameHeight)
											   	  .takeWhile(yCoord -> fourInLine.getColorAt(column - 1, yCoord) == color)
												  .count() ;
		return upwardsVertical || downwardsVertical;
	}
	
	protected boolean currentPieceHorizontalTriumph(FourInLine fourInLine, char color, int column) {
		
		boolean leftHorizontal  = 4 <= IntStream.range(0, column)
				 							    .takeWhile(xCoord -> fourInLine.getColorAt(column - 1 - xCoord, fourInLine.getRow(column) - 1) == color)
				 							    .count() ;
		boolean rightHorizontal = 4 <= IntStream.range(column - 1, fourInLine.gameBase)
											    .takeWhile(xCoord -> fourInLine.getColorAt(xCoord, fourInLine.getRow(column) - 1) == color)
											    .count() ;
		return leftHorizontal || rightHorizontal;
	}
	
	protected boolean currentPieceIncreasingDiagonalTriumph(FourInLine fourInLine, char color, int column) {
		
		boolean leftIncreasingDiagonal  = 4 <= IntStream.range(0, column)
				 									    .takeWhile(yCoord -> fourInLine.getColorAt(column - 1 - yCoord, fourInLine.getRow(column) - 1 - yCoord) == color)
				 									    .count() ;
		boolean rightIncreasingDiagonal = 4 <= IntStream.range(column - 1, fourInLine.gameHeight)
														.takeWhile(yCoord -> fourInLine.getColorAt(column - fourInLine.getRow(column) + yCoord, yCoord) == color)
														.count() ;
		return leftIncreasingDiagonal || rightIncreasingDiagonal;
	}
	
	protected boolean currentPieceDecreasingDiagonalTriumph(FourInLine fourInLine, char color, int column) {
		
		boolean leftDecreasingDiagonal  = 4 <= IntStream.range(0, column)
														.takeWhile(yCoord -> fourInLine.getColorAt(column - 1 - yCoord, fourInLine.getRow(column) - 1 + yCoord) == color)
														.count() ;
		
		boolean rightDecreasingDiagonal = 4 <= IntStream.range(column - 1, fourInLine.gameHeight)
				 										.takeWhile(yCoord -> fourInLine.getColorAt(column - 1 + fourInLine.getRow(column) - 1 - yCoord, yCoord) == color)
				 										.count() ;
		return leftDecreasingDiagonal || rightDecreasingDiagonal;
	}
	
	public boolean currentPieceDiagonalTriumph(FourInLine fourInLine, char color, int column) {
		return currentPieceIncreasingDiagonalTriumph(fourInLine, color, column) ||
			   currentPieceDecreasingDiagonalTriumph(fourInLine, color, column);
	}
}
