import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * An abstract testing class for testing the European and English solitaire boards
 * which are both rectilinear in shape.
 */
public abstract class AbstractModelTest {

  protected abstract MarbleSolitaireModel createModel();

  protected abstract MarbleSolitaireModel createModel(int size);

  protected abstract MarbleSolitaireModel createModel(int row, int col);

  protected abstract MarbleSolitaireModel createModel(int size, int row, int col);

  @Test
  public void testCorrectInitializationDefault() {
    MarbleSolitaireModel game1 = createModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game1.getSlotAt(0,0));
  }

  @Test
  public void testCorrectInitialization1Arg() {
    MarbleSolitaireModel game3 = createModel(5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(12, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game3.getSlotAt(0,0));
  }

  @Test
  public void testCorrectInitialization2Arg() {
    MarbleSolitaireModel game2 = createModel(3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game2.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game2.getSlotAt(0,0));
  }

  @Test
  public void testCorrectInitialization3Arg() {
    MarbleSolitaireModel game4 = createModel(3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game4.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game4.getSlotAt(6, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game4.getSlotAt(0,0));
  }

  /**
   * Tests that an error is thrown when a model is constructed with an even armThickness.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testEvenArmThickness1ArgConstructor() {
    createModel(2);
  }

  /**
   * Tests that an error is thrown when a model is constructed with an even armThickness.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testEvenArmThickness3ArgConstructor() {
    createModel(4, 3, 3);
  }


  /**
   * Tests that an error is thrown when a model is constructed with the empty slot
   * in an invalid location.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidEmptySlot1ArgConstructor() {
    createModel(6, 6);
  }

  /**
   * Tests that an error is thrown when a model is constructed with the empty slot
   * in an invalid location.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidEmptySlot3ArgConstructor() {
    createModel(3, 0, 1);
  }

  /**
   * Tests that an error is thrown when a model is constructed with the empty slot off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsEmptySlotGreaterThan() {
    createModel(3, 10, 10);
  }

  /**
   * Tests that an error is thrown when a model is constructed with the empty slot off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsEmptySlotLessThan() {
    createModel(3, -3, -3);
  }

  /**
   * Tests that an error is thrown when a model is constructed with the empty slot off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsEmptySlotRow() {
    createModel(3, 10, 3);
  }

  /**
   * Tests that an error is thrown when a model is constructed with the empty slot off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsEmptySlotCol() {
    createModel(3, 3, 10);
  }

  /**
   * Tests the getBoardSize() method.
   */
  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel game1 = createModel();
    MarbleSolitaireModel game3 = createModel(5);
    assertEquals(7, game1.getBoardSize());
    assertEquals(13, game3.getBoardSize());
  }

  /**
   * Tests the getSlotState() method on all 3 types of SlotStates.
   */
  @Test
  public void testGetSlotState() {
    MarbleSolitaireModel game3 = createModel(5);
    MarbleSolitaireModel game4 = createModel(3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game4.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, game3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game4.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game4.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game3.getSlotAt(7, 7));
  }

  @Test
  public void testMove() {
    MarbleSolitaireModel example = createModel();

    // check slot states before move is made
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(3, 3));

    // test move down
    example.move(1, 3, 3, 3);

    // check slot states after move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(3, 3));

    // test move left
    example.move(2, 5, 2, 3);

    // check slot states after move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 3));

    // test move right
    example.move(2, 2, 2, 4);

    // check slot states after move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 4));

    // test move up
    example.move(4, 3, 2, 3);

    // check slot states after move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, example.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, example.getSlotAt(2, 3));
  }

  /**
   * Tests that an error is thrown when the given from position is off the board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromOffBoardGreaterThan() {
    MarbleSolitaireModel game = createModel();
    game.move(10, 10, 3, 3);
  }

  /**
   * Tests that an error is thrown when the given from position is off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromOffBoardLessThan() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(-1, 1, 1, 1);
  }

  /**
   * Tests that an error is thrown when the given to position is off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveToOffBoardGreaterThan() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(5, 6, 7, 6);
  }

  /**
   * Tests that an error is thrown when the given to position is off the board.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveToOffBoardLessThan() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(1, 1, -1, 1);
  }

  /**
   * Tests that an error is thrown when the given from position is an empty slot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromEmpty() {
    MarbleSolitaireModel game1 = createModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, game1.getSlotAt(3, 3));
    game1.move(3, 3, 3, 5);
  }

  /**
   * Tests that an error is thrown when the given from position is invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromInvalid() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(1, 1, 1, 3);
  }

  /**
   * Tests that an error is thrown when the given to position is a marble slot.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveToMarble() {
    MarbleSolitaireModel game1 = createModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, game1.getSlotAt(2, 3));
    game1.move(0, 3, 2, 3);
  }

  /**
   * Tests that an error is thrown when the given to position is invalid.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveToInvalid() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(2, 5, 0, 5);
  }

  /**
   * Tests that an error is thrown when a diagonal move is attempted.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveDiagonal() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(3, 3, 5, 5);
  }

  /**
   * Tests that an error is thrown when a move of less than 2 slots is attempted.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2Horizontal() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(1, 3, 1, 2);
  }

  /**
   * Tests that an error is thrown when a move of more than 2 slots is attempted.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2Horizontal() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(3, 1, 3, 5);
  }

  /**
   * Tests that an error is thrown when a move of less than 2 slots is attempted.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveLessThan2Vertical() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(1, 3, 2, 3);
  }

  /**
   * Tests that an error is thrown when a move of more than 2 slots is attempted.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveGreaterThan2Vertical() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(1, 2, 5, 2);
  }

  /**
   * Tests that an error is thrown when no marble is jumped over with the given move.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNoMarbleJumpDown() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(0, 3, 2, 3);
  }

  /**
   * Tests that an error is thrown when no marble is jumped over with the given move.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNoMarbleJumpUp() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(2, 3, 0, 3);
  }

  /**
   * Tests that an error is thrown when no marble is jumped over with the given move.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNoMarbleJumpRight() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(2, 2, 2, 4);
  }

  /**
   * Tests that an error is thrown when no marble is jumped over with the given move.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNoMarbleJumpLeft() {
    MarbleSolitaireModel game1 = createModel();
    game1.move(2, 4, 2, 2);
  }

}
