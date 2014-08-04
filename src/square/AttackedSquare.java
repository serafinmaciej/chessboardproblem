package square;

/**
 * Set when piece attacks a square
 * @author Maciek
 *
 */
public class AttackedSquare extends SquareOccupier {

	public AttackedSquare(int x, int y){
		super(x,y);
		mLetter = "-";
	}

}
