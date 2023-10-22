package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import nemo.Cardinals.East;
import nemo.Cardinals.North;
import nemo.Cardinals.South;
import nemo.Cardinals.West;


public class NemoTest {
	
	private Nemo nemo;
	String Oeste;
	String Este;
	String Norte;
	String Sur;
	
	@BeforeEach void setUp() {
		nemo = new Nemo(0, 0, new North());
		Oeste = "Oeste";
		Este = "Este";
		Norte = "Norte";
		Sur = "Sur";
	}
	
	@Test public void test00CanPlaceNemoAnywherePointingToWhereDesired() {
		Nemo nemo = new Nemo(1, 2, new West());
		assertNemoPositionAndOrientation(1, 2, 0, Oeste, nemo);
	}
	
	@Test public void test01DescendsOneUnit() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('d', 0, 0, -1, Norte, nemo);
    }	

	@Test public void test02AscendsOneUnit() {
		nemo.proceedWithNemoCommandExecution('d');
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('u', 0, 0, 0, Norte, nemo);
	}

	@Test public void test03MovesForward() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('f', 0, 1, 0, Norte, nemo);
	}
	
	@Test public void test04CanTurnLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('l', 0, 0, 0, Oeste, nemo);
	}
	
	@Test public void test05CanTurnRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('r', 0, 0, 0, Este, nemo);
	}
	
	@Test public void test06CanShootCapsule() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('m', 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test07CannotAscendWhenInSurface() {
		assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
		('u', 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test08AllowsStringsRepresentingCommandChains() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("fldurm", 0, 1, 0, Norte, nemo);
	}
	
	@Test public void test09SlideChangesNemoPosition() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("ffffudufduuddudff", 0, 7, -2, Norte, nemo);
	}
	
	@Test public void test10RotationChangesNemoOrientation() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("lllrrlrllrrlrrlrr", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test11RotationDoesNotChangeNemoPosition() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rlrllrlrlrrlrlrlr", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test12SlideDoesNotChangeNemoOrientation() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("fduudufufuduufudu", 0, 4, 0, Norte, nemo);
	}

	@Test public void test13CanTurn90DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("l", 0, 0, 0, Oeste, nemo);
	}
	
	@Test public void test14CanTurn180DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("ll", 0, 0, 0, Sur, nemo);
	}
	
	@Test public void test15CanTurn270DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("lll", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test16CanTurn360DegreesByTurningLeft() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("llll", 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test17CanTurn90DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("r", 0, 0, 0, Este, nemo);
	}
	
	@Test public void test18CanTurn180DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rr", 0, 0, 0, Sur, nemo);
	}
	
	@Test public void test19CanTurn270DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rrr", 0, 0, 0, Oeste, nemo);
	}
	
	@Test public void test20CanTurn360DegreesByTurningRight() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rrrr", 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test21CanReturnToOriginalState() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("rfllfdufdrrufl", 0, 0, 0, Norte, nemo);
	}
	
	@Test public void test22IgnoresEmptyCommand() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("", 0, 0, 0, Norte, nemo);

	}
	
	@Test public void test23CanReachAnyDesiredPositionAndDirection() {
		assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
		("dddddrffffffffffffrfffffffffffffl", 12, -13, -5, Este, nemo);
	}
	
	@Test public void test24CannotOpenCapsuleBelowSomeDepth() {
		assertEquals( "Intento de expulsión de cápsula en condiciones no óptimas. Nemo ha implosionado.", 
				assertThrows( Error.class, () -> nemo.proceedWithNemoCommandExecution("ddm"))
				.getMessage());
	}
	
	private void assertNemoPositionAndOrientation(int x, int y, int depth, String cardinal, Nemo nemo) {
		assertEquals(x, nemo.getXCoord());
		assertEquals(y, nemo.getYCoord());
		assertEquals(depth, nemo.getDepthValue());
		assertEquals(cardinal, nemo.getOrientationValue());
	}
	
	private void assertNemoPositionAndOrientationAfterHavingExecutedCharacterCommand
	(Character command, int x, int y, int depth, String cardinal, Nemo nemo) {
		nemo.proceedWithNemoCommandExecution(command);		
		assertNemoPositionAndOrientation(x, y, depth, cardinal, nemo);
	}

	private void assertNemoPositionAndOrientationAfterHavingExecutedStringCommand
	(String command, int x, int y, int depth, String cardinal, Nemo nemo) {
		nemo.proceedWithNemoCommandExecution(command);		
		assertNemoPositionAndOrientation(x, y, depth, cardinal, nemo);
	}
}