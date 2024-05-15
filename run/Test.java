public class Test{
    public static void main(String[] args){
        int[] layerInfo = {6, 5, 2};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);

        double[] inputs = {0, 1, 0, 0, 0, 1};
        double[] expectedOutputs = {1.0, 0.0};

        double targetMSE = 0.001; // Define o MSE alvo desejado
        int maxIterations = 2000; // Define o número máximo de iterações permitidas

        int iteration = 0;
        double currentMSE = Double.MAX_VALUE;

        // Loop principal
        while (currentMSE > targetMSE && iteration < maxIterations) {
            neuralNetwork.runFeedForward(inputs); // Executa o feedforward
            neuralNetwork.calculateMSE(expectedOutputs); // Calcula o MSE atual
            currentMSE = neuralNetwork.getMSE(); // Obtém o MSE atual
            System.out.println("Iteration " + iteration + ", MSE: " + currentMSE);

            neuralNetwork.runBackpropagation(expectedOutputs, 0.1); // Executa a retropropagação

            iteration++;
        }

        System.out.println("Final MSE: " + currentMSE);
        neuralNetwork.printOutputs();
    }
}
