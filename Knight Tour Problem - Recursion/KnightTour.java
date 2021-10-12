
class KnightTour {

	final static int SIZE = 8;

	// this method intializes all squares the board the -1
	static void InitializeBoard(int sol[][]) {
		for (int row = 0; row < SIZE; row++) 
			for (int col = 0; col < SIZE; col++) 
				sol[row][col] = -1;
	}


	// this method prints the board
	static void PrintBoard(int sol[][]) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) 
				System.out.print(sol[row][col] + " ");
			System.out.println();
		}
	}


	// this method verifies if the square is the possible move or not
	static boolean isSafe(int row, int col, int sol[][]) {
		return (row >= 0 && row < SIZE && col >= 0 && col < SIZE && sol[row][col] == -1);
	}


	// this method makes the new move and marks the moveCount value in if that move is possible
	// then, recursive call to proceed to the next move
	// until the board is filled (moveCount = 64)
	// if the move is not possible, re-initialize that square and try a different square
	// if possible, return true to the main function
	// if not, state that there is no solution
	public static boolean solveKnightTour(int row, int col, int moveCount, int sol[][], int row_move[], int col_move[]) {
		int nextRow = 0;
		int nextCol = 0;
		// base case, if the board is filled
		if (moveCount == SIZE*SIZE) 
			return true;
		
		// try possible move
		for (int k = 0; k < 8; k++) {
			nextRow = row + row_move[k];
			nextCol = col + col_move[k];
			if (isSafe(nextRow, nextCol, sol)) {
				sol[nextRow][nextCol] = moveCount;
				if (solveKnightTour(nextRow, nextCol, moveCount + 1, sol, row_move, col_move))
					return true;
				else 
					sol[nextRow][nextCol] = -1;
			}

		}
		return false;
	}

	
	// main method
	public static void main(String[] args) {
		// the board, solution
		int[][] sol = new int[SIZE][SIZE];

		// possible row and col for next move
		// the value in each index for 2 following arrays are correspondent
		int[] row_Move = {2,1,-1,-2,-2,-1,1,2};
		int[] col_Move = {1,2,2,1,-1,-2,-2,-1};

		InitializeBoard(sol);

		// first moveCount is 0 in square (0,0)
		sol[0][0] = 0;
		if (solveKnightTour(0,0,1,sol,row_Move,col_Move))
			PrintBoard(sol);
		else	
			System.out.println("No solution!");
	}
}
