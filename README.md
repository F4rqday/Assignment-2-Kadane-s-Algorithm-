# Assignment 2 — Kadane (Student B, Pair 3)

**Pair 3 — Student B**    
**Name:** Farkhad Imanbayev 
**Teammate:** Bekzat Duesenbek (**Student A**)
**Group:** _SE-2438_



## Overview

This repo implements **Kadane's Algorithm** (maximum subarray sum) with **position tracking** and **metrics**,

plus a simple **CLI benchmark** that exports CSV suitable for plotting.

## Features

### Kadane Core
- **Linear-time O(n)** algorithm for maximum subarray sum
- **Constant auxiliary space O(1)**
- Tracks **start** and **end** indices of the best subarray
- Automatically handles:
  - Empty and all-negative arrays
  - Single-element arrays
  - Mixed positive/negative inputs

---

### Performance Tracking
- Monitors:
  - Comparisons  
  - Assignments  
  - Array accesses  
  - Elapsed execution time (ms)
- Results automatically exported to CSV:

n,trial,maxSum,start,end,comparisons,assignments,arrayAccesses,elapsedMillis

- Each run includes average runtime over configurable trials  
- Ideal for validating **Θ(n)** performance growth

---

### CLI Benchmark Tool

- Run with:
```bash
java -jar target/assignment2-kadane-v2-2.0.0.jar --sizes 100,1000,10000 --trials 3 --dist random --seed auto
``` 

- Supports multiple input distributions:
    - Random
    - Sorted
    - Reversed
    - Nearly sorted
- Generates detailed CSV in  
    `docs/performance-plots/data-<timestamp>.csv`


---

### Unit Testing

- Comprehensive tests using **JUnit 5**
- Covers:
    - Empty input arrays
    - Single element arrays
    - All-negative values
    - Mixed values (positive + negative)
    - Correctness of `start` / `end` indices
    - Edge-case handling and stability

```
mvn test
```

--- 

### Performance Validation

- Benchmarks confirm linear runtime growth (Θ(n))
- Example output snippet:

```
n,trial,maxSum,start,end,comparisons,assignments,arrayAccesses,elapsedMillis
1000,1,1723,99,999,2000,1184,1000,2
10000,3,4869,2477,9290,20000,10755,10000,9
100000,3,9821,60065,74630,200000,101878,100000,86
```

--- 


## Repo structure:

```
assignment2-kadane/

├── src/main/java/

│   ├── algorithms/Kadane.java

│   ├── metrics/PerformanceTracker.java

│   └── cli/BenchmarkRunner.java

├── src/test/java/

│   └── algorithms/KadaneTest.java

├── docs/

│   ├── analysis-report-template.md

│   └── performance-plots/ (CSV output here)

├── README.md

└── pom.xml

```


## Partner Analysis Report

**Path:** docs/analysis-report.pdf  