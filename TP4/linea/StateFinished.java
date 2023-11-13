package linea;

public class StateFinished extends GameState {

	public FourInLine playBlueInGameWith(FourInLine fourInLine, int column) {
		throw new RuntimeException("Game has finished.");
	}

	public FourInLine playRedInGameWith(FourInLine fourInLine, int column) {
		throw new RuntimeException("Game has finished.");
	}

	public boolean gameFinished() {
		return true;
	}
}