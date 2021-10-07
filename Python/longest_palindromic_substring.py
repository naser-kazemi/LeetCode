class Solution:

    @staticmethod
    def initialize_table(size: int) -> list[list[bool]]:
        table = [[False for x in range(size)] for y in range(size)]
        for i in range(size):
            table[i][i] = True
        return table

    @staticmethod
    def check_for_length_twos(table: list[list[bool]], s: str, size: int):
        for i in range(size - 1):
            if s[i] == s[i + 1]:
                table[i][i + 1] = True

    @staticmethod
    def find_palindromes_indices(table: list[list[bool]], s: str, size: int) -> (int, int):
        start = end = 0
        max_length = 1
        for i in range(0, size - 1):
            if table[i][i + 1]:
                start = i
                end = i + 1
                max_length = 2
        for k in range(3, size + 1):
            for i in range(0, size - k + 1):
                j = i + k - 1
                if table[i + 1][j - 1] and s[i] == s[j]:
                    table[i][j] = True
                    if k > max_length:
                        start = i
                        end = j
                        max_length = k

        return start, end

    def longestPalindrome(self, s: str) -> str:
        size = len(s)

        table = Solution.initialize_table(size)
        Solution.check_for_length_twos(table, s, size)
        i, j = Solution.find_palindromes_indices(table, s, size)

        return s[i:j + 1]


def main():
    string = input()
    print(Solution().longestPalindrome(string))


if __name__ == '__main__':
    main()
