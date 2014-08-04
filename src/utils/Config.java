package utils;

import java.util.ArrayList;

import square.SquareOccupier;

/**
 * Simple C-style class for storing parsed input data
 * @author Maciek
 *
 */
public class Config {
	/**
	 * M - x board dimension
	 */
	public int xDimen;
	
	/**
	 * N - y board dimension
	 */
	public int yDimen;
	
	/**
	 * Specifies whether to verbose output or not
	 */
	public boolean verbose;
	
	/**
	 * Specifies verbose output
	 */
	public int verboseOffset;
	
	/**
	 * Specifies pieces to be put on-board
	 */
	public ArrayList<SquareOccupier> pieces;
}
