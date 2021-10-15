/**
 * Help from Naser Kazemi
 * Help from https://www.geeksforgeeks.org/minimum-cost-path-left-right-bottom-moves-allowed/
 */


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ROW;
    static int COL;

    static class Cell {
        int x;
        int y;
        int distance;

        Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static class distanceComparator
            implements Comparator<Cell> {
        public int compare(Cell a, Cell b) {
            if (a.distance < b.distance) {
                return -1;
            } else if (a.distance > b.distance) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static boolean isInsideGrid(int i, int j) {
        return (i >= 0 && i < ROW &&
                j >= 0 && j < COL);
    }

    static int shortestPath(int[][] grid, int row,
                            int col) {
        int[][] dist = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }


        dist[0][col - 1] = grid[0][col - 1];

        PriorityQueue<Cell> pq = new PriorityQueue<Cell>(
                row * col, new distanceComparator());

        pq.add(new Cell(0, col - 1, dist[0][col - 1]));
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int rows = curr.x + dx[i];
                int cols = curr.y + dy[i];

                if (isInsideGrid(rows, cols)) {
                    if (dist[rows][cols] >
                            dist[curr.x][curr.y] +
                                    grid[rows][cols]) {
                        if (dist[rows][cols] != Integer.MAX_VALUE) {
                            Cell adj = new Cell(rows, cols,
                                    dist[rows][cols]);

                            pq.remove(adj);
                        }

                        dist[rows][cols] = dist[curr.x][curr.y] +
                                grid[rows][cols];

                        pq.add(new Cell(rows, cols,
                                dist[rows][cols]));
                    }
                }
            }
        }
        return dist[row - 1][0];
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ROW = scanner.nextInt();
        COL = scanner.nextInt();
        int[][] matrix = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                try {
                    matrix[i][j] = scanner.nextInt();
                } catch (Exception e) {
                    matrix[i][j] = 0;
                    scanner.next();
                }
            }
        }
        System.out.println(shortestPath(matrix, ROW, COL));
    }

}
