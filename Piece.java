
public class Piece{
	
	private boolean isFire;
	private Board b;
	private int xpos;
	private int ypos;
	private String type;
	private boolean king = false;
	private boolean jump = false;
	private int moveX = 0; private int moveY = 0;

	public Piece(boolean isFire0, Board b0, int x0, int y0, String type0) {
		isFire = isFire0;
		b = b0;
		xpos = x0;
		ypos = y0;
		type = type0;
	}

	public boolean isFire() {
		return isFire;
	}

	public int side() {
		if (isFire == true) {
			return 0;
		}
		else {
			return 1;
		}
	}

	public boolean isKing() {
		if (king == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isBomb() {
		if (type == "bomb") {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isShield() {
		if (type == "shield") {
			return true;
		}
		else {
			return false;
		}
	}

	public void move(int x,int y) {
			if ((((int)x-(int)xpos) == 2) || (((int)x-(int)xpos) == -2)) {
				b.remove((x+xpos)/2,(y+ypos)/2); jump = true;
				if (isBomb()) {
					if (b.pieceAt(x+1,y+1)!=null){
						if (!b.pieceAt(x+1,y+1).isShield()) {
							b.remove(x+1,y+1);
						}
					}
					if (b.pieceAt(x+1,y-1)!=null){
						if (!b.pieceAt(x+1,y-1).isShield()) {
							b.remove(x+1,y-1);
						}
					}
					if (b.pieceAt(x-1,y+1)!=null){
						if (!b.pieceAt(x-1,y+1).isShield()) {
							b.remove(x-1,y+1);
						}
					}
					if (b.pieceAt(x-1,y-1)!=null){
						if (!b.pieceAt(x-1,y-1).isShield()) {
							b.remove(x-1,y-1);
						}
					}
				}
			}
			b.place(this,x,y);
			b.remove(xpos,ypos);
			xpos = x; ypos = y;
			if (jump && isBomb()) {
				b.remove(x,y);
			}

		if ((y == 7) && isFire) {
			king = true;
		}
		else if ((y == 0) && !isFire) {
			king = true;
		}
	}

	public boolean hasCaptured() {
		return jump;
	}

	public void doneCapturing() {
		jump = false;
	}

}