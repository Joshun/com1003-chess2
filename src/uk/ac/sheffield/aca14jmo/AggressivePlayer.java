package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.AggressivePlayer.java
 *
 * Class to represent an aggressive player making decisions based on piece value
 */
public class AggressivePlayer extends ComputerPlayer {
    public AggressivePlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);
    }

    private int moveValue(Move m) {
        if (! m.takesPiece()) {
            return 0;
        }
        else {
            Board b = getBoard();
            Piece p = b.getPiece(m.getEndX(), m.getEndY());
            int value = PieceCode.charToInt(p.getChar());
            return value;
        }
    }

    public boolean makeMove() {
        ArrayList<Move> allPossibleMoves = getAllPossibleMoves();

        int size = allPossibleMoves.size();

        Move moveToMake = null;
        Move takeMove = null;
        int highestValue = 0;

        // Loop through possible moves and find the one with the highest value
        for (int i=0; i<size; i++) {
            Move m = allPossibleMoves.get(i);
            int mValue = moveValue(m);
            if (m.takesPiece() && mValue > highestValue) {
                highestValue = mValue;
                takeMove = m;
            }
        }

        // If no moves to take a piece were found, choose a random move
        if (takeMove == null) {
            int moveIndex = getRandom(size);
            moveToMake = allPossibleMoves.get(moveIndex);
        }
        else {
            moveToMake = takeMove;
        }

        moveToMake.execute(getOpponent());

        return hasWon(moveToMake);
    }
}
