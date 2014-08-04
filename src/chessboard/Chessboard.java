package chessboard;
import java.awt.Point;
import java.util.Vector;

import square.EmptySquare;
import square.King;
import square.Knight;
import square.Rook;
import square.SquareOccupier;


public class Chessboard {
	public static final String SIMPLE_CHESSBOARD = "SIMPLE_CHESSBOARD";
	public static final String ATTACKED_CHESSBOARD = "ATTACKED_CHESSBOARD";
	
	public static int chessboardDimension;
	
	private SquareOccupier [][] mChessboard;
	private SquareOccupier [][] mAttackedChessboard;

	
	
	public Chessboard(int chessboardDimension){
		this.chessboardDimension = chessboardDimension;
		
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
	
	public int getSize(){
		return chessboardDimension;
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
		int i = (int) Math.round(Math.random() * (chessboardDimension-1));
		int j = (int) Math.round(Math.random() * (chessboardDimension-1));
		Rook rook = new Rook(i,j);
		King king = new King(i,j);
		Knight knight = new Knight(i,j);
		mChessboard[i][j] = knight;
		mAttackedChessboard[i][j] = knight;
		printCurrentChessboard(SIMPLE_CHESSBOARD);
		knight.markAttackedSquares(mAttackedChessboard);
		printCurrentChessboard(ATTACKED_CHESSBOARD);
	}
	
	public void reset(){
		reset(mChessboard);
		reset(mAttackedChessboard);
	}
	
	private void printCurrentChessboard(SquareOccupier [][] chessboardToPrint){
		for(int i = 0; i<chessboardDimension; i++){
			for(int j = 0; j<chessboardDimension; j++){
				String squareText = chessboardToPrint[i][j].getLetter()+" ";
				System.out.print(squareText);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void reset(SquareOccupier [][] chessboardToReset){
		for(int i = 0; i<chessboardDimension; i++){
			for(int j = 0; j<chessboardDimension; j++){
				chessboardToReset[i][j] = new EmptySquare(i,j);
			}
		}
	}

	public Vector<Point> getAvailableSpotsForPieces(Vector<SquareOccupier> pieces) {
		reset(mAttackedChessboard);
		boolean successfulSpotSelection = true;
		for(SquareOccupier piece : pieces){
			mAttackedChessboard[piece.getPosition().x][piece.getPosition().y] = piece;
			successfulSpotSelection = successfulSpotSelection && piece.markAttackedSquares(mAttackedChessboard);
		}
		if(!successfulSpotSelection){
			return null;
		}
		Vector<Point> points = new Vector<Point>();
		for(int i = 0; i<chessboardDimension; i++){
			for(int j = 0; j<chessboardDimension; j++){
				if(mAttackedChessboard[i][j].getLetter().equals("-")){
					points.add(new Point(i,j));
				}
			}
		}
		//printCurrentChessboard(mAttackedChessboard);
		return points;
	}
}
