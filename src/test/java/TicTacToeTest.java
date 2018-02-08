import com.tictactoe.InsertException;
import com.tictactoe.PlayerTurnException;
import com.tictactoe.TicTacToe;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TicTacToeTest {

    @Test
    public void shouldCreateEmptyGridWhenCreatingNewTicTacToeGame() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        List<Character> grid = ticTacToe.getGrid();
        //then
        List<Character> expectedGrid = anExpectedEmptyGrid();
        assertEquals("The grid should contains only 1", expectedGrid, grid);
    }

    @Test
    public void shouldBePossibleToAddXorOToTheEmptyPlaceInTheGrid() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(0, 'O');
        //then
        List<Character> expectedGrid = anExpectedEmptyGrid();
        expectedGrid.set(0, 'O');
        assertEquals("The grid should be filled with 0 in the first column and first row", expectedGrid, ticTacToe.getGrid());
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenIndexOfTheGridIsAbove8() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(9, 'X');
        //then exception should be thrown
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenIndexOfTheGridIsBelow0() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(-1, '0');
        //then exception should be thrown
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenTryingToAddDifferentCharacter() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.insert(0, 'g');
        //then exception should be thrown
    }

    @Test(expected = PlayerTurnException.class)
    public void shouldThrowPlayerTurnExceptionWhenTryingToInsertXTwiceInARow() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(2, 'O');
        //when
        ticTacToe.play(3, 'O');
        //then exception should be thrown
    }

    @Test(expected = InsertException.class)
    public void shouldThrowInsertExceptionWhenTryingToInsertIntoOccupiedIndex() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.insert(2, 'O');
        //when
        ticTacToe.insert(2, 'X');
        //then exception should be thrown
    }

    @Test
    public void shouldBeAvailableToPlayByTurns() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        ticTacToe.play(2, 'o');
        ticTacToe.play(3, 'x');
        //then no exception should be thrown
        assertEquals("The field should be O", new Character('O'), ticTacToe.getGrid().get(2));
        assertEquals("The field should be X", new Character('X'), ticTacToe.getGrid().get(3));
    }

    @Test
    public void shouldReturnTrueForWinWhenThreeSymbolsInOneLineAreEqual() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(0, 'o');
        ticTacToe.play(4, 'x');
        ticTacToe.play(1, 'o');
        ticTacToe.play(5, 'x');
        //when
        Character result = ticTacToe.play(2, 'o');
        //then
        assertEquals("O should be the winner !", new Character('O'), result);
    }

    @Test
    public void shouldReturnTrueForWinWhen3SymbolsIsInOneColumnAreEqual() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(0, 'o');
        ticTacToe.play(1, 'x');
        ticTacToe.play(3, 'o');
        ticTacToe.play(2, 'x');
        //when
        Character result = ticTacToe.play(6, 'O');
        //then
        assertEquals("O should be the winner !", new Character('O'), result);
    }

    @Test
    public void shouldNotBeWinnerForRowColumnWithEmptyField() {
        //given
        TicTacToe ticTacToe = new TicTacToe();
        //when
        Character result = ticTacToe.play(6, 'o');
        //then
        assertNull("Result should be null", result);
    }
    @Test
    public void shouldReturnTrueForWinWhen3SymbolsIsInDiagonalAreEqual(){
        //given
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play(0, 'O');
        ticTacToe.play(1, 'X');
        ticTacToe.play(4, 'O');
        ticTacToe.play(2, 'X');
        //when
        Character result = ticTacToe.play(8, 'O');
        //then boolean won should be set to true
        assertEquals("O should be the winner", new Character('O'), result);
    }

    private List<Character> anExpectedEmptyGrid() {
        List<Character> expectedGrid = new ArrayList<Character>();
        for (int i = 0; i <= 8; i++) {
            expectedGrid.add(i, '1');
        }
        return expectedGrid;
    }
}
