# SrSudoku
Creating a app for playing sudoku (with computer solver, I hope)

## Compilling and running
```bash
mvn compile exec:java -DfilePath=/path/to/your/file.txt
```

With debug:
```bash
mvn compile exec:java -DfilePath=/path/to/your/file.txt -Ddebug
```

Example:
```bash
mvn compile exec:java -DfilePath=src/main/resources/tests/t1.txt -Ddebug
```