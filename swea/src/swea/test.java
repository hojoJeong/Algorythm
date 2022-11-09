package swea;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class test {
	static int n;
	static int max = 0;
	static int[] t, p;
	
	static int cal(int a) {
		if(a <= 1) {
			return a;
		}
		
		return cal(n-1)*n;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		
		
		
		System.out.println(cal(n));
	}
	
}