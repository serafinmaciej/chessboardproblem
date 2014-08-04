package utils;

import java.util.ArrayList;

import square.SquareOccupier;

/**
 * Helper class for copying array with SquareOccupiers
 * @author Maciek
 *
 */
public class ArrayCopier {
	/**
	 * Class returns deep copy of array
	 * @param arrayToCopy array to be cloned
	 * @return deep copy
	 */
	public static ArrayList<SquareOccupier> getDeepCopy(ArrayList<SquareOccupier> arrayToCopy){
		ArrayList<SquareOccupier> clonedArray = new ArrayList<SquareOccupier>(arrayToCopy.size());
		
		for(int i = 0; i<arrayToCopy.size();i++){
			clonedArray.add(arrayToCopy.get(i).clone());
		}
		return clonedArray;
	}
	
	/**
	 * Class returns shallow copy of array
	 * @param arrayToCopy array to be cloned
	 * @return shallow copy
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<SquareOccupier> getShallowCopy(ArrayList<SquareOccupier> arrayToCopy){
		ArrayList<SquareOccupier> clonedArray = (ArrayList<SquareOccupier>) arrayToCopy.clone();
		return clonedArray;
	}
}