package square;
import java.awt.Point;
import java.lang.reflect.Constructor;

import chessboard.Chessboard;

/**
* Parent-class describing a single square on board
* @author Maciek
*
*/
public class SquareOccupier implements Comparable<SquareOccupier> {
	protected String mLetter = "";
	protected Point mPosition = new Point(0,0);
	
	public SquareOccupier(int x, int y){
		mPosition = new Point(x,y);
	}
	
	/**
	 * Returns a letter connected with this square occupier
	 * @return
	 */
	public String getLetter(){
		return mLetter;
	}

	/**
	 * Sets on-board position
	 * @param x x position
	 * @param y y position
	 */
	public void setPosition(int x, int y){
		mPosition.x = x;
		mPosition.y = y;
	}
	
	/**
	 * Sets on-board position
	 * @param position position
	 */
	public void setPosition(Point position){
		mPosition = position;
	}
	
	/**
	 * Returns current square occupier position
	 * @return the position
	 */
	public Point getPosition(){
		return mPosition;
	}

	/**
	 * Method marks squares that are attacked by this square ocuppier
	 * Method to override in each extending class
	 * Method returns false if there is an error in board - current piece is attacking other
	 * @param chessboard 
	 * @return true if square marking went OK, false if error
	 */
	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		return true;
	}
	
	/**
	 * Method using reflection in order to clone this object
	 */
	public SquareOccupier clone(){
		SquareOccupier clonedObject = null;
		try {
			Class<? extends SquareOccupier> currentClass = getClass();
			Constructor<? extends SquareOccupier> constructor = currentClass.getConstructor(int.class, int.class);
			clonedObject = (SquareOccupier) constructor.newInstance(mPosition.x, mPosition.y);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return clonedObject;
	}
	
	/**
	 * Method for implementing Comparable interface
	 */
	@Override
	public int compareTo(SquareOccupier o) {
		return getFieldNo() - o.getFieldNo();
	}
	
	protected boolean isOnBoard(int xOffset, int yOffset){
		boolean isOnChessboardX = mPosition.x + xOffset < Chessboard.chessboardDimensionX && mPosition.x + xOffset >= 0;
		boolean isOnChessboardY = mPosition.y + yOffset < Chessboard.chessboardDimensionY && mPosition.y + yOffset >= 0;
		boolean isCurrentSquare = (xOffset == 0 && yOffset == 0);
		return isOnChessboardX && isOnChessboardY && !isCurrentSquare;
	}
	
	protected boolean markSquare(int x, int y, SquareOccupier[][] chessboard){
		if(chessboard[x][y] instanceof EmptySquare || chessboard[x][y] instanceof AttackedSquare){
			chessboard[x][y] = new AttackedSquare(x, y);
			return true;
		}
		return false;
	}
	
	private int getFieldNo(){
		int result = mPosition.y * Chessboard.chessboardDimensionX + mPosition.x;
		return result;
	}
}
