#include <bits/stdc++.h>

using namespace std;

// 참가자 중 동명이인이 있을 수도 있음
string solution(vector<string> participant, vector<string> completion) {
    unordered_map<string, int> name2ct;
    name2ct.reserve(participant.size() * 2);
    
    for (const string& name : participant) {
        name2ct[name]++;
    }
    
    for (const string& name : completion) {
        name2ct[name]--;
    }
    
    for (const auto& entry : name2ct) {
        if (entry.second > 0) {
            return entry.first;
        }
    }
    
    return "";
}