class Solution:

    def convert(self, s: str, num_rows: int) -> str:
        length = len(s)
        if num_rows == 1 or length <= num_rows:
            return s
        step = 2 * (num_rows - 1)
        zigzag: str = ''
        for i in range(num_rows):
            if i == 0 or i == num_rows - 1:
                for j in range(i, length, step):
                    zigzag += s[j]
            else:
                j = i
                step_1 = 2 * (num_rows - i - 1)
                step_2 = step - step_1
                flag: bool = True
                while j < length:
                    zigzag += s[j]
                    if flag:
                        j += step_1
                    else:
                        j += step_2
                    flag = not flag
        return zigzag


def main():
    num_rows = int(input())
    s = input()
    print(Solution().convert(s, num_rows))


if __name__ == '__main__':
    main()
