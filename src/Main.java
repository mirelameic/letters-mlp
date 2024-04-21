public class Main{
    public static void main(String[] args){
        int[] layerSizes = {2, 3, 1}; // 2 neurônios de entrada, 3 neurônios ocultos e 1 neurônio de saída
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerSizes);
        System.out.println(neuralNetwork);



        // quebrando para mais de dois valores, método calculate outputs esta errado
        double[] inputs = {0.5};
        double[] outputs = neuralNetwork.feedForward(inputs);
        
        // exibindo as saídas produzidas pela rede neural
        System.out.println("Outputs:");
        for (double output : outputs) {
            System.out.println(output);
        }
    }
}
