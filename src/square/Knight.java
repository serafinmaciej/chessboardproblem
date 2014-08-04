package square;


public class Knight extends SquareOccupier {
	public Knight(int x, int y){
		super(x,y);
		mLetter = "N";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		for(int i = -2 ; i<=2; i++){
			if(i==0) continue;
			for(int j = -2 ; j<=2; j++){
				if(i==j || i == -j || j==0) continue;
				if(isOnBoard(i,j,chessboard.length)){
					boolean successful = markSquare(mPosition.x+i, mPosition.y+j, chessboard);
					if(!successful){
						return false;
					}
				}
			}
		}
		return true;
	}
}
