#include <vector>
using namespace std;

vector<int> solution(int brown, int yellow) {
    // 카펫의 크기는 3*3 이상
    int total = brown + yellow;
    for (int height=3; height * height <= total; height++) {
        if (total % height != 0) {
            continue;
        }
        
        int width = total / height;
        
        if ((width-2)*(height-2) == yellow) { // 내부 영역 개수가 노란색과 같은가?
            return {width, height};
        }
    }
    
    return {};
}