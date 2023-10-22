package nemo.Cardinals;

import nemo.Nemo;
import nemo.PointInXYPlane;

public class West extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.executeHorizontalSlideMovement(new PointInXYPlane(-1, 0));
	}

	public void left(Nemo nemo) {
		nemo.executeHorizontalRotationMovement(new South());
	}

	public void right(Nemo nemo) {
		nemo.executeHorizontalRotationMovement(new North());
	}	
	
	public String orientation() {
		return "Oeste";
	}	
}