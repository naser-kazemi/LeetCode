class Solution:

    def new_string(self, s: str) -> str:
        return '|' + '|'.join(s[i:i + 1] for i in range(0, len(s))) + '|'

    def longestPalindrome(self, s: str) -> str:
        s = self.new_string(s)
        length = len(s)
        palindrome_radius = [0 for _ in range(length)]
        center = radius = 0

        for i in range(length):
            current_mirror = 2 * center - i
            if radius > i:
                palindrome_radius[i] = min(radius - i, palindrome_radius[current_mirror])
            else:
                palindrome_radius[i] = 0
            try:
                while s[i + (1 + palindrome_radius[i])] == s[i - (palindrome_radius[i] + 1)]:
                    palindrome_radius[i] += 1
            except:
                pass
            if i + palindrome_radius[i] > radius:
                center = i
                radius = i + palindrome_radius[i]

        r, c = max(palindrome_radius), palindrome_radius.index(max(palindrome_radius))

        return s[c - r: c + r].replace("|", "")


def main():
    string = input()
    print(Solution().longestPalindrome(string))


if __name__ == '__main__':
    main()
