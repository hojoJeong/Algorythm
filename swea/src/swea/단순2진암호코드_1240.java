package swea;

import java.util.*;
import java.io.*;
public class 단순2진암호코드_1240 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[] coderule = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
		int t = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int ans = 0;
			String code = "";
			for(int i=0; i<N; i++) {
				String temp = br.readLine();
				if(code.equals("")) {
					for(int j=M-1; j>=0; j--) {
						if(temp.charAt(j) -'0' == 1) {
							code = temp.substring(j-55, j+1);
							break;
						}
					}
				}
				
			}
			int sumodd = 0;
			int sumeven = 0;
			for(int j=0; j<code.length(); j+=7) {
				String temp = code.substring(j, j+7);
				for(int k=0; k<coderule.length; k++) {
					if(temp.equals(coderule[k])) {
						if(j%2 == 0) {
							sumodd += k;
						}
						else if(j%2 == 1) {
							sumeven += k;
						}
					}
				}
			}
			
			if((sumodd*3+sumeven)%10 == 0) {
				for(int j=0; j<code.length(); j+=7) {
					String temp = code.substring(j, j+7);
					for(int k=0; k<coderule.length; k++) {
						if(temp.equals(coderule[k])) ans+=k;
					}
				}
			} else ans = 0;
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}
