// 양의 정수로 이루어진 배열. 조건을 만족하도록 배열에 연산 처리(0회 이상)
// - 배열의 첫 번째 요소 값이 1
// - 인접한 두 요소 사이의 절댓값 차이가 1보다 작거나 같아야 함
// return: 조건을 만족하도록 배열을 바꾼 뒤, 배열 안에서 만들 수 있는 가장 큰 원소값
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maximumElementAfterDecrementingAndRearranging(vector<int>& arr) {
        sort(arr.begin(), arr.end()); // 오름차순 정렬

        arr[0] = 1;
        for (int i=1; i<arr.size(); i++) {
            if (arr[i] > arr[i-1]+1) {
                arr[i] = arr[i-1]+1;
            }
        }

        return arr.back();
    }
};