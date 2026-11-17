package Exercise_03._05Extra;

public class Exercise_03_05Extra {
	public static void main(String[] args) {
		int cardNumber = (int)(Math.random() * 52);
		int suit = cardNumber / 13;
		int rank = cardNumber % 13;

		String rankName;
		if (rank == 0) {
			rankName = "Ace";
		}
		else if (rank == 10) {
			rankName = "Jack";
		}
		else if (rank == 11) {
			rankName = "Queen";
		}
		else if (rank == 12) {
			rankName = "King";
		}
		else {
			rankName = String.valueOf(rank + 1);
		}

		String suitName;
		if (suit == 0) {
			suitName = "Spades";
		}
		else if (suit == 1) {
			suitName = "Hearts";
		}
		else if (suit == 2) {
			suitName = "Diamonds";
		}
		else {
			suitName = "Clubs";
		}

		System.out.println("The card is " + rankName + " of " + suitName);
	}
}
