
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;


/**
 * class for testing MarbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {

  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable reader;


  @Before
  public void setup() {
    model = new EnglishSolitaireModel();
    Appendable dest1 = new StringBuilder();
    view = new MarbleSolitaireTextView(model, dest1);

  }

  /**
   * Tests that an error is thrown if a null model is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new MarbleSolitaireControllerImpl(null, view, reader);
  }

  /**
   * tests that an error is thrown if a null view is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    new MarbleSolitaireControllerImpl(model, null, reader);
  }

  /**
   * tests that an error is thrown if a null readable is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    new MarbleSolitaireControllerImpl(model, view, null);
  }

  /**
   * tests that the controller displays the default board when quit
   * with no moves made or attempted.
   */
  @Test
  public void testControllerQuitNoMoves() {

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }

  /**
   * tests that the controller can quit when given a q for the from column.
   */
  @Test
  public void testControllerQuitFromCol() {

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("1 q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }

  /**
   * tests that the controller can quit when given a q for the to row.
   */
  @Test
  public void testControllerQuitToRow() {

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("1 2 q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }

  /**
   * Tests that the controller can quit if a q is given for the to column.
   */
  @Test
  public void testControllerQuitToCol() {

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("1 1 3 q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }

  /**
   * tests that the controller displays the default board when quit
   * with no moves made or attempted.
   */
  @Test
  public void testControllerQuitAfterValidMove() {

    StringBuilder mockLog = new StringBuilder();
    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("2 4 4 4 Q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", viewOut.toString());
  }

  /**
   * Tests that playGame throws an IllegalStateException when the Readable runs out
   * of input and doesn't quit / the game isn't over.
   */
  @Test (expected = IllegalStateException.class)
  public void testEmptyReadableNoQuit() {

    StringBuilder mockLog = new StringBuilder();
    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("2 4 4 4");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();
  }

  /**
   * tests that the controller displays the default board when quit
   * with no moves made or attempted.
   */
  @Test
  public void testControllerQuit2ValidMoves() {

    StringBuilder mockLog = new StringBuilder();
    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("2 4 4 4 5 4 3 4 q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);
    controller.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n", viewOut.toString());
  }

  /**
   * tests that the controller provides the correct input to the model.
   */
  @Test
  public void testControllerCorrectInput() {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    fromRow = 2;
    fromCol = 4;
    toRow = 4;
    toCol = 4;

    StringBuilder mockLog = new StringBuilder();
    StringBuilder viewOut = new StringBuilder();
    EnglishSolitaireModel mockModel = new MockModel(mockLog);
    Readable reader = new StringReader(
            "" + fromRow + " " + fromCol + " " + toRow + " " + toCol + " " + "q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(mockModel, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, reader);
    controller.playGame();

    // User inputs begin at 1, but the board is designed to start at 0. So we
    // subtract 1 from user inputs to account for this.
    fromRow -= 1;
    fromCol -= 1;
    toRow -= 1;
    toCol -= 1;

    assertEquals("inputs given are: " +
            fromRow +
            " " +
            fromCol +
            " " +
            toRow +
            " " +
            toCol, mockLog.toString());

  }

  /**
   * tests that the controller renders a message to re-enter a value
   * if the given input is invalid.
   */
  @Test
  public void testControllerInvalidMoveMessage() {

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader("a q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);

    controller.playGame();

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid input. Re-enter value.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }

  /**
   * tests that the controller renders a play again message if an illegal
   * move is attempted.
   */
  @Test
  public void testControllerIllegalMoveMessage() {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    fromRow = 1;
    fromCol = 3;
    toRow = 3;
    toCol = 3;

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader(
            "" + fromRow + " " + fromCol + " " + toRow + " " + toCol + " " + "q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);

    controller.playGame();

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }

  /**
   * tests that the controller does not make moves, nor throw an error
   * if the move is invalid.
   */
  @Test
  public void testControllerIllegalMove() {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    fromRow = 1;
    fromCol = 3;
    toRow = 3;
    toCol = 3;

    StringBuilder viewOut = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Readable reader = new StringReader(
            "" + fromRow + " " + fromCol + " " + toRow + " " + toCol + " " + "q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, reader);

    String initialBoard = "" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";

    assertEquals(view.toString(), initialBoard);
    controller.playGame();

    assertEquals(view.toString(), initialBoard);
  }

  /**
   * tests that the controller will ignore bad input and still correctly pass
   * 4 valid values to the model.
   */
  @Test
  public void testControllerIgnoreBadInput() {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    fromRow = 2;
    fromCol = 4;
    toRow = 4;
    toCol = 4;

    StringBuilder mockLog = new StringBuilder();
    StringBuilder viewOut = new StringBuilder();
    EnglishSolitaireModel mockModel = new MockModel(mockLog);
    Readable reader = new StringReader(
            "" + "a" + " " + fromRow + " " + fromCol
                    + " " + "[" + " " + toRow + " " + "." + " " + toCol + " " + " " + "q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(mockModel, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, reader);
    controller.playGame();

    fromRow -= 1;
    fromCol -= 1;
    toRow  -= 1;
    toCol -= 1;
    assertEquals("inputs given are: " +
            fromRow +
            " " +
            fromCol +
            " " +
            toRow +
            " " +
            toCol, mockLog.toString());

  }

  /**
   * Test that the controller catches IOExceptions in the playGame method
   * and throws IllegalStateExceptions instead.
   */
  @Test (expected = IllegalStateException.class)
  public void testControllerIOExceptions() {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;
    fromRow = 1;
    fromCol = 3;
    toRow = 3;
    toCol = 3;

    StringBuilder mockLog = new StringBuilder();
    Appendable viewOut = new MockAppendable();
    EnglishSolitaireModel mockModel = new MockModel(mockLog);
    Readable reader = new StringReader(
            "" + fromRow + " " + fromCol + " " + toRow + " " + toCol + " " + "q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(mockModel, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockModel, view, reader);
    controller.playGame();
  }

  /**
   * tests that the controller ends the game and sends the correct output
   * when the game is over.
   */
  @Test
  public void testGameOver() {
    StringBuilder viewOut = new StringBuilder();
    EnglishSolitaireModel mockGameOver = new MockGameOver();
    Readable reader = new StringReader("q");
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(mockGameOver, viewOut);

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mockGameOver, view, reader);
    controller.playGame();

    assertEquals("" +
            "Game over!\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", viewOut.toString());
  }
}
