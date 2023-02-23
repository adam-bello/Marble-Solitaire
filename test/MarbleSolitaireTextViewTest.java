import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireTextView view;
  private MarbleSolitaireTextView view2;
  private MarbleSolitaireTextView view3;
  private MarbleSolitaireTextView view4;
  private MarbleSolitaireTextView view5;
  private MarbleSolitaireTextView view6;
  private MarbleSolitaireTextView eView;
  private MarbleSolitaireTextView eView2;
  private MarbleSolitaireModelState model;
  private Appendable dest1;

  /**
   * A mock class for creating mock appendables used for testing the view.
   */

  @Before
  public void setup() {
    MarbleSolitaireModelState model1 = new EnglishSolitaireModel(3);
    MarbleSolitaireModelState model2 = new EnglishSolitaireModel(5);
    MarbleSolitaireModelState model3 = new EnglishSolitaireModel(5, 4);
    MarbleSolitaireModelState eModel = new EuropeanSolitaireModel(5);
    MarbleSolitaireModelState eModel2 = new EuropeanSolitaireModel();
    view = new MarbleSolitaireTextView(model2);
    view2 = new MarbleSolitaireTextView(model1);
    view3 = new MarbleSolitaireTextView(model3);
    eView = new MarbleSolitaireTextView(eModel);
    eView2 = new MarbleSolitaireTextView(eModel2);

    // model and view after a successful move is made
    MarbleSolitaireModel model4 = new EnglishSolitaireModel(3);
    model4.move(1, 3, 3, 3);
    view4 = new MarbleSolitaireTextView(model4);

    dest1 = new StringBuilder();
    view5 = new MarbleSolitaireTextView(model1, dest1);
    MockAppendable mock1 = new MockAppendable();
    view6 = new MarbleSolitaireTextView(model1, mock1);
    model = new EnglishSolitaireModel();

  }

  /**
   * Test the constructor with 1 argument throws an IllegalArgumentException when provided a
   * null state.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNullState() {
    new MarbleSolitaireTextView(null);
  }

  /**
   * Test the constructor with 2 arguments throws an IllegalArgumentException when provided a
   * null state.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNullState2Arg() {
    new MarbleSolitaireTextView(null, dest1);
  }

  /**
   * Test the constructor with 2 arguments throws an IllegalArgumentException when provided a
   * null Appendable.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    new MarbleSolitaireTextView(model, null);
  }

  /**
   * Test the toString method of MarbleSolitaireTextView.
   */
  @Test
  public void testToString() {
    assertEquals(
            "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O", view.toString());

    assertEquals(
            "" +
                    "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view2.toString());

    assertEquals("" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O _\n" +
            "    O O O", view3.toString());

    assertEquals("" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view4.toString());

    assertEquals("" +
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", eView.toString());

    assertEquals("" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", eView2.toString());

  }

  @Test
  public void testRenderBoard() {
    try {
      view5.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Error rendering message");
    }
    assertEquals(view5.toString(), dest1.toString());
  }

  @Test (expected  = IllegalStateException.class)
  public void testRenderBoardIOException() {
    try {
      view6.renderBoard();
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not render board");
    }
  }

  @Test
  public void testRenderMessage() {
    try {
      view5.renderMessage("Game Over!");
    }
    catch (IOException e) {
      throw new IllegalStateException("Error rendering message");
    }
    assertEquals("Game Over!", dest1.toString());
  }

  @Test (expected = IllegalStateException.class)
  public void testRenderMessageIOException() {
    try {
      view6.renderMessage("Game Over");
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not render message");
    }
  }
}
