package utils;

import java.util.ArrayList;

import square.SquareOccupier;

public class DeepCopier {
	public static ArrayList<SquareOccupier> getDeepCopy(ArrayList<SquareOccupier> arrayToCopy){
		ArrayList<SquareOccupier> clonedArray = new ArrayList<SquareOccupier>(arrayToCopy.size());
		
		for(int i = 0; i<arrayToCopy.size();i++){
			clonedArray.add(arrayToCopy.get(i).clone());
		}
		return clonedArray;
	}
}
