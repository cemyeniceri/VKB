/**
 * 
 */
package com.example.VKB;

/**
 * @author CEM
 *
 */
public class IcKonFunc {

	/** The Least squares algorithm for time-of-arrival-based transmitter location*/
	/** enAzKareler method is obtained from Performance Comparison of TOA and TDOA Based Location Estimation
	 *  Algorithms in LOS Environment */	
public static double [][] enAzKareler(double mobile[][],double dist[]){

		double kMatrix [] = new double[mobile.length]; 
		double aMtarix[][] = new double[(mobile.length)-1][3];
		double aMtarixTranspoze[][] = new double[3][(mobile.length)-1];
		double temp1[][] = new double[3][3];
		double temp2[][] = new double[3][3];
		double temp3[][] = new double[3][(mobile.length)-1];
		double bMatrix[][] = new double[(mobile.length)-1][1];	
		double calculatedTransmitter[][] = new double[3][1];
		
		
		/** calculatedTransmitter value will be calculated from 2*AMatrix*calculatedTransmitter = bMatrix */
		/** calculatedTransmitter=((1/2)*((((aMtarixTranspoze*aMtarix)^(-1))*aMtarixTranspoze)*bMatrix)) */
		
		/** k Matrix from The Least Squares Algorithm */
		for(int i=0; i<mobile.length; i++){
			for(int j=0; j<mobile[i].length; j++)
				kMatrix[i] += kareAl(mobile[i][j]);
		}
		
		/** A Matrix from The Least Squares Algorithm */
		for(int i=0; i<aMtarix.length; i++){
			for(int j=0; j<aMtarix[i].length; j++){
				aMtarix[i][j] = mobile[i+1][j]-mobile[0][j];
			} 
		}
		
		/** B Matrix from The Least Squares Algorithm */
		for(int i=0; i<(dist.length)-1; i++){
			bMatrix[i][0] = kareAl(dist[0])-kareAl(dist[i+1])+(kMatrix[i+1])-(kMatrix[0]);
		}
		
		aMtarixTranspoze = transpoze(aMtarix);
		temp1 = matrixMultiply(aMtarixTranspoze, aMtarix);
		temp2 = matrixInverse(temp1);
		temp3 = matrixMultiply(temp2, aMtarixTranspoze);
		
		for(int i=0; i<temp3.length; i++){
			for(int j=0; j<temp3[i].length; j++){
				temp3[i][j] = temp3[i][j]*0.5;
			}
		}
		
		calculatedTransmitter = matrixMultiply(temp3, bMatrix);
		return calculatedTransmitter;
	}

	/** square function */
	public static double kareAl(double x){
		return x*x;
	}
	
	/** dynamic matrix multiplication */
	public static double [][] matrixMultiply(double a[][],double b[][]){
		double [][] result = new double [a.length][b[0].length]; /** b[0].length gets number of column  */
		for(int i=0; i<a.length; i++){
			for(int j=0; j<b[0].length; j++){
				for(int k=0; k<b.length; k++){
					result[i][j] += a[i][k]*b[k][j];
				}
			}
		}
		return result;
	}
		
	/** dynamic matrix inverse function*/
	public static double[][] matrixInverse(double a[][]){
		double [][]identityMatrix = new double[a.length][a[0].length];
		double d,k;
		/** loading the identity matrix */
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a.length; j++){
				if(i == j)
					identityMatrix[i][j] = 1;
				else
					identityMatrix[i][j] = 0;
			}
		}
		
		for(int i=0; i<a.length; i++){
			d = a[i][i];
			for(int j=0; j<a.length; j++){
				a[i][j] = a[i][j]/d;
				identityMatrix[i][j] = identityMatrix[i][j]/d;		
			}
			for(int x=0; x<a.length; x++){
				if(x!=i){
					k = a[x][i];
					for(int j=0; j<a.length; j++){
						a[x][j] = a[x][j]-(a[i][j]*k);
						identityMatrix[x][j] = identityMatrix[x][j]-(identityMatrix[i][j]*k);
						}
					}
			}
		}
		
		return identityMatrix;	
	}
	
	/** dynamic matrix transpose function */
	 public static double[][] transpoze(double a[][]){
		 double [][]trans = new double[a[0].length][a.length];
		for(int i=0; i<a[0].length; i++){
			for(int j=0; j<a.length; j++){
				trans[i][j] = a[j][i];
			}
		}
		return trans;
	}
	 
}
