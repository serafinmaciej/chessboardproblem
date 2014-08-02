package square;
import java.awt.Point;


public class Rook extends SquareOccupier {
	public Rook(Point position){
		super(position);
		mLetter = "R";
	}
	
	@Override
	public void markAttackedSquares(SquareOccupier[][] chessboard) {
		for(int i = 0 ; i<chessboard.length; i++){
			if(chessboard[mPosition.x][i] != this){
				chessboard[mPosition.x][i] = new AttackedSquare(new Point(mPosition.x,i));
			}
			if(chessboard[i][mPosition.y] != this){
				chessboard[i][mPosition.y] = new AttackedSquare(new Point(i, mPosition.y));
			}
		}
	}
}
