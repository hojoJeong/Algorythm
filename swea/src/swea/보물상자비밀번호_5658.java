package swea;

import java.util.*;
import java.io.*;
public class 보물상자비밀번호_5658 {
	static String[] codetemp = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
	static ArrayList<String> num16, code;
	static ArrayList<Integer> result;
	static Set<String> set;
	static int n, k, ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=t; tc++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			num16 = new ArrayList<>();
			set = new HashSet<>();
			result =new ArrayList<>();
			code = new ArrayList<>();
			ans = 0;
			
			String str = br.readLine();
			for(int i=0; i<str.length(); i++) {
				num16.add(String.valueOf(str.charAt(i)));
			}
			
			for(int i=0; i<codetemp.length; i++) {
				code.add(codetemp[i]);
			}
			
			//최초 한 번 넣어줌
			addValueToSet();
			
			for(int i=0; i<n/4-1; i++) {
				rotation();
				addValueToSet();
			}
			
			
			change16to10();
			Collections.sort(result, Collections.reverseOrder());
			
			System.out.printf("#%d %d\n", tc, result.get(k-1));
		}
	}
	
	//한 칸씩 밀어줌
	static void rotation() {
		String temp = num16.get(num16.size()-1);
		for(int i=num16.size()-1; i>0; i--) {
			num16.set(i, num16.get(i-1));
		}
		num16.set(0, temp);
	}
	
	static void addValueToSet() {
		for(int i=0; i<num16.size(); i+=n/4) {
			String temp = "";
			for(int j=i; j<i+n/4; j++) {
				temp += num16.get(j);
			}
			set.add(temp);
		}
	}
	
	static void change16to10() {
		Iterator<String> it = set.iterator();

		for(int i=0; i<set.size(); i++) {
			String temp = it.next();
			int resulttemp = 0;
			for(int j=0; j<temp.length(); j++) {
				resulttemp += code.indexOf(String.valueOf(temp.charAt(j))) * (int)(Math.pow(16, temp.length()-j-1));
			}
			result.add(resulttemp);
		}
	}
}
