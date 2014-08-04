package chessboard;
import java.util.Collections;
import java.util.Vector;

import square.SquareOccupier;


public class ChessboardResult {
	//private Vector<King> kings = new Vector<King>();
	//private Vector<Rook> rooks = new Vector<Rook>();
	Vector<SquareOccupier> mPlacedPieces = new Vector<SquareOccupier>();
	
	public ChessboardResult(Vector<SquareOccupier> placedPieces){
		saveDeepCopiedVector(placedPieces);
	}
	
	private void saveDeepCopiedVector(Vector<SquareOccupier> placedPieces){
		for(int i = 0; i<placedPieces.size();i++){
			mPlacedPieces.add(placedPieces.get(i).clone());
		}
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
	public boolean isIdentical(ChessboardResult result){
		if(mPlacedPieces.size() != result.mPlacedPieces.size()){
			return false;
		}
		else{
			SquareOccupier ourSquareOccupier, thierSquareOccupier;
			
			//System.out.println("our: "+this.toString());
			//System.out.println("their: "+result.toString());
			
			for(int i = 0; i < mPlacedPieces.size(); i++){
				ourSquareOccupier = mPlacedPieces.get(i);
				thierSquareOccupier = result.mPlacedPieces.get(i);
				if(ourSquareOccupier.getClass() != thierSquareOccupier.getClass() || ourSquareOccupier.compareTo(thierSquareOccupier) != 0){
					return false;
				}
			}
		}
		return true;
		
	}
	/*
	private boolean compareKings(ChessboardResult result){
		if(kings.size() != result.kings.size()) return false;
		else{
			for(int i = 0; i < kings.size(); i++){
				King thisKing = 
				if(kings.get(i).compareTo(o))
			}
		}
		
	}
	
	private boolean compareRooks(ChessboardResult result){
		return true;
	}*/
}
