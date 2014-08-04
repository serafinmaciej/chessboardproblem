package chessboard;
import java.awt.Point;
import java.util.ArrayList;

import square.SquareOccupier;


public class ChessboardController {
	private Chessboard mChessboard;
	private ArrayList<ChessboardResult> mResults = new ArrayList<ChessboardResult>();
	
	public ChessboardController(Chessboard chessboard){
		mChessboard = chessboard;
	}
	
	public void findUniqueConfigsForPieces(ArrayList<SquareOccupier> pieces){
		ArrayList<SquareOccupier> placedPieces = new ArrayList<SquareOccupier>(1);
		SquareOccupier pieceToPlace = pieces.remove(0);
		placedPieces.add(pieceToPlace);
		for(int i = 0; i < Chessboard.chessboardDimension; i++){
			for(int j = 0; j < Chessboard.chessboardDimension; j++){
				pieceToPlace.setPosition(new Point(i,j));
				performCheckFor(placedPieces,pieces);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void performCheckFor(ArrayList<SquareOccupier> placedPieces, ArrayList<SquareOccupier> piecesToPlace) {
		if(piecesToPlace.size() == 0){
			saveUniqueResult(placedPieces);
		}
		else{
			ArrayList<Point> freeSquares = mChessboard.getNotAttackedSquaresForPieces(placedPieces);
			if(freeSquares == null || freeSquares.isEmpty()){
				freeSquares = null;
				placedPieces.clear();
				piecesToPlace.clear();
				return;
			}
			else{
				//ArrayList<SquareOccupier> newPiecesToPlace = DeepCopier.getDeepCopy(piecesToPlace);
				//ArrayList<SquareOccupier> newPlacedPieces =  DeepCopier.getDeepCopy(placedPieces);
				
				ArrayList<SquareOccupier> newPiecesToPlace = (ArrayList<SquareOccupier>) piecesToPlace.clone();
				ArrayList<SquareOccupier> newPlacedPieces =  (ArrayList<SquareOccupier>) placedPieces.clone();
				SquareOccupier pieceToPlace = newPiecesToPlace.remove(0);
				
				newPlacedPieces.add(pieceToPlace);
				for(int i = 0; i< freeSquares.size(); i++){
					pieceToPlace.setPosition(freeSquares.get(i));
					performCheckFor(newPlacedPieces, newPiecesToPlace);
				}
			}
		}
	}
	
	private void saveUniqueResult(ArrayList<SquareOccupier> placedPieces){
		ArrayList<Point> places = mChessboard.getNotAttackedSquaresForPieces(placedPieces);
		if(places != null){
			ChessboardResult result = new ChessboardResult(placedPieces);
			boolean uniqueResult = true;
			for(int i = 0; i <mResults.size(); i++){
				ChessboardResult storedResult = mResults.get(i);
				if(storedResult.isIdentical(result)){
					uniqueResult = false;
					break;
				}
			}
			if(uniqueResult){
				mResults.add(result);
				//System.out.println("Saving: "+result.toString());
				//System.out.println("found: ");
				mChessboard.printCurrentChessboard();
			}
			//else{
				//System.out.println("_found duplicate: ");
				//mChessboard.printCurrentChessboard(Chessboard.ATTACKED_CHESSBOARD);
			//}
		}
	}
}
