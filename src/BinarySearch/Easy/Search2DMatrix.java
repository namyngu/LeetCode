package BinarySearch.Easy;

// You are given an m x n integer matrix matrix with the following two properties:
//
// Each row is sorted in non-decreasing order.
// The first integer of each row is greater than the last integer of the previous row.
// Given an integer target, return true if target is in matrix or false otherwise.
//
// You must write a solution in O(log(m * n)) time complexity.
//
//
//
// Example 1:
//
//
// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
// Output: true
// Example 2:
//
//
// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
// Output: false
//
//
// Constraints:
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 100
//        -104 <= matrix[i][j], target <= 104

public class Search2DMatrix {
  public static void main(String[] args) {
    Search2DMatrix start = new Search2DMatrix();
    int[][] input = {{1}, {3}};

    int target = 3;

    System.out.println(start.searchMatrix(input, target));
  }

  // Idea 1: Can convert the 2D matrix into a single array but time complexity will be O(n + log(n))
  // -> O(n)
  // Doesn't meet the requirements.

  // Idea 2: Do binary search by rows first using top and bottom boundary.
  // When boundary reaches <= 1, do Binary search by columns!
  public boolean searchMatrix(int[][] matrix, int target) {
    // Edge case only 1 row
    if (matrix.length == 1) {
      return binarySearch(matrix[0], target);
    }

    int top = 0; // top boundary
    int bottom = matrix.length - 1; // bottom boundary
    int middle = 0;

    while (bottom >= top) {
      middle = (int) Math.floor((bottom - top) / 2.0) + top;

      if (target > matrix[middle][0]) {
        top = middle + 1;
      } else if (target < matrix[middle][0]) {
        bottom = middle - 1;
      } else {
        return true;
      }
    }

    // check columns now.
    int row = middle;
    if (bottom < row) {
      // check columns in previous row
      row = row - 1;

      // Edge Cases
      if (row < 0) {
        return false;
      }

      return binarySearch(matrix[row], target);

    } else {
      // check columns in current row
      return binarySearch(matrix[row], target);
    }
  }

  boolean binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int middle = (int) Math.floor((right - left) / 2.0) + left;

      if (target > nums[middle]) {
        left = middle + 1;
      } else if (target < nums[middle]) {
        right = middle - 1;
      } else {
        return true;
      }
    }
    return false;
  }
}
