package MatrixMultiplicationFinalExam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    // Method to benchmark Elementary matrix multiplication
    public static double benchmarkMatrixMultiplication(Matrix matrixA, Matrix matrixB) {
        long startTime = System.nanoTime();
        Matrix.elementaryMultiplication(matrixA, matrixB);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    // Method to Benchmark simple matrix transpose
    public static double benchmarkSimpleTranspose(Matrix matrixA, Matrix matrixB) {
        long startTime = System.nanoTime();
        Matrix.transpose(matrixA, matrixB);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    // Method to Benchmark the Recursive transpose algorithm
    public static double benchmarkRecTranspose(Matrix matrixA, Matrix matrixB, int s) {
        long startTime = System.nanoTime();
        Matrix.transposeRec(matrixA, matrixB, s);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    // Benchmark Elementary multiplication transposed
    public static double benchmarkElementaryMultiplicationTransposed(Matrix matrixA, Matrix matrixB, int s) {
        long startTime = System.nanoTime();
        Matrix.elementaryMultiplicationTransposed(matrixA, matrixB, s);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    // Method to Benchmark the Tiled multiplication algorithm
    public static double benchmarkTiledMultiplication(Matrix matrixA, Matrix matrixB, int s) {
        long startTime = System.nanoTime();
        Matrix.tiledMultiplication(matrixA, matrixB, s);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public static void main(String[] args) throws IOException {
        MatrixGenerator matrixGenerator = new MatrixGenerator();

        // THE FOLLOWING CSV FILES ARE CREATED FROM THE CODE BELOW:
        // - (Horserace file) matrixMultiplicationBenchmark.csv
        // - RecursiveTransposeOptimalSTask4.csv
        // - RecursiveTransposeBenchmark.csv
        // - TiledMultOptimalSTask7.csv
        // - TransposeMatrixBenchmark.csv

        // Task 4 Recursive transpose optimal test
        // String csvFileTask4 = "RecursiveTransposeOptimalSTask4.csv";
        // BufferedWriter writerTask4;
        // writerTask4 = new BufferedWriter(new FileWriter(csvFileTask4));
        // String Task4Header = "Size,S,Time";
        // writerTask4.write(Task4Header);
        // writerTask4.newLine();
        // writerTask4.flush();
        // System.out.println("Working on Task4 for finding optimal s for recursive
        // transpose... ");
        // for (int ii = 2048; ii <= 4096; ii += 1024) {
        // for (int kk = 0; kk < 2; kk++) {
        // Matrix A = matrixGenerator.generateMatrix(ii, ii, (int)
        // (Math.sqrt(Math.pow(2, 53) / ii) - kk));
        // Matrix B = matrixGenerator.generateMatrix(ii, ii, (int)
        // (Math.sqrt(Math.pow(2, 53) / ii) - kk));
        // for (int j = ii; j >= 2; j = j / 2) {
        // double time = benchmarkRecTranspose(A, B, j);
        // String row = "" + ii + "," + j + "," + time;
        // writerTask4.write(row);
        // writerTask4.newLine();
        // writerTask4.flush();
        // System.out.println(" Size n: " + ii + " s: " + j + " Time: " + time);
        // }
        // }
        // }
        // writerTask4.close();

        // Recursive transpose Benchmark
        String csvFileRecTranspose = "RecursiveTransposeBenchmark.csv";
        BufferedWriter writerRecTranspose;
        writerRecTranspose = new BufferedWriter(new FileWriter(csvFileRecTranspose));
        String RecTransposeHeader = "Algorithm,Size,Time";
        writerRecTranspose.write(RecTransposeHeader);
        writerRecTranspose.newLine();
        writerRecTranspose.flush();
        System.out.println("Working on Recursive Transpose Benchamr. . . ");
        for (int ii = 2048; ii <= 4096; ii += 1024) {
            for (int kk = 0; kk < 10; kk++) {
                Matrix A = matrixGenerator.generateMatrix(ii, ii, (int) (Math.sqrt(Math.pow(2, 53) / ii) - kk));
                Matrix B = matrixGenerator.generateMatrix(ii, ii, (int) (Math.sqrt(Math.pow(2, 53) / ii) - kk));
                double time = benchmarkRecTranspose(A, B, 16);
                String row = "RecTranspose," + ii + "," + time;
                writerRecTranspose.write(row);
                writerRecTranspose.newLine();
                writerRecTranspose.flush();
                System.out.println(" Size n: " + ii + " Time: " + time);
            }
        }
        writerRecTranspose.close();

        // Task 7 Tiled Optimal test
        // String csvFileTask7 = "TiledMultOptimalSTask7.csv";
        // BufferedWriter writerTask7;
        // writerTask7 = new BufferedWriter(new FileWriter(csvFileTask7));
        // String Task7Header = "Size,S,Time";
        // writerTask7.write(Task7Header);
        // writerTask7.newLine();
        // writerTask7.flush();
        // System.out.println("Working on Task7 for finding optimal s for
        // tiledmultiplication... ");
        // for (int i = 2048; i <= 4096; i += 1024) {
        // for (int k = 0; k < 2; k++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(i, i, (int)
        // (Math.sqrt(Math.pow(2, 53) / i) - k));
        // Matrix matrixB = matrixGenerator.generateMatrix(i, i, (int)
        // (Math.sqrt(Math.pow(2, 53) / i) - k));
        // for (int j = i; j >= 2; j = j / 2) {
        // double time = benchmarkTiledMultiplication(matrixA, matrixB, j);
        // String row = "" + i + "," + j + "," + time;
        // writerTask7.write(row);
        // writerTask7.newLine();
        // writerTask7.flush();
        // System.out.println(" Size n: " + i + " s: " + j + " Time: " + time);
        // }
        // }
        // }
        // writerTask7.close();

        // Create benchmark csv for simple Transpose Matrix
        // String csvFileSimpleTranspose = "TransposeMatrixBenchmark.csv";
        // BufferedWriter w1 = new BufferedWriter(new
        // FileWriter(csvFileSimpleTranspose));
        // String headerSimpleTranspose = "Algorithm,Size,Time";
        // w1.write(headerSimpleTranspose);
        // w1.newLine();
        // w1.flush();
        // System.out.println("Working on Transpose Benchmark...");
        // for (int iii = 2048; iii <= 4096; iii += 1024) {
        // for (int kkk = 0; kkk < 2; kkk++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(iii, iii,
        // (int) (Math.sqrt(Math.pow(2, 53) / iii) - kkk));
        // Matrix matrixB = matrixGenerator.generateMatrix(iii, iii,
        // (int) (Math.sqrt(Math.pow(2, 53) / iii) - kkk));
        // double time = benchmarkSimpleTranspose(matrixA, matrixB);
        // String row = "Transpose," + iii + "," + time;
        // w1.write(row);
        // w1.newLine();
        // w1.flush();
        // System.out.println(" Size: " + iii + " Time: " + time);
        // }
        // }
        // w1.close();

        // // (Horse Race) Create the matrixMultiplicationBenchmark.csv
        // String csvFileHorseRace = "matrixMultiplicationBenchmark.csv";
        // BufferedWriter w = new BufferedWriter(new FileWriter(csvFileHorseRace));
        // String header = "Algorithm,Size,Time";
        // w.write(header);
        // w.newLine();
        // w.flush();

        // // Elementary Multiplication
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(2048, 2048, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(2048, 2048, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkMatrixMultiplication(matrixA, matrixB);
        // String row = "elementaryMultiplication,2048," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(3072, 3072, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(3072, 3072, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkMatrixMultiplication(matrixA, matrixB);
        // String row = "elementaryMultiplication,3072," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(4096, 4096, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(4096, 4096, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkMatrixMultiplication(matrixA, matrixB);
        // String row = "elementaryMultiplication,4096," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }

        // // Elementary Multiplication Transposed
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(2048, 2048, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(2048, 2048, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkElementaryMultiplicationTransposed(matrixA,
        // matrixB, 16);
        // String row = "elementaryMultiplicationTransposed,2048," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(3072, 3072, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(3072, 3072, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkElementaryMultiplicationTransposed(matrixA,
        // matrixB, 16);
        // String row = "elementaryMultiplicationTransposed,3072," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(4096, 4096, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(4096, 4096, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkElementaryMultiplicationTransposed(matrixA,
        // matrixB, 16);
        // String row = "elementaryMultiplicationTransposed,4096," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }

        // // Tiled Multiplication
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(2048, 2048, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(2048, 2048, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkTiledMultiplication(matrixA, matrixB, 8);
        // String row = "tiledMultiplication,2048," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(3072, 3072, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(3072, 3072, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkTiledMultiplication(matrixA, matrixB, 8);
        // String row = "tiledMultiplication,3072," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // for (int i = 0; i < 10; i++) {
        // Matrix matrixA = matrixGenerator.generateMatrix(4096, 4096, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // Matrix matrixB = matrixGenerator.generateMatrix(4096, 4096, (int)
        // (Math.sqrt(Math.pow(2, 53) / i)));
        // double elapsedTime = benchmarkTiledMultiplication(matrixA, matrixB, 8);
        // String row = "tiledMultiplication,4096," + elapsedTime;
        // w.write(row);
        // w.newLine();
        // w.flush();
        // }
        // w.close();

    }

}
