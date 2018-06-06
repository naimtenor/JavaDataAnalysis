/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 13, 2017
 */

package dawj.ch03.time;

import java.util.Iterator;

public class MovingAverage extends TimeSeries<Double> {
    private final TimeSeries parent;
    private final int length;

    public MovingAverage(TimeSeries parent, int length) {
        this.parent = parent;
        this.length = length;
        if (length > parent.size()) {
            throw new IllegalArgumentException("길이가 너무 깁니다.");
        }
        
        double[] tmp = new double[length];  // 평균 계산용 임시 배열
        double sum = 0;
        int i=0;
        Iterator it = parent.iterator();
        for (int j = 0; j < length; j++) {
            sum += tmp[i++] = nextValue(it);
        }
        this.add(System.currentTimeMillis(), sum/length);

        while (it.hasNext()) {
            sum -= tmp[i%length];
            sum += tmp[i++%length] = nextValue(it);
            this.add(System.currentTimeMillis(), sum/length);
        }
    }
    
    /*  현재 Entry에 있는 더블형 값을 반환. 
    */
    private static double nextValue(Iterator it) {
        TimeSeries.Entry<Double> entry = (TimeSeries.Entry)it.next();
        return entry.getEvent();
    }
}
