package Exercise_07._03Extra;

public class Exercise_07_03Extra {
	private static final int[] queens = new int[8];
	private static int count = 0;

	public static void main(String[] args) {
		placeQueens(0);
		System.out.println("How many solutions? " + count);
	}

	private static void placeQueens(int row) {
		if (row == 8) {
			count++;
			System.out.println("Solution " + count + ":");
			printBoard();
			return;
		}

		for (int column = 0; column < 8; column++) {
			if (isValid(row, column)) {
				queens[row] = column;
				placeQueens(row + 1);
			}
		}
	}

	private static boolean isValid(int row, int column) {
		for (int i = 0; i < row; i++) {
			if (queens[i] == column
				|| Math.abs(queens[i] - column) == row - i) {
				return false;
			}
		}
		return true;
	}

	private static void printBoard() {
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if (queens[row] == column) {
					System.out.print("|Q|");
				}
				else if (column < queens[row]) {
					System.out.print("| ");
				}
				else {
					System.out.print(" |");
				}
			}
			System.out.println();
		}
	}
}
