package nemo.Commands;

import nemo.Nemo;

public class Right extends Command {

	public void sendNemoCommandDirection(Nemo nemo) {
		nemo.directRightRotationMovement();
	}

	public boolean canHandle(Character command) {
		return command == 'r';
	}
}