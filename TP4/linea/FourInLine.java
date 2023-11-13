package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class FourInLine {
	
	private ArrayList<ArrayList<Character>> board = new ArrayList();
	public int gameBase;
	public int gameHeight;
	private TriumphType gameTriumphType = new TypeNull();
	private List<GameState> possibleCurrentGameStates = new ArrayList<GameState>( Arrays.asList( new StateTurnRed(), 
																		   						 new StateFinished() ) );
	private GameState gameState = new StateTurnRed();

	private int lastPlayedColumn = 1;
	
	public FourInLine(int base, int height, char type) {
		
		if ((base > 0)   &&
			(height > 0)) {
			
			gameBase = base;
			gameHeight = height;
			checkAndDetermineTriumphType(type);
	
			for (int i = 0; i < gameBase; i++) {
				board.add(new ArrayList());
			}
		}
		else {
			throw new RuntimeException("Parámetros inválidos.");
		}
	}
	
	private FourInLine checkAndDetermineTriumphType(char type) {
		TriumphType typeInstance = TriumphType.typeFor(type);
		return typeInstance.checkMeAsTriumphTypeFor( this );
	}
	
	public FourInLine establishTriumphType(TriumphType type) {
		gameTriumphType = type;
		return this;
	}
	
	public FourInLine playRedAt(int column) {
		if (columnIsValid(column)) {
			
			gameState.playRedInGameWith(this, column);
			return this;
		}
		else {
			throw new RuntimeException("Inadequate column.");
		}
	}	

	public FourInLine playBlueAt(int column) {
		if (columnIsValid(column)) {
			
			gameState.playBlueInGameWith(this, column);
			return this;
		}
		else {
			throw new RuntimeException("Inadequate column.");
		}
	}	

	private boolean columnIsValid(int column) {
		return 1 <= column && column <= gameBase && board.get(column - 1).size() != gameHeight;
	}

	public FourInLine dropPieceWithColorAt(char color, int column) {
		board.get(column - 1).add(color);
		lastPlayedColumn = column;
		return this;
	}
	
	public void changeTurnTo(GameState nextTurnGameState) {
		possibleCurrentGameStates.remove(0);
		possibleCurrentGameStates.add(0, nextTurnGameState);
		possibleCurrentGameStates.stream()
						   		 .filter( turnInstance -> turnInstance.gameFinished() == finished() )
						   		 .forEach( turnInstance -> gameState = turnInstance);
	}
	
	public boolean finished() {
		return wins('O') ||
			   wins('X') ||
			   draw();
	}
	
	public boolean wins(char color) {
		return gameTriumphType.verifyTriumphInGameAsTypeWithColorAndColumn( this, color, lastPlayedColumn );
	}

	private boolean draw() {
		return board.stream()
					.allMatch( columnArray -> columnArray.size() == gameHeight );
	}
	
	public char getColorAt(int x, int y) {
		if (x < 0 || 
			y < 0 || 
			x >= gameBase || 
			y >= gameHeight ||
			board.get(x).size() <= y) {
			
			return ' ';			
		}
		else {
			return board.get(x).get(y);
		}
	}
	
	public int getRow(int column) {
		return board.get(column - 1).size();
	}
	
	public String show() {
		String line = "|" + IntStream.range(0, gameBase)
        .mapToObj(i -> "---|")
        .collect(Collectors.joining()) + "\n";
		
		String grid = line + IntStream.range(0, gameHeight)
									  .mapToObj(i -> "|" + IntStream.range(0, gameBase).mapToObj(j -> " " + getColorAt(j, gameHeight - 1 - i) + " |")
											  										   .collect(Collectors.joining()) + "\n" + line)
									  .collect(Collectors.joining());

		return grid;
	}

	public ArrayList<ArrayList<Character>> tablero() {
		return board;
	}
}
