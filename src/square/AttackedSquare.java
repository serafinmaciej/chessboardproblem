package square;
import java.awt.Point;


public class AttackedSquare extends SquareOccupier {

	public AttackedSquare(Point position) {
		super(position);
		mLetter = "!";
	}

}
