import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * A class used for testing the EuropeanSolitaireModel. This class extends the AbstractModelTest
 * class.
 */
public class EuropeanSolitaireModelTest extends AbstractModelTest {
  private MarbleSolitaireModel game1;
  private MarbleSolitaireModel game2;
  private MarbleSolitaireModel game3;
  private MarbleSolitaireModel game4;

  @Before
  public void setup() {
    game1 = new EuropeanSolitaireModel();
    game2 = new EuropeanSolitaireModel(3, 3);
    game3 = new EuropeanSolitaireModel(5);
    game4 = new EuropeanSolitaireModel(3, 3, 3);
  }

  @Test
  public void testGetScore() {
    assertEquals(36, game1.getScore());
    assertEquals(36, game2.getScore());
    assertEquals(128, game3.getScore());
    assertEquals(36, game4.getScore());

    game1.move(1, 3, 3, 3);
    game1.move(2, 5, 2, 3);
    game3.move(4, 6, 6, 6);

    assertEquals(34, game1.getScore());
    assertEquals(127, game3.getScore());
  }

  @Override
  protected MarbleSolitaireModel createModel() {
    return new EuropeanSolitaireModel();
  }

  @Override
  protected MarbleSolitaireModel createModel(int size) {
    return new EuropeanSolitaireModel(size);
  }

  @Override
  protected MarbleSolitaireModel createModel(int row, int col) {
    return new EuropeanSolitaireModel(row, col);
  }

  @Override
  protected MarbleSolitaireModel createModel(int size, int row, int col) {
    return new EuropeanSolitaireModel(size, row, col);
  }


  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel example = new EuropeanSolitaireModel(1);

    // ensure that game is not over before moves are made
    assertEquals(true, example.isGameOver());


  }
}
