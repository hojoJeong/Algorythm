import java.util.*;
import java.io.*;

public class 차량정비소_2477_2 {
	static Customer[] customer;
	static Desk[] receptionDesk, repairDesk;
	static int N, M, K, A, B, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			receptionDesk = new Desk[N];
			repairDesk = new Desk[M];
			customer = new Customer[K];
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int time = Integer.parseInt(st.nextToken());
				receptionDesk[i] = new Desk(false, time);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int time = Integer.parseInt(st.nextToken());
				repairDesk[i] = new Desk(false, time);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				int time = Integer.parseInt(st.nextToken());
				customer[i] = new Customer(i, time, -1, -1, -1, -1, -1);
			}

			findResult();
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	// 뒤에서 부터 작업이 완료된 사람은 빼줘야 동시간대에 들어갈 수 있음
	static void findResult() {
		int time = 0;
		int emptyRepairDesk = M;
		int emptyReceptionDesk = N;
		int receptDone = 0;
		int repairDone = 0;
		while (true) {
			
			if (repairDone == K) {
				break;
			}

			// 접수 완료된 인원 빼줌
			for (int i = 0; i < K; i++) {
				if (customer[i].startTimeReception != -1
						&& time - customer[i].startTimeReception == receptionDesk[customer[i].receptionDesk].time) {
					customer[i].endTimeReception = time;
					receptionDesk[customer[i].receptionDesk].working = false;
					emptyReceptionDesk++;
					receptDone++;
				}
			}
	
			
			Arrays.sort(customer, new Comparator<Customer>() {
				// 도착 시간이 같으면 고객 번호가 늦은 순으로 정렬
				@Override
				public int compare(Customer o1, Customer o2) {
					if (o1.arrivalTime == o2.arrivalTime) {
						return o1.num - o2.num;
					} else {
						return o1.arrivalTime - o2.arrivalTime;
					}
				}
			});

			// 접수창구 비어있고 모든 고객의 접수가 종료된 게 아니면 접수 시작
			if (emptyReceptionDesk > 0 && receptDone < K) {

				for (int i = 0; i < N; i++) {
					if (!receptionDesk[i].working) {
						for (int j = 0; j < K; j++) {
							if (customer[j].startTimeReception == -1 && customer[j].arrivalTime <= time) {
								
								customer[j].startTimeReception = time;
								customer[j].receptionDesk = i;
								receptionDesk[i].working = true;
								emptyReceptionDesk--;
								break;
							}
						}
					}
				}
			}

		

			// 정비 종료된 인원 빼줌
			for (int i = 0; i < K; i++) {
				if (customer[i].startTimeRepair != -1
						&& time - customer[i].startTimeRepair == repairDesk[customer[i].repairDesk].time) {
					repairDesk[customer[i].repairDesk].working = false;
					emptyRepairDesk++;
					repairDone++;
				}
			}

			Arrays.sort(customer, new Comparator<Customer>() {

				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.endTimeReception == o2.endTimeReception) {
						return o1.receptionDesk - o2.receptionDesk;
					} else {
						return o1.endTimeReception - o2.endTimeReception;
					}
				}
			});

			// 정비창구 비어있고 대기 인원 있으면 정비 시작
			if (emptyRepairDesk > 0) {
				// 정비 창구로 보내기 전 이용했던 접수창구 번호가 작은 고객이 우선이므로 정렬해줌

				for (int i = 0; i < M; i++) {
					if (!repairDesk[i].working) {
						for (int j = 0; j < K; j++) {
							if (customer[j].endTimeReception != -1 && customer[j].startTimeRepair == -1) {
								customer[j].startTimeRepair = time;
								customer[j].repairDesk = i;
								repairDesk[i].working = true;
								emptyRepairDesk--;
								break;
							}
						}
					}
				}
			}

			time++;
		}

		for (int i = 0; i < K; i++) {
			if (customer[i].receptionDesk == A - 1 && customer[i].repairDesk == B - 1) {
				ans += (customer[i].num + 1);
			}
		}
		if (ans == 0) {
			ans = -1;
		}

	}

	public static class Customer {
		int num, arrivalTime, startTimeReception, receptionDesk, endTimeReception, startTimeRepair, repairDesk;

		public Customer(int num, int arrivalTime, int startTimeReception, int receptionDesk, int endTimeReception,
				int startTimeRepair, int repairDesk) {
			this.num = num;
			this.arrivalTime = arrivalTime;
			this.startTimeReception = startTimeReception;
			this.receptionDesk = receptionDesk;
			this.endTimeReception = endTimeReception;
			this.startTimeRepair = startTimeRepair;
			this.repairDesk = repairDesk;
		}
	}

	public static class Desk {
		boolean working;
		int time;

		public Desk(boolean working, int time) {
			this.working = working;
			this.time = time;
		}
	}
}
