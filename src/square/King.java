package square;

public class King extends SquareOccupier {
	public King(int x, int y){
		super(x,y);
		mLetter = "K";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <=1 ; j++){
				if(isOnBoard(i,j,chessboard.length)){
					boolean succesful = markSquare(mPosition.x+i,mPosition.y+j,chessboard);
					if(!succesful){
						return false;
					}
				}
			}
		}
		return true;
	}
	
}