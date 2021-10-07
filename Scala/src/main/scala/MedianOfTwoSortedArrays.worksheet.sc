object Solution {

  def isOdd(x: Int, y: Int): Boolean = {
    (x + y) % 2 == 1
  }

  def maxOfTwo(xs: Array[Int], xsEnd: Int, ys: Array[Int], ysEnd: Int): Int = {
    if (xsEnd == 0) ys(ysEnd - 1)
    else {
      if (ysEnd == 0) xs(xsEnd - 1)
      else Math.max(xs(xsEnd - 1), ys(ysEnd - 1))
    }
  }

  def findRightHalfStart(
      xs: Array[Int],
      xsEnd: Int,
      ys: Array[Int],
      ysEnd: Int
  ): Double = {
    if (xsEnd == xs.length) ys(ysEnd)
    else {
      if (ysEnd == ys.length) xs(xsEnd)
      else Math.min(xs(xsEnd), ys(ysEnd))
    }
  }

  def findMedianAux(xs: Array[Int], ys: Array[Int]): Double = {
    val xsLength = xs.length
    val ysLength = ys.length
    val leftHandLength: Int = (xsLength + ysLength + 1) / 2
    var xsMin = 0
    var xsMax = xsLength
    var xsEnd = 0
    var ysEnd = 0
    var ans: Double = 0.0
    while (xsMin <= xsMax) {
      xsEnd = (xsMin + xsMax) / 2
      ysEnd = leftHandLength - xsEnd
      if (xsEnd > 0 && xs(xsEnd - 1) > ys(ysEnd)) xsMax = xsEnd - 1
      else if (xsEnd < xsLength && xs(xsEnd) < ys(ysEnd - 1)) xsMin = xsEnd + 1
      else {
        if (isOdd(xsLength, ysLength)) return maxOfTwo(xs, xsEnd, ys, ysEnd)
        else
          return (maxOfTwo(xs, xsEnd, ys, ysEnd) + findRightHalfStart(
            xs,
            xsEnd,
            ys,
            ysEnd
          )) / 2.0
      }
    }
    0.0
  }
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    if (nums1.length < nums2.length)
      findMedianAux(nums1, nums2)
    else
      findMedianAux(nums2, nums1)
  }
}

val nums1: Array[Int] = Array(1, 2)
val nums2: Array[Int] = Array(3)

Solution.findMedianSortedArrays(nums1, nums2)

// Solution.findRightHalfStart(nums1, 1, nums2, 1)
