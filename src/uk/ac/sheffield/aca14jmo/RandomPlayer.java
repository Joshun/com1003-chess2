package uk.ac.sheffield.aca14jmo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.RandomPlayer.java
 *
 * Class to represent a player that randomly picks a move from all possible moves
 */
public class RandomPlayer extends ComputerPlayer {
    public RandomPlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);
    }

    public boolean makeMove() {
        // Declare an arraylist for all possible moves RandomPlayer could make
        ArrayList<Move> allPossibleMoves = getAllPossibleMoves();

        int size = allPossibleMoves.size();
        int randomIndex = getRandom(size);
        Move selectedMove = allPossibleMoves.get(randomIndex);

        boolean win = hasWon(selectedMove);
        selectedMove.execute(getOpponent());

        // Player hasn't won (yet)
        return win;
    }
}
