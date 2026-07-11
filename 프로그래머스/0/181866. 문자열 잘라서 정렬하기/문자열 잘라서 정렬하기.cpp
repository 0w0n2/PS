#include <bits/stdc++.h>

using namespace std;

vector<string> solution(string myString) {
    vector<string> answer;
    string temp = "";
    
    for (char c : myString) {
        if (c == 'x') {
            if (temp != "") {
                answer.push_back(temp);
                temp = "";
            }
            continue;
        }
        temp += c;
    }
    
    if (temp != "") {
        answer.push_back(temp);
    }
    sort(answer.begin(), answer.end());
    
    return answer;
}