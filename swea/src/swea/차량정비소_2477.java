package swea;

import java.util.*;
import java.io.*;

public class 차량정비소_2477 {
	static int N, M, K, A, B, ans;
	static ArrayList<Customer> customer;
	static ArrayList<Desk> reception, repair;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			System.out.println();
			System.out.println("N : " + N + " M : " + M + " K: " + K + " A : " + A + " B : " + B);
			ans = 0;

			customer = new ArrayList<>();
			reception = new ArrayList<>();
			repair = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int time = Integer.parseInt(st.nextToken());
				reception.add(new Desk(time, 0));
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int time = Integer.parseInt(st.nextToken());
				repair.add(new Desk(time, 0));
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				int tk = Integer.parseInt(st.nextToken());
				customer.add(new Customer(tk, -1, 0, -1, 0, 0));
			}

			search(0, 0, 0,0);
			System.out.println();
			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	static void search(int time, int completecus, int recep1, int repair1) {
		System.out.println("time : " + time);

		if (completecus == K) {
			int result = 0;

			for (int i = 0; i < K; i++) {
				
				if (customer.get(i).recepnum + 1 == A && customer.get(i).repairnum + 1 == B) {
					System.out.println("i : " + i + " recepnum : " + customer.get(i).recepnum + " repairnum : "
							+ customer.get(i).repairnum);
					result += i + 1;
				}
			}
			if (result != 0)
				ans = result;
			else
				ans = -1;
			return;
		}
		int curtime = time;
		int ctemp = 0;
		int emptyrecep = recep1;
		int emptyrepair = repair1;

		for (int z = 0; z < N; z++) {
			if (reception.get(z).status == 0)
				emptyrecep++;
		}
		for (int z = 0; z < M; z++) {
			if (repair.get(z).status == 0)
				emptyrepair++;
		}

		// 0:대기, 1:접수, 2:접수 완료, 3:정비, 4:정비 완료
		for (int i = 0; i < K; i++) {
			// 정비 완료 됐을 때
			if (customer.get(i).status == 3) {
				int j = customer.get(i).repairnum;
				if (repair.get(j).time == curtime - customer.get(i).repairtime) {
					customer.get(i).status = 4;
					repair.get(j).status = 0;
					emptyrepair++;
					System.out.println("정비 종료 || cusnum : " + i + " repairnum : " + customer.get(i).repairnum
							+ " repairtime : " + customer.get(i).repairtime + " cus status : " + customer.get(i).status
							+ " repair status : " + repair.get(j).status);

				}
			}

			// 접수 완료 됐을 때
			if (customer.get(i).status == 1) {
				int j = customer.get(i).recepnum;
				if (reception.get(j).time == curtime - customer.get(i).receptime) {
					customer.get(i).status = 2;
					reception.get(j).status = 0;
					emptyrecep++;
					System.out.println("접수 완료 || cusnum : " + i + " recepnum : " + customer.get(i).recepnum
							+ " receptime : " + customer.get(i).receptime + " cus status : " + customer.get(i).status
							+ " recep status : " + reception.get(j).status);
				}

			}
		}

		// 동시에 여러명이 종료, 들어갈 수 있기 때문에 for문을 따로 돌려 줌
		// 0:대기, 1:접수, 2:접수 완료, 3:정비, 4:정비 완료
		for (int i = 0; i < K; i++) {
			// 정비 창구 비었을 때
			if (customer.get(i).status == 2 && emptyrepair != 0) {
				ArrayList<Integer> recnum = new ArrayList<>();
				for (int z = 0; z < K; z++) {
					if (customer.get(z).status == 2) {
						recnum.add(customer.get(z).recepnum);
					}
				}
				Collections.sort(recnum);
				
				System.out.println("recnum size : " + recnum.size());
				for (int z = 0; z < recnum.size(); z++) {
					for (int j = 0; j < M; j++) {
						if (repair.get(j).status == 0) {
							for (int q = 0; q < K; q++) {
								if (recnum.get(z) == customer.get(q).recepnum && customer.get(q).status == 2) {
									customer.get(q).status = 3;
									customer.get(q).repairnum = j;
									customer.get(q).repairtime = curtime;
									repair.get(j).status = 1;
									emptyrepair--;
									System.out.println("정비 시작 || cusnum : " + q + " repairnum : "
											+ customer.get(q).repairnum + " repairtime : " + customer.get(q).repairtime
											+ " cus status : " + customer.get(q).status + " repair status : "
											+ repair.get(j).status);
									
								}
							}
						}
					}
				}


			}

			// 접수 창구 비었을 때

			if (customer.get(i).status == 0 && curtime >= customer.get(i).tk) {
				for (int j = 0; j < N; j++) {
					if (reception.get(j).status == 0) {
						customer.get(i).status = 1;
						customer.get(i).recepnum = j;
						customer.get(i).receptime = curtime;
						reception.get(j).status = 1;
						emptyrecep--;
						System.out.println("접수 시작 || cusnum : " + i + " recepnum : " + customer.get(i).recepnum
								+ " receptime : " + customer.get(i).receptime + " cus status : "
								+ customer.get(i).status + " recep status : " + reception.get(j).status);
						break;
					}
				}
			}
		}

//		System.out.printf("complete customer :");

		for (int i = 0; i < K; i++) {
			if (customer.get(i).status == 4) {
//				System.out.printf(" "+i);
				ctemp++;
			}
		}
//		System.out.println(" ctemp : "+ctemp);
		search(curtime + 1, ctemp, emptyrecep, emptyrepair);
	}

	public static class Customer {
		int tk, recepnum, receptime, repairnum, repairtime, status;

		public Customer(int tk, int recepnum, int receptime, int repairnum, int repairtime, int status) {
			this.tk = tk;
			this.recepnum = recepnum; // 접수 창구 번호
			this.receptime = receptime; // 접수 시작 시간
			this.repairnum = repairnum; // 정비 창구 번호
			this.repairtime = repairtime; // 정비 시작 시간
			this.status = status; // 0:대기, 1:접수, 2:접수 완료, 3:정비, 4:정비 완료
		}
	}

	public static class Desk {
		int time, status;

		public Desk(int time, int status) {
			this.time = time;
			this.status = status; // 0:비어있음, 1:작업 중
		}
	}

}
