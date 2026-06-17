#include <string>
#include <vector>

using namespace std;

// 자연수 n을 x로 나눈 나머지가 1이 되도록 하는 가장 작은 자연수 x를 return

int solution(int n) {
    for (int i=2; i<n; i++) {
        if (n%i==1) {
            return i;
        }
    }
    return 0;
}