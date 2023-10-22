package nemo.Cardinals;

import nemo.Nemo;

public abstract class Cardinal {
	public abstract String orientation();
	public abstract void forward(Nemo nemo);	
	public abstract void left(Nemo nemo);
	public abstract void right(Nemo nemo);	
}
