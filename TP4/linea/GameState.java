package linea;

public abstract class GameState {
	protected abstract FourInLine playBlueInGameWith(FourInLine fourInLine, int column);
	protected abstract FourInLine playRedInGameWith(FourInLine fourInLine, int column);
	protected abstract boolean gameFinished();
}
