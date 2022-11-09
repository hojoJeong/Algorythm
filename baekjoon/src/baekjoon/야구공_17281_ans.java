package baekjoon;

import java.util.*;
import java.io.*;
public class �߱���_17281_ans {

	static int N, ans = -1;
	static boolean[] selected = new boolean[10];
	static int[] players = new int[10];
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		arr = new int[N][10];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=9;j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		// 1�� ������ 4�� Ÿ�ڷ� �̸� ���� 
		players[4] = 1;
		selected[4]=true;
		
		permutation(2);
		System.out.println(ans);

	}
	
	// Ÿ�� ���ϱ�
	private static void permutation(int cnt) {
		
		if(cnt==10) {
			// ������ �������� ���� ����
			ans = Math.max(ans, game());
			return;
		}
		
		for(int i=1;i<=9;i++) {
			if(!selected[i]) {
				selected[i] = true;
				players[i] = cnt;
				permutation(cnt+1);
				selected[i] = false;
			}
		}
	}
	
	// ���� ����
	private static int game() {
		int start = 1;
		int score = 0;
		
		for(int i=0;i<N;i++) {
			
			// �� ���ڵ��� ��ġ�� �����ϱ� ���� �迭( �ƿ��� 0�� �ε����� ���� )
			int[] point = {0,0,0,0,0};
			
			// �ƿ��� 3���� �Ǳ� ������ ����
			while(point[0]<3) {
				run(point,arr[i][players[start]]);
				if(start==9) {
					start=1;
				}else {
					start++;
				}
			}
			
			// �� �̴��� ������ ���� ������ score�� ����
			score += point[4];
		}
		return score;
	}
	
	// ��� ���ڵ� �̵�
	private static void run(int[] point, int n) {
		
		for(int i=0;i<n;i++) {
			// Ȩ���� ���� ���ڵ��� ���� point[4]�� ������ = ����
			point[4]+=point[3];
			point[3]=point[2];
			point[2]=point[1];
			point[1]=0;
		}
		// ������ �����ִ� ���ڵ��� �̵���Ų �Ŀ� ���� ���� ģ ������ ��ġ�� ����
		point[n]++;
	}
}

