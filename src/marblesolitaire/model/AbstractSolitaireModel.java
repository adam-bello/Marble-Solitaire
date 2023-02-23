package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * represents an abstract solitaire model which can be used as a basis for other
 * implementations of MarbleSolitaireModel.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  int armThickness;
  SlotState[][] board;

  /**
   * Constructor for making an AbstractSolitaireModel with a given arm thickness.
   * @param armThickness The arm thickness or in some implementations' cases the dimension
   *                     of the board.
   */
  public AbstractSolitaireModel(int armThickness) {

    this.armThickness = armThickness;
    this.board = this.initBoard(armThickness);
  }

  /**
   * Constructor for making an AbstractSolitaireModel with a given arm thickness, and empty
   * slot location.
   * @param armThickness The arm thickness or in some implementations' cases the dimension
   *                     of the board.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol) {

    this.armThickness = armThickness;
    this.board = this.initBoard(armThickness, sRow, sCol);
  }

  protected abstract SlotState[][] initBoard(int armThickness);

  protected abstract SlotState[][] initBoard(int armThickness, int sRow, int sCol);


  /**
   * takes in 4 ints as a position to move from and to and then determines if
   * the move is valid. If it is, it moves the marble in the from location to
   * the to location and removes the marble in between these positions.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the from or to position provided is not on the board,
   *                 if the from or to position provided is an invalid slot, from location is not a
   *                 marble, to location is not empty, if the slot in between the from and to
   *                 position is not a marble, if the attempted move is diagonal, or if a move is
   *                 more or less than exactly 2 slots.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if ((fromRow >= (3 * armThickness) - 2 || fromCol >= (3 * armThickness) - 2)
            || (fromRow < 0 || fromCol < 0)
            || (toRow >= (3 * armThickness) - 2
            || toCol >= (3 * armThickness) - 2)
            || (toRow  < 0 || toCol < 0)) {
      throw new IllegalArgumentException(
              "Given row or column to move to or from is not on the board.");
    }
    else if ((board[fromRow][fromCol] != SlotState.Marble)
            || (board[toRow][toCol] != SlotState.Empty)
            || (fromRow != toRow && fromCol != toCol)) {
      throw new IllegalArgumentException("Illegal move. Moves must be made horizontally" +
              "or vertically and begin at a marble and end at an empty slot.");
    }
    else if (fromRow == toRow && fromCol < toCol) {
      if (toCol - fromCol != 2) {
        throw new IllegalArgumentException("Must move exactly 2 spots.");
      }
      else if (board[fromRow][fromCol + 1] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
      else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow][fromCol + 1] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow == toRow && fromCol > toCol) {
      if (fromCol - toCol != 2) {
        throw new IllegalArgumentException("Must move exactly 2 spots.");
      }
      else if (board[fromRow][fromCol - 1] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
      else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow][fromCol - 1] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow < toRow && fromCol == toCol) {
      if (toRow - fromRow != 2) {
        throw new IllegalArgumentException("Must move exactly 2 spots.");
      }
      else if (board[fromRow + 1][fromCol] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
      else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow + 1][fromCol] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow > toRow && fromCol == toCol) {
      if (fromRow - toRow != 2) {
        throw new IllegalArgumentException("Must move exactly 2 spots.");
      }
      else if (board[fromRow - 1][fromCol] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble.");
      }
      else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow - 1][fromCol] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
  }

  /**
   * Determines if the game is over by checking to see if any marbles have
   * legal moves available.
   * @return a boolean which is false if the game isn't over, and true if it is over.
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < (3 * armThickness) - 2; i++) {
      for (int j = 0; j < (3 * armThickness) - 2; j++) {
        if (board[i][j] == SlotState.Marble) {
          if (i >= 2
                  && board[i - 1][j] == SlotState.Marble
                  && board[i - 2][j] == SlotState.Empty) {
            return false;
          }
          else if (j >= 2
                  && board[i][j - 1] == SlotState.Marble
                  && board[i][j - 2] == SlotState.Empty) {
            return false;
          }
          else if (i <= (3 * armThickness) - 5
                  && board[i + 1][j] == SlotState.Marble
                  && board[i + 2][j] == SlotState.Empty) {
            return false;
          }
          else if (j <= (3 * armThickness) - 5
                  && board[i][j + 1] == SlotState.Marble
                  && board[i][j + 2] == SlotState.Empty) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * calculates the size of the board as the width or height.
   * @return the size of the board (width or height).
   */
  @Override
  public int getBoardSize() {
    return (3 * armThickness) - 2;
  }

  /**
   * Checks the slot state of the slot at the given position.
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return one of Empty, Invalid, or marble from the SlotState enum
   * @throws IllegalArgumentException if the provided row or column is not on the board.
   */
  @Override
  public MarbleSolitaireModelState.SlotState getSlotAt(int row, int col)
          throws IllegalArgumentException {
    if (row >= (3 * armThickness) - 2 || col >= (3 * armThickness) - 2 || row < 0 || col < 0) {
      throw new IllegalArgumentException("Given row and column is not on the board.");
    }
    return board[row][col];
  }

  /**
   * gets the score of the game as the number of slots containing marbles.
   * @return the number of marbles left in the game.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < (3 * armThickness) - 2; i++) {
      for (int j = 0; j < (3 * armThickness) - 2; j++) {
        if (board[i][j] == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }


}
