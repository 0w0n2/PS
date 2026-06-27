#include <string>
#include <vector>

using namespace std;

// 슬라이딩 윈도우
// 연속된 자연수의 합 (start~end)
// 합이 n보다 작으면 end를 늘리고, n보다 크면 start를 늘린다
int solution(int n) {
    int answer = 0; // 가짓수의 합
    
    int start = 1;
    int end = 1;
    int sum = 1;
    
    while (start <= n) {
        if (sum == n) {
            answer++;
            sum -= start;
            start++;
        } else if (sum < n) {
            end++;
            sum += end;
        } else {
            sum -= start;
            start++;
        }
    }
    
    return answer;
}