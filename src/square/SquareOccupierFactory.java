package square;


/**
 * Factory for creating SquareOccupier objects
 * @author Maciek
 *
 */
public class SquareOccupierFactory {
	
	/**
	 * Method creates SquareOcuppier objects for specified letter
	 * K - King, N - kNight, Q - Queen, R - Rook, B - Bishop
	 * @param letter letter for creating object
	 * @return SquareOccupier object, or null if error
	 */
	public static SquareOccupier create(String letter){
		SquareOccupier ocuppier = null;
		switch(letter){
			case "K":
				ocuppier = new King(0,0);
				break;
			case "N":
				ocuppier = new Knight(0,0);
				break;
			case "Q":
				ocuppier = new Queen(0,0);
				break;
			case "R":
				ocuppier = new Rook(0,0);
				break;
			case "B":
				ocuppier = new Bishop(0,0);
				break;
		}
		return ocuppier;
	}
}
