public class Main{
    public static void main(String[] args){
        /* 2 neurônios de entrada, 5 neurônios ocultos e 2 neurônios de saída */
        int[] layerSizes = {2, 5, 2};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerSizes);
        System.out.println(neuralNetwork);

        /* 2 dados de entrada = 0.5, 1.0 */
        double[] inputs = {0.5, 1};
        double[] outputs = neuralNetwork.getOutputs(inputs);
        
        System.out.println("---- outputs ----");
        for (double output : outputs){
            System.out.println(output);
        }
        System.out.println();
    }
}
