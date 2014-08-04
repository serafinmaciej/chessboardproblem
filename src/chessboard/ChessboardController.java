package chessboard;
import java.awt.Point;
import java.util.Vector;

import square.SquareOccupier;


public class ChessboardController {
	private Chessboard mChessboard;
	private Vector<ChessboardResult> mResults = new Vector<ChessboardResult>();
	
	public ChessboardController(Chessboard chessboard){
		mChessboard = chessboard;
	}
	
	public void findUniqueConfigsForPieces(Vector<SquareOccupier> pieces){
		//mChessboard.printCurrentChessboard(Chessboard.ATTACKED_CHESSBOARD);
		Vector<SquareOccupier> placedPieces = new Vector<SquareOccupier>();
		SquareOccupier pieceToPlace = pieces.remove(0);
		placedPieces.add(pieceToPlace);
		for(int i = 0; i < mChessboard.getSize(); i++){
			for(int j = 0; j < mChessboard.getSize(); j++){
				pieceToPlace.setPosition(new Point(i,j));
				performCheckFor(placedPieces,pieces);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void performCheckFor(Vector<SquareOccupier> placedPieces, Vector<SquareOccupier> piecesToPlace) {
		//System.out.println("performCheckFor; placedPieces.size():"+placedPieces.size()+" piecesToPlace.size(): "+piecesToPlace.size());
		
		Vector<SquareOccupier> newPiecesToPlace = (Vector<SquareOccupier>) piecesToPlace.clone();
		Vector<SquareOccupier> newPlacedPieces = (Vector<SquareOccupier>) placedPieces.clone();
		
		if(newPiecesToPlace.size() == 0){
			Vector<Point> places = mChessboard.getAvailableSpotsForPieces(placedPieces);
			if(places!= null){
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
					mChessboard.printCurrentChessboard(Chessboard.ATTACKED_CHESSBOARD);
				}
				else{
					//System.out.println("_found duplicate: ");
					//mChessboard.printCurrentChessboard(Chessboard.ATTACKED_CHESSBOARD);
				}
			}
		}
		else{
			Vector<Point> places = mChessboard.getAvailableSpotsForPieces(placedPieces);
			if(places == null){
				//System.out.println("walek, return");
				return;
			}
			SquareOccupier pieceToPlace = newPiecesToPlace.remove(0);
			
			newPlacedPieces.add(pieceToPlace);
			for(int i = 0; i< places.size(); i++){
				pieceToPlace.setPosition(places.get(i));
				performCheckFor(newPlacedPieces, newPiecesToPlace);
			}
		}
		
		
	}
}
