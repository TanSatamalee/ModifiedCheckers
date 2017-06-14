import static org.junit.Assert.*;

import org.junit.Test;

public class TestPiece {

	@Test
    public void testPlaceAndPlaceAt() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,2,4,"pawn");
        b.place(p,1,1);
 		assertEquals(null,b.pieceAt(0,0));
 		b.place(p,0,0);
 		assertEquals(p,b.pieceAt(0,0));
    }

    @Test
    public void testCanSelectAndSelect() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,1,1,"pawn");
        b.place(p,1,1);
        assertTrue(b.canSelect(1,1));
        b.select(1,1);
        assertFalse(b.canSelect(3,3));
    }

    @Test
    public void testRemove() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,1,1,"pawn");
        b.place(p,1,1);
        Piece X = b.remove(1,1);
        assertEquals(X,p);
        assertEquals(null,b.pieceAt(1,1));
    }

    @Test
    public void testCanEndTurnAndEndTurn() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,1,1,"pawn");
        b.place(p,1,1);
        b.place(p,0,0);
        b.select(1,1);
        b.select(2,2);
        assertTrue(b.canEndTurn());
    }

    @Test
    public void testMove() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,1,1,"pawn");
        b.place(p,1,1);
        p.move(2,2);
        assertEquals(p,b.pieceAt(2,2));
        assertEquals(null, b.pieceAt(1,1));
    }  

    @Test
    public void testCapture() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,1,1,"pawn");
        Piece s = new Piece(false,b,2,2,"shield");
        b.place(p,1,1);
        b.place(s,2,2);
        p.move(3,3);
        assertEquals(p,b.pieceAt(3,3));
        assertEquals(null,b.pieceAt(2,2));
        assertEquals(null,b.pieceAt(1,1));
    }

    @Test
    public void testWinner() {
        Board b = new Board(true);
        Piece p = new Piece(true,b,1,1,"pawn");
        b.place(p,1,1);
        assertEquals("Fire",b.winner());
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestPiece.class);
    }
}