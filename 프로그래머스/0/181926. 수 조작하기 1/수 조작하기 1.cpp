#include <bits/stdc++.h>
using namespace std;

int solution(int n, string control) {
    for (char c : control) { // java처럼 control.toCharArray() 따로 안 해줘도 됨
        switch (c) {
            case 'w':
                n += 1;
                break;
            case 's':
                n -= 1;
                break;
            case 'd':
                n += 10;
                break;
            case 'a':
                n -= 10;
                break;
        }
    }
    return n;
}