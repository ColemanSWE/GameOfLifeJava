import model.Board;
import model.CellState;
import model.SimulationRule;

public class Simulation {
	
	private Board simulationBoard;
	private SimulationRule simulationRule;
	
	// Constructor for Simulation class that creates a board
	public Simulation(Board simulationBoard, SimulationRule simulationRule) {
		this.simulationBoard = simulationBoard;
		this.simulationRule = simulationRule;
	}
	
	// Simulates a "step" in the game by looping through the x and y coordinates
	// and changing the board state as it goes.
	public void step() {
		Board nextState = simulationBoard.copy();
		
		for (int y = 0; y < simulationBoard.getHeight(); y++) {
			for (int x = 0; x < simulationBoard.getWidth(); x++) {
				CellState newState = simulationRule.getNextState(x, y, simulationBoard);
				nextState.setState(x, y, newState);
			}
		}
		
		this.simulationBoard = nextState;
	}
	
	public Board getBoard() {
		return simulationBoard;
	}
}
