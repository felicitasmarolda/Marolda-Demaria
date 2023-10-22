package nemo.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nemo.Nemo;

public abstract class Command {
	static List<Command> possibleCommands = new ArrayList<>(Arrays.asList(
												  new Up(),
												  new Down(),
												  new Left(),
												  new Right(),
												  new Launch(),
												  new Forward())); 
	public abstract void sendNemoCommandDirection(Nemo nemo);
	public abstract boolean canHandle(Character command);
	public static Command commandFor(Character commandChar) {
		return possibleCommands
			    .stream()
			    .filter(commandInstance -> commandInstance.canHandle(commandChar))
			    .toList()
			    .get(0);
	}
}
