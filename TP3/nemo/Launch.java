package nemo.Commands;

import nemo.Nemo;

public class Launch extends Command {

	public void sendNemoCommandDirection(Nemo nemo) {
		nemo.directCapsuleLaunch();
	}

	public boolean canHandle(Character command) {
		return command == 'm';
	}
}