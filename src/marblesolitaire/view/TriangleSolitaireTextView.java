package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the view of the TriangleSolitaireModel. Has a field for the board it is
 * representing and an Appendable for the destination of the view.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState game;
  private Appendable destination;

  /**
   * Used for creating a TriangleSolitaireTextView with a given game state and a default
   * destination.
   * @param game The game to be represented in the view.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game) {
    if (game == null) {
      throw new IllegalArgumentException("provided model is null.");
    }
    this.game = game;
    this.destination = System.out;
  }

  /**
   * Used for creating a TriangleSolitaireTextView with a given game state and destination.
   * @param game The game to be represented in the view.
   * @param destination The destination of the view output.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game, Appendable destination) {
    if (game == null || destination == null) {
      throw new IllegalArgumentException("provided model or destination is null");
    }
    this.game = game;
    this.destination = destination;
  }

  /**
   * Creates and returns a String version of the board in which O represents a marble,
   * _ represents an empty slot, and " " represents an invalid position.
   * @return A string version of the board.
   */
  @Override
  public String toString() {
    String view = "";
    for (int i = 0; i < game.getBoardSize(); i++) {
      for (int j = 0; j < game.getBoardSize(); j++) {
        if (game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          view = view + "";
        }
        else if (game.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          if (j == 0) {
            for (int count = 0; count < (game.getBoardSize()) - i; count++) {
              if (count == ((game.getBoardSize()) - i) - 1) {
                view = view + "O";
                if (i == j) {
                  view = view + "\n";
                }
              }
              else {
                view = view + " ";
              }
            }
          } else if (j == i) {
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
            for (int count = 0; count < (game.getBoardSize()) - i; count++) {
              if (count == ((game.getBoardSize()) - i) - 1) {
                view = view + "_";
                if (i == j) {
                  view = view + "\n";
                }
              }
              else {
                view = view + " ";
              }
            }

          } else if (j == i) {
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

  /**
   * Appends the current board state to the Appendable as output.
   * @throws IOException if there is an error with the Appendable.
   */
  @Override
  public void renderBoard() throws IOException {
    destination.append(this.toString());
  }

  /**
   * Appends the given message to the Appendable as output.
   * @param message the message to be transmitted
   * @throws IOException if there is an error with the Appendable.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    destination.append(message);
  }
}
