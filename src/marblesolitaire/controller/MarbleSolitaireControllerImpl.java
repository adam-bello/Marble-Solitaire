package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents a text-based controller for marble solitaire, which uses Readable and
 * Appendable to handle user input and output. It can play one game from beginning to end,
 * with an option to quit at any time.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;

  /**
   * Constructs a controller which takes in a model state, a view, and an input form the user.
   * @param model The model to be affected by the controller.
   * @param view the output location for the controller's commands.
   * @param input the input to determine what the controller does.
   * @throws IllegalArgumentException if the model, view, or Readable provided is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view,
                                       Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Must provide model, view, and Readable");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Boolean q = false;
    String fromRow = "";
    String fromCol = "";
    String toRow = "";
    String toCol = "";
    Scanner scan = new Scanner(this.input);

    while (!model.isGameOver() && !q) {
      this.checkOutOfInput(scan);
      this.renderTheBoard();
      this.renderThisMessage("Score: " + model.getScore());

      try {
        while (toCol.equals("") && !q) {
          this.checkOutOfInput(scan);

          if (scan.hasNextInt()) {
            if (fromRow.equals("")) {
              fromRow = String.valueOf(scan.nextInt());
            } else if (fromCol.equals("")) {
              fromCol = String.valueOf(scan.nextInt());
            } else if (toRow.equals("")) {
              toRow = String.valueOf(scan.nextInt());
            } else if (toCol.equals("")) {
              toCol = String.valueOf(scan.nextInt());
            }
          } else if (scan.hasNext()) {
            if (scan.next().equalsIgnoreCase("q")) {
              q = true;
              this.quitMessage();
            } else {
              this.renderThisMessage("Invalid input. Re-enter value.");
            }
          }
        }
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Not enough input");
      }

      if (!q) {
        this.attemptMove(fromRow, fromCol, toRow, toCol);
        fromRow = "";
        fromCol = "";
        toRow = "";
        toCol = "";
      }
    }

    if (!q) {
      this.gameOverMessage();
    }
  }

  private void quitMessage() throws IllegalStateException {
    try {
      view.renderMessage("Game quit!");
      this.view.renderMessage("\n");
      view.renderMessage("State of game when quit:");
      this.view.renderMessage("\n");
      view.renderBoard();
      this.view.renderMessage("\n");
      view.renderMessage("Score: " + model.getScore());
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException(
              "Unable to render final game state or final message");
    }
  }

  private void gameOverMessage() throws IllegalStateException {
    try {
      view.renderMessage("Game over!");
      this.view.renderMessage("\n");
      view.renderBoard();
      this.view.renderMessage("\n");
      view.renderMessage("Score: " + model.getScore());
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to render final game state or final message");
    }
  }

  private void renderTheBoard() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to render board");
    }
  }

  private void renderThisMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to render message");
    }
  }

  private void attemptMove(String fromRow, String fromCol, String toRow, String toCol) {
    try {
      model.move(Integer.parseInt(fromRow) - 1, Integer.parseInt(fromCol) - 1,
              Integer.parseInt(toRow) - 1, Integer.parseInt(toCol) - 1);
    } catch (IllegalArgumentException a) {
      this.renderThisMessage("Invalid move. Play again.");
    }
  }

  private void checkOutOfInput(Scanner scan) {
    if (!scan.hasNext()) {
      throw new IllegalStateException("Readable ran out of input");
    }
  }
}

