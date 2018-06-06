/*  Java Data Analysis
 *  John R. Hubbard
 *  Sep 13, 2017
 */

package dawj.ch02.merge;

import java.util.Scanner;

class Country implements Comparable {
    protected String name;
    protected int population;
    
    public Country(String name, int population) {
        this.name = name;
        this.population = population;
    }

    /*  다음 줄 값을 입력 받을 때 새로운 Country 객체를 생성.
        더이상 입력 줄이 없을 경우 
        새로운 객체의 필드는 널 값으로 남게 됨.
    */
    public Country(Scanner in) {
        if (in.hasNextLine()) {
            this.name = in.next();
            this.population = in.nextInt();
        }
    }
    
    public boolean isNull() {
        return this.name == null;
    }
    
    @Override
    public int compareTo(Object object) {
        Country that = (Country) object;
        return this.population - that.population;
    }

    @Override
    public String toString() {
        return String.format("%-10s %,12d", name, population);
    }
}
