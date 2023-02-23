package cs3500.marblesolitaire.controller;

/**
 *  Represents the controller for a game of marble solitaire. It can be used
 *  to play one game from beginning to end.
 */
public interface MarbleSolitaireController {

  /**
   * Used for playing a game of marble solitaire from beginning to end.
   */
  void playGame() throws IllegalStateException;
}
