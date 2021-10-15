//
// Created by Naser Kazemi on 10/12/2021 AD.
//

#include <iostream>

using namespace std;

class Solution {
public:
    string intToRoman(int num) {
        int base[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        string roman_chars[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        string result = "";
        int len = sizeof(base) / sizeof(int);
        for (int i = 0; i < len; i++) {
            int div = num / base[i];
            if (div > 0) {
                for (int j = 0; j < div; j++)
                    result += roman_chars[i];
                num -= (div) * base[i];
            }
        }
        return result;
    }
};

