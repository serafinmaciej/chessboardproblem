package square;

import chessboard.Chessboard;

/**
* Class describing a queen
* @author Maciek
*
*/
public class Queen extends SquareOccupier {
	public Queen(int x, int y){
		super(x,y);
		mLetter = "Q";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		int offsetStart = -mPosition.x;
		int offsetEnd = Chessboard.chessboardDimensionX - mPosition.x;
		
		//bishop
		for(int offset = offsetStart; offset<=offsetEnd; offset++){
			if(isOnBoard(offset,offset)){
				boolean successful = markSquare(mPosition.x+offset, mPosition.y+offset, chessboard);
				if(!successful){
					return false;
				}
			}
			if(isOnBoard(offset,-offset)){
				boolean successful = markSquare(mPosition.x+offset, mPosition.y-offset, chessboard);
				if(!successful){
					return false;
				}
			}
		}
		
		//rook
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
