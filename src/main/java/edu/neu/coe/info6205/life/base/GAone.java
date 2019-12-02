package edu.neu.coe.info6205.life.base;


import java.util.Random;

 
public class GAone {
	public static String getPattern() {
		int row = 10, colnum = 10; 
		int[][] grid = new int[row][colnum];
//		int[][] gri = new int[row][colnum];
//		Random r1 = new Random();
//		int[][] resultgrid = new int[row][colnum];
//		for(int i = 0; i< M;i++) {
//			for(int j = 0; j< N; j++) {
//				if(r1.nextInt(1000)>500) {
//					grid[i][j]=1;
//				}else {
//					grid[i][j]=0;
//				}
//			}
//		}
		Random r = new Random();
		Random r1 = new Random();
		int number1 = r1.nextInt(row-1);
		int number2 = r1.nextInt(colnum-1);
		grid[number1][number2]=1;
		grid[number1][number2+1]=1;
		grid[number1+1][number2+1]=1;
		int number3 = 1+ r1.nextInt(row-1);
		int number4 = r1.nextInt(colnum-2);
		grid[number3][number4]=1;
		grid[number3][number4+1]=1;
		grid[number3-1][number4+1]=1;
		grid[number3-1][number4+2]=1;
		int number = 1000;
		//int number = 1;
		String t = "";
		while(number>=0) {
			grid = nextgrid(grid, row, colnum);
			number--;
		}
		t = genr(grid,row,colnum);
		return t;
}

public static int[][] nextgrid(int grid[][], int row, int colnum){ 
    int[][] nextgeneration = new int[row][colnum]; 

    for (int i = 1; i < row - 1; i++) { 
        for (int j = 1; j < colnum- 1; j++){ 
            int neighboursofcell = 0; 
            for (int k = -1; k <= 1; k++){
                for (int l = -1; l <= 1; l++){
                	neighboursofcell = neighboursofcell + grid[i + k][j + l]; 
                }
            }
            neighboursofcell = neighboursofcell - grid[i][j]; 
            if (((grid[i][j] == 1) && (neighboursofcell < 2))||((grid[i][j] == 1) && (neighboursofcell > 3))){ 
                nextgeneration[i][j] = 0; 
            }
            else if((grid[i][j] == 0) && (neighboursofcell == 3)) {
				nextgeneration[i][j] = 1;
			}
            else
                nextgeneration[i][j] = grid[i][j]; 
        } 
    }
    return nextgeneration;
}
//public static void main(String[] args) {
//	GAone ga = new GAone();
//	String aa = ga.getPattern();
//	System.out.println(aa);
//}
public static String genr(int grid[][], int M, int N) {
	String target = "";
		 for (int i = 0; i < M; i++) 
		    { 
		        for (int j = 0; j < N; j++) 
		        { 
		            if (grid[i][j] == 1) {
		            	if(target=="") {
		            		target = target + i +" "+ j;
		            	}else {
		            		target = target + "," +" "+i+" "+j;
		            	}
		            }
		             
		        } 
		    }
		 return target;
	}

} 
   