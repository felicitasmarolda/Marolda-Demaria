package nemo.Depths;

import nemo.Nemo;

public class FirstLevelDepth extends DepthState{

	public void sendNemoUp(Nemo nemo) {
		nemo.executeUpwardsSlideMovement();
	}

	public void sendNemoDown(Nemo nemo) {
		nemo.executeDownwardsSlideMovement(new BelowFirstLevelDepth());
	}
	
	public void shootCapsuleFromThisDepth(Nemo nemo) {
		nemo.executeCapsuleLaunch();
	}
}