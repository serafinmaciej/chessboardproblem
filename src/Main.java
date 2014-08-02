
public class Main {
	public static void main (String args[]){
		System.out.println("test");
		Chessboard chessboard = new Chessboard(4);
		chessboard.printCurrentChessboard(Chessboard.ATTACKED_CHESSBOARD);
		chessboard.addRandomPiece();
		chessboard.printCurrentChessboard(Chessboard.ATTACKED_CHESSBOARD);
	}
}
