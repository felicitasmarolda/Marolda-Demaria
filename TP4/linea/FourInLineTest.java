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

    @Test public void test03GameEndsWhenBoardFull(){
        assertFinishedAfterPlayingAt(new FourInLine( 3, 2, 'C'), 1,2,2,3,3,1);
    }

    @Test public void test04RedWinsByHorizontalLineAsTypeA(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 4, 4, 'A'), 'X', 'O', 1,1,2,2,3,3,4);
    }

    @Test public void test05BlueWinsByVerticalLineAsTypeA(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 6, 4, 'A'), 'O', 'X', 5,1,2,1,3,1,2,1);
    }

    @Test public void test06BlueDoesNotWinByIncreasingDiagonalAsTypeA(){
        FourInLine fourInLine = new FourInLine( 7, 4, 'A');
        assertFinishedAfterPlayingAt(fourInLine, 6,1,2,2,4,3,3,3,1,4,4,4);
    }

    @Test public void test07BlueDoesNotWinByDecreasingDiagonalAsTypeA(){
        assertFinishedAfterPlayingAt(new FourInLine( 7, 4, 'A'), 5,1,1,1,2,1,2,2,3,3,3,4);
    }

    @Test public void test08BlueWinsByCrecentLineAsTypeB(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 7, 4, 'B'), 'O', 'X', 6,1,2,2,4,3,3,3,1,4,4,4);
    }

    @Test public void test09BlueWinsByDecrecentLineAsTypeB(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 6, 4, 'B'), 'O', 'X', 5,1,1,1,2,1,2,2,3,3,3,4);
    }

    @Test public void test10BlueDoesNotWinByVerticalLineAsTypeB(){
        assertFinishedAfterPlayingAt(new FourInLine( 4, 4, 'B'), 3,1,2,1,2,1,2,1);
    }

    @Test public void test11RedDoesNotWinByHorizontalLineAsTypeB(){
        assertFinishedAfterPlayingAt(new FourInLine( 4, 4, 'B'), 1,1,2,2,3,3,4);
    }

    @Test public void test12BlueWinsByHorizontalLineAsTypeC(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 4, 4, 'C'), 'O', 'X', 3,1,2,1,2,1,2,1);
    }

    @Test public void test13RedWinsByVerticalLineAsTypeC(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 6, 5, 'C'), 'X', 'O', 2,1,2,1,2,1,2);
    }

    @Test public void test14BlueWinsByCrescentLineAsTypeC(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 4, 4, 'C'), 'O', 'X', 2,1,3,2,3,3,4,4,4,4);
    }
    @Test public void test15BlueWinsByDecrecentLineAsTypeC(){
        assertVictoryOfColorAfterPlayAt(new FourInLine( 6, 4, 'C'), 'O', 'X', 5,1,1,1,2,1,2,2,3,3,3,4);
    }

    @Test public void test16CantPlayWhenGameEnded(){
        FourInLine fourInLine;
        assertThrowsLikeAfterPlayingAt(new FourInLine( 2, 4, 'C'), "Game has finished.", () -> new FourInLine( 2, 4, 'C').playRedAt(1), 2,1,2,1,2,1,2);
    }

    @Test public void test17CantPlayInNonExistentColumn() {
        FourInLine fourInLine = new FourInLine( 3, 2, 'C');
        assertThrowsLike("Inadequate column.", () -> fourInLine.playRedAt(4));
    }

    @Test public void test18CanNotPlayInFullColumn(){
        FourInLine fourInLine = new FourInLine( 3, 6, 'C');
        assertThrowsLikeAfterPlayingAt(fourInLine, "Inadequate column.", () -> fourInLine.playRedAt(1), 1,1,1,1,1,1);
    }

    @Test public void test19BlueCanNotStartTheGame(){
        FourInLine fourInLine = new FourInLine( 3, 3, 'C');
        assertThrowsLike("Incorrect turn.", () -> fourInLine.playBlueAt(1));
    }

    @Test public void test20CanNotPlayTwoTimesInARow(){
        FourInLine fourInLine = new FourInLine( 4, 6, 'C');
        assertThrowsLikeAfterPlayingAt(fourInLine, "Incorrect turn.", () -> fourInLine.playRedAt(1), 1,2,2,1,1);
    }

    @Test public void test21BaseCanNotBeLessThan0(){
        assertThrowsLike("Parámetros inválidos.", () -> new FourInLine( -1, 4, 'C'));
    }

    @Test public void test22HeightCanNotBeLessThan0(){
        assertThrowsLike("Parámetros inválidos.", () -> new FourInLine( 4, -1, 'C'));
    }

    @Test public void test23TypeCanNotBeAnythingOtherThanABC(){
        assertThrowsLike("Parámetro de tipo de estrategia inválido.", () -> new FourInLine( 4, 6, 'T'));
    }

    @Test public void print1() {
        FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,1,1,2,1,2,2,3,3,3,4);
        System.out.println("Print 1:\n");
        System.out.println(line.show());
    }

    @Test public void print2() {
        FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,2,2,4,3,3,3,1,4,4,4);
        System.out.println("Print 2:\n");
        System.out.println(line.show());
    }

    @Test public void print3() {
        FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,1,2,2,3,3,4);
        System.out.println("Print 3:\n");
        System.out.println(line.show());
    }

    @Test public void print4() {
        FourInLine line = new FourInLine( 4, 4, 'C');
        allGameMoves(line, 1,2,1,2,1,2,3,2);
        System.out.println("Print 4:\n");
        System.out.println(line.show());
    }

    @Test public void testerPrint() {
        FourInLine line = new FourInLine( 6, 6, 'C');
        allGameMoves(line, 1,1,2,2,3,3,4);
        System.out.println("Tester print:\n");
        System.out.println(line.show());
    }
    private void assertBoardAfterPlayingAt(FourInLine fourInLine, String linea, int ... moves) {
        allGameMoves(fourInLine, moves);
        assertEquals(showInTest(linea), fourInLine.show() );
    }

    private void assertThrowsLike(String message, Executable executable) {
        assertEquals(message, assertThrows(Exception.class, executable).getMessage());
    }

    private void allGameMoves(FourInLine fourInLine, int ... charArrayOfColumnEntries) {

        IntStream.range(0, charArrayOfColumnEntries.length)
                .forEach(i -> {
                    int column = charArrayOfColumnEntries[i];
                    if (i % 2 == 0) {
                        fourInLine.playRedAt(column);
                    } else {
                        fourInLine.playBlueAt(column);
                    } });
    }
    private String showInTest(String changedLine){
        return "|---|---|---|\n" +
                "|   |   |   |\n" +
                "|---|---|---|\n" +
                "|   |   |   |\n" +
                "|---|---|---|\n" + changedLine + "|---|---|---|\n";
    }
    private void assertVictoryOfColorAfterPlayAt(FourInLine fourInLine, char win, char loose, int ... moves) {
        allGameMoves(fourInLine, moves);
        assertFinishedWinningWithAndLoosingWith(fourInLine, win, loose);
    }

    private static void assertFinishedWinningWithAndLoosingWith(FourInLine fourInLine, char win, char loose) {
        assertTrue( fourInLine.finished() );
        assertTrue( fourInLine.wins(win));
        assertFalse( fourInLine.wins(loose));
    }
    private void assertFinishedAfterPlayingAt(FourInLine fourInLine, int ... moves) {
        allGameMoves(fourInLine, moves);
        assertTrue( fourInLine.finished() );
    }
    private void assertThrowsLikeAfterPlayingAt(FourInLine fourInLine, String message, Executable executable, int ... moves) {
        allGameMoves(fourInLine, moves);
        assertThrowsLike(message, executable);
    }

}
