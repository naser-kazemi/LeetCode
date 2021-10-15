import scala.math.min
import scala.math.max
import scala.annotation.tailrec

object Solution {
  def maxArea(height: Array[Int]): Int = {

    @tailrec
    def iterate(left: Int, right: Int, maxArea: Int): Int = {
      if (left >= right) maxArea
      else {
        if (height(left) < height(right))
          iterate(
            left + 1,
            right,
            max(maxArea, min(height(left), height(right)) * (right - left))
          )
        else
          iterate(
            left,
            right - 1,
            max(maxArea, min(height(left), height(right)) * (right - left))
          )
      }
    }

    iterate(0, height.length - 1, 0)
  }
}

val height = Array(4, 3, 2, 1, 4)
Solution.maxArea(height)
