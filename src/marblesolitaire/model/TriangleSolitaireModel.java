package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a triangle version of the Marble Solitaire game. This contains a dimension
 * represented as an integer and extends the AbstractSolitaireModel class.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {
  int dimension;

  /**
   * Used for constructing a TriangleSolitaireModel with a default dimension of 5 and an
   * empty slot at (0, 0).
   */
  public TriangleSolitaireModel() {
    super(5);
    this.dimension = 5;

  }

  /**
   * Used for constructing a TriangleSolitaireModel with a given dimension and an empty slot
   * at (0, 0).
   * @param dimension The dimension of the board.
   * @throws IllegalArgumentException if dimension is non-positive
   */
  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    super(dimension);
    this.dimension = dimension;
  }

  /**
   * Used for constructing a TriangleSolitaireModel with a default dimension of 5 and an
   * empty slot at the given location.
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   * @throws IllegalArgumentException if the empty slot provided is an invalid position
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
    this.dimension = 5;
  }

  /**
   * Used for constructing a TriangleSolitaireModel with a given dimension and empty slot location.
   * @param dimension The dimension of the board.
   * @param sRow The row of the empty slot.
   * @param sCol The column of the empty slot.
   * @throws IllegalArgumentException if the dimension or empty slot provided is invalid.
   */
  public TriangleSolitaireModel(int dimension, int sRow, int sCol) throws IllegalArgumentException {
    super(dimension, sRow, sCol);
    this.dimension = dimension;
  }

  /**
   * Initializes the board when given only a dimension.
   * @param dimension the dimension of the board.
   * @return The board with all slots initialized to the correct state.
   * @throws IllegalArgumentException if the given dimension is invalid.
   */
  protected SlotState[][] initBoard(int dimension) throws IllegalArgumentException {
    this.checkInvalidDimension(dimension);

    SlotState[][] board = new SlotState[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (i == 0 && j == 0) {
          board[i][j] = SlotState.Empty;
        }
        else if (j > i) {
          board[i][j] = SlotState.Invalid;
        }
        else {
          board[i][j] = SlotState.Marble;
        }
      }
    }

    return board;
  }

  /**
   * Initializes the board when given a dimension, row and column for the empty slot.
   * @param dimension the dimension of the board.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @return the initialized board with all slots initialized to the correct state.
   * @throws IllegalArgumentException if the given dimension, or empty slot location is invalid.
   */
  protected SlotState[][] initBoard(int dimension, int sRow, int sCol)
          throws IllegalArgumentException {
    this.checkInvalidDimension(dimension);
    this.checkInvalidRowCol(dimension, sRow, sCol);

    SlotState[][] board = new SlotState[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (j > i) {
          if (i == sRow && j == sCol) {
            throw new IllegalArgumentException("Invalid Empty Slot");
          }
          board[i][j] = SlotState.Invalid;
        }
        else if (i == sRow && j == sCol) {
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
   *                 position is not a marble, or if a move is more or less than exactly 2 slots.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if ((fromRow >= dimension || fromCol >= dimension)
            || (fromRow < 0 || fromCol < 0)
            || (toRow >= dimension || toCol >= dimension)
            || (toRow  < 0 || toCol < 0)) {
      throw new IllegalArgumentException("Given row or column to move from is not on the board.");
    }
    else if ((board[fromRow][fromCol] != SlotState.Marble)
            || (board[toRow][toCol] != SlotState.Empty)) {
      throw new IllegalArgumentException("Must move from a marble to an empty slot.");
    }
    else if (fromRow == toRow && fromCol > toCol) {
      if (fromCol - toCol != 2 ) {
        throw new IllegalArgumentException("Must move exactly 2 slots when moving horizontally");
      } else if (board[fromRow][toCol + 1] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble");
      } else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow][toCol + 1] = SlotState.Empty;
        board[fromRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow == toRow && fromCol < toCol) {
      if (toCol - fromCol != 2) {
        throw new IllegalArgumentException("Must move exactly 2 slots when moving horizontally");
      } else if (board[fromRow][fromCol + 1] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble");
      } else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow][fromCol + 1] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow < toRow && fromCol < toCol) {
      if (toRow - fromRow != 2) {
        throw new IllegalArgumentException("Must move exactly 2 rows when moving down");
      } else if (toCol - fromCol != 2) {
        throw new IllegalArgumentException("Must move exactly 2 columns when moving right");
      } else if (board[fromRow + 1][fromCol + 1] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble");
      } else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow + 1][fromCol + 1] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow < toRow && fromCol == toCol) {
      if (toRow - fromRow != 2) {
        throw new IllegalArgumentException("Must move exactly 2 rows when moving down");
      } else if (board[fromRow + 1][toCol] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble");
      } else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[fromRow + 1][toCol] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    } else if (fromRow > toRow && fromCol == toCol) {
      if (fromRow - toRow != 2) {
        throw new IllegalArgumentException("Must move exactly 2 rows when moving up");
      } else if (board[toRow + 1][fromCol] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble");
      } else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[toRow + 1][fromCol] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
    else if (fromRow > toRow && fromCol > toCol) {
      if (fromRow - toRow != 2) {
        throw new IllegalArgumentException("Must move exactly 2 rows when moving up");
      } else if (fromCol - toCol != 2) {
        throw new IllegalArgumentException("Must move exactly 2 columns when moving left");
      } else if (board[toRow + 1][toCol + 1] != SlotState.Marble) {
        throw new IllegalArgumentException("Must jump over a marble");
      } else {
        board[fromRow][fromCol] = SlotState.Empty;
        board[toRow + 1][toCol + 1] = SlotState.Empty;
        board[toRow][toCol] = SlotState.Marble;
      }
    }
  }

  /**
   * Determines if the game is over by checking if there are any remaining moves.
   * @return whether or not the game is over.
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (board[i][j] == SlotState.Marble) {
          if (j >= 2
                  && board[i][j - 1] == SlotState.Marble
                  && board[i][j - 2] == SlotState.Empty) {
            return false;
          }
          else if (j <= dimension - 3
                  && board[i][j + 1] == SlotState.Marble
                  && board[i][j + 2] == SlotState.Empty) {
            return false;
          }
          else if (j >= 2 && i >= 2
                  && board[i - 1][j - 1] == SlotState.Marble
                  && board[i - 2][j - 2] == SlotState.Empty) {
            return false;
          }
          else if (j >= 2 && i <= dimension - 3
                  && board[i + 1][j - 1] == SlotState.Marble
                  && board[i + 2][j - 2] == SlotState.Empty) {
            return false;
          }
          else if (j <= dimension - 3 && i >= 2
                  && board[i - 1][j + 1] == SlotState.Marble
                  && board[i - 2][j + 2] == SlotState.Empty) {
            return false;
          } else if (j <= dimension - 3 && i <= dimension - 3
                  && board[i + 1][j + 1] == SlotState.Marble
                  && board[i + 2][j + 2] == SlotState.Empty) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * returns the size of the board which is simply the dimension.
   * @return the size of the board.
   */
  @Override
  public int getBoardSize() {
    return this.dimension;
  }

  /**
   * returns the SlotState of a given slot on the board.
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return A slot state of the given slot.
   * @throws IllegalArgumentException if the given row and column are invalid.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    this.checkInvalidRowCol(this.dimension, row, col);
    return board[row][col];
  }

  /**
   * Returns the score of the game as the number of marbles left remaining.
   * @return the score of the game.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (board[i][j] == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }

  private void checkInvalidDimension(int dimension) throws IllegalArgumentException {
    if (dimension < 0) {
      throw new IllegalArgumentException("Dimension must be positive.");
    }
  }

  private void checkInvalidRowCol(int dimension, int sRow, int sCol)
          throws IllegalArgumentException {
    if (sRow < 0 || sCol < 0 || sRow > dimension - 1 || sCol > dimension - 1) {
      throw new IllegalArgumentException("Invalid Empty Slot");
    }
  }

}
