/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 25, 2017
 */

package dawj.ch04;

import java.util.Random;

public class Simulation {
    static final Random RANDOM = new Random();
    static final int n = 5;  // number of dice used
    static final int N = 1000000;  // 1,000,000 simulations
    
    public static void main(String[] args) {
        double[] dist = new double[n+1];
        for (int i = 0; i < N; i++) {
            int x = numRedDown(n);
            ++dist[x];
        }
        for (int i = 0; i <= n; i++) {
            System.out.printf("%4d%8.4f%n", i, dist[i]/N);
        }
    }
    
    /*  한 면은 붉은색이고 나머지 세 면은 녹색인 사면체 주사위를 던저는 실험.
        아래면이 붉은색이 아닐 경우 false를 반환한다.
    */
    static boolean redDown() {
        int m = RANDOM.nextInt(4);  // 0 <= m < 4
        return (m == 0);            // P(m = 0) = 1/4
    } 
    
    /*  한 면은 붉은색이고 나머지 세 면은 녹색인 사면체 주사위를 던저는 실험.
        아래면이 붉은색일 경우의 수를 센다.
    */
    static int numRedDown(int n) {
        int numRed = 0;
        for (int i = 0; i < n; i++) {
            if (redDown()) {
                ++numRed;
            }
        }
        return numRed;
    }
}


