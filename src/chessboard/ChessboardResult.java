package chessboard;
import java.util.ArrayList;
import java.util.Collections;

import square.SquareOccupier;
import utils.ArrayCopier;

/**
 * Class wrapping chessboard result
 * Chessboard result = set of sorted pieces
 * @author Maciek
 *
 */
public class ChessboardResult {
	ArrayList<SquareOccupier> mPlacedPieces = null;
	
	public ChessboardResult(ArrayList<SquareOccupier> placedPieces){
		mPlacedPieces = ArrayCopier.getDeepCopy(placedPieces);
		Collections.sort(mPlacedPieces);
	}
	
	@Override
	public String toString(){
		String placedPiecesString = "";
		SquareOccupier ourSquareOccupier;
		
		for(int i = 0; i< mPlacedPieces.size(); i++){
			ourSquareOccupier = mPlacedPieces.get(i);
			placedPiecesString += ourSquareOccupier.getLetter()+" ("+ourSquareOccupier.getPosition().x+","+ourSquareOccupier.getPosition().y+") ";
		}
		return placedPiecesString;
	}
}
