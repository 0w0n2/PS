#include <string>
#include <vector>
#include <queue>

// priorityQueue에서 내림차순으로 정렬한 후, 현재 가장 큰 작업량을 1씩 줄여서 다시 넣기

using namespace std;

long long solution(int n, vector<int> works) {
    priority_queue<int> pq; // c++의 pq는 기본적으로 최대 힙 -> top()에 가장 큰 값이 옴
    
    // 20_000 * 50_000 = 1_000_000_000 <- int 언더
    int total = 0;
    
    for (int work : works) {
        pq.push(work);
        total += work;
    }
    
    // 남은 시간 동안 모든 작업 가능
    if (total <= n) {
        return 0;
    }
    
    // java랑 다르게 pq.pop()이 값을 뱉지 않음(return void)
    while (n > 0) {
        int cur = pq.top();
        pq.pop();
        
        cur--;
        
        pq.push(cur);
        n--;
    }
    
    long long answer = 0;
    
    while (!pq.empty()) {
        long long work = pq.top();
        pq.pop();
        
        answer += work * work;
    }
     
    return answer;
}