import java.util.Vector;

import square.Knight;
import square.Rook;
import square.SquareOccupier;
import chessboard.Chessboard;
import chessboard.ChessboardController;


public class Main {
	public static void main (String args[]){
		Chessboard chessboard = new Chessboard(4);
		ChessboardController controller = new ChessboardController(chessboard);
		
		Vector<SquareOccupier> pieces = new Vector<SquareOccupier>();
		pieces.add(new Knight(0,0));
		pieces.add(new Knight(0,0));
		pieces.add(new Knight(0,0));
		pieces.add(new Knight(0,0));
		pieces.add(new Rook(0,0));
		pieces.add(new Rook(0,0));
		controller.findUniqueConfigsForPieces(pieces);
		
		//chessboard.addRandomPiece();
	}
}
