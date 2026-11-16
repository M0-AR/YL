// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_20._08;

import java.util.*;

public class Exercise_20_08 {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);


		List<Integer> lotteryList = new ArrayList<>();
		List<Integer> guessList = new ArrayList<>();


		lotteryList.add((int)(Math.random() * 10));
		lotteryList.add((int)(Math.random() * 10));
		lotteryList.add((int)(Math.random() * 10));


		System.out.print("Enter a three-digit number: ");
		int guess = input.nextInt();


		guessList.add(guess / 100);
		int remainingDigits = guess % 100;
		guessList.add(remainingDigits / 10);
		guessList.add(remainingDigits % 10);


		System.out.print("The lottery number is ");
		for (Integer e: lotteryList) {
			System.out.print(e);
		}
		System.out.println();



		if (guessList.equals(lotteryList)) {
			System.out.println("Exact match: you win $10,000");
			System.exit(0);
		}



		if (guessList.containsAll(lotteryList)) {
			System.out.println("All digits match: you win $3,000");
			System.exit(0);
		}


		Collections.sort(lotteryList);
		Collections.sort(guessList);



		List<Integer> list1 = Arrays.asList(lotteryList.get(0), lotteryList.get(1));
		List<Integer> list2 = Arrays.asList(lotteryList.get(0), lotteryList.get(2));
		List<Integer> list3 = Arrays.asList(lotteryList.get(1), lotteryList.get(2));
		if (guessList.containsAll(list1) ||
			 guessList.containsAll(list2) ||
			 guessList.containsAll(list3)) {
			System.out.println("Two digits match: you win $2,000");
		}
	}
}
