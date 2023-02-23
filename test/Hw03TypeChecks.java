import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw03TypeChecks {

  /**
   * Main method.
   * @param args arg.
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helper(new EnglishSolitaireModel(), rd, ap);
    helper(new EnglishSolitaireModel(3, 3), rd, ap);
  }

  private static void helper(
          cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model,
          Readable rd,Appendable ap) {
    new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(model,
            new cs3500.marblesolitaire.view.MarbleSolitaireTextView(model,ap),rd);
  }

}
