package nemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nemo.Cardinals.Cardinal;
import nemo.Commands.Command;
import nemo.Commands.Down;
import nemo.Commands.Forward;
import nemo.Commands.Left;
import nemo.Commands.Right;
import nemo.Commands.Launch;
import nemo.Commands.Up;
import nemo.Depths.DepthState;
import nemo.Depths.SurfaceDepth;

public class Nemo {

	public PointInXYPlane coordinate = new PointInXYPlane(0, 0);
	public Cardinal orientationState;
	ArrayList<DepthState> depthStates = new ArrayList<>();
	
	public Nemo(int x, int y, Cardinal direc) {
		executeHorizontalSlideMovement(new PointInXYPlane(x, y));
		orientationState = direc;
		depthStates.add(new SurfaceDepth());
	}
	
	public int getXCoord() {
		return coordinate.getX();
	}
	
	public int getYCoord() {
		return coordinate.getY();
	}
	
	public Cardinal getOrientationState() {
		return orientationState;
	}
	
	public String getOrientationValue() {
		return orientationState.orientation();
	}
	
	public DepthState getDepthState() {
		return depthStates.get(depthStates.size() - 1);
	}
	
	public int getDepthValue() {
		return - (depthStates.size() - 1);
	}
	
	public void directDownwardsSlideMovement() {
		getDepthState().sendNemoDown(this);
	}
	
	public void directUpwardsSlideMovement() {
		getDepthState().sendNemoUp(this);
	}
	
	public void directForwardSlideMovement() {
		getOrientationState().forward(this);
	}
	
	public void directRightRotationMovement() {
		getOrientationState().right(this);
	}
	
	public void directLeftRotationMovement() {
		getOrientationState().left(this);
	}
	
	public void directCapsuleLaunch() {
		getDepthState().shootCapsuleFromThisDepth(this);
	}
	
	public void executeUpwardsSlideMovement() {
		depthStates.remove(depthStates.size() - 1);
	}
	
	public void executeDownwardsSlideMovement(DepthState depth) {
		depthStates.add(depth);
	}

	public void executeHorizontalSlideMovement(PointInXYPlane pointInXYPlane) {
		coordinate = coordinate.addWith(pointInXYPlane);
	}
	
	public void executeHorizontalRotationMovement(Cardinal orientation) {
		orientationState = orientation;
	}
	
	public void executeCapsuleLaunch() {}
	
	public void proceedWithNemoCommandExecution( String string ) {
		string
		.chars()
	    .forEach(commandUnicodeValue -> proceedWithNemoCommandExecution((char) commandUnicodeValue));
	}
	
	public void proceedWithNemoCommandExecution( Character commandChar ) {
		Command.commandFor(commandChar).sendNemoCommandDirection(this);
	}
}
