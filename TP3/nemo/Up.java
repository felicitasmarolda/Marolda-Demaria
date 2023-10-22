package nemo.Commands;

import nemo.Nemo;

public class Up extends Command {

	public void sendNemoCommandDirection(Nemo nemo) {
		nemo.directUpwardsSlideMovement();
	}

	public boolean canHandle(Character command) {
		return command == 'u';
	}
}