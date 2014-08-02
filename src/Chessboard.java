import java.awt.Point;

import square.EmptySquare;
import square.Rook;
import square.SquareOccupier;


public class Chessboard {
	public static final String SIMPLE_CHESSBOARD = "SIMPLE_CHESSBOARD";
	public static final String ATTACKED_CHESSBOARD = "ATTACKED_CHESSBOARD";
	
	private SquareOccupier [][] mChessboard;
	private SquareOccupier [][] mAttackedChessboard;

	private int mChessboardDimension;
	
	public Chessboard(int chessboardDimension){
		mChessboardDimension = chessboardDimension;
		
		mChessboard = new SquareOccupier[chessboardDimension][];
		for(int i = 0; i< chessboardDimension; i++){
			mChessboard[i] = new SquareOccupier[chessboardDimension];
		}
		
		mAttackedChessboard = new SquareOccupier[chessboardDimension][];
		for(int i = 0; i< chessboardDimension; i++){
			mAttackedChessboard[i] = new SquareOccupier[chessboardDimension];
		}
		reset();
	}
	
	public void printCurrentChessboard(String chessboardToPrint){
		if(chessboardToPrint.equals(SIMPLE_CHESSBOARD)){
			printCurrentChessboard(mChessboard);
		}
		else if(chessboardToPrint.equals(ATTACKED_CHESSBOARD)){
			printCurrentChessboard(mAttackedChessboard);
		}
	}
	
	public void addRandomPiece(){
		
		int i = (int) Math.round(Math.random() * (mChessboardDimension-1));
		int j = (int) Math.round(Math.random() * (mChessboardDimension-1));
		Rook rook = new Rook(new Point(i,j));
		mChessboard[i][j] = rook;
		mAttackedChessboard[i][j] = rook;
		printCurrentChessboard(SIMPLE_CHESSBOARD);
		rook.markAttackedSquares(mAttackedChessboard);
	}
	
	public void reset(){
		reset(mChessboard);
		reset(mAttackedChessboard);
	}
	
	public void printCurrentChessboard(SquareOccupier [][] chessboardToPrint){
		for(int i = 0; i<mChessboardDimension; i++){
			for(int j = 0; j<mChessboardDimension; j++){
				String squareText = chessboardToPrint[i][j].getLetter()+" ";
				System.out.print(squareText);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void reset(SquareOccupier [][] chessboardToReset){
		for(int i = 0; i<mChessboardDimension; i++){
			for(int j = 0; j<mChessboardDimension; j++){
				chessboardToReset[i][j] = new EmptySquare(new Point(i,j));
			}
		}
	}
}
