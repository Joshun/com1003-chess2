package uk.ac.sheffield.aca14jmo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by joshua on 22/04/15.
 */
public class RandomPlayer extends ComputerPlayer {
    public RandomPlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);

    }
    public boolean makeMove() {
        Board board = getBoard();
        Pieces pieces = getPieces();
        int colour = pieces.getPiece(0).getColour();

        ArrayList<Move> allPossibleMoves = getAllPossibleMoves();

    }
}
