package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * Created by joshua on 22/04/15.
 */
public abstract class ComputerPlayer extends Player{
    private boolean hasWon = false;
    public ComputerPlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);

    }

    public abstract boolean makeMove();

    public ArrayList<Move> getAllPossibleMoves() {
        Board board = getBoard();
        Pieces pieces = getPieces();
        int colour = pieces.getPiece(0).getColour();

        ArrayList<Move> possibleMoves = new ArrayList<>();
        ArrayList<Move> pieceMoves = new ArrayList<>();

        int numPieces = pieces.getNumPieces();
        for(int i=0; i<numPieces; i++) {
            pieceMoves = pieces.getPiece(i).availableMoves();
            possibleMoves.addAll(pieceMoves);
        }
        return possibleMoves;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setWin() {
        hasWon = true;
    }
}
