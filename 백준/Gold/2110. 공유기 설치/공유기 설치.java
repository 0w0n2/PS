import java.io.*;
import java.util.*;

public class Main {
	
	static int N, C, H[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1번 집에는 반드시 설치
		// 이분 탐색으로 가장 인접한 거리 k를 지정하고,
		// 1번 집의 좌표+k 보다 큰 거리에 있는 집들 중 lower bound (제일 가까운)에 가스 하나 설치,
		// 그 이후로는 남은 c-2개 가스에 대해 남은 집들에 설치 했을 때 선택된 집들이 모두 k 이상인 거리를 유지할 수 있는가? validation 체크하며 이분탐색의 범위를 재설정 
		
		N = Integer.parseInt(st.nextToken()); // 집의 개수
		C = Integer.parseInt(st.nextToken()); // 방역 가스의 개수
		
		H = new int[N]; // 각 집의 좌표
		for (int i=0;i<N;i++) H[i] = Integer.parseInt(br.readLine());
		Arrays.sort(H); 	// 각 집의 좌표를 오름차순 정렬
		
		System.out.print(binarySearch(0, H[H.length-1] - H[0]));
		
	}
	
	static int binarySearch(int start, int end) {
		
		while(start<=end) {
			int k = (start+end)/2;
			
			int idx = 0;
			boolean isOkay = true;
			for (int n=1;n<C;n++) {	// 공유기 C개를 모두 설치할 때까지 반복 (1개는 가장 왼쪽 집에 설치되어 있음)
				if ((idx = lowerBound(idx, k)) == -1) {
					isOkay = false;
					break;
				}
			}
			
			if (isOkay) {	// 가능한 k 거리라면, 최대 k를 구하기 위해서 이분탐색의 영역을 오른쪽으로 좁힌다.
				start = k + 1;
			} else {
				end = k - 1; // 불가능한 k 거리라면, 현재 k보다 작은 거리를 찾아야 하므로 이분탐색의 영역을 왼쪽으로 좁힌다.
			}
			
			
		}
		return start-1;
	}

	
	static int lowerBound(int lastIdx, int key) {	
		// 이전에 선택된 집과의 거리가 key 로 정해진 거리(k) 이상을 만족시키는 가장 가까운 집의 인덱스를 리턴 (lowerBound)
		
		int start = lastIdx + 1;	// 이전에 선택된 집 이후 집부터 선택 
		int end = H.length-1;
		
		while(start<=end) {
			int mid = (start+end)/2;
			
			if (H[mid]-H[lastIdx] < key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	
		return (start < H.length) ? start : -1;	
		// 이전 집과의 거리가 k 이상임이 보장되는 가장 가까운 집의 인덱스를 리턴, 만약 선택 가능한 집이 없다면 -1을 리턴
	}

}
