/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 26, 2017
 */

package dawj.ch07;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;

public class TestWekaJ48 {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("data/AnonFruit.arff");
        Instances instances = source.getDataSet();
        instances.setClassIndex(3);  // 대상 속성: (단과일)
        
        J48 j48 = new J48();  // ID3 알고리즘 확장
        j48.setOptions(new String[]{"-U"});  // 모호하지 않은 트리 사용
        j48.buildClassifier(instances);

        for (Instance instance : instances) {
            double prediction = j48.classifyInstance(instance);
            System.out.printf("%4.0f%4.0f%n", 
                    instance.classValue(), prediction);
        }
    }
}

/*
run:
 1.0   1
 1.0   1
 1.0   1
 1.0   0
 1.0   1
 0.0   0
 1.0   1
 0.0   0
 0.0   0
 0.0   0
 1.0   1
 1.0   1
 1.0   1
 1.0   1
 0.0   0
 1.0   1
*/