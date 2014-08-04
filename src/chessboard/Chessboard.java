package chessboard;
import java.awt.Point;
import java.util.ArrayList;

import square.EmptySquare;
import square.SquareOccupier;


/**
 * Class handling chessboard. Allows to get list of not attacked squares with list of pieces specified
 * Also allows to print current chesboard
 * @author Maciek
 *
 */
public class Chessboard {
	public static int chessboardDimensionX;
	public static int chessboardDimensionY;
	
	private SquareOccupier [][] mChessboard;

	/**
	 * Constructor, specify M and N as input
	 * @param chessboardDimensionX x dimension - M
	 * @param chessboardDimensionY y dimension - N
	 */
	public Chessboard(int chessboardDimensionX, int chessboardDimensionY){
		Chessboard.chessboardDimensionX = chessboardDimensionX;
		Chessboard.chessboardDimensionY = chessboardDimensionY;
		
		mChessboard = new SquareOccupier[chessboardDimensionX][];
		for(int i = 0; i< chessboardDimensionX; i++){
			mChessboard[i] = new SquareOccupier[chessboardDimensionY];
		}
		reset();
	}
	
	/**
	 * Method returns list of squares that are not attacked by either of specified pieces
	 * Returns null if there is an error - e.g some of specified pieces attack each other
	 * @param pieces list of pieces on chessboard
	 * @return list of free squares, or null if error
	 */
	public ArrayList<Point> getNotAttackedSquaresForPieces(ArrayList<SquareOccupier> pieces) {
		boolean successfulPlaceOnBoard = placePiecesOnBoard(pieces);
		if(!successfulPlaceOnBoard){
			return null;
		}
		ArrayList<Point> squares = getNotAttackedSquares();
		return squares;
	}
	
	/**
	 * Prints last specified chessboard
	 */
	public void printCurrentChessboard(){
		for(int i = 0; i<chessboardDimensionX; i++){
			for(int j = 0; j<chessboardDimensionY; j++){
				String squareText = mChessboard[i][j].getLetter()+" ";
				System.out.print(squareText);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void reset(){
		for(int i = 0; i<chessboardDimensionX; i++){
			for(int j = 0; j<chessboardDimensionY; j++){
				mChessboard[i][j] = new EmptySquare(i,j);
			}
		}
	}
	
	private boolean placePiecesOnBoard(ArrayList<SquareOccupier> pieces){
		reset();
		boolean successfulPlaceOnBoard = true;
		for(SquareOccupier piece : pieces){
			mChessboard[piece.getPosition().x][piece.getPosition().y] = piece;
			successfulPlaceOnBoard = successfulPlaceOnBoard && piece.markAttackedSquares(mChessboard);
			if(!successfulPlaceOnBoard){
				break;
			}
		}
		return successfulPlaceOnBoard;
	}
	
	private ArrayList<Point> getNotAttackedSquares(){
		ArrayList<Point> squares = new ArrayList<Point>();
		for(int i = 0; i<chessboardDimensionX; i++){
			for(int j = 0; j<chessboardDimensionY; j++){
				if(mChessboard[i][j] instanceof EmptySquare){
					squares.add(new Point(i,j));
				}
			}
		}
		return squares;
	}
}
