package nemo.Cardinals;

import nemo.Nemo;
import nemo.PointInXYPlane;

public class North extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.executeHorizontalSlideMovement(new PointInXYPlane(0, 1));
	}

	public void left(Nemo nemo) {
		nemo.executeHorizontalRotationMovement(new West());
	}

	public void right(Nemo nemo) {
		nemo.executeHorizontalRotationMovement(new East());
	}

	public String orientation() {
		return "Norte";
	}	
}