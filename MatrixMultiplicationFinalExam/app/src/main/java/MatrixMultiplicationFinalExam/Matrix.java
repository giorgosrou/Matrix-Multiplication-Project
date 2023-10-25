package MatrixMultiplicationFinalExam;

public class Matrix {
    /**
     * number of rows in the matrix
     */
    public int rows = 0;
    /**
     * number of columns in the matrix
     */
    public int cols = 0;
    /**
     * reference to underlying data (can be much larger than rows * columns)
     */
    public double[] data = null;
    /**
     * Index of the first element (0,0) in the data array
     */
    public int start = 0;
    /**
     * Underlying row length (the distance from (i,j) to (i+1,j) in the data
     * array)
     */
    public int stride = 0;

    /**
     * The full constructor
     * 
     * @param rows   rows
     * @param cols   columns
     * @param data   reference to data
     * @param start  start index
     * @param stride stride length
     */
    public Matrix(int rows, int cols, double[] data, int start, int stride) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
        this.start = start;
        this.stride = stride;
    }

    /**
     * Initializes an rows * cols matrix of zeros
     * 
     * @param rows rows
     * @param cols columns
     */
    public Matrix(int rows, int cols) {
        this(rows, cols, new double[rows * cols], 0, cols);
    }

    /**
     * Initializes a rows * cols matrix with the given array of length
     * rows*cols
     * 
     * @param rows rows
     * @param cols columns
     * @param data data array of length rows*cols
     */
    public Matrix(int rows, int cols, double[] data) {
        this(rows, cols, data, 0, cols);
    }

    /**
     * Initializes an empty matrix
     */
    public Matrix() {

    }

    /**
     * Returns a string representation of the matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; ++i) {
            if (i > 0)
                sb.append('\n');
            for (int j = 0; j < cols; ++j) {
                if (j > 0)
                    sb.append(" ");
                sb.append(data[start + i * stride + j]);
            }
        }
        return sb.toString();
    }

    public double[] getArray() {
        return data;
    }

    /**
     * A slow bounds-checked helper function to get an element in the array.
     * This function is only good for debugging purposes, don't use in your
     * matrix multiplication routines.
     * 
     * @param i row
     * @param j column
     * @return Element at (i,j)
     */
    public double getSlow(int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[start + i * stride + j];
    }

    /**
     * A slow bounds-checked helper function to set an element in the array.
     * This function is only good for debugging purposes, don't use in your
     * matrix multiplication routines.
     * 
     * @param i row
     * @param j column
     * @param v Value to set at (i,j)
     */
    public void setSlow(int i, int j, double v) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[start + i * stride + j] = v;
    }

    /**
     * @return Returns a deep copy of the matrix
     */
    public Matrix copy() {
        Matrix A = new Matrix(rows, cols);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                A.data[i * cols + j] = data[start + i * stride + j];
            }
        }
        return A;
    }

    /**
     * Performs the O(n^3) elementary multiplication with three nested loops.
     * 
     * @param A Left-hand input of size n*m.
     * @param B Right-hand input of size m*p.
     * @return Matrix C of size n*p satisfying C=AB.
     */
    public static Matrix elementaryMultiplication(Matrix A, Matrix B) {
        Matrix C = new Matrix(A.rows, B.cols);
        elementaryMultiplication(A, B, C);
        return C;
    }

    /**
     * Performs the O(n^3) elementary matrix multiplication in place, that is,
     * computes C += AB. Importantly, the matrix C must be of correct shape,
     * and it is *not* zeroed; this enables us to accumulate products.
     * 
     * @param C Output matrix
     * @param A Left-hand operand
     * @param B Right-hand operand
     */
    public static void elementaryMultiplication(Matrix A, Matrix B, Matrix C) {
        for (int i = 0; i < A.rows; i++) {
            for (int j = 0; j < B.cols; j++) {
                for (int k = 0; k < A.cols; k++) {
                    C.data[i * B.cols + j] += A.data[i * A.cols + k] * B.data[k * B.cols + j];
                }
            }
        }
    }

    /**
     * Returns a transposed copy of the matrix.
     * 
     * @return A transposed copy of the matrix.
     */
    public static Matrix transpose(Matrix A) {
        Matrix TransposedMatrix = new Matrix(A.rows, A.cols);
        transpose(A, TransposedMatrix);
        return TransposedMatrix;
    }

    /**
     * Stores a transposed version of the matrix A into B. Assuming A has
     * m rows and n cols, B should have n rows and m cols. The function does
     * not reallocate any data in B, but simply stores the transposed matrix
     * *in* B.
     * 
     * @param A
     * @param B
     */
    public static void transpose(Matrix A, Matrix B) {
        int n = B.cols;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B.data[B.start + i * B.stride + j] = A.data[A.start + j * A.stride + i];
            }
        }
    }

    /**
     * Recursive transpose
     * 
     * @param A Input matrix
     * @param B Output matrix (must be of correct shape)
     * @param s Minimum size: if the subproblem size is at most this, then the
     *          regular transpose is called.
     */
    public static void transposeRec(Matrix A, Matrix B, int s) {
        int n = B.cols;
        if (n <= s) {
            transpose(A, B);
        } else {
            transposeRec(A.view(0, 0, n / 2, n / 2),
                    B.view(0, 0, n / 2, n / 2), s);
            transposeRec(A.view(0, n / 2, n / 2, n / 2),
                    B.view(n / 2, 0, n / 2, n / 2), s);
            transposeRec(A.view(n / 2, 0, n / 2, n / 2),
                    B.view(0, n / 2, n / 2, n / 2), s);
            transposeRec(A.view(n / 2, n / 2, n / 2, n / 2),
                    B.view(n / 2, n / 2, n / 2, n / 2), s);
        }
    }

    /**
     * Performs the O(n^3) elementary multiplication with three nested loops.
     * A transposed copy of the right-hand operand is constructed before
     * computing the multiplication, using the transposeRec function.
     * 
     * @param A Left-hand input of size n*m.
     * @param B Right-hand input of size m*p.
     * @param s The minimum size parameter for transposeRec.
     * @return Matrix C of size n*p satisfying C=AB.
     */
    public static Matrix elementaryMultiplicationTransposed(Matrix A, Matrix B, int s) {
        Matrix C = new Matrix(A.rows, A.cols);
        Matrix Btransposed = B.copy();
        transposeRec(B, Btransposed, s);
        System.out.println(Btransposed.toString());
        System.out.println();
        for (int i = 0; i < A.rows; i++) {
            for (int j = 0; j < B.cols; j++) {
                for (int k = 0; k < A.cols; k++) {
                    C.data[i * Btransposed.cols + j] += A.data[i * A.cols + k]
                            * Btransposed.data[j * A.cols + k];
                }
            }
        }
        return C;
    }

    /**
     * Performs tiled matrix multiplication using a tile size of s*s.
     * 
     * @param A Left-hand input.
     * @param B Right-hand input.
     * @param s Tile size.
     * @return Matrix C satisfying C=AB.
     */
    public static Matrix tiledMultiplication(Matrix A, Matrix B, int s) {
        int n = A.rows / s;
        Matrix C = new Matrix(A.rows, A.rows);
        // for each tile
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    // perform matrix multiplication within each tile
                    for (int ii = i * s; ii < (i + 1) * s; ++ii) {
                        for (int jj = j * s; jj < (j + 1) * s; ++jj) {
                            for (int kk = k * s; kk < (k + 1) * s; ++kk) {
                                C.data[ii * (n * s) + jj] += A.data[ii * (n * s) + kk] * B.data[kk * (n * s) + jj];
                            }
                        }
                    }
                }
            }
        }
        return C;
    }

    /**
     * Returns a view (a shallow matrix header) of the submatrix with the
     * given number of rows and columns whose upper-left corner is at (i0,j0).
     * No data is copied.
     * 
     * @param i0   Upper-left row.
     * @param j0   Upper-left column.
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @return A shallow view of the submatrix.
     */
    public Matrix view(int i0, int j0, int rows, int cols) {
        return new Matrix(rows, cols, data, start + stride * i0 + j0, stride);
    }

    /**
     * Recursive matrix multiplication
     * 
     * @param A Left-hand operand
     * @param B Right-hand operand
     * @param C Output matrix
     * @param s Subproblem size
     */
    public static void recursiveMultiplication(Matrix A, Matrix B, Matrix C, int s) {
        /* Fill here the missing implementation */
    }

    /**
     * Computes the matrix product using Strassen's algorithm.
     * 
     * @param A Left-hand operand
     * @param B Right-hand operand
     * @param C Output matrix such that C=AB
     * @param s Minimum size for recursion: for subproblem sizes at most this,
     *          an O(n^3) algorithm will be used.
     */
    public static void strassen(Matrix A, Matrix B, Matrix C, int s) {
        /* Fill here the missing implementation */
    }

    /**
     * Set all elements of the matrix equal to v.
     * 
     * @param v Target value.
     */
    public void setAll(double v) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                data[start + i * stride + j] = v;
            }
        }
    }

    /**
     * Computes A += B in-place
     * 
     * @param B The right-hand-side operand
     */
    public void add(Matrix B) {
        /* Fill here the missing implementation */
    }

    /**
     * Returns a new matrix C satisfying C = A+B
     * 
     * @param A Left-hand operand
     * @param B Right-hand operan
     * @return Matrix C satisfying C=A+B
     */
    public static Matrix add(Matrix A, Matrix B) {
        /* Fill here the missing implementation */
        return null;
    }

    /**
     * Stores A+B into C. Shapes of all matrices must match.
     * 
     * @param A Left-hand operand
     * @param B Right-hand operand
     * @param C Output matrix
     */
    public static void add(Matrix A, Matrix B, Matrix C) {
        /* Fill here the missing implementation */
    }

    /**
     * Computes A -= B in-place
     * 
     * @param B The right-hand-side operand
     */
    public void sub(Matrix B) {
        /* Fill here the missing implementation */
    }

    /**
     * Returns a new matrix C satisfying C = A-B
     * 
     * @param A Left-hand operand
     * @param B Right-hand operan
     * @return Matrix C satisfying C=A-B
     */
    public static Matrix sub(Matrix A, Matrix B) {
        /* Fill here the missing implementation */
        return null;
    }

    /**
     * Stores A-B into C. Shapes of all matrices must match.
     * 
     * @param A Left-hand operand
     * @param B Right-hand operand
     * @param C Output matrix
     */
    public static void sub(Matrix A, Matrix B, Matrix C) {
        /* Fill here the missing implementation */
    }

    /**
     * Returns true iff that is a Matrix that corresponds in shape to this and
     * all elements of this and that compare equal.
     * 
     * @param that The right hand side operand
     */
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Matrix))
            return false;
        Matrix M = (Matrix) that;
        if (cols != M.cols)
            return false;
        if (rows != M.rows)
            return false;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (data[start + i * stride + j] != M.data[M.start + i * M.stride + j])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        // Testing elemenary multiplication algorithm

        // double[] Adata = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
        // 1.0, 1.0, 1.0, 1.0, 1.0 };

        // double[] Bdata = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
        // 1.0, 1.0, 1.0, 1.0, 1.0 };

        // double[] Adata = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
        // 12.0, 13.0, 14.0, 15.0, 16.0, 17.0,
        // 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 29.0, 30.0,
        // 31.0, 32.0, 33.0, 34.0,
        // 35.0, 36.0, 37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0,
        // 48.0, 49.0, 50.0, 51.0,
        // 52.0,
        // 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0, 61.0, 62.0, 63.0, 64.0 };
        // double[] Bdata = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
        // 12.0, 13.0, 14.0, 15.0, 16.0, 17.0,
        // 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 29.0, 30.0,
        // 31.0, 32.0, 33.0, 34.0,
        // 35.0, 36.0, 37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0,
        // 48.0, 49.0, 50.0, 51.0,
        // 52.0,
        // 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0, 61.0, 62.0, 63.0, 64.0 };
        // Matrix A = new Matrix(8, 8, Adata, 0, 8);
        // Matrix B = new Matrix(8, 8, Bdata, 0, 8);
        // Matrix C = elementaryMultiplication(A, B);
        // System.out.println(C.toString());
        // System.out.println();

        // Testing simple transpose algorithm

        // double[] Ddata = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
        // 12.0, 13.0, 14.0, 15.0, 16.0, 17.0,
        // 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 29.0, 30.0,
        // 31.0, 32.0, 33.0, 34.0,
        // 35.0, 36.0, 37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0,
        // 48.0, 49.0, 50.0, 51.0,
        // 52.0,
        // 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0, 61.0, 62.0, 63.0, 64.0 };
        // Matrix D = new Matrix(8, 8, Ddata, 0, 8);
        // Matrix E = new Matrix(D.rows, D.cols);
        // transpose(D, E);
        // System.out.println(E.toString());
        // System.out.println();

        // Testing recursive transpose algorithm

        // Matrix F = new Matrix(D.rows, D.cols);
        // transposeRec(D, F, 2);
        // System.out.println(F.toString());
        // System.out.println();

        // Testing transposed matrix multiplication

        // Matrix G = elementaryMultiplicationTransposed(A, B, 2);
        // System.out.println(G.toString());

        // Testing Tile Multiplication

        // Matrix D = tiledMultiplication(A, B, 2);
        // System.out.println(D.toString());
        // System.out.println();

    }
}
