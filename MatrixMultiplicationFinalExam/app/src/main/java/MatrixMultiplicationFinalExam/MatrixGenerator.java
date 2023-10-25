package MatrixMultiplicationFinalExam;

import java.util.Random;

public class MatrixGenerator {

    public Matrix generateMatrix(int rows, int cols, int seed) {
        Matrix matrix = new Matrix(rows, cols);
        Random random = new Random(seed);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setSlow(i, j, random.nextDouble());
            }
        }
        return matrix;
    }
}
