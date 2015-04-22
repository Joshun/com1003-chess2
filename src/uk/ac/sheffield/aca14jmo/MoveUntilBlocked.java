package uk.ac.sheffield.aca14jmo;

import java.util.ArrayList;

/**
 * uk.ac.sheffield.aca14jmo.MoveUntilBlocked.java
 *
 * Class to represent pieces that keep moving until another piece blocks their path
 */

public abstract class MoveUntilBlocked extends Piece {
	public MoveUntilBlocked(int pcode, int ix, int iy, int c, Board b){
		super(pcode, ix, iy, c, b);
	}

	public ArrayList<Move> getHorizontalVertical() {
		int currentX = getX();
		int currentY = getY();
		Board b = getBoard();
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		for(int testX=currentX-1; testX>=0; testX--) {
			if (b.occupied(testX, currentY)) {
				// Only a possible move if the colour is different to the current piece (i.e. piece is "takeable")
				if (b.getPiece(testX, currentY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, currentY, true));
				}
				// We don't want to search any further this way as the piece blocks moving any further
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, testX, currentY, false));
			}
		}

		// Search right of piece
		for(int testX=currentX+1; testX<=7; testX++) {
			if (b.occupied(testX, currentY)) {
				if (b.getPiece(testX, currentY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, currentY, true));
				}
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, testX, currentY, false));
			}
		}

		// Vertical moves

		// Search below piece
		for(int testY=currentY-1; testY>=0; testY--) {
			if (b.occupied(currentX, testY)) {
				if (b.getPiece(currentX, testY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, currentX, testY, true));
				}
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, currentX, testY, false));
			}
		}

		// Search above piece
		for(int testY=currentY+1; testY<=7; testY++) {
			if (b.occupied(currentX, testY)) {
				if (b.getPiece(currentX, testY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, currentX, testY, true));
				}
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, currentX, testY, false));
			}
		}

		return possibleMoves;
	}

	public ArrayList<Move> getDiagonal() {
		int currentX = getX();
		int currentY = getY();
		Board b = getBoard();
		ArrayList<Move> possibleMoves = new ArrayList<Move>();

		// Search north-east diagonal
		for (int i=1; (currentX+i<=7) && (currentY+i<=7); i++) {
			int testX = currentX+i;
			int testY = currentY+i;

			if (b.occupied(testX, testY)) {
				// Only a possible move if the colour is different to the current piece (i.e. piece is "takeable")
				if (b.getPiece(testX, testY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, testY, true));
				}
				// We don't want to search any further this way as the piece blocks moving any further
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, testX, testY, false));
			}
		}

		// Search south-west diagonal
		for (int i=1; (currentX-i>=0) && (currentY-i>=0); i++) {
			int testX = currentX-i;
			int testY = currentY-i;

			if (b.occupied(testX, testY)) {
				// Only a possible move if the colour is different to the current piece (i.e. piece is "takeable")
				if (b.getPiece(testX, testY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, testY, true));
				}
				// We don't want to search any further this way as the piece blocks moving any further
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, testX, testY, false));
			}
		}

		// Search north-west diagonal
		for (int i=1; (currentX-i>=0) && (currentY+i<=7); i++) {
			int testX = currentX-i;
			int testY = currentY+i;

			if (b.occupied(testX, testY)) {
				// Only a possible move if the colour is different to the current piece (i.e. piece is "takeable")
				if (b.getPiece(testX, testY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, testY, true));
				}
				// We don't want to search any further this way as the piece blocks moving any further
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, testX, testY, false));
			}
		}

		// Search south-east diagonal
		for (int i=1; (currentX+i<=7) && (currentY-i>=0); i++) {
			int testX = currentX+i;
			int testY = currentY-i;

			if (b.occupied(testX, testY)) {
				// Only a possible move if the colour is different to the current piece (i.e. piece is "takeable")
				if (b.getPiece(testX, testY).getColour() != getColour()) {
					possibleMoves.add(new Move(this, currentX, currentY, testX, testY, true));
				}
				// We don't want to search any further this way as the piece blocks moving any further
				break;
			}
			else {
				possibleMoves.add(new Move(this, currentX, currentY, testX, testY, false));
			}
		}

		return possibleMoves;
	}
}
