package nemo.Cardinals;

import nemo.Nemo;
import nemo.PointInXYPlane;

public class East extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.executeHorizontalSlideMovement(new PointInXYPlane(1, 0));
	}

	public void left(Nemo nemo) {
		nemo.executeHorizontalRotationMovement(new North());
	}

	public void right(Nemo nemo) {
		nemo.executeHorizontalRotationMovement(new South());
	}	
	
	public String orientation() {
		return "Este";
	}	
}