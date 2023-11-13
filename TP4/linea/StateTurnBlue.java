package linea;

public class StateTurnBlue extends GameState {

	public FourInLine playBlueInGameWith(FourInLine fourInLine, int column) {
		fourInLine.dropPieceWithColorAt('O', column);
		fourInLine.changeTurnTo(new StateTurnRed());
		return fourInLine;
	}

	public FourInLine playRedInGameWith(FourInLine fourInLine, int column) {
		throw new RuntimeException("Incorrect turn.");
	}


	public boolean gameFinished() {
		return false;
	}
}
