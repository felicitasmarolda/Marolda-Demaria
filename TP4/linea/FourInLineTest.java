package linea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class FourInLineTest {
	
    @Test public void test00NewLine(){
        assertEquals(showInTest("|   |   |   |\n"), new FourInLine( 3, 3, 'C' ).show() );
    }

    @Test public void test01RedPlaysCorrectlyAndShown(){
        assertBoardAfterPlayingAt(new FourInLine( 3, 3, 'C' ),"| X |   |   |\n",1);
    }

    @Test public void test02BLuePlaysCorrectlyAndShown(){
        assertBoardAfterPlayingAt(new FourInLine( 3, 3, 'C' ),"| O | X |   |\n",2,1);
    }

    @Test public void test03GameEndsInDrawWhenBoardFull(){
    	FourInLine fourInLine = new FourInLine( 3, 2, 'C');
    	allGameMoves( fourInLine, 1,2,2,3,3,1 );
    	assertTrue( fourInLine.draw() );
    }

    @Test public void test04RedWinsByHorizontalLineAsTypeA(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 4, 4, 'A'), 'X', 'O', 1,1,2,2,3,3,4);
    }

    @Test public void test05BlueWinsByVerticalLineAsTypeA(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 6, 4, 'A'), 'O', 'X', 5,1,2,1,3,1,2,1);
    }

    @Test public void test06BlueDoesNotWinByIncreasingDiagonalAsTypeA(){
        assertHasNotFinishedAfterPlayingAt(new FourInLine( 7, 4, 'A'), 6,1,2,2,4,3,3,3,1,4,4,4);
    }

    @Test public void test07BlueDoesNotWinByDecreasingDiagonalAsTypeA(){
        assertHasNotFinishedAfterPlayingAt(new FourInLine( 7, 4, 'A'), 5,1,1,1,2,1,2,2,3,3,3,4);
    }

    @Test public void test08BlueWinsByCrecentLineAsTypeB(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 7, 4, 'B'), 'O', 'X', 6,1,2,2,4,3,3,3,1,4,4,4);
    }

    @Test public void test09BlueWinsByDecrecentLineAsTypeB(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 6, 4, 'B'), 'O', 'X', 5,1,1,1,2,1,2,2,3,3,3,4);
    }

    @Test public void test10BlueDoesNotWinByVerticalLineAsTypeB(){
        assertHasNotFinishedAfterPlayingAt(new FourInLine( 4, 4, 'B'), 3,1,2,1,2,1,2,1);
    }

    @Test public void test11RedDoesNotWinByHorizontalLineAsTypeB(){
        assertHasNotFinishedAfterPlayingAt(new FourInLine( 4, 4, 'B'), 1,1,2,2,3,3,4);
    }

    @Test public void test12BlueWinsByHorizontalLineAsTypeC(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 4, 4, 'C'), 'O', 'X', 3,1,2,1,2,1,2,1);
    }

    @Test public void test13RedWinsByVerticalLineAsTypeC(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 6, 5, 'C'), 'X', 'O', 2,1,2,1,2,1,2);
    }

    @Test public void test14BlueWinsByCrescentLineAsTypeC(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 4, 4, 'C'), 'O', 'X', 2,1,3,2,3,3,4,4,4,4);
    }
    @Test public void test15BlueWinsByDecrecentLineAsTypeC(){
        assertVictoryOfColorAfterPlayingAt(new FourInLine( 6, 4, 'C'), 'O', 'X', 5,1,1,1,2,1,2,2,3,3,3,4);
    }
    
    @Test public void test16BaseCanNotBeLessThan0(){
    	assertThrowsLike("Invalid dimension parameters, must be greater than 0.", () -> new FourInLine( -1, 4, 'C'));
    }
    
    @Test public void test17HeightCanNotBeLessThan0(){
    	assertThrowsLike("Invalid dimension parameters, must be greater than 0.", () -> new FourInLine( 4, -1, 'C'));
    }
    
    @Test public void test18TypeCanNotBeAnythingOtherThanABC(){
    	assertThrowsLike("Invalid triumph type parameter.", () -> new FourInLine( 4, 6, 'T'));
    }
    
    @Test public void test19BlueCanNotStartTheGame(){
    	assertThrowsLike("Incorrect turn.", () -> new FourInLine( 3, 3, 'C').playBlueAt(1));
    }
    
    @Test public void test20CantPlayInNonExistentColumn() {
    	assertThrowsLike("Inadequate column.", () -> new FourInLine( 3, 2, 'C').playRedAt(4));
    }
    
    @Test public void test21CanNotPlayTwoTimesInARow(){
    	FourInLine fourInLine = new FourInLine( 4, 6, 'C');
    	assertThrowsLikeAfterPlayingAt("Incorrect turn.", () -> fourInLine.playRedAt(1), fourInLine, 1,2,2,1,1);
    }
    
    @Test public void test22CanNotPlayInFullColumn(){
    	FourInLine fourInLine = new FourInLine( 3, 6, 'C');
    	assertThrowsLikeAfterPlayingAt("Inadequate column.", () -> fourInLine.playRedAt(1), fourInLine, 1,1,1,1,1,1);
    }

    @Test public void test23CantPlayWhenGameEnded(){
    	FourInLine fourInLine = new FourInLine( 2, 4, 'C');
        assertThrowsLikeAfterPlayingAt("Game has finished.", () -> fourInLine.playRedAt(1), fourInLine, 2,1,2,1,2,1,2);
    }
    
    private String showInTest(String changedLine){
    	
    	return "|---|---|---|\n" +
    		   "|   |   |   |\n" +
    		   "|---|---|---|\n" +
    		   "|   |   |   |\n" +
    		   "|---|---|---|\n" + 
    		     changedLine     +
    		   "|---|---|---|\n"  ;
    }
    
    private void allGameMoves(FourInLine fourInLine, int ... charArrayOfColumnEntries) {
    	
    	IntStream.range(0, charArrayOfColumnEntries.length)
    			 .forEach(i -> { int column = charArrayOfColumnEntries[i];
    			 				 if (i % 2 == 0) {
    			 					 fourInLine.playRedAt(column);
    			 				 } 
    			 				 else {
    			 					 fourInLine.playBlueAt(column);
   			 				 	} 
    			 			   }
    					 );
    }
    
    private void assertBoardAfterPlayingAt(FourInLine fourInLine, String linea, int ... moves) {
    	
    	allGameMoves(fourInLine, moves);
    	assertEquals(showInTest(linea), fourInLine.show() );
    }

    private void assertVictoryOfColorAfterPlayingAt(FourInLine fourInLine, char win, char loose, int ... moves) {
    	
    	allGameMoves(fourInLine, moves);
    	assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins(win));
        assertFalse( fourInLine.wins(loose));
    }
    
    private void assertHasNotFinishedAfterPlayingAt(FourInLine fourInLine, int ... moves) {
    	
    	allGameMoves(fourInLine, moves);
    	assertFalse( fourInLine.finished() );
    }
    
    private void assertThrowsLike(String message, Executable executable) {
    	
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }
        
    private void assertThrowsLikeAfterPlayingAt(String message, Executable executable, 
    											FourInLine fourInLine, int ... moves) {
    	
        allGameMoves(fourInLine, moves);
        assertThrowsLike(message, executable);
    }
}
