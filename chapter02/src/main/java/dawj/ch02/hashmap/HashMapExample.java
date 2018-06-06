/*  Java Data Analysis
 *  John R. Hubbard
 *  Sep 13, 2017
 */

package dawj.ch02.hashmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        File dataFile = new File("data/Countries.dat");
        HashMap<String,Integer> dataset = new HashMap();
        try {
            Scanner input = new Scanner(dataFile);
            while (input.hasNext()) {
                String country = input.next();
                int population = input.nextInt();
                dataset.put(country, population);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        System.out.printf("dataset.size(): %d%n", dataset.size());
        System.out.printf("dataset.get(\"페루\"): %,d%n", dataset.get("페루"));
        dataset.put("페루", 31000000);
        System.out.printf("dataset.size(): %d%n", dataset.size());
        System.out.printf("dataset.get(\"페루\"): %,d%n", dataset.get("페루"));
    }
}
