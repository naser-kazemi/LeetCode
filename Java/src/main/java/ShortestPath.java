import java.util.Scanner;

public class ShortestPath {

    static int[][] initiateDistMap(int dim1, int dim2, int startX, int startY) {
        int[][] distMap = new int[dim1][dim2];
        for (int i = 0; i < dim1; i++)
            for (int j = 0; j < dim2; j++) distMap[i][j] = 10000000;
        distMap[startX][startY] = 0;
        return distMap;
    }

    static int[][] initiateOriginMap(int dim1, int dim2) {
        int[][] originMap = new int[dim1][dim2];
        for (int i = 0; i < dim1; i++)
            for (int j = 0; j < dim2; j++) originMap[i][j] = -1;
        return originMap;
    }

    static boolean[][] initiateVisitedMap(int dim1, int dim2) {
        boolean[][] visitedMap = new boolean[dim1][dim2];
        for (int i = 0; i < dim1; i++)
            for (int j = 0; j < dim2; j++) visitedMap[i][j] = false;
        return visitedMap;
    }


    static int shortestPath(int dim1, int dim2, int startX, int startY, int[][] matrix, int endX, int endY) {
        int[][] distMap = initiateDistMap(dim1, dim2, startX, startY);
        int[][] originMap = initiateOriginMap(dim1, dim2);
        boolean[][] visitedMap = initiateVisitedMap(dim1, dim2);
        boolean isFinished = false;
        int x = 0, y = 0;
        while (!isFinished) {
            exploreStage(dim2, matrix, distMap, originMap, visitedMap, x, y, x < dim1 - 1, x + 1);
            exploreStage(dim2, matrix, distMap, originMap, visitedMap, x, y, x > 0, x - 1);
            exploreNeighbour(matrix, distMap, originMap, visitedMap, x, y, y < dim2 - 1, y + 1, distMap[x], x, y + 1, x * dim2);
            exploreNeighbour(matrix, distMap, originMap, visitedMap, x, y, y > 0, y - 1, distMap[x], x, y - 1, x * dim2);
            visitedMap[x][y] = true;

            int[][] distMapTemp = initiateDistMapTemp(dim1, dim2, distMap, visitedMap);
            int[] min = getMinValue(distMapTemp);
            x = min[0];
            y = min[1];
            if (x == endX && y == endY)
                isFinished = true;

        }
        return distMap[x][y];
    }

    private static int[][] initiateDistMapTemp(int dim1, int dim2, int[][] distMap, boolean[][] visitedMap) {
        int[][] distMapTemp = new int[dim1][dim2];
        for (int i = 0; i < dim1; i++)
            for (int j = 0; j < dim2; j++) {
                if (visitedMap[i][j])
                    distMapTemp[i][j] = 10000000;
                else
                    distMapTemp[i][j] = distMap[i][j];
            }
        return distMapTemp;
    }

    private static int[] getMinValue(int[][] numbers) {
        int minValue = numbers[0][0];
        int iMin = 0, jMin = 0;
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if (numbers[j][i] < minValue) {
                    minValue = numbers[j][i];
                    iMin = j;
                    jMin = i;
                }
            }
        }
        return new int[]{iMin, jMin};
    }

    private static void exploreNeighbour(int[][] matrix, int[][] distMap, int[][] originMap, boolean[][] visitedMap, int x, int y, boolean b, int i, int[] ints, int x2, int i2, int i3) {
        if (b)
            if (distMap[x][i] > matrix[x][i] + ints[y] && !visitedMap[x2][i2]) {
                distMap[x][i] = matrix[x][i] + ints[y];
                originMap[x][i] = (i3) + y;
            }
    }

    private static void exploreStage(int dim2, int[][] matrix, int[][] distMap, int[][] originMap, boolean[][] visitedMap, int x, int y, boolean b, int i) {
        exploreNeighbour(matrix, distMap, originMap, visitedMap, i, y, b, y, distMap[x], i, y, x * dim2);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dim1 = scanner.nextInt();
        int dim2 = scanner.nextInt();
        int[][] matrix = new int[dim1][dim2];
        int count = 1;
        int startX = 0, startY = 0, endX = 0, endY = 0;
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                try {
                    matrix[i][j] = scanner.nextInt();
                } catch (Exception e) {
                    if (count == 1) {
                        startX = i;
                        startY = j;
                    } else {
                        endX = i;
                        endY = j;
                    }
                    matrix[i][j] = 0;
                    count++;
                    scanner.next();
                }
            }
        }

        System.out.println(shortestPath(dim1, dim2, startX, startY, matrix, endX, endY));

    }


}

/*
4 4
1 4 5 *
12 7 10 8
3 6 9 9
* 4 2 7
 */