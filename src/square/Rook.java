package square;


public class Rook extends SquareOccupier {
	public Rook(int x, int y){
		super(x,y);
		mLetter = "R";
	}
	
	@Override
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
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
