package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private int side;
    private WeightedQuickUnionUF grid;
    private boolean[][] matrix;
    private int inNode;
    private int outNode;
    private int numOfOpen;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IndexOutOfBoundsException();
        }

        this.side = N;
        this.grid = new WeightedQuickUnionUF(N * N + 2);
        this.matrix = new boolean[N][N];
        this.inNode = 0;
        this.outNode = N * N + 1;
        this.numOfOpen = 0;
    }

    private int twoD2oneD(int row, int col) {
        return row * side + col + 1;
    }

    private boolean isTop(int row, int col) {
        return row == 0;
    }

    private boolean isBottom(int row, int col) {
        return row == side - 1;
    }

    private boolean isValid(int row, int col) {
        return !(row < 0 || row >= side || col < 0 || col >= side);
    }

    public void open(int row, int col) {
        // if the input is invalid, throw error
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        // if it is already open, do nothing
        if (isOpen(row, col)) {
            return;
        }

        // label the exact (row, col) particle as open
        matrix[row][col] = true;
        numOfOpen += 1;

        // transform 2D label to 1D label and union it with surrounded particle if possible
        int index = twoD2oneD(row, col);

        // if it is top particle, connect it with the inNode
        if (isTop(row, col)) {
            grid.union(index, inNode);
        }

        // if it is bottom particle, connect it with the outNode
        if (isBottom(row, col)) {
            grid.union(index, outNode);
        }

        //check surroundings and do union operation if possible
        if (row > 0 && isOpen(row - 1, col)) {
            int otherIndex = twoD2oneD(row - 1, col);
            grid.union(index, otherIndex);
        }
        if (col > 0 && isOpen(row, col - 1)) {
            int otherIndex = twoD2oneD(row, col - 1);
            grid.union(index, otherIndex);
        }
        if (col < side - 1 && isOpen(row, col + 1)) {
            int otherIndex = twoD2oneD(row, col + 1);
            grid.union(index, otherIndex);
        }
        if (row < side - 1 && isOpen(row + 1, col)) {
            int otherIndex = twoD2oneD(row + 1, col);
            grid.union(index, otherIndex);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return matrix[row][col];
    }

    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        int index = twoD2oneD(row, col);
        return grid.connected(index, inNode);
    }

    public int numberOfOpenSites() {
        return numOfOpen;
    }

    public boolean percolates() {
        return grid.connected(inNode, outNode);
    }

    public static void main(String[] args) {
        Percolation pc = new Percolation(4);
        pc.open(1, 1);
        pc.open(0, 1);
        System.out.println(pc.isOpen(2, 0));
        System.out.println(pc.isFull(1, 1));
        pc.open(2, 1);
        System.out.println(pc.percolates());
        pc.open(3, 1);
        System.out.println(pc.percolates());
    }
}
