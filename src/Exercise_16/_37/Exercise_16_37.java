package Exercise_16._37;

public class Exercise_16_37 {
  public static class TicTacToe {
    private final char[][] board = new char[3][3];

    public TicTacToe() {
      for (int row = 0; row < 3; row++) {
        for (int column = 0; column < 3; column++) {
          board[row][column] = ' ';
        }
      }
    }

    public boolean move(int row, int column, char player) {
      if (row < 0 || row >= 3 || column < 0 || column >= 3) {
        return false;
      }
      if (player != 'X' && player != 'O') {
        return false;
      }
      if (board[row][column] != ' ') {
        return false;
      }
      board[row][column] = player;
      return true;
    }

    public char winner() {
      for (int i = 0; i < 3; i++) {
        if (board[i][0] != ' ' && board[i][0] == board[i][1]
            && board[i][1] == board[i][2]) {
          return board[i][0];
        }
        if (board[0][i] != ' ' && board[0][i] == board[1][i]
            && board[1][i] == board[2][i]) {
          return board[0][i];
        }
      }
      if (board[0][0] != ' ' && board[0][0] == board[1][1]
          && board[1][1] == board[2][2]) {
        return board[0][0];
      }
      if (board[0][2] != ' ' && board[0][2] == board[1][1]
          && board[1][1] == board[2][0]) {
        return board[0][2];
      }
      return ' ';
    }
  }

  public static void main(String[] args) {
    TicTacToe game = new TicTacToe();
    check(game.move(0, 0, 'X'));
    check(game.move(1, 1, 'X'));
    check(game.move(2, 2, 'X'));
    check(game.winner() == 'X');
    check(!game.move(0, 0, 'O'));
    System.out.println("Exercise_16_37 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
