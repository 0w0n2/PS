#include <vector>

using namespace std;

// 컴퓨터 번호: 0 ~ n-1
vector<int> par;

// c++은 Java와 달리 함수나 변수를 사용하기 전에 컴파일러가 이름을 먼저 알고 있어야 함(컴파일러가 코드를 위에서 아래로 읽기 때문에)
// 아래쪽에 정의된 함수를 위쪽 코드에서 바로 호출하면 아직 선언되지 않은 함수로 인식되어 컴파일 오류가 남(위쪽에 바로 구현까지 해도 ㄱㅊ)
int find(int a);

void merge(int a, int b);

int solution(int n, vector<vector<int>> computers) {
    par.resize(n);
    
    for (int i=0; i<n; i++) {
        par[i] = -1;
    }
    
    for (int i=0; i<computers.size(); i++) {
        for (int j=i+1; j<computers[i].size(); j++) {
            if (computers[i][j]==1) {
                merge(i, j);
            }
        }
    }
    
    int answer = 0;
    for (int i=0; i<n; i++) {
        if (par[i] == -1) {
            answer++;
        }
    }
    
    return answer;
}

int find(int a) {
    if (par[a]==-1) {
        return a;
    }
    
    return par[a] = find(par[a]);
}

void merge(int a, int b) {
    a = find(a);
    b = find(b);
    
    if (a == b) {
        return;
    }
    
    par[a] = b;
}