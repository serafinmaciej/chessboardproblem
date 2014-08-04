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

	private int getFieldNo(){
		int result = mPosition.y * Chessboard.chessboardDimension + mPosition.x;
		//System.out.println("("+mPosition.x+","+mPosition.y+"): "+result);
		return result;
	}
	
	@Override
	public int compareTo(SquareOccupier o) {
		return getFieldNo() - o.getFieldNo();
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
	
	protected boolean isOnBoard(int i, int j, int chessboardSize){
		boolean isOnChessboardX = mPosition.x + i < chessboardSize && mPosition.x + i >= 0;
		boolean isOnChessboardY = mPosition.y + j < chessboardSize && mPosition.y + j >= 0;
		boolean isCurrentSquare = (i == 0 && j == 0);
		return isOnChessboardX && isOnChessboardY && !isCurrentSquare;
	}
	
	protected boolean markSquare(int x, int y, SquareOccupier[][] chessboard){
		if(chessboard[x][y] instanceof EmptySquare || chessboard[x][y] instanceof AttackedSquare){
			chessboard[x][y] = new AttackedSquare(x, y);
			return true;
		}
		return false;
	}
}
