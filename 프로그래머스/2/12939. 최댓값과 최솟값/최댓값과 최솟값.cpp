#include <string>
#include <vector>
#include <sstream>
#include <climits> // INT_MAX, INT_MIN

using namespace std;

string solution(string s) {
    stringstream ss(s);
    
    int num;
    int minValue = INT_MAX;
    int maxValue = INT_MIN;
    
    while (ss >> num) {
        if (num < minValue) {
            minValue = num;
        }
        if (num > maxValue) {
            maxValue = num;
        }
    }
    
    return to_string(minValue) + " " + to_string(maxValue);
}