import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * A class used for testing the TriangleSolitaireTextView class.
 */
public class TriangleSolitaireTextViewTest {

  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireView view3;
  private MarbleSolitaireView view4;
  private MarbleSolitaireView view5;
  private Appendable dest1;

  @Before
  public void setup() {
    MarbleSolitaireModelState model1 = new TriangleSolitaireModel(7);
    MarbleSolitaireModelState model2 = new TriangleSolitaireModel(3, 2);
    MarbleSolitaireModelState model3 = new TriangleSolitaireModel(4, 0);
    dest1 = new StringBuilder();
    view1 = new TriangleSolitaireTextView(model1, dest1);
    view2 = new TriangleSolitaireTextView(model2, dest1);
    view3 = new TriangleSolitaireTextView(model3, dest1);
    MockAppendable mock1 = new MockAppendable();
    view5 = new MarbleSolitaireTextView(model1, mock1);

    MarbleSolitaireModel model4 = new TriangleSolitaireModel(7,3, 2);
    model4.move(5, 4, 3, 2);
    view4 = new TriangleSolitaireTextView(model4, dest1);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullState() {
    new TriangleSolitaireTextView(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullState2Arg() {
    new TriangleSolitaireTextView(null, dest1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    MarbleSolitaireModelState model1 = new TriangleSolitaireModel(7);
    new TriangleSolitaireTextView(model1, null);
  }

  /**
   * tests that the toString method of the view produces the correct ouput when a game
   * is initialized and after a move is made.
   */
  @Test
  public void testToString() {
    assertEquals("" +
            "      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view1.toString());

    assertEquals("" +
            "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O O O O", view2.toString());

    assertEquals("" +
            "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "_ O O O O", view3.toString());

    assertEquals("" +
            "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O _ O\n" +
            " O O O O _ O\n" +
            "O O O O O O O", view4.toString());
  }

  /**
   * tests that the board is rendered correctly by the view by comparing it to the appendable.
   */
  @Test
  public void testRenderBoard() {
    try {
      view1.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Error rendering message");
    }
    assertEquals(view1.toString(), dest1.toString());
  }

  /**
   * tests that the board correctly throws an IOException if there is a error transmitting
   * to the Appendable.
   */
  @Test (expected  = IllegalStateException.class)
  public void testRenderBoardIOException() {
    try {
      view5.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not render board");
    }
  }

  @Test
  public void testRenderMessage() {
    try {
      view1.renderMessage("Game Over!");
    }
    catch (IOException e) {
      throw new IllegalStateException("Error rendering message");
    }
    assertEquals("Game Over!", dest1.toString());
  }

  @Test (expected = IllegalStateException.class)
  public void testRenderMessageIOException() {
    try {
      view5.renderMessage("Game Over");
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not render message");
    }
  }
}
