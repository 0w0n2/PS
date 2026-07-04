#include<bits/stdc++.h>

using namespace std;

vector<int> p;

int find(int x) {
    	if(p[x]<0) return x;
        return p[x] = find(p[x]);
    }
    
void merge(int a, int b) {
    a = find(a);
    b = find(b);

    if (a==b) return;

    p[b] = a;
}

int main(int argc, char** argv) {
	int test_case;
	int T;
	cin>>T;
    
	for(test_case = 1; test_case <= T; ++test_case) {
		int n, m;
    	cin >> n >> m;
    
     	p.assign(n+1, -1);
        
        string answer;
        
        while (m-- > 0) {
        	int cmd, a, b;
            cin >> cmd >> a >> b;
            
            switch (cmd) {
                case 0:
                    merge(a, b);
                    break;
                case 1:
                    answer += (find(a) == find(b) ? '1' : '0');
                    break;
            }
        }
        
        printf("#%d %s\n", test_case, answer.c_str());
	}
	return 0;
}