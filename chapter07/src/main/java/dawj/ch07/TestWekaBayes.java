/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 28, 2017
 */

package dawj.ch07;

import java.util.List;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Prediction;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestWekaBayes {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("data/AnonFruit.arff");
        Instances train = source.getDataSet();
        train.setClassIndex(3);  // 대상 속성 : 단과일

        NaiveBayes model=new NaiveBayes();
        model.buildClassifier(train);

        Instances test = train;
        Evaluation eval = new Evaluation(test);
        eval.evaluateModel(model,test);
        List <Prediction> predictions = eval.predictions();
        
        int k = 0;
        for (Instance instance : test) {
            double actual = instance.classValue();
            double prediction = eval.evaluateModelOnce(model, instance);
            System.out.printf("%2d.%4.0f%4.0f", ++k, actual, prediction);
            System.out.println(prediction != actual? " *": "");
        }
    }
}

/*
run:
 1.   1   1
 2.   1   1
 3.   1   1
 4.   1   1
 5.   1   1
 6.   0   1 *
 7.   1   1
 8.   0   0
 9.   0   0
10.   0   1 *
11.   1   1
12.   1   1
13.   1   1
14.   1   1
15.   0   0
16.   1   1
*/
