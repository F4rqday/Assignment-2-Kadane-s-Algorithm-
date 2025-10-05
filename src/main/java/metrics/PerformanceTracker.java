package metrics;


public class PerformanceTracker {
    private long comparisons = 0;
    private long assignments = 0;
    private long arrayAccesses = 0;

    public void incComparison() { comparisons++; }
    public void incAssignment() { assignments++; }
    public void incArrayAccess() { arrayAccesses++; }

    public long getComparisons() { return comparisons; }
    public long getAssignments() { return assignments; }
    public long getArrayAccesses() { return arrayAccesses; }

    public String headerCSV() {
        return "n,trial,maxSum,start,end,comparisons,assignments,arrayAccesses,elapsedMillis";
    }

    public String toCSV(int n, int trial, int maxSum, int start, int end, long elapsedMillis) {
        return String.format("%d,%d,%d,%d,%d,%d,%d,%d",
                n, trial, maxSum, start, end, comparisons, assignments, arrayAccesses, elapsedMillis);
    }
}
