# Comparative results of the fractal creation program time

System configuration: 
    
- Processor: 4 cores and 8 threads;
- RAM: 8 GB.

### Test 1

Samples: 100

Iterations per sample: 1_000_000

| Threads count | Time(second) | Boost  |
|---------------|--------------|--------|
| 1             | 27.067       | x1     |
| 4             | 9.582        | x2.825 |
| 8             | 6.134        | x4.413 |

### Test 2

Samples: 1_000

Iterations per sample: 1_000_000

| Threads count | Time(second) | Boost  |
|---------------|-------------|--------|
| 1             | 250.36      | x1     |
| 4             | 70.879      | x3.532 |
| 8             | 47.974      | x5.219 |

### Test 3

Samples: 100_000

Iterations per sample: 1_000

| Threads count | Time(second) | Boost  |
|---------------|--------------|--------|
| 1             | 29.329       | x1     |
| 4             | 9.041        | x4.801 |
| 8             | 5.788        | x5.067 |
