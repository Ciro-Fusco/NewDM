public class Test {
    public static void main(String[] args) {


        /*
        if (args.length == 21) {

            // Features:
            double[] features = new double[args.length];
            for (int i = 0, l = args.length; i < l; i++) {
                features[i] = Double.parseDouble(args[i]);
            }
*/
            //double[] features ={2,5,0,0,1,0,0,0,1,0,1,0,0,1,0,1,0,1,0,0,0};
            double[] features ={15,15,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,1,0,0,1};
            // Prediction:
            int prediction = RandomForestClassifier.predict(features);
            System.out.println(prediction);
            /*
            0->20
            1->50
            2->100
            3->150
            4->200
            5->300
            6->500
             */

        }
    }
