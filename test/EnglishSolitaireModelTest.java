import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing EnglishSolitaireModelTest. This class extends the AbstractModelTest class.
 */
public class EnglishSolitaireModelTest extends AbstractModelTest {

  // Example Models
  private MarbleSolitaireModel game1;
  private MarbleSolitaireModel game2;
  private MarbleSolitaireModel game3;
  private MarbleSolitaireModel game4;

  @Before
  public void setup() {
    game1 = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(3, 3);
    game3 = new EnglishSolitaireModel(5);
    game4 = new EnglishSolitaireModel(3, 3, 3);
  }

  /**
   * Tests the getScore() method both before and after moves have been made.
   */
  @Test
  public void testGetScore() {
    assertEquals(32, game2.getScore());
    assertEquals(104, game3.getScore());
    assertEquals(32, game4.getScore());

    game2.move(1,3,3, 3);
    game2.move(2, 5, 2, 3);
    game3.move(4, 6, 6, 6);

    assertEquals(30, game2.getScore());
    assertEquals(103, game3.getScore());
  }

  @Override
  protected MarbleSolitaireModel createModel() {
    return new EnglishSolitaireModel();
  }

  @Override
  protected MarbleSolitaireModel createModel(int size) {
    return new EnglishSolitaireModel(size);
  }

  @Override
  protected MarbleSolitaireModel createModel(int row, int col) {
    return new EnglishSolitaireModel(row, col);
  }

  @Override
  protected MarbleSolitaireModel createModel(int size, int row, int col) {
    return new EnglishSolitaireModel(size, row, col);
  }

  @Test
  public void testIsGameOver() {
    // ensure that game is not over before moves are made
    assertEquals(false, game1.isGameOver());

    game1.move(5, 3, 3, 3);
    game1.move(4, 5, 4, 3);
    game1.move(6, 4, 4, 4);
    game1.move(4, 3, 4, 5);
    game1.move(4, 6, 4, 4);
    game1.move(3, 4, 5, 4);
    game1.move(3, 6, 3, 4);
    game1.move(3, 3, 3, 5);
    game1.move(1, 4, 3, 4);
    game1.move(3, 5, 3, 3);
    game1.move(2, 6, 2, 4);
    game1.move(2, 3, 2, 5);
    game1.move(0, 3, 2, 3);
    game1.move(3, 2, 3, 4);
    game1.move(3, 0, 3, 2);
    game1.move(4, 1, 4, 3);
    game1.move(6, 2, 4, 2);
    game1.move(4, 3, 4, 1);
    game1.move(4, 0, 4, 2);
    game1.move(3, 2, 5, 2);
    game1.move(1, 2, 3, 2);
    game1.move(2, 0, 2, 2);

    // show that game is still not over
    assertEquals(false, game1.isGameOver());

    game1.move(2, 3, 2, 1);

    // show that game is over with final move and no possible moves
    // left available.
    assertEquals(true, game1.isGameOver());
  }
}
