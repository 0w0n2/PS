#include <vector>

using namespace std;

/**
정수가 담긴 리스트(num_list)가 주어질 때,
모든 원소들의 곱이 모든 원소들의 합의 제곱보다 작으면 1, 크면 0을 return
*/
int solution(vector<int> num_list) {
    // 9^10 -- (9*10)^2 ---> int
    int sum = 0 , mul = 1;
    for (int num : num_list) {
        sum += num;
        mul *= num;
    }
    
    return mul < sum * sum ? 1 : 0;
}