/*  Data Analysis with Java
 *  John R. Hubbard
 *  Aug 4, 2017
 */

package dawj.ch11;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Example2 {
    static int m=4, p=2, n=3;

    public static void main(String[] args) {
        //  TO BE COMPLETED BY THE READER
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* ("a", i, k, x)를 읽어 배열 요소 x=a[i,k]로 표현하고,
     * j=1..n에 대해 key=(i,j)와 값 x를 쓴다.
     * 그리고 ("b", k, j, y)를 읽어 y=b[k,j]로 표현하고,
     * i=1..m에 대해 key=(i,j)와 값 y를 쓴다.
     */
    public static void map1(String element, PrintWriter writer) 
    		throws IOException {
    	Scanner input = new Scanner(new File(element));
    	String name = input.next();	// "a" 또는 "b"
    	if (name.equals("a")) {
    		int i = input.nextInt();
    		int k = input.nextInt();
    		double x = input.nextDouble();
    		for (int j = 1; j <= n; j++) {
    			writer.printf("(%d, %d), %.4f\n", i, j, x);
    		}
    	} else {
    		int k = input.nextInt();
    		int j = input.nextInt();
    		double y = input.nextDouble();
    		for (int i = 1; i <= m; i++) {
    			writer.printf("(%d, %d), %.4f\n", i, j, y);
    		}
    	}
    	input.close();
    }
    
    /* 키 (i,j)에 대한 값은 아래와 같다.
     * "a[i,1] a[i,2] ... a[i,p] b[1,j], b[2,j] ... b[p,j]"
     * 리듀스 함수는 다음을 처리한다. a[i,1]*b[1,j] + a[i,2]*b[2,j] + ... + a[i,p]*b[p,j]
     */
    public static void reduce1(String key, String value, PrintWriter writer) 
    		throws IOException {
    	double[] x = new double[p];
    	double[] y = new double[p];
    	Scanner scanner = new Scanner(value);
    	for (int k = 0; k < p; k++) {
    		x[k] = scanner.nextDouble();
    	}
    	for (int k = 0; k < p; k++) {
    		y[k] = scanner.nextDouble();
    	}
    	double sum = 0.0;
    	for (int k = 0; k < p; k++) {
    		sum += x[k]*y[k];
    	}
    	writer.printf("%s %.4f\n", key, sum);
    } 
    
    
































    
    
    
    
    
    

    
    /*  Reads ("a", i, k, x), representing array element x = a[i,k],
        and writes key = (i, j) and value x, for j = 1..n;
        then reads ("b", k, j, y), representing y = b[k,j]
        and writes key = (i, j) and value y, for i = 1..m.
    */
    public static void map(String element, PrintWriter writer) 
            throws IOException {
        Scanner input = new Scanner(new File(element));
        String name = input.next();  // "a" or "b"
        if (name.equals("a")) {
            int i = input.nextInt();
            int k = input.nextInt();
            double x = input.nextDouble();  // x = a[i,k]
            for (int j = 1; j <= n; j++) {
                writer.printf("(%d,%d), %.4f%n", i, j, x);
            }
        } else {  // name = "b"
            int k = input.nextInt();
            int j = input.nextInt();
            double y = input.nextDouble();  // y = b[j,k]
            for (int i = 1; i <= m; i++) {
                writer.printf("(%d,%d), %.4f%n", i, j, y);
            }
        }
        input.close();
    }

    /*  For a key (i,j) the value will be:
        "a[i,1] a[i,2] ... a[i,p] b[1,j] b[2,j] ... b[p,j".
        Reduces to a[i,1]*b[1,j] + a[i,2]*b[2,j] + ... + a[i,p]*b[p,j]. 
    */
    public static void reduce(String key, String value, PrintWriter writer)
            throws IOException {
        double[] x = new double[p];
        double[] y = new double[p];
        Scanner scanner = new Scanner(value);
        for (int k = 0; k < p; k++) {
            x[k] = scanner.nextDouble();
        }
        for (int k = 0; k < p; k++) {
            y[k] = scanner.nextDouble();
        }
        double sum = 0.0;
        for (int k = 0; k < p; k++) {
            sum += x[k]*y[k];
        }
        writer.printf("%s %.4f%n", key, sum);
    }
}
