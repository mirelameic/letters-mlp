public class Test{
    public static void main(String[] args){
        /* inicializando a rede neural com 
        2 neurônios de entrada, 5 neurônios ocultos e 2 neurônios de saída */
        int[] layerInfo = {2, 5, 2};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);
        System.out.println(neuralNetwork);

        /* feedforward com 2 dados de entrada = 0.5, 1.0 */
        double[] inputs = {0.5, 1};
        neuralNetwork.runFeedForward(inputs);
        neuralNetwork.printOutputs();

        /* calculo do erro com base nos resultados esperados */
        double[] expectedOutputs = {0.3, 0.1};
        // Layer[] test = neuralNetwork.getLayers();
        // test[2].calculateOutputSquaredErrors(expectedOutputs);
        // test[2].printOutputSquarredErrors();
        neuralNetwork.runBackpropagation(expectedOutputs, 0.1);
    }
}
