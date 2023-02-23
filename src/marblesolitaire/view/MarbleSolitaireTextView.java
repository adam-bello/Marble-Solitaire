package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class representing the text view of the game board. Contains a
 * MarbleSolitaireModelState field used as the model to portray in the
 * text view.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState game;
  private Appendable destination;

  /**
   * Constructor for creating a text view of the game which takes in a
   * model state.
   * @param game The MarbleSolitaireModelState to be represented in the text view.
   * @throws IllegalArgumentException if the provided model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) {
    if (game == null) {
      throw new IllegalArgumentException("provided model is null.");
    }
    this.game = game;
    this.destination = System.out;
  }

  /**
   * Constructs the view of the game which takes in a model state and an appendable.
   * @param game The MarbleSolitaireModelState to be represented in the text view.
   * @param destination The destination where the view will be displayed.
   * @throws IllegalArgumentException if the model or the appendable are null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable destination) {
    if (game == null || destination == null) {
      throw new IllegalArgumentException("provided model or destination is null");
    }
    this.game = game;
    this.destination = destination;
  }

  /**
   * Creates a string version of the game model.
   * @return A string version of the game mode.
   *          - Space for Invalid positions
   *          - O for marbles
   *          - _ for empty
   *          ALl columns have a space between them except
   *          before the first and after the last. There is
   *          no new line after the last row.
   */
  public String toString() {
    String view = "";
    for (int i = 0; i < game.getBoardSize(); i++) {
      for (int j = 0; j < game.getBoardSize(); j++) {
        if (game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          if (j >= (((game.getBoardSize() + 2) / 3) * 2) - 1) {
            view = view + "";
          }
          else if (j == 0) {
            view = view + " ";
          }
          else {
            view = view + "  ";
          }
        }
        else if (game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          if (j == 0) {
            view = view + "O";
          } else if (j == game.getBoardSize() - 1 ||
                  game.getSlotAt(i, j + 1) == MarbleSolitaireModelState.SlotState.Invalid) {
            if (i == game.getBoardSize() - 1) {
              view = view + " O";
            } else {
              view = view + " O\n";
            }
          }
          else {
            view = view + " O";
          }
        }
        else if (game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          if (j == 0) {
            view = view + "_";
          } else if (j == game.getBoardSize() - 1 ||
                  game.getSlotAt(i, j + 1) == MarbleSolitaireModelState.SlotState.Invalid) {
            if (i == game.getBoardSize() - 1) {
              view = view + " _";
            } else {
              view = view + " _\n";
            }
          }
          else {
            view = view + " _";
          }
        }
      }
    }
    return view;
  }

  @Override
  public void renderBoard() throws IOException {
    destination.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    destination.append(message);
  }
}
