#include <iostream>

/*
- n*m 크기의 체스판
- 칸 중 K개의 칸은 검은색, 나머지 칸들은 모두 흰색
- 흰 칸을 0개 이상 검은 칸으로 칠해 올바른 체스판을 만들 수 있는지
- 올바른 체스판 = 모든 칸이 인접한 칸과 색상이 다른 체스판을 의미
- K개의 검은 칸이 주어짐
*/

// 흰 칸은 검은 칸으로 칠할 수 있지만, 이미 검은 칸은 흰색으로 되돌릴 수 없음
// -> 처음부터 검은색으로 주어진 칸들이 모두 같은 홀 or 짝에 있어야 함

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    int k;

    cin >> n >> m >> k;

    int baseParity = -1;
    bool isPossible = true;

    for (int i=0; i<k; i++) { // K개의 검은 칸이 주어짐
        int r, c;
        cin >> r >> c;

        int parity = (r+c) % 2; // 홀짝

        if (baseParity == -1) { // 처음 들어온 검은 칸의 홀짝 저장
            baseParity = parity;
        } else if (baseParity != parity) {
            cout << "NO\n";
            return 0;
        }
    }

    cout << "YES\n";
}
