package square;
import java.awt.Point;
import java.lang.reflect.Constructor;

import chessboard.Chessboard;

public class SquareOccupier implements Comparable<SquareOccupier> {
	protected String mLetter = "";
	protected Point mPosition = new Point(0,0);
	
	public SquareOccupier(int x, int y){
		mPosition = new Point(x,y);
	}
	
	public String getLetter(){
		return mLetter;
	}
	
	public void setPosition(int x, int y){
		mPosition.x = x;
		mPosition.y = y;
	}
	
	public void setPosition(Point position){
		mPosition = position;
	}
	
	public Point getPosition(){
		return mPosition;
	}

	public boolean markAttackedSquares(SquareOccupier[][] chessboard) {
		return true;
	}

	public boolean isIdentical(SquareOccupier occupier){
		boolean identicalClass = (this.getClass() == occupier.getClass());
		boolean identicalPosition = (this.compareTo(occupier) == 0);
		return identicalClass && identicalPosition;
	}
	
	public SquareOccupier clone(){
		SquareOccupier clonedObject = null;
		try {
			Class<? extends SquareOccupier> currentClass = getClass();
			Constructor<? extends SquareOccupier> constructor = currentClass.getConstructor(int.class, int.class);
			clonedObject = (SquareOccupier) constructor.newInstance(mPosition.x, mPosition.y);;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return clonedObject;
		
	}
	
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
