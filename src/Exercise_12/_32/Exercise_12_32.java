// Adapted from jsquared21/Intro-to-Java-Programming (MIT License, Copyright (c) 2017 Jabari H James).
package Exercise_12._32;

import java.util.*;
import java.io.*;

public class Exercise_12_32 {

	public static void main(String[] args) {

		ArrayList<String> boys = new ArrayList<>();
		ArrayList<String> girls = new ArrayList<>();
		ArrayList<String> line = new ArrayList<>();


		print();
		for (int year = 2010; year >= 2001; year--) {
			try {

				java.net.URL url = new java.net.URL(getURL(year));


				Scanner input = new Scanner(url.openStream());


				for (int i = 0; i < 5 && input.hasNext(); i++) {
					readURL(input, line);
					boys.add(line.get(1));
					girls.add(line.get(3));
				}
			}
			catch (java.net.MalformedURLException ex) {
				System.out.println("Invalid URL");
			}
			catch (java.io.IOException ex) {
				System.out.println("I/O Errors: no such file");
			}

			print(year, boys, girls);


			boys.clear();
			girls.clear();
		}
	}


	public static void print(int year, ArrayList<String> b, ArrayList<String> g) {
		System.out.printf("%5d  ", year);
		for (int i = 0; i < 5; i++) {
			System.out.print(g.get(i));
			print(g.get(i));
		}
		for (int i = 0; i < 5; i++) {
			System.out.print(b.get(i) + "  ");
			print(b.get(i));
		}
		System.out.println();
	}


	public static void print(String str) {
		int space = 10 - str.length();
		for (int j = 0; j < space; j++) {
				System.out.print(" ");
		}
	}


	public static void print() {
		System.out.println(
		"--------------------------------------------------------" +
		"-------------------------------------------------------------\n" +
		" Year  Rank 1    Rank 2    Rank 3    Rank 4    Rank 5    " +
		"Rank 1      Rank 2      Rank 3      Rank 4      Rank 5\n" +
		"--------------------------------------------------------" +
		"-------------------------------------------------------------");
	}


	public static void readURL(Scanner input, ArrayList<String> list) {
			for (int i = 0; i < 5; i++) {
				list.add(i, input.next());
			}
	}


	public static String getURL(int year) {
		return "http://www.cs.armstrong.edu/liang/data/babynamesranking" +
				year + ".txt";
	}
}
