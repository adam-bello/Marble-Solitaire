import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * class for creating mock models used for testing.
 */
class MockModel extends EnglishSolitaireModel {
  private StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * appends the log with the given inputs.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    log.append("inputs given are: " +
            fromRow +
            " " +
            fromCol +
            " " +
            toRow +
            " " +
            toCol);
  }
}