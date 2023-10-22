package nemo.Commands;

import nemo.Nemo;

public class Forward extends Command {

	public void sendNemoCommandDirection(Nemo nemo) {
		nemo.directForwardSlideMovement();
	}

	public boolean canHandle(Character command) {
		return command == 'f';
	}
}