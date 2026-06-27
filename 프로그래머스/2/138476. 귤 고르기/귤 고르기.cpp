#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

int solution(int k, vector<int> tangerine) {
    unordered_map<int, int> num2ct;
    
    for (int num : tangerine) {
        num2ct[num]++;
    }
    
    vector<int> counts;
    
    for (auto entry : num2ct) {
        counts.push_back(entry.second); // value
    }
    
    sort(counts.begin(), counts.end(), greater<int>());
    
    int ans = 0;
    for (int count : counts) {
        k -= count;
        ans++;
        
        if (k <= 0) {
            break;
        }
    }
    
    return ans;
}