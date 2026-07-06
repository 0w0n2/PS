class Solution {
public:
    // 한 수열에서 원소 수는 항상 2개
    int removeCoveredIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const vector<int>& o1, const vector<int>& o2) {
            if (o1[0] != o2[0]) { // 정렬 1순위: [0] 오름차순, 2순위: [1] 내림차순
                return o1[0] < o2[0];
            } 
            return o1[1] > o2[1];
        });

        int ans = 0;
        int maxRight = -1;

        for (const vector<int>& interval : intervals) {
            if (interval[1] > maxRight) { // 이전 수열이 이번 차례의 수열을 감쌀 수 있는지
                ans++;
                maxRight = interval[1];
            }
        }

        return ans;
    }
};