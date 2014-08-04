package chessboard;
import java.util.ArrayList;
import java.util.Collections;

import square.SquareOccupier;
import utils.DeepCopier;


public class ChessboardResult {
	ArrayList<SquareOccupier> mPlacedPieces = null;
	
	public ChessboardResult(ArrayList<SquareOccupier> placedPieces){
		mPlacedPieces = DeepCopier.getDeepCopy(placedPieces);
		Collections.sort(mPlacedPieces);
	}
	
	public boolean isIdentical(ChessboardResult result){
		if(this.mPlacedPieces.size() != result.mPlacedPieces.size()){
			return false;
		}
		else{
			SquareOccupier thisSquareOccupier, passedSquareOccupier;
			for(int i = 0; i < mPlacedPieces.size(); i++){
				thisSquareOccupier = this.mPlacedPieces.get(i);
				passedSquareOccupier = result.mPlacedPieces.get(i);
				if(!thisSquareOccupier.isIdentical(passedSquareOccupier)){
					return false;
				}
			}
		}
		return true;
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
