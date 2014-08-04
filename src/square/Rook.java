package square;

import chessboard.Chessboard;

/**
* Class describing a rook
* @author Maciek
*
*/
public class Rook extends SquareOccupier {
	public Rook(int x, int y){
		super(x,y);
		mLetter = "R";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		int biggerDimension = Chessboard.chessboardDimensionX > Chessboard.chessboardDimensionY ? Chessboard.chessboardDimensionX : Chessboard.chessboardDimensionY;
		for(int i = 0 ; i<biggerDimension; i++){
			boolean successful = true;
			if(i != mPosition.y && i < Chessboard.chessboardDimensionY){
				successful = successful && markSquare(mPosition.x, i, chessboard);
				
			}
			if(i != mPosition.x && i < Chessboard.chessboardDimensionX){
				successful = successful && markSquare(i, mPosition.y, chessboard);
			}
			if(!successful){
				return false;
			}
		}
		return true;
	}
}
