public class Test{
    public static void main(String[] args){
        int[] layerInfo = {6, 5, 2};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);

        double[] inputs = {0, 1, 0, 0, 0, 1};
        double[] expectedOutputs = {1.0, 0.0};

        double targetMSE = 0; // Define o MSE alvo desejado
        int maxIterations = 200000; // Define o número máximo de iterações permitidas

        int iteration = 0;
        double currentMSE = Double.MAX_VALUE;

        // Loop principal
        while (currentMSE > targetMSE && iteration < maxIterations) {
            neuralNetwork.runFeedForward(inputs); // Executa o feedforward
            neuralNetwork.calculateMSE(expectedOutputs); // Calcula o MSE atual
            currentMSE = neuralNetwork.getMSE(); // Obtém o MSE atual
            System.out.println("Iteration " + iteration + ", MSE: " + currentMSE);

            neuralNetwork.runBackpropagation(expectedOutputs, 0.01); // Executa a retropropagação

            iteration++;
        }

        System.out.println("Final MSE: " + currentMSE);
        neuralNetwork.printOutputs();

        // int[] layerInfo = {2, 3, 2};
        // NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);
        // neuralNetwork.getLayers()[1].getNeurons()[0].updateBias(-0.1);
        // neuralNetwork.getLayers()[1].getNeurons()[0].updateWeight(0, 0.1);
        // neuralNetwork.getLayers()[1].getNeurons()[0].updateWeight(1, -0.1);

        // neuralNetwork.getLayers()[1].getNeurons()[1].updateBias(-0.1);
        // neuralNetwork.getLayers()[1].getNeurons()[1].updateWeight(0, 0.1);
        // neuralNetwork.getLayers()[1].getNeurons()[1].updateWeight(1, 0.1);

        // neuralNetwork.getLayers()[1].getNeurons()[2].updateBias(0.1);
        // neuralNetwork.getLayers()[1].getNeurons()[2].updateWeight(0, -0.1);
        // neuralNetwork.getLayers()[1].getNeurons()[2].updateWeight(1, -0.1);

        // neuralNetwork.getLayers()[2].getNeurons()[0].updateBias(-0.1);
        // neuralNetwork.getLayers()[2].getNeurons()[0].updateWeight(0, 0.1);
        // neuralNetwork.getLayers()[2].getNeurons()[0].updateWeight(1, 0.0);
        // neuralNetwork.getLayers()[2].getNeurons()[0].updateWeight(2, 0.1);

        // neuralNetwork.getLayers()[2].getNeurons()[1].updateBias(0.1);
        // neuralNetwork.getLayers()[2].getNeurons()[1].updateWeight(0, -0.1);
        // neuralNetwork.getLayers()[2].getNeurons()[1].updateWeight(1, 0.1);
        // neuralNetwork.getLayers()[2].getNeurons()[1].updateWeight(2, -0.1);


        // double[] inputs = {1, 1};
        // double[] expectedOutputs = {1, 0};
        // neuralNetwork.runFeedForward(inputs);
        // neuralNetwork.printOutputs();
        // neuralNetwork.runBackpropagation(expectedOutputs, 0.5);

    }
}
