import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Class for creating a model which returns true in the game over method no matter what.
 */
class MockGameOver extends EnglishSolitaireModel {


  public MockGameOver() {
    // constructor is empty because this class does not need to instantiate any fields
    // in the contrsuction of its objects.
  }

  @Override
  public boolean isGameOver() {
    return true;
  }
}
