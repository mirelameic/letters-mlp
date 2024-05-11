public class Test{
    public static void main(String[] args){
        /* 2 neurônios de entrada, 5 neurônios ocultos e 2 neurônios de saída */
        int[] layerInfo = {2, 5, 2};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);
        System.out.println(neuralNetwork);

        /* 2 dados de entrada = 0.5, 1.0 */
        double[] inputs = {0.5, 1};
        neuralNetwork.getOutputs(inputs);
        neuralNetwork.printOutputs();
    }
}
