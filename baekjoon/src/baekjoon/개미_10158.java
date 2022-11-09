package baekjoon;

import java.util.*;
import java.io.*;

public class °³¹Ì_10158 {
	static int w, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());

		p += t % (w * 2);
		q += t % (h * 2);
		if (p > w)
			p = Math.abs(w * 2 - p);
		if (q > h)
			q = Math.abs(h * 2 - q);
		
		System.out.printf("%d %d", p, q);
	}

}
