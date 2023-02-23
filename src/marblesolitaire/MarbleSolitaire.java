package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * A class for playing a game of marble solitaire. Can be configured by specifying the type
 * of board and optionally the size, and location of empty slot.
 */
public final class MarbleSolitaire {

  /**
   * The main method for playing a game of MarbleSolitaire with a given configuration.
   * @param args The commands to determine the style of the game.
   */
  public static void main(String[] args) {
    String board;
    int size;
    int row;
    int col;
    MarbleSolitaireModel model;
    MarbleSolitaireView view;

    // (I tried implementing this using System.in as my readable and found that things
    // became buggy. I opted to use other types of Readables with which I've had no problems.)

    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("English")) {
        model = new EnglishSolitaireModel();
        view = new MarbleSolitaireTextView(model, System.out);
        Readable reader = new StringReader("q");
        MarbleSolitaireControllerImpl controller =
                new MarbleSolitaireControllerImpl(model, view, reader);
        controller.playGame();
      } else if (args[0].equalsIgnoreCase("European")) {
        model = new EuropeanSolitaireModel();
        view = new MarbleSolitaireTextView(model, System.out);
        Readable reader = new StringReader("q");
        MarbleSolitaireControllerImpl controller =
                new MarbleSolitaireControllerImpl(model, view, reader);
        controller.playGame();
      } else if (args[0].equalsIgnoreCase("Triangle")) {
        model = new TriangleSolitaireModel();
        view = new TriangleSolitaireTextView(model, System.out);
        Readable reader = new StringReader("3 3 1 1 q");
        MarbleSolitaireControllerImpl controller =
                new MarbleSolitaireControllerImpl(model, view, reader);
        controller.playGame();
      }
    } else if (args.length == 3) {
      if (args[1].equalsIgnoreCase("-size")) {
        size = Integer.parseInt(args[2]);

        if (args[0].equalsIgnoreCase("English")) {
          model = new EnglishSolitaireModel(size);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("European")) {
          model = new EuropeanSolitaireModel(size);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("Triangle")) {
          model = new TriangleSolitaireModel(size);
          view = new TriangleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("3 3 1 1 q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        }
      }
    } else if (args.length == 4) {
      if (args[1].equalsIgnoreCase("-hole")) {
        row = Integer.parseInt(args[2]);
        col = Integer.parseInt(args[3]);

        if (args[0].equalsIgnoreCase("English")) {
          model = new EnglishSolitaireModel(row, col);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("European")) {
          model = new EuropeanSolitaireModel(row, col);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("Triangle")) {
          model = new TriangleSolitaireModel(row, col);
          view = new TriangleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        }
      }
    } else if (args.length == 6) {
      if (args[1].equalsIgnoreCase("-size")) {
        size = Integer.parseInt(args[2]);
        row = Integer.parseInt(args[4]);
        col = Integer.parseInt(args[5]);

        if (args[0].equalsIgnoreCase("English")) {
          model = new EnglishSolitaireModel(size, row, col);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("European")) {
          model = new EuropeanSolitaireModel(size, row, col);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("Triangle")) {
          model = new TriangleSolitaireModel(size, row, col);
          view = new TriangleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        }
      } else if (args[1].equalsIgnoreCase("-hole")) {
        size = Integer.parseInt(args[5]);
        row = Integer.parseInt(args[2]);
        col = Integer.parseInt(args[3]);

        if (args[0].equalsIgnoreCase("English")) {
          model = new EnglishSolitaireModel(size, row, col);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new InputStreamReader(System.in);
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("European")) {
          model = new EuropeanSolitaireModel(size, row, col);
          view = new MarbleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        } else if (args[0].equalsIgnoreCase("Triangle")) {
          model = new TriangleSolitaireModel(size, row, col);
          view = new TriangleSolitaireTextView(model, System.out);
          Readable reader = new StringReader("q");
          MarbleSolitaireControllerImpl controller =
                  new MarbleSolitaireControllerImpl(model, view, reader);
          controller.playGame();
        }
      }
    }
  }
}
