package square;
import java.awt.Point;

public class SquareOccupier {
	protected String mLetter = "";
	protected Point mPosition = new Point(0,0);
	
	public SquareOccupier(Point position){
		mPosition = position;
	}
	
	public String getLetter(){
		return mLetter;
	}
	
	public void setPosition(Point position){
		mPosition = position;
	}
	
	public Point getPosition(){
		return mPosition;
	}

	public void markAttackedSquares(SquareOccupier[][] chessboard) {
	}
}
