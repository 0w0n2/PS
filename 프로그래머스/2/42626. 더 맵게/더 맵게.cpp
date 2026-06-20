#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> pq; // 최소 힙
    
    for (int s : scoville) {
        pq.push(s);
    }
    
    int step = 0;
    while (pq.size() >= 2 && pq.top() < K) {        
        int first = pq.top(); pq.pop();
        int second = pq.top(); pq.pop();
        
        pq.push(first + second * 2);
        step++;
    }
    
    return (pq.top() < K) ? -1 : step;
}