package programmers;

import java.util.*;
import java.io.*;

public class 연속부분수열합의개수 {
	static int[] elements = { 1, 1, 4, 7, 9 };
	static int answer = 0;

	static Set<Integer> set;

	public static void main(String[] args) {
		
		Set<Integer> set = new TreeSet<>();
		int length = 1;
		for (int i = 0; i < elements.length; i++) {
			int temp = 0;
			for (int j = i; j < i + length; j++) {
				temp += elements[j % 5];
			}
			set.add(temp);
			if(i == elements.length-1 && length < elements.length) {
				i = -1;
				length++;
			}
		}
		answer += set.size();

		System.out.println(answer);
	}
}
