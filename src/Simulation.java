
public class Simulation {
	
	int width; 
	int height;
	int [] [] board;
	
	// Constructor for Simulation class
	public Simulation(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new int[width][height];
	}
	
	// Prints board with the given height and width using nested for loops.
	public void printBoard() {
		System.out.println("---");
		for (int y = 0; y < height; y++) {
			String line = "|";
			for (int x = 0; x < width; x++) {
				if (this.board[x][y] == 0) {
					line += ".";
				} else {
					line += "*";
				}
			}
			line += "|";
			System.out.println(line);
		}
		System.out.println("---\n");
	}
	
	// set the alive spaces in the initial state using coordinates.
	public void setAlive(int x, int y) {
		this.board[x][y] = 1;
	}
	
	// same except for dead spaces
	public void setDead(int x, int y) {
		this.board[x][y] = 0;
	}
	
	// Counts the amount of squares around the current square that are alive. 
	public int countAliveNeighbors(int x, int y) {
		int count = 0;
		
		count += this.board[x - 1][y - 1];
		count += this.board[x][y - 1];
		count += this.board[x + 1][y - 1];
		
		count += this.board[x - 1][y];
		count += this.board[x + 1][y];
		
		count += this.board[x - 1][y + 1];
		count += this.board[x][y - 1];
		count += this.board[x + 1][y + 1];
		
		return count;
	}
	
	public void step() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int aliveNeighbors = countAliveNeighbors(x, y);
				
				if (this.board[x][y] == 1) {
					if (aliveNeighbors < 2) {
						this.board[x][y] = 0;
					}
				} else {
					
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation(8, 5);
		
		simulation.setAlive(2, 2);
		simulation.setAlive(3, 2);
		simulation.setAlive(4, 2);
		
		simulation.printBoard();
		
		System.out.println(simulation.countAliveNeighbors(3, 2));
	}
}
