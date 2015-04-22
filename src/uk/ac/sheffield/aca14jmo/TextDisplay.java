package uk.ac.sheffield.aca14jmo;

public class TextDisplay implements Display {

	private char numberToLetter(int n) {
		return (char)((n-1)+65);
	}

	private void printTopBorder(int min, int max) {
		System.out.print("  ");
		for (int i=min; i<=max; i++) {
			System.out.print(numberToLetter(i) + " ");
		}
		System.out.println();
	}
	public void showPiecesOnBoard(Piece[][] data) {
		printTopBorder(1, 8);
		for (int row=7; row>=0; row--) {
			System.out.print(row+1);
			for (int col=0; col<=7; col++) {
				Piece piece = data[col][row];
				System.out.print(" " + (piece == null ? "-" : piece.getChar()));
			}
			System.out.println();
		}
	}
}
