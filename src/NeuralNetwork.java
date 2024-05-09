public class NeuralNetwork{
    private Layer[] layers;
    private int[] layerSizes;
    private double[] finalOutputs;

    public NeuralNetwork(int[] layerSizes){
        this.layerSizes = layerSizes;
        layers = new Layer[layerSizes.length];
        /* inicializa a rede neural
         * criando cada camada com o respectivo indice,
         * o número de neurônios especificado no vetor layerSizes
         * e o numero de inputs por neuronio
         * (número de neurônios da camada anterior,
         * ou apenas 1 se for a camada de entrada)
         */
        for (int i = 0; i < layerSizes.length; i++){
            int numNeurons = layerSizes[i];
            int numInputsPerNeuron = (i == 0) ? 1 : layerSizes[i - 1];
            layers[i] = new Layer(i, numNeurons, numInputsPerNeuron);
        }
    }

    private double[] feedForward(double[] inputs){
        double[] outputs = inputs;
        /* calcula a saída da rede neural
        * percorre as camadas e calcula a saída de cada uma
        * a saída de uma camada é a entrada da próxima
        */
        for (Layer layer : layers){
            outputs = layer.calculateOutputs(outputs);
        }
        this.finalOutputs = outputs;
        return finalOutputs;
    }
    
    public double[] getOutputs(double[] inputs){
        if (inputs.length != layerSizes[0]){
            throw new IllegalArgumentException("Number of inputs must be equal to number of neurons in input-layer");
        }
        return feedForward(inputs);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n------------NEURAL NETWORK------------\n\n");
        for (int i = 0; i < layers.length; i++){
            sb.append(layers[i].toString());
        }
        return sb.toString();
    }

    public void printOutputs(){
        System.out.println("---- OUTPUTS ----");
        for (double output : finalOutputs){
            System.out.println(output);
        }
        System.out.println();
    }
}
