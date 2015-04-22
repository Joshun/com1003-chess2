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

    private int getRandom(int max) {
        return (int)(Math.random() * max);
    }

    public boolean makeMove() {
        Board board = getBoard();
        Pieces pieces = getPieces();
        int colour = pieces.getPiece(0).getColour();

        // Declare an arraylist for all possible moves RandomPlayer could make
        ArrayList<Move> allPossibleMoves = getAllPossibleMoves();

        int size = allPossibleMoves.size();
        int randomIndex = getRandom(size);
        Move selectedMove = allPossibleMoves.get(randomIndex);

        // Player has won if it has taken king
        if (selectedMove.takesPiece()) {
            int takePieceX = selectedMove.getEndX();
            int takePieceY = selectedMove.getEndY();
            Piece takePiece = board.getPiece(takePieceX, takePieceY);

            if (takePiece instanceof King) {
                return true;
            }
        }
        selectedMove.execute(getOpponent());

        // Player hasn't won (yet)
        return false;
    }
}
