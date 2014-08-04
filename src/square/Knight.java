package square;

/**
* Class describing a knight
* @author Maciek
*
*/
public class Knight extends SquareOccupier {
	public Knight(int x, int y){
		super(x,y);
		mLetter = "N";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		for(int xOffset = -2 ; xOffset<=2; xOffset++){
			if(xOffset==0) continue;
			for(int yOffset = -2 ; yOffset<=2; yOffset++){
				if(ignoreOffset(xOffset, yOffset)){
					continue;
				}
				else if(isOnBoard(xOffset,yOffset)){
					boolean successful = markSquare(mPosition.x+xOffset, mPosition.y+yOffset, chessboard);
					if(!successful){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private boolean ignoreOffset(int xOffset, int yOffset){
		return xOffset==yOffset || xOffset == -yOffset || yOffset==0;
	}
}
