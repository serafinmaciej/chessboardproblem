package square;

import chessboard.Chessboard;


public class Bishop extends SquareOccupier {
	public Bishop(int x, int y){
		super(x,y);
		mLetter = "B";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		int offsetStart = -mPosition.x;
		int offsetEnd = Chessboard.chessboardDimensionX - mPosition.x;
		
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
		return true;
	}
}
