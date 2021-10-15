object Solution {

  def isPalindrome(x: Int): Boolean = {
    if (x < 0) return false
    if (x == 0) return true
    var rev = 0
    var temp = x
    while (temp > 0) {
      rev = rev * 10 + temp % 10
      temp /= 10
    }
    rev == x
  }
}
