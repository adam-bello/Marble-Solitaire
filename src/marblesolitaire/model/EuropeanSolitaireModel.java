package cs3500.marblesolitaire.model.hw04;

/**
 * represents a European model of the Marble Solitaire game.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * Default constructor for EuropeanSolitaireModel which sends the super class an arm thickness
   * of 3.
   */
  public EuropeanSolitaireModel() {
    super(3);
  }

  /**
   * Creates a European Solitaire model game with the given arm thickness.
   * @param armThickness the arm thickness of the board.
   * @throws IllegalArgumentException if the arm thickness is invalid.
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness);
  }

  /**
   * Creates a European Solitaire model game with the given empty slot location.
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot.
   * @throws IllegalArgumentException if the empty slot location is invalid.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Creates a European Solitaire model game with the given arm thickness and empty slot
   * location.
   * @param armThickness the arm thickness of the board.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness or the location of the empty
   *          slot is invalid.
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  /**
   * Initializes the board using the given arm thickness and returns it.
   * @param armThickness the arm thickness of the board.
   * @return the board with all slots initialized to the correct state.
   * @throws IllegalArgumentException if the arm thickness is invalid.
   */
  @Override
  protected SlotState[][] initBoard(int armThickness) throws IllegalArgumentException {
    this.checkInvalidArmThickness(armThickness);

    SlotState[][] board = new SlotState[(3 * armThickness) - 2][(3 * armThickness) - 2];
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (i < armThickness - 1 && j < armThickness - 1) {
          if (i > 0 && j >= (armThickness - 1) - i) {
            board[i][j] = SlotState.Marble;
          } else {
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i < armThickness - 1 && j >= (2 * armThickness) - 1) {
          if (i > 0 && j < ((2 * armThickness) - 1) + i) {
            board[i][j] = SlotState.Marble;
          } else {
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i >= (2 * armThickness) - 1 && j < armThickness - 1) {
          if (j > 0 && i < ((2 * armThickness) - 1) + j) {
            board[i][j] = SlotState.Marble;
          } else {
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i >= (2 * armThickness) - 1 && j >= (2 * armThickness) - 1) {
          if (i < (3 * armThickness) - 3 &&
                  j < ((3 * armThickness) - 3) - (i - ((armThickness * 2) - 1))) {
            board[i][j] = SlotState.Marble;
          } else {
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i == (board.length - 1) / 2 && j == (board[0].length - 1) / 2) {
          board[i][j] = SlotState.Empty;
        }
        else {
          board[i][j] = SlotState.Marble;
        }
      }
    }
    return board;
  }

  /**
   * Initializes the board using the given arm thickness, and empty slot location.
   * @param armThickness the arm thickness of the board.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @return the initializes board with all slots initialized to the correct state.
   * @throws IllegalArgumentException if the arm thickness or empty slot location is invalid.
   */
  @Override
  protected SlotState[][] initBoard(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    this.checkInvalidArmThickness(armThickness);
    this.checkInvalidRowCol(sRow, sCol);


    SlotState[][] board = new SlotState[(3 * armThickness) - 2][(3 * armThickness) - 2];
    for (int i = 0; i < (3 * this.armThickness) - 2; i++) {
      for (int j = 0; j < (3 * this.armThickness) - 2; j++) {
        if (i < armThickness - 1 && j < armThickness - 1) {
          if (i > 0 && j >= (armThickness - 1) - i) {
            board[i][j] = SlotState.Marble;
          } else {
            checkInvalidEmptySlot(i, j, sRow, sCol);
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i < armThickness - 1 && j >= (2 * armThickness) - 1) {
          if (i > 0 && j < ((2 * armThickness) - 1) + i) {
            board[i][j] = SlotState.Marble;
          } else {
            checkInvalidEmptySlot(i, j, sRow, sCol);
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i >= (2 * armThickness) - 1 && j < armThickness - 1) {
          if (j > 0 && i < ((2 * armThickness) - 1) + j) {
            board[i][j] = SlotState.Marble;
          } else {
            checkInvalidEmptySlot(i, j, sRow, sCol);
            board[i][j] = SlotState.Invalid;
          }
        }
        else if (i >= (2 * armThickness) - 1 && j >= (2 * armThickness) - 1) {
          if (i < (3 * armThickness) - 3 &&
                  j < ((3 * armThickness) - 3) - (i - ((armThickness * 2) - 1))) {
            board[i][j] = SlotState.Marble;
          } else {
            checkInvalidEmptySlot(i, j, sRow, sCol);
            board[i][j] = SlotState.Invalid;
          }
        } else {
          board[i][j] = SlotState.Marble;
        }
        if (i == sRow && j == sCol) {
          board[i][j] = SlotState.Empty;
        }
      }
    }
    return board;
  }

  private void checkInvalidEmptySlot(int i, int j, int sRow, int sCol)
          throws IllegalArgumentException {
    if (i == sRow && j == sCol) {
      throw new IllegalArgumentException("Invalid empty cell position("
              + sRow + "," + sCol + ")");
    }
  }

  private void checkInvalidArmThickness(int armThickness) throws IllegalArgumentException {
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number");
    }
  }

  private void checkInvalidRowCol(int sRow, int sCol)
          throws IllegalArgumentException {
    if (sRow >= this.getBoardSize() || sCol >= this.getBoardSize()) {
      throw new IllegalArgumentException(
              "The given row or column is beyond the size of the board.");
    }
    else if (sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Rows and columns must be positive numbers.");
    }
  }
}
