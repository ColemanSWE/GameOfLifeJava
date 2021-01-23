package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;
import model.BoundedBoard;
import model.CellState;
import model.SimulationRule;
import model.StandardRule;

class StandardRuleTest {
	
	private Board board;
	private SimulationRule simulationRule;
	
	@BeforeEach
	void setUp() {
		board = new BoundedBoard(3, 3);
		simulationRule = new StandardRule();
	}
	
	@Test
	void getNextState_alive_oneNeighbor() {
		board.setState(1, 1, CellState.ALIVE);
		board.setState(0, 0, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
	
	@Test
	void getNextState_alive_twoNeighbor() {
		board.setState(1, 1, CellState.ALIVE);
		board.setState(2, 2, CellState.ALIVE);
		board.setState(1, 2, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.ALIVE, nextState);
	}
	
	@Test
	void getNextState_alive_threeNeighbor() {
		board.setState(1, 1, CellState.ALIVE);
		board.setState(0, 2, CellState.ALIVE);
		board.setState(2, 1, CellState.ALIVE);
		board.setState(2, 2, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.ALIVE, nextState);
	}
	
	@Test
	void getNextState_alive_fourNeighbor() {
		board.setState(1, 1, CellState.ALIVE);
		board.setState(0, 2, CellState.ALIVE);
		board.setState(2, 1, CellState.ALIVE);
		board.setState(0, 1, CellState.ALIVE);
		board.setState(1, 0, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
	
	
	@Test
	void getNextState_alive_eightNeighbor() {
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				board.setState(x, y, CellState.ALIVE);
			}
		}
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
	
	@Test
	void getNextState_dead_noNeighbor() {
		board.setState(1, 1, CellState.DEAD);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
	
	@Test
	void getNextState_dead_twoNeighbor() {
		board.setState(1, 1, CellState.DEAD);
		board.setState(2, 2, CellState.ALIVE);
		board.setState(1, 2, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
	
	@Test
	void getNextState_dead_threeNeighbor() {
		board.setState(1, 1, CellState.DEAD);
		board.setState(2, 2, CellState.ALIVE);
		board.setState(1, 2, CellState.ALIVE);
		board.setState(0, 1, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.ALIVE, nextState);
	}
	
	@Test
	void getNextState_dead_fourNeighbor() {
		board.setState(1, 1, CellState.DEAD);
		board.setState(2, 2, CellState.ALIVE);
		board.setState(1, 2, CellState.ALIVE);
		board.setState(0, 1, CellState.ALIVE);
		board.setState(0, 2, CellState.ALIVE);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
	
	@Test
	void getNextState_dead_eightNeighbor() {
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				board.setState(x, y, CellState.ALIVE);
			}
		}
		
		board.setState(1, 1, CellState.DEAD);
		
		CellState nextState = simulationRule.getNextState(1, 1, board);
		
		assertEquals(CellState.DEAD, nextState);
	}
}
