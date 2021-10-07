import io, os

input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline


def upper_bound_index(arr, n, x):
    mid = 0
    low = 0
    high = n
    while low < high:
        mid = low + (high - low) // 2
        if x <= arr[mid]:
            high = mid
        else:
            low = mid + 1

    if low < n and arr[low] < x:
        low += 1
    return low


def main():
    n = int(input())
    soldier_powers = list(map(int, input().split()))
    total_power = sum(soldier_powers)
    m = int(input())
    upper_bound: int
    lower_bound: int
    defence: int
    attack: int
    for i in range(m):
        defence, attack = map(int, input().split())
        upper_bound = upper_bound_index(soldier_powers, n, defence)
        lower_bound = upper_bound - 1
        ans = 1000000000
        if upper_bound < n:
            if soldier_powers[upper_bound] >= defence:
                ans = min(ans, max(attack - (total_power - soldier_powers[upper_bound]), 0));
        if lower_bound <= n or lower_bound >= 1:
            if soldier_powers[lower_bound] < defence:
                ans = min(ans, (defence - soldier_powers[lower_bound]) +
                          max(attack - (total_power - soldier_powers[lower_bound]), 0))

        print(ans)


if __name__ == '__main__':
    main()
