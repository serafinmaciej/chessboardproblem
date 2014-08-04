import java.util.ArrayList;

import square.Bishop;
import square.King;
import square.Knight;
import square.Queen;
import square.SquareOccupier;
import chessboard.Chessboard;
import chessboard.ChessboardController;


public class ChessboardProblem {
	public static void main (String args[]){
		long startTime = System.currentTimeMillis();
		
		Chessboard chessboard = new Chessboard(7);
		ChessboardController controller = new ChessboardController(chessboard);
		
		ArrayList<SquareOccupier> pieces = new ArrayList<SquareOccupier>();
		pieces.add(new King(0,0));
		pieces.add(new King(0,0));
		pieces.add(new Queen(0,0));
		pieces.add(new Queen(0,0));
		pieces.add(new Bishop(0,0));
		pieces.add(new Bishop(0,0));
		pieces.add(new Knight(0,0));
		controller.findUniqueConfigsForPieces(pieces);
		
		//chessboard.addRandomPiece();
		//chessboard.printCurrentChessboard();
		
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("Execution time: "+executionTime);
	}
}
