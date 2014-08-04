package square;

import chessboard.Chessboard;


public class Queen extends SquareOccupier {
	public Queen(int x, int y){
		super(x,y);
		mLetter = "Q";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		int offsetStart = -mPosition.x;
		int offsetEnd = Chessboard.chessboardDimension - mPosition.x;
		
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
		for(int i = 0 ; i<chessboard.length; i++){
			boolean successful = true;
			if(i != mPosition.y){
				successful = successful && markSquare(mPosition.x, i, chessboard);
				
			}
			if(i != mPosition.x){
				successful = successful && markSquare(i, mPosition.y, chessboard);
			}
			if(!successful){
				return false;
			}
		}
		return true;
	}
}
