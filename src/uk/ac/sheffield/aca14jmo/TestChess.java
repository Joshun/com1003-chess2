package uk.ac.sheffield.aca14jmo;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * uk.ac.sheffield.aca14jmo.TestChess.java
 *
 * Class to test core functionality of pieces is correct
 */

public class TestChess  {
	public static boolean sameMoves(ArrayList<Move> m1, ArrayList<Move> m2) {
		return m1.size() == m2.size() && m1.containsAll(m2);
	}

	@Before
	public void setup() {
		// Enable debug console output (comment out to disable)
		DebugLog.enable();
	}

	@Test
	public void testPawn() throws Exception {
		Board b = new Board();

		Pawn p1 = new Pawn(1, 5, PieceCode.WHITE, b);
		Pawn p2 = new Pawn(1, 6, PieceCode.BLACK, b);
		Pawn p3 = new Pawn(2, 6, PieceCode.BLACK, b);

		b.setPosition(p1.getX(), p1.getY(), p1);
		b.setPosition(p2.getX(), p2.getY(), p2);
		b.setPosition(p3.getX(), p3.getY(), p3);

		ArrayList<Move> p1CorrectMoves = new ArrayList<Move>();
		p1CorrectMoves.add(new Move(p1, p1.getX(), p1.getY(), 2, 6, true));

		ArrayList<Move> p1AvailableMoves = p1.availableMoves();
		DebugLog.println("pawn correct moves " + p1CorrectMoves);
		DebugLog.println("pawn available moves " + p1AvailableMoves);
		assertEquals(sameMoves(p1CorrectMoves, p1AvailableMoves), true);
	}

	@Test
	public void testKnight() throws Exception {
		Board b = new Board();

		Knight n1 = new Knight(2, 5, PieceCode.BLACK, b);
		Knight n2 = new Knight(3, 3, PieceCode.BLACK, b);
		Knight n3 = new Knight(0, 6, PieceCode.WHITE, b);

		b.setPosition(n1.getX(), n1.getY(), n1);
		b.setPosition(n2.getX(), n2.getY(), n2);
		b.setPosition(n3.getX(), n3.getY(), n3);

		ArrayList<Move> n1CorrectMoves = new ArrayList<Move>();
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 1, 7, false));
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 3, 7, false));
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 4, 6, false));
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 4, 4, false));
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 0, 4, false));
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 0, 6, true));
		n1CorrectMoves.add(new Move(n1, n1.getX(), n1.getY(), 1, 3, false));

		ArrayList<Move> n1AvailableMoves = n1.availableMoves();
		DebugLog.println("knight correct moves " + n1CorrectMoves);
		DebugLog.println("knight available moves " + n1AvailableMoves);
		assertEquals(sameMoves(n1CorrectMoves, n1AvailableMoves), true);
	}

	@Test
	public void testBishop() throws Exception {
		Board b = new Board();

		Bishop b1 = new Bishop(2, 4, PieceCode.BLACK, b);
		Bishop b2 = new Bishop(3, 5, PieceCode.BLACK, b);
		Bishop b3 = new Bishop(4, 6, PieceCode.WHITE, b);

		b.setPosition(b1.getX(), b1.getY(), b1);
		b.setPosition(b2.getX(), b2.getY(), b2);
		b.setPosition(b3.getX(), b3.getY(), b3);

		ArrayList<Move> b2CorrectMoves = new ArrayList<Move>();
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 1, 7, false));
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 2, 6, false));
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 4, 4, false));
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 4, 6, true));
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 5, 3, false));
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 6, 2, false));
		b2CorrectMoves.add(new Move(b2, b2.getX(), b2.getY(), 7, 1, false));

		ArrayList<Move> b2AvailableMoves = b2.availableMoves();
		DebugLog.println("bishop correct moves " + b2CorrectMoves);
		DebugLog.println("bishop available moves " + b2AvailableMoves);
		assertEquals(sameMoves(b2CorrectMoves, b2AvailableMoves), true);
	}


	@Test
	public void testRook() throws Exception {
		Board b = new Board();

		Rook r1 = new Rook(0, 0, PieceCode.WHITE, b);
		Rook r2 = new Rook(0, 1, PieceCode.WHITE, b);
		Rook r3 = new Rook(1, 0, PieceCode.BLACK, b);

		b.setPosition(r1.getX(), r1.getY(), r1);
		b.setPosition(r2.getX(), r2.getY(), r2);
		b.setPosition(r3.getX(), r3.getY(), r3);

		ArrayList<Move> r1CorrectMoves = new ArrayList<Move>();
		r1CorrectMoves.add(new Move(r1, r1.getX(), r1.getY(), 1, 0, true));

		ArrayList<Move> r1AvailableMoves = r1.availableMoves();
		DebugLog.println("rook correct moves " + r1CorrectMoves);
		DebugLog.println("rook available moves " + r1AvailableMoves);
		assertEquals(sameMoves(r1CorrectMoves, r1AvailableMoves), true);
	}

	@Test
	public void testKing() {
		Board b = new Board();
		
		King k1 = new King(5, 5, PieceCode.WHITE, b);
		King k2 = new King(6, 6, PieceCode.BLACK, b);
		King k3 = new King(4, 5, PieceCode.WHITE, b);

		b.setPosition(k1.getX(), k1.getY(), k1);
		b.setPosition(k2.getX(), k2.getY(), k2);
		b.setPosition(k3.getX(), k3.getY(), k3);

		ArrayList<Move> k1CorrectMoves = new ArrayList<Move>();
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 5, 6, false));
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 6, 6, true));
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 6, 5, false));
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 6, 4, false));
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 5, 4, false));
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 4, 6, false));
		k1CorrectMoves.add(new Move(k1, k1.getX(), k1.getY(), 4, 4, false));

		ArrayList<Move> k1AvailableMoves = k1.availableMoves();
		DebugLog.println("king correct moves " + k1CorrectMoves);
		DebugLog.println("king available moves " + k1AvailableMoves);
		assertEquals(sameMoves(k1CorrectMoves, k1AvailableMoves), true);
	}

	@Test
	public void testQueen() {
		Board b = new Board();

		Queen q1 = new Queen(3, 3, PieceCode.WHITE, b);
		Queen q2 = new Queen(3, 5, PieceCode.WHITE, b);
		Queen q3 = new Queen(2, 4, PieceCode.BLACK, b);

		b.setPosition(q1.getX(), q1.getY(), q1);
		b.setPosition(q2.getX(), q2.getY(), q2);
		b.setPosition(q3.getX(), q3.getY(), q3);

		ArrayList<Move> q1CorrectMoves = new ArrayList<Move>();
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 2, 4, true));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 3, 4, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 4, 4, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 5, 5, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 6, 6, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 7, 7, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 4, 2, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 5, 1, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 6, 0, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 3, 2, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 3, 1, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 3, 0, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 2, 2, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 1, 1, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 0, 0, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 0, 3, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 1, 3, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 2, 3, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 4, 3, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 5, 3, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 6, 3, false));
		q1CorrectMoves.add(new Move(q1, q1.getX(), q1.getY(), 7, 3, false));

		ArrayList<Move> q1AvailableMoves = q1.availableMoves();
		DebugLog.println("queen correct moves " + q1CorrectMoves);
		DebugLog.println("queen available moves " + q1AvailableMoves);
		assertEquals(sameMoves(q1CorrectMoves, q1AvailableMoves), true);
	}

}