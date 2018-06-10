/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 22, 2017
 */

package dawj.ch07;

/*  http://slidewiki.org/deck/1218_id3-example#tree-1218-slide-17752-5-view
*/
public class ComputeGain {
    public static void main(String[] args) {
        System.out.printf("h(11,16) = %.4f%n", h(11,16));
        System.out.println("Gain(크기):");
        System.out.printf("\th(3,5) = %.4f%n", h(3,5));
        System.out.printf("\th(6,7) = %.4f%n", h(6,7));
        System.out.printf("\th(2,4) = %.4f%n", h(2,4));
        System.out.printf("\tg({3,6,2},{5,7,4}) = %.4f%n", 
                    g(new int[]{3,6,2},new int[]{5,7,4}));
        System.out.println("Gain(색상):");
        System.out.printf("\th(3,4) = %.4f%n", h(3,4));
        System.out.printf("\th(3,5) = %.4f%n", h(3,5));
        System.out.printf("\th(2,3) = %.4f%n", h(2,3));
        System.out.printf("\th(2,4) = %.4f%n", h(2,4));
        System.out.printf("\tg({3,3,3,2},{4,5,3,4}) = %.4f%n", 
                    g(new int[]{3,3,3,2},new int[]{4,5,3,4}));
        System.out.println("Gain(표면):");
        System.out.printf("\th(4,7) = %.4f%n", h(4,7));
        System.out.printf("\th(4,6) = %.4f%n", h(4,6));
        System.out.printf("\th(3,3) = %.4f%n", h(3,3));
        System.out.printf("\tg({4,4,3},{7,6,3}) = %.4f%n", 
                    g(new int[]{4,4,3},new int[]{7,6,3}));
        System.out.println("Gain(크기|매끈):");
        System.out.printf("\th(1,3) = %.4f%n", h(1,3));
        System.out.printf("\th(3,3) = %.4f%n", h(3,3));
        System.out.printf("\tg({1,3,0},{3,3,1}) = %.4f%n", 
                    g(new int[]{1,3,0},new int[]{3,3,1}));
        System.out.println("Gain(색상|매끈):");
        System.out.printf("\th(2,3) = %.4f%n", h(2,3));
        System.out.printf("\tg({2,2,0},{3,2,2}) = %.4f%n", 
                    g(new int[]{2,2,0},new int[]{3,2,2}));
        System.out.println("Gain(크기|거침):");
        System.out.printf("\th(3,6) = %.4f%n", h(3,6));
        System.out.printf("\th(1,2) = %.4f%n", h(1,2));
        System.out.printf("\tg({2,1,1},{2,2,2}) = %.4f%n", 
                    g(new int[]{2,1,1},new int[]{2,2,2}));
        System.out.println("Gain(색상|거침):");
        System.out.printf("\th(4,6) = %.4f%n", h(4,6));
        System.out.printf("\tg({1,1,1},{2,2,2}) = %.4f%n", 
                    g(new int[]{1,0,2,1},new int[]{1,2,2,1}));
    }
    
    /*  분리할 데이터 {A1, A2, ...}의 이득값을 얻는다. 
        Ai는 n[i] 의 포인트와, m[i]의 선호 포인트를 가진다.
    */
    public static double g(int[] m, int[] n) {
        int sm = 0, sn = 0;
        double nsh = 0.0;
        for (int i = 0; i < m.length; i++) {
            sm += m[i];
            sn += n[i];
            nsh += n[i]*h(m[i],n[i]);
        }
        return h(sm, sn) - nsh/sn;
    }
    
    /*  n개 중 m개의 선호값에 대한 엔트로피 계산
    */
    public static double h(int m, int n) {
        if (m == 0 || m == n) {
            return 0;
        }
        double p = (double)m/n, q = 1 - p;
        return -p*lg(p) - q*lg(q);
    }
    
    /*  x의 이진 로그 값 계산
    */
    public static double lg(double x) {
        return Math.log(x)/Math.log(2);
    }
}
