package square;

/**
* Class describing a king
* @author Maciek
*
*/
public class King extends SquareOccupier {
	public King(int x, int y){
		super(x,y);
		mLetter = "K";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		for(int xOffset = -1; xOffset <= 1; xOffset++){
			for(int yOffset = -1; yOffset <=1 ; yOffset++){
				if(isOnBoard(xOffset,yOffset)){
					boolean succesful = markSquare(mPosition.x+xOffset,mPosition.y+yOffset,chessboard);
					if(!succesful){
						return false;
					}
				}
			}
		}
		return true;
	}
	
}