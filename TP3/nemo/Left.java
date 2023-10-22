package nemo.Commands;

import nemo.Nemo;

public class Left extends Command {

	public void sendNemoCommandDirection(Nemo nemo) {
		nemo.directLeftRotationMovement();
	}

	public boolean canHandle(Character command) {
		return command == 'l';
	}
}