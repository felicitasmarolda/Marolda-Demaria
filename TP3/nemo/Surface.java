package nemo.Depths;

import nemo.Nemo;

public class SurfaceDepth extends DepthState{

	public void sendNemoUp(Nemo nemo) {}

	public void sendNemoDown(Nemo nemo) {
		nemo.executeDownwardsSlideMovement(new FirstLevelDepth());
	}

	public void shootCapsuleFromThisDepth(Nemo nemo) {
		nemo.executeCapsuleLaunch();
	}
}
