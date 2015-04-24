package uk.ac.sheffield.aca14jmo;

/**
 * uk.ac.sheffield.aca14jmo
 *
 * Class to represent a single move made by either player
 */

public class Move {
	private Piece piece;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private boolean takesPiece;

	public Move(Piece piece, int startX, int startY, int endX, int endY, boolean takesPiece) {
		this.piece = piece;
		this.startX = startX;
		this.startY = startY;
		this.endY = endY;
		this.endX = endX;
		this.takesPiece = takesPiece;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	public boolean takesPiece() {
		return takesPiece;
	}

	public void execute(Player opponent) {
		Pieces opponentPieces = opponent.getPieces();
		Board b = piece.getBoard();
		// Remove from opponent's pieces
		if (takesPiece) {
			Piece opponentsPiece = b.getPiece(endX, endY);
			opponentPieces.delete(opponentsPiece);
		}
		piece.setPosition(endX, endY);
		// Set new coordinates to point to piece object
		b.setPosition(endX, endY, piece);
		// Remove from old coordinates
		b.remove(startX, startY);

	}

	public boolean equals(Object obj2) {
		Move m2 = (Move)obj2;
		return this.startX == m2.getStartX() && this.startY == m2.getStartY()
		 && this.endX == m2.getEndX() && this.endY == m2.getEndY()
		 && this.piece == m2.piece && this.takesPiece == m2.takesPiece();
	}

	public String toString() {
		return "[sx:" + startX + "][sy:" + startY + "][ex:" + endX + "][ey:" + endY + "]" + "[tp:" + takesPiece + "]";
	}
}