package linea;

public class StateTurnRed extends GameState {

	public FourInLine playBlueInGameWith(FourInLine fourInLine, int column) {
		throw new RuntimeException("Incorrect turn.");
	}

	public FourInLine playRedInGameWith(FourInLine fourInLine, int column) {
		fourInLine.dropPieceWithColorAt('X', column);
		fourInLine.changeTurnTo(new StateTurnBlue());
		return fourInLine;
	}


	public boolean gameFinished() {
		return false;
	}
}
