package chessboard;
import java.awt.Point;
import java.util.ArrayList;

import square.Bishop;
import square.EmptySquare;
import square.King;
import square.Knight;
import square.Queen;
import square.Rook;
import square.SquareOccupier;


public class Chessboard {
	public static int chessboardDimensionX;
	public static int chessboardDimensionY
	;
	private SquareOccupier [][] mChessboard;

	public Chessboard(int chessboardDimensionX, int chessboardDimensionY){
		Chessboard.chessboardDimensionX = chessboardDimensionX;
		Chessboard.chessboardDimensionY = chessboardDimensionY;
		
		mChessboard = new SquareOccupier[chessboardDimensionX][];
		for(int i = 0; i< chessboardDimensionX; i++){
			mChessboard[i] = new SquareOccupier[chessboardDimensionY];
		}
		reset();
	}
	
	public ArrayList<Point> getNotAttackedSquaresForPieces(ArrayList<SquareOccupier> pieces) {
		boolean successfulPlaceOnBoard = placePiecesOnBoard(pieces);
		if(!successfulPlaceOnBoard){
			return null;
		}
		ArrayList<Point> squares = getNotAttackedSquares();
		return squares;
	}

	public void addRandomPiece(){
		int i = (int) Math.round(Math.random() * (chessboardDimensionX-1));
		int j = (int) Math.round(Math.random() * (chessboardDimensionY-1));
		Rook rook = new Rook(i,j);
		King king = new King(i,j);
		Knight knight = new Knight(i,j);
		Bishop bishop = new Bishop(i, j);
		Queen queen = new Queen(i,j);
		mChessboard[i][j] = bishop;
		printCurrentChessboard();
		boolean successful = bishop.markAttackedSquares(mChessboard);
		
		printCurrentChessboard();
		System.out.println("successful: "+successful);
	}
	
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
