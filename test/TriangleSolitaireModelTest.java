import org.junit.Before;
import org.junit.Test;

//import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * A class used for testing the TriangleSolitaireModel class.
 */
public class TriangleSolitaireModelTest {

  private MarbleSolitaireModel model1;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireModel model3;
  private MarbleSolitaireModel model4;

  @Before
  public void setup() {
    model1 = new TriangleSolitaireModel();
    model2 = new TriangleSolitaireModel(2);
    model3 = new TriangleSolitaireModel(3, 3);
    model4 = new TriangleSolitaireModel(7, 2, 2);
  }

  @Test
  public void testConstructor0Arg() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4,4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 1));
  }

  @Test
  public void testConstructor1Arg() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(0, 1));
  }

  @Test
  public void testConstructor2Arg() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(1,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(0, 1));
  }

  @Test
  public void testConstructor3Arg() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model4.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model4.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model4.getSlotAt(5,5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(0, 1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeDimension1Arg() {
    new TriangleSolitaireModel(-2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeDimension3Arg() {
    new TriangleSolitaireModel(-2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidEmpty1Arg() {
    new TriangleSolitaireModel(3, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidEmpty3Arg() {
    new TriangleSolitaireModel(5, 3, 4);
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, model1.getBoardSize());
    assertEquals(2, model2.getBoardSize());
    assertEquals(5, model3.getBoardSize());
    assertEquals(7, model4.getBoardSize());
  }

  @Test
  public void testGetScore() {
    assertEquals(14, model1.getScore());
    assertEquals(2, model2.getScore());
    assertEquals(14, model3.getScore());
    assertEquals(27, model4.getScore());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidRow() {
    model1.getSlotAt(10, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidCol() {
    model1.getSlotAt(2, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetSlotAtRowOffBoard() {
    model1.getSlotAt(-1, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetSlotAtColOffBoard() {
    model1.getSlotAt(2, -1);
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(3,4));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(2,1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(3,4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromRowTooBig() {
    model1.move(10, 1, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromColTooBig() {
    model1.move(3, 10, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromRowTooSmall() {
    model1.move(-1, 1, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromColTooSmall() {
    model1.move(3, -1, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToRowTooBig() {
    model1.move(3, 1, 10, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToColTooBig() {
    model1.move(3, 1, 1, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToRowTooSmall() {
    model1.move(3, 1, -1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToColTooSmall() {
    model1.move(3, 1, 1, -1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromEmpty() {
    model1.move(0, 0, 2, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromInvalid() {
    model1.move(0, 2, 2, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToMarble() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    model1.move(4, 4, 2, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToInvalid() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(2, 4));
    model1.move(2, 2, 2, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2Right() {
    model1.move(4, 1, 4, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2Left() {
    model1.move(4, 4, 4, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2RightUp() {
    model3.move(4, 1, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2RightDown() {
    model3.move(1, 1, 4, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2LeftDown() {
    model3.move(1, 0, 4, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2LeftUp() {
    model3.move(4, 3, 1, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2Right() {
    model1.move(4, 1, 4, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2Left() {
    model1.move(4, 4, 4, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2RightUp() {
    model3.move(4, 1, 3, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2RightDown() {
    model3.move(1, 1, 2, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2LeftDown() {
    model3.move(1, 0, 2, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2LeftUp() {
    model3.move(4, 3, 3, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoMarbleJumpRight() {
    model3.move(2, 0, 2, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoMarbleJumpLeft() {
    model3.move(2, 2, 2, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoMarbleJumpDownRight() {
    model3.move(2, 2, 4, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoMarbleJumpUpRight() {
    model3.move(4, 0, 2, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoMarbleJumpDownLeft() {
    model3.move(2, 0, 4, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNoMarbleJumpUpLeft() {
    model3.move(4, 3, 2, 1);
  }

  @Test
  public void testMove() {
    MarbleSolitaireModel example = new TriangleSolitaireModel(3, 1);

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(3, 1));

    // test move down left
    example.move(1, 1, 3, 1);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(3, 1));

    // test move up left
    example.move(4, 3, 2, 1);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 1));

    // test move down right
    example.move(1, 0, 3, 2);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(3, 2));

    // test move up right
    example.move(4, 1, 2, 1);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 1));

    MarbleSolitaireModel example2 = new TriangleSolitaireModel(7,6, 4);

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example2.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example2.getSlotAt(6, 4));

    //test move left
    example2.move(6,6,6,4);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example2.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example2.getSlotAt(6, 4));

    //test move right
    example2.move(6, 3, 6, 5);

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example2.getSlotAt(6, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example2.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example2.getSlotAt(6, 5));

  }

  @Test
  public void testIsGameOver() {

    MarbleSolitaireModel example = new TriangleSolitaireModel(3, 1, 1);
    MarbleSolitaireModel example2 = new TriangleSolitaireModel(3);

    assertEquals(true, example.isGameOver());
    assertEquals(false, example2.isGameOver());

    example2.move(2, 2, 0, 0);
    example2.move(2, 0, 2, 2);
    example2.move(0, 0, 2, 0);

    assertEquals(true, example2.isGameOver());
  }
}
