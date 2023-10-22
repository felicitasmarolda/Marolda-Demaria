package nemo.Commands;

import nemo.Nemo;

public class Down extends Command {

	public void sendNemoCommandDirection(Nemo nemo) {
		nemo.directDownwardsSlideMovement();
	}

	public boolean canHandle(Character command) {
		return command == 'd';
	}
}