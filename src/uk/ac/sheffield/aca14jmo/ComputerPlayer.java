package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.ComputerPlayer.java
 *
 * Abstract class to represent a computer player providing common functionality
 */
public abstract class ComputerPlayer extends Player {
    private String name;

    public ComputerPlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);
        name = n;
    }

    public abstract boolean makeMove();

    protected static int getRandom(int max) {
        return (int)(Math.random() * max);
    }

    public ArrayList<Move> getAllPossibleMoves() {
        Board board = getBoard();
        Pieces pieces = getPieces();
        int colour = pieces.getPiece(0).getColour();

        ArrayList<Move> possibleMoves = new ArrayList<>();
        ArrayList<Move> pieceMoves = new ArrayList<>();

        int numPieces = pieces.getNumPieces();
        for(int i=0; i<numPieces; i++) {
            pieceMoves = pieces.getPiece(i).availableMoves();
            if (pieceMoves != null) {
                possibleMoves.addAll(pieceMoves);
            }
        }
        DebugLog.println("[ComputerPlayer] Possible moves: " + possibleMoves);

        return possibleMoves;
    }

    protected boolean hasWon(Move m) {
        if (m.takesPiece()) {
            int takePieceX = m.getEndX();
            int takePieceY = m.getEndY();
            Piece takePiece = getBoard().getPiece(takePieceX, takePieceY);

            if (takePiece instanceof King) {
                System.out.println(name + " has won.");
            }

            return takePiece instanceof King;
        }
        else {
            return false;
        }
    }
}
