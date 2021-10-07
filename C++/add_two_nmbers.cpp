//
// Created by Naser Kazemi on 9/30/2021 AD.
//
#include <iostream>
#include <vector>

using namespace std;


struct ListNode {
    int val;
    ListNode *next;

    ListNode() : val(), next(nullptr) {}

    ListNode(int val) : val(val), next(nullptr) {}

    ListNode(int val, ListNode *next) : val(val), next(next) {}
};

class Solution {
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
        ListNode result;
        ListNode *ptr = &result;
        int carry = 0;
        while (l1 || l2 || carry) {
            int sum = (l1 ? l1->val : 0) + (l2 ? l2->val : 0) + carry;
            carry = sum / 10;
            ptr->next = new ListNode(sum % 10);
            ptr = ptr->next;
            l1 = l1 ? l1->next : l1;
            l2 = l2 ? l2->next : l2;
        }
        return result.next;
    }
};


int main() {
    ListNode test;
    cout << test.val;
    return 0;
}