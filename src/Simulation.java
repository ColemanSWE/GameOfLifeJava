import model.Board;
import model.BoundedBoard;
import model.CellState;
import model.SimulationRule;
import model.StandardRule;

public class Simulation {
	
	private Board simulationBoard;
	private SimulationRule simulationRule;
	
	// Constructor for Simulation class that creates a board
	public Simulation(Board simulationBoard, SimulationRule simulationRule) {
		this.simulationBoard = simulationBoard;
		this.simulationRule = simulationRule;
	}
	
	// Simulates a "step" in the game by looping through the x and y coordinates
	// and changing the board state as it goes. Also uses the printBoard function
	// to show every step. Can be set to however many turns you want. 
	public void step(int turns) {
		printBoard();
		for (int turn = 0; turn < turns; turn++) {
			Board nextState = simulationBoard.copy();
			
			for (int y = 0; y < simulationBoard.getHeight(); y++) {
				for (int x = 0; x < simulationBoard.getWidth(); x++) {
					CellState newState = simulationRule.getNextState(x, y, simulationBoard);
					nextState.setState(x, y, newState);
				}
			}
			this.simulationBoard = nextState;
			printBoard();
		}
	}
	
	public Board getBoard() {
		return simulationBoard;
	}
	
	// This is used just to replace the CellState variables with actual symbols that can be seen. 
	public String drawState(int x, int y) {
		if (simulationBoard.getState(x, y) == CellState.ALIVE) {
			return "*";
		} else {
			return ":";
		}
	}
	
	// Used to draw the board in console. 
	public void printBoard() {
		System.out.println("---");
		for (int y = 0; y < simulationBoard.getHeight(); y++) {
			String line = "|";
			for (int x = 0; x < simulationBoard.getWidth(); x++) {
				if (simulationBoard.getState(x, y) == CellState.DEAD) {
					simulationBoard.setState(x, y, CellState.DEAD);
					line += drawState(x, y);
				} else {
					simulationBoard.setState(x, y, CellState.ALIVE);
					line += drawState(x, y);
				}
			}
			line += "|";
			System.out.println(line);
		}
		System.out.println("---\n");
	}
	
	// Using these class specifically. 
	public void setAlive(int x, int y) {
		simulationBoard.setState(x, y, CellState.ALIVE);
	}

	public void setDead(int x, int y) {
		simulationBoard.setState(x, y, CellState.DEAD);
	}
	
	public static void main(String[] args) {
		// Setting the board type and size as well as the rules. 
		Board board = new BoundedBoard(10, 10);
		SimulationRule simulationRule = new StandardRule();
		
		// Constructs the simulation using the constructor and type of rules/board
		Simulation simulation = new Simulation(board, simulationRule);
		
		// How one sets the initial alive cells. 
		simulation.setAlive(3, 3);
		simulation.setAlive(4, 3);
		simulation.setAlive(5, 3);
		
		simulation.setAlive(3, 6);
		simulation.setAlive(4, 6);
		simulation.setAlive(5, 6);
		
		simulation.step(10);
	}
}