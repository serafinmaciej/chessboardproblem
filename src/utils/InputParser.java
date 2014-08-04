package utils;

import java.util.ArrayList;

import square.SquareOccupier;
import square.SquareOccupierFactory;

/**
 * Class responsible for handling user input
 * @author Maciek
 *
 */
public class InputParser {
	/**
	 * Method parses passed arguments and returns Config object if parsing was successful, or null if unsuccessful
	 * @param args input arguments
	 * @return Config object, or null if error
	 */
	public Config parse(String[] args){
		Config config = null;
		try{
			String M = args[0];
			String N = args[1];
			int xDimen = Integer.parseInt(M);
			int yDimen = Integer.parseInt(N);
			
			ArrayList<SquareOccupier> pieces = new ArrayList<SquareOccupier>();
			
			boolean verbose = false;
			int verboseOffset = -1;
			for(int i = 2; i<args.length; i++){
				SquareOccupier occupier = SquareOccupierFactory.create(args[i]);
				if(occupier != null){
					pieces.add(occupier);
				}
				else if(args[i].equalsIgnoreCase("-v")){
					verboseOffset = Integer.parseInt(args[i+1]);
					if(verboseOffset > 0){
						verbose = true;
					}
					break;
				}
				else{
					return null;
				}
			}
			
			if(pieces.size() > 0){
				config = new Config();
				config.pieces = pieces;
				config.verbose = verbose;
				config.verboseOffset = verboseOffset;
				config.xDimen = xDimen;
				config.yDimen = yDimen;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return config;
	}
}
