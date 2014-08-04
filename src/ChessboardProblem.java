import java.util.HashMap;

import chessboard.Chessboard;
import chessboard.ChessboardController;
import utils.Config;
import utils.InputParser;

/**
 * Main class for chessboard problem
 * @author Maciek
 *
 */
public class ChessboardProblem {
	
	public static void main (String args[]){
		if(args.length < 3){
			printHelp();
		}
		else{
			InputParser parser = new InputParser();
			Config config = parser.parse(args);
			if(config == null){
				printHelp();
			}
			else{
				Chessboard chessboard = new Chessboard(config.xDimen, config.yDimen);
				ChessboardController controller = new ChessboardController(chessboard);
				if(config.verbose){
					controller.verboseEvery(config.verboseOffset);
				}
				
				long startTime = System.currentTimeMillis();
				HashMap<String, String> resultsMap = controller.findUniqueConfigsForPieces(config.pieces);
				System.out.println("Found "+resultsMap.keySet().size() +" configurations.");
				printElapsedTime(startTime);
			}
		}
	}
	
	private static void printElapsedTime(long startTime){
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("Execution time: "+executionTime);
	}
	
	private static void printHelp(){
		System.out.println("Usage: java -jar ChessboardProblem.jar M N [pieces] [optional arguments]");
		System.out.println("Available pieces: K - king, R - rook, N - knight, B - bishop, Q - queen");
		System.out.println();
		System.out.println("Optional arguments: ");
		System.out.println("-v number: verbose, print every number\'th chessboard");
		System.out.println();
		System.out.println("Example usages: ");
		System.out.println("java -jar ChessboardProblem.jar 3 3 K K R -v 1");
		System.out.println("java -jar ChessboardProblem.jar 4 4 R R N N N N");
		System.out.println("java -jar ChessboardProblem.jar 7 7 K K Q Q B B N -v 5000");
	}
}
