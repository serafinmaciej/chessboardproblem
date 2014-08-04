package chessboard;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import square.SquareOccupier;
import utils.ArrayCopier;

/**
 * Main class responsible for finding actual results
 * @author Maciek
 *
 */
public class ChessboardController {
	private Chessboard mChessboard;

	private long mStartTime;
	private HashMap<String, String> mMap = new HashMap<String, String>();

	private boolean mVerbose = false;
	private int mVerboseOffset;
	
	
	public ChessboardController(Chessboard chessboard){
		mChessboard = chessboard;
	}
	
	/**
	 * Sets verbose mode on
	 * @param verboseOffset board will be printed every verboseOffset chessboard found
	 */
	public void verboseEvery(int verboseOffset){
		mVerbose = true;
		mVerboseOffset = verboseOffset;
	}
	
	/**
	 * Finds configurations in which neither of specified pieces attack each other
	 * @param pieces pieces on board
	 * @return HashMap with results
	 */
	public HashMap<String, String> findUniqueConfigsForPieces(ArrayList<SquareOccupier> pieces){
		mMap = new HashMap<String, String>();
		mStartTime = System.currentTimeMillis();
		ArrayList<SquareOccupier> placedPieces = new ArrayList<SquareOccupier>(1);
		SquareOccupier pieceToPlace = pieces.remove(0);
		placedPieces.add(pieceToPlace);
		for(int i = 0; i < Chessboard.chessboardDimensionX; i++){
			for(int j = 0; j < Chessboard.chessboardDimensionY; j++){
				pieceToPlace.setPosition(i,j);
				performCheckFor(placedPieces,pieces);
			}
		}
		
		long executionTime = System.currentTimeMillis() - mStartTime;
		System.out.println("Found "+mMap.keySet().size() +" configurations in "+executionTime+" ms");
		return mMap;
	}

	
	/**
	 * Method that is supposed to be called recursively. 
	 * At every iteration one piece from piecesToPlace array is placed in good position on-board and moved to placedPieces array
	 * @param placedPieces pieces that are placed on board in good position
	 * @param piecesToPlace pieces that have to be placed
	 */
	private void performCheckFor(ArrayList<SquareOccupier> placedPieces, ArrayList<SquareOccupier> piecesToPlace) {
		if(piecesToPlace.size() == 0){
			saveUniqueResult(placedPieces);
		}
		else{
			ArrayList<Point> freeSquares = mChessboard.getNotAttackedSquaresForPieces(placedPieces);
			if(freeSquares == null || freeSquares.isEmpty()){
				return;
			}
			else{
				ArrayList<SquareOccupier> newPiecesToPlace = ArrayCopier.getShallowCopy(piecesToPlace);
				ArrayList<SquareOccupier> newPlacedPieces = ArrayCopier.getShallowCopy(placedPieces);

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
			
			String key = result.toString();
			String storedValue = mMap.get(key);
			if(storedValue == null){
				mMap.put(key, key);
				if(mVerbose){
					verbose();
				}
			}
			else if(!storedValue.equals(key)){
				System.out.println("!!COLLISION");
				System.out.println("storedValue: "+storedValue+" key: "+key);
			}
		}
	}
	
	private void verbose(){
		if(mMap.keySet().size() % mVerboseOffset == 0){
			System.out.println("Current progress: "+mMap.keySet().size());
			long executionTime = System.currentTimeMillis() - mStartTime;
			System.out.println("Execution time: "+executionTime);
			mChessboard.printCurrentChessboard();
		}
	}
}
