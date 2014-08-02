package square;
import java.awt.Point;

public class EmptySquare extends SquareOccupier {
	public EmptySquare(Point position){
		super(position);
		mLetter = "-";
	}
}
