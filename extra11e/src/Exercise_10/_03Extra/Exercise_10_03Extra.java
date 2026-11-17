package Exercise_10._03Extra;

public class Exercise_10_03Extra {
	public static void main(String[] args) {
		EQ board1 = new EQ();
		board1.set(0, 2);
		board1.set(1, 4);
		board1.set(2, 7);
		board1.set(3, 1);
		board1.set(4, 0);
		board1.set(5, 3);
		board1.set(6, 6);
		board1.set(7, 5);
		System.out.println("Is board 1 a correct eight queen placement? "
			+ board1.isSolved());

		EQ board2 = new EQ(new int[] {0, 4, 7, 5, 2, 6, 1, 3});
		if (board2.isSolved()) {
			System.out.println("Eight queens are placed correctly in board 2");
			board2.printBoard();
		}
		else {
			System.out.println("Eight queens are placed incorrectly in board 2");
		}
	}
}

class EQ {
	private final int[] queens;

	public EQ() {
		queens = new int[8];
		for (int i = 0; i < queens.length; i++) {
			queens[i] = -1;
		}
	}

	public EQ(int[] queens) {
		this.queens = queens;
	}

	public int get(int i) {
		return queens[i];
	}

	public void set(int i, int j) {
		queens[i] = j;
	}

	public boolean isSolved() {
		for (int row = 0; row < queens.length; row++) {
			if (queens[row] < 0 || queens[row] >= queens.length) {
				return false;
			}
			for (int previous = 1; previous <= row; previous++) {
				int otherColumn = queens[row - previous];
				if (otherColumn == queens[row]
					|| otherColumn == queens[row] - previous
					|| otherColumn == queens[row] + previous) {
					return false;
				}
			}
		}
		return true;
	}

	public void printBoard() {
		for (int row = 0; row < queens.length; row++) {
			for (int column = 0; column < queens.length; column++) {
				System.out.print(column == queens[row] ? "|X" : "| ");
			}
			System.out.println("| ");
		}
	}
}
