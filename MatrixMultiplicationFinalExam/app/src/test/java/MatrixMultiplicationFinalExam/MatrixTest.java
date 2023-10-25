package MatrixMultiplicationFinalExam;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MatrixTest {

    // Testing the matrix multiplication algorithms 

    @Test
    public void testMultiplication1x1(){
        double[] Adata = {1.0};
        double[] Bdata = {1.0};

        Matrix A = new Matrix(1,1,Adata);
        Matrix B = new Matrix(1,1, Bdata);

        double[] Cdata = {1.0};
        double[] expected = new Matrix(1,1, Cdata).data;

        int s = 1;


        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,s).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,s).data;
        
        // Elementary multiplication
        assertArrayEquals(expected, actualElemMul, 0); 
        // Transposed multiplication   
        assertArrayEquals(expected, actualElemMulTrans, 0);  
        // Tiled multiplication  
        assertArrayEquals(expected, actualTiledMul, 0);    
    }

    public void testMultiplication2x2(){
        double[] Adata = {1.0, 2.0, 3.0, 4.0};
        double[] Bdata = {4.0, 2.0, 3.0, 4.0};

        Matrix A = new Matrix(1,1,Adata);
        Matrix B = new Matrix(1,1, Bdata);

        double[] Cdata = {1.0};
        double[] expected = new Matrix(1,1, Cdata).data;

        int s = 1;


        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,s).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,s).data;
        
        // Elementary multiplication
        assertArrayEquals(expected, actualElemMul, 0); 
        // Transposed multiplication   
        assertArrayEquals(expected, actualElemMulTrans, 0);  
        // Tiled multiplication  
        assertArrayEquals(expected, actualTiledMul, 0);    
    }



    @Test
    public void testMultiplication4x4(){
        double[] Adata = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
            12.0, 13.0, 14.0, 15.0, 16.0 };
        double[] Bdata = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
            12.0, 13.0, 14.0, 15.0, 16.0 };

        Matrix A = new Matrix(4,4, Adata);
        Matrix B = new Matrix(4,4, Bdata);

        double[] Cdata = {90.0, 100.0, 110.0, 120.0, 202.0, 228.0, 254.0, 280.0, 314.0, 
            356.0, 398.0, 440.0, 426.0, 484.0, 542.0, 600.0};
        double[] expected = new Matrix(2,2, Cdata).data;

        int s = 4;

        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,s).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,s).data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualElemMul, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualElemMulTrans, 0);
        // Tiled multiplication
        assertArrayEquals(expected, actualTiledMul, 0);    
    }

    @Test
    public void testMultiplication8x8(){
        Matrix A = new Matrix(8,8);
        Matrix B = new Matrix(8,8);

        A.setAll(2.0);
        B.setAll(1.0);

        Matrix C = new Matrix(8,8);
        C.setAll(16);
        double[] expected = C.data;

        int s = 8;

        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,s).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,s).data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualElemMul, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualElemMulTrans, 0);
        // Tiled multiplication
        assertArrayEquals(expected, actualTiledMul, 0);    
    }


    @Test
    public void testMultiplicationAall0s(){
        double[] Adata = {0.0, 0.0, 0.0, 0.0};
        double[] Bdata = {12.0, 11.0, 10.0, 9.0};

        Matrix A = new Matrix(2,2, Adata);
        Matrix B = new Matrix(2,2, Bdata);

        double[] Cdata = {0.0, 0.0, 0.0, 0.0};
        double[] expected = new Matrix(2,2, Cdata).data;

        int s = 4;

        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,s).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,s).data;

        // Elementary multiplication
        assertArrayEquals(expected, actualElemMul, 0); 
        // Transposed multiplication   
        assertArrayEquals(expected, actualElemMulTrans, 0);  
        // Tiled multiplication  
        assertArrayEquals(expected, actualTiledMul, 0);    
    }


    @Test
    public void testMultiplicationAll1s(){
        double[] Adata = {1.0, 1.0, 1.0, 1.0, 1.0};
        double[] Bdata = {1.0, 1.0, 1.0, 1.0, 1.0};
        
        Matrix A = new Matrix(2,2, Adata);
        Matrix B = new Matrix(2,2, Bdata);

        double[] Cdata = {2.0, 2.0, 2.0, 2.0};

        double[] expected = new Matrix(2,2, Cdata).data;


        int s = 2;
    
        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,s).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,s).data;
    
        // Elementary multiplication
        assertArrayEquals(expected, actualElemMul, 0); 
        // Transposed multiplication   
        assertArrayEquals(expected, actualElemMulTrans, 0);  
        // Tiled multiplication  - doesn't work for all 1s
        assertArrayEquals(expected, actualTiledMul, 0);    
        
    }

    @Test
    public void testMultiplicationLarge(){
        Matrix A = new Matrix(1024,1024);
        Matrix B = new Matrix(1024,1024);

        A.setAll(2.0);
        B.setAll(1.0);

        Matrix C = new Matrix(1024,1024);
        C.setAll(2048); // 2 * 1042
        double[] expected = C.data;


        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,16).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,8).data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualElemMul, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualElemMulTrans, 0);
        // Tiled multiplication
        assertArrayEquals(expected, actualTiledMul, 0);    
    }

    @Test
    public void testMultiplicationLargeNum(){
        double[] Adata = {1000000.0, 1000000.0, 1000000.0, 1000000.0};
        double[] Bdata = {1000000.0, 1000000.0, 1000000.0, 1000000.0};

        Matrix A = new Matrix(2,2, Adata);
        Matrix B = new Matrix(2,2, Bdata);



        Matrix C = new Matrix(2,2);
        C.setAll(2000000000000.0); // 2 * 1M * 1M
        double[] expected = C.data;


        double[] actualElemMul = Matrix.elementaryMultiplication(A,B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,1).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A,B,1).data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualElemMul, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualElemMulTrans, 0);
        // Tiled multiplication
        assertArrayEquals(expected, actualTiledMul, 0);    
    }

    
    @Test
    public void testMultiplicationEmpty(){
        Matrix A = new Matrix(2,2);
        Matrix B = new Matrix(2,2);

        double[] expected = new Matrix(2,2).data;

        double[] actualElemMul = Matrix.elementaryMultiplication(A, B).data;
        double[] actualElemMulTrans = Matrix.elementaryMultiplicationTransposed(A,B,1).data;
        double[] actualTiledMul = Matrix.tiledMultiplication(A, B,1).data;


        // Elementary multiplication
        assertArrayEquals(expected, actualElemMul,0);
        // Transposed multiplication   
        assertArrayEquals(expected, actualElemMulTrans, 0);  
        // Tiled multiplication  
       assertArrayEquals(expected, actualTiledMul, 0);    
    }
  

    // Testing the transpose algortihms

    @Test
    public void testTranspose1x1(){
        double[] A1data = { 1.0};
        double[] A2data = { 1.0};

        Matrix A1 = new Matrix(1,1, A1data);
        Matrix A2 = new Matrix(1,1, A2data);
        Matrix AT2 = new Matrix(1,1);


        double[] Cdata = {1.0};
        double[] expected = new Matrix(2,2, Cdata).data;

        int s = 1;

        double[] actualTrans = Matrix.transpose(A1).data;

        Matrix.transposeRec(A2,AT2,s);
        
        double[] actualTransRec = AT2.data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualTrans, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualTransRec, 0);

    }

    @Test
    public void testTranspose2x2(){
        double[] A1data = { 1.0, 2.0, 3.0, 4.0};
        double[] A2data = { 1.0, 2.0, 3.0, 4.0 };

        Matrix A1 = new Matrix(2,2, A1data);
        Matrix A2 = new Matrix(2,2, A2data);
        Matrix AT2 = new Matrix(2,2);


        double[] Cdata = {1.0, 3.0, 2.0, 4.0};
        double[] expected = new Matrix(2,2, Cdata).data;

        int s = 2;

        double[] actualTrans = Matrix.transpose(A1).data;

        Matrix.transposeRec(A2,AT2,s);
        
        double[] actualTransRec = AT2.data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualTrans, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualTransRec, 0);

    }

    @Test
    public void testTranspose4x4(){
        double[] A1data = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
            12.0, 13.0, 14.0, 15.0, 16.0 };
        double[] A2data = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
            12.0, 13.0, 14.0, 15.0, 16.0 };

        Matrix A1 = new Matrix(4,4, A1data);
        Matrix A2 = new Matrix(4,4, A2data);
        Matrix AT2 = new Matrix(4,4);


        double[] Cdata = {1.0, 5.0, 9.0, 13.0, 2.0, 6.0, 10.0, 14.0, 3.0, 7.0, 11.0, 15.0, 4.0, 8.0, 12.0, 16.0};
        double[] expected = new Matrix(2,2, Cdata).data;

        int s = 4;

        double[] actualTrans = Matrix.transpose(A1).data;

        Matrix.transposeRec(A2,AT2,s);
        
        double[] actualTransRec = AT2.data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualTrans, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualTransRec, 0);

    }

    @Test
    public void testTransposeAll0s(){
        double[] A1data = { 0.0, 0.0, 0.0, 0.0};
        double[] A2data = { 0.0, 0.0, 0.0, 0.0};

        Matrix A1 = new Matrix(2,2, A1data);
        Matrix A2 = new Matrix(2,2, A2data);
        Matrix AT2 = new Matrix(2,2);


        double[] Cdata = { 0.0, 0.0, 0.0, 0.0};
        double[] expected = new Matrix(2,2, Cdata).data;

        int s = 4;

        double[] actualTrans = Matrix.transpose(A1).data;

        Matrix.transposeRec(A2,AT2,s);
        
        double[] actualTransRec = AT2.data;

        // Elementary multiplication 
        assertArrayEquals(expected, actualTrans, 0);
        // Transposed multiplication
        assertArrayEquals(expected, actualTransRec, 0);

    }





    
    
}
