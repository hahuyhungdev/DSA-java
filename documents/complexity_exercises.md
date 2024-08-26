
# Exercises on Calculating f(n) and Analyzing Time Complexity

## Exercise 1

```c
for (i = 0; i < n; i++) {
    for (j = 0; j < n; j++) {
        // code
    }
}
```

### Analysis:
- Outer loop runs `n` times.
- Inner loop runs `n` times for each iteration of the outer loop.
- Total iterations: `f(n) = n * n = n^2`
- Time complexity: `O(n^2)`

## Exercise 2

```c
for (i = 0; i < n; i++) {
    for (j = 0; j < 2 * n; j++) {
        // code
    }
    for (k = 0; k < 3 * n; k++) {
        // code
    }
}
```

### Analysis:
- Outer loop runs `n` times.
- First inner loop runs `2n` times for each iteration of the outer loop.
- Second inner loop runs `3n` times for each iteration of the outer loop.
- Total iterations: `f(n) = n * (2n + 3n) = n * 5n = 5n^2`
- Time complexity: `O(n^2)`

## Exercise 3

```c
for (i = 0; i < n; i++) {
    for (j = 0; j < 50; j++) {
        // code
    }
    for (k = 0; k < n * n; k++) {
        // code
    }
}
```

### Analysis:
- Outer loop runs `n` times.
- First inner loop runs `50` times for each iteration of the outer loop.
- Second inner loop runs `n^2` times for each iteration of the outer loop.
- Total iterations: `f(n) = n * (50 + n^2) = 50n + n^3`
- Time complexity: `O(n^3)` (since `n^3` is the dominant term)

## Exercise 4

```c
for (i = 0; i < n; i++) {
    for (j = 0; j < i; j++) {
        // code
    }
}
```

### Analysis:
- Outer loop runs `n` times.
- Inner loop runs from 0 to `i-1` for each iteration of the outer loop.
- Total iterations: `f(n) = 0 + 1 + 2 + ... + (n-1) = (n(n-1))/2 ≈ n^2/2`
- Time complexity: `O(n^2)`

## Exercise 5

```c
for (i = 0; i < n; i++) {
    for (j = 0; j < n; j++) {
        for (k = 0; k < n; k++) {
            // code
        }
    }
}
```

### Analysis:
- Outer loop runs `n` times.
- First inner loop runs `n` times for each iteration of the outer loop.
- Second inner loop runs `n` times for each iteration of the first inner loop.
- Total iterations: `f(n) = n * n * n = n^3`
- Time complexity: `O(n^3)`
