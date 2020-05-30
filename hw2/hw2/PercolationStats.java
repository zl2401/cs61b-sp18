package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int testTime;
    private double[] testResult;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        testTime = T;
        testResult = new double[testTime];

        for (int i = 0; i < testTime; i++) {
            Percolation tempPc = pf.make(N);
            while (!tempPc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                tempPc.open(row, col);
            }
            testResult[i] = (double) tempPc.numberOfOpenSites() / (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(testResult);
    }

    public double stddev() {
        return StdStats.stddev(testResult);
    }

    public double confidenceLow() {
        double m = mean();
        double std = stddev();
        double twoSig = 1.96;
        return m - twoSig * std / Math.sqrt(testTime);
    }

    public double confidenceHigh() {
        double m = mean();
        double std = stddev();
        double twoSig = 1.96;
        return m + twoSig * std / Math.sqrt(testTime);
    }

    
}
