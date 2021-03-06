package uk.ac.sheffield.aca14jmo;/*
 * uk.ac.sheffield.aca14jmo.Player.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* uk.ac.sheffield.aca14jmo.Player.java
*
* Abstract class to represent a chess player
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/

public abstract class Player {

  public static final int BLACK = 0;
  public static final int WHITE = 1;

  private String name;
  private Pieces pieces;
  private Board board;
  private Player opponent;

  public Player (String n, Pieces p, Board b, Player o) {
    name = n;
    pieces = p;
    board = b;
    opponent = o;
  }

  public Board getBoard() {
    return board;
  }

  public Player getOpponent() {
    return opponent;
  }

  public void setOpponent(Player p) {
    opponent = p;
  }

  public Pieces getPieces() {
    return pieces;
  }

  // I added this because player name should be independent of whatever subclass is instantiated
  public String getName() {
    return name;
  }

  public abstract boolean makeMove();
 
  public void deletePiece(Piece p) {
    pieces.delete(p);
  }

  public String toString() {
    return name;
  }

}
