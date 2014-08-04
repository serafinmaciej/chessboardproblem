package square;

/**
* Class describing an empty square, on which neither of pieces is placed and neither is attacking
* @author Maciek
*
*/
public class EmptySquare extends SquareOccupier {
	public EmptySquare(int x, int y){
		super(x,y);
		mLetter = "-";
	}
}
