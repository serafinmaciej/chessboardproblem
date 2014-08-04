import java.sql.Date;
import java.util.ArrayList;

import square.Knight;
import square.Rook;
import square.SquareOccupier;
import chessboard.Chessboard;
import chessboard.ChessboardController;


public class Main {
	public static void main (String args[]){
		long startTime = System.currentTimeMillis();
		
		Chessboard chessboard = new Chessboard(4);
		ChessboardController controller = new ChessboardController(chessboard);
		
		ArrayList<SquareOccupier> pieces = new ArrayList<SquareOccupier>();
		pieces.add(new Knight(0,0));
		pieces.add(new Knight(0,0));
		pieces.add(new Knight(0,0));
		pieces.add(new Knight(0,0));
		pieces.add(new Rook(0,0));
		pieces.add(new Rook(0,0));
		controller.findUniqueConfigsForPieces(pieces);
		
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("Execution time: "+executionTime);
	}
}
