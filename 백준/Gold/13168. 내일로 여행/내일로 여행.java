import java.util.*;
import java.io.*;

public class Main {
	static class Way{
		String how;
		int start, end;
		double cost;
		
		Way(String how, int start, int end, double cost){
			this.how = how;
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	static HashMap<String, Integer> cityCode = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시의 수(N개인데, 중복있을 수 있음)
		int R = Integer.parseInt(st.nextToken()); // 내일로 티켓의 가격
		
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			String city = st.nextToken();
			if(!cityCode.containsKey(city)) {
				cityCode.put(city, cityCode.size()); // 도시별 숫자 코드(인덱스) 지정
			}
		}
//		cityCode.forEach((key, value)->{
//			System.out.println(key+" : "+value);
//		});
		
		int M = Integer.parseInt(br.readLine()); // 여행할 도시 수
		int[] travelSequence = new int[M]; // 여행 순서 저장 배열
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<M;i++) {
			String city = st.nextToken();
			travelSequence[i] = cityCode.get(city);
		}
		
		int K = Integer.parseInt(br.readLine()); // 교통 수단 개수
		int INF = 1_000_000_000;
		int size = cityCode.size();
		double[][] miniCost = new double[size][size]; // 도시 쌍 별 최소 비용
		double[][] discountCost = new double[size][size]; // "내일로"사용, 할인가 적용 후 최소 비용
		
		for (int i=0;i<size;i++) {
			Arrays.fill(miniCost[i], INF);
			Arrays.fill(discountCost[i], INF);
			miniCost[i][i] = discountCost[i][i] = 0;
		}
		
		for (int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			String how = st.nextToken();
			int start = cityCode.get(st.nextToken());
			int end = cityCode.get(st.nextToken());
			double cost = Double.parseDouble(st.nextToken());
			
			miniCost[start][end] = Math.min(miniCost[start][end], cost);
			miniCost[end][start] = Math.min(miniCost[end][start], cost); // 양방향 이동
			
			if (how.equals("Mugunghwa") || how.equals("ITX-Saemaeul") || how.equals("ITX-Cheongchun")) {
                discountCost[start][end] = 0; // 무료 이용
                discountCost[end][start] = 0;
            } else if (how.equals("S-Train") || how.equals("V-Train")) { // 반값 이용
                discountCost[start][end] = Math.min(discountCost[start][end], cost/2);
                discountCost[end][start] = Math.min(discountCost[end][start], cost/2);
            } else { // 그외 할인 X
                discountCost[start][end] = Math.min(discountCost[start][end], cost);
                discountCost[end][start] = Math.min(discountCost[end][start], cost);
            }
		}
		
		for (int k=0;k<size;k++) {
			for (int i=0;i<size;i++) {
				for (int j=0;j<size;j++) {
					if (miniCost[i][k] < INF && miniCost[k][j] < INF) {
                        miniCost[i][j] = Math.min(miniCost[i][j], miniCost[i][k] + miniCost[k][j]);
                    }
                    if (discountCost[i][k] < INF && discountCost[k][j] < INF) {
                        discountCost[i][j] = Math.min(discountCost[i][j], discountCost[i][k] + discountCost[k][j]);
                    }
				}
			}
		}
		
		double miniSum = 0;
		double discountSum = R;
		for (int i=1;i<M;i++) {
			int start = travelSequence[i-1];
			int end = travelSequence[i];
			
			miniSum += miniCost[start][end];
			discountSum += discountCost[start][end];
//			System.out.println(discountCost[start][end]);
		}
//		System.out.println(miniSum);
//		System.out.println(discountSum);
		
		System.out.println(miniSum>discountSum ? "Yes":"No");
	}
}