public class NeuralNetwork{
    private Layer[] layers;
    private int[] layerInfo;
    private double[] inputs;
    private double[] finalOutputs;
    double[] outputSquaredErrors;
    double MSE;

    public NeuralNetwork(int[] layerInfo){
        this.layerInfo = layerInfo;
        layers = new Layer[layerInfo.length];
        /* inicializa a rede neural criando cada camada com:
         * o seu respectivo indice,
         * o número de neurônios especificado no vetor layerInfo,
         * o numero de inputs por neuronio
         * (número de neurônios da camada anterior,
         * ou apenas 1 se for a camada de entrada),
         * a camada anterior (null se for a camada de entrada)
         * e a camada seguinte (no caso sempre null)
         */
        for (int i = 0; i < layerInfo.length; i++){
            int numNeurons = layerInfo[i];
            int numInputsPerNeuron = (i == 0) ? 1 : layerInfo[i - 1];
            Layer previousLayer = (i == 0) ? null : layers[i - 1];
            layers[i] = new Layer(i, numNeurons, numInputsPerNeuron, previousLayer, null);
        }
    }

    private void backpropagation(double[] expectedOutputs, double learningRate){
        /* calcula o erro na camada de saída,
         * propaga o erro de volta para as camadas ocultas
         * e atualiza os pesos e bias em todas as camadas
         */
        double[] errors = layers[layers.length - 1].calculateOutputSquaredErrors(expectedOutputs);
        for (int i = layers.length - 1; i >= 0; i--){
            errors = layers[i].backpropagate(errors);
        }
        
        for (Layer layer : layers){
            layer.updateWeightsAndBiases(learningRate);
        }
    }

    private double[] feedForward(double[] inputs){
        this.inputs = inputs;
        double[] outputs = inputs;
        /* calcula os valores de saida da rede neural
        * percorre as camadas e calcula a saída de cada uma
        * -> a saída de uma camada é a entrada da próxima
        */
        for (Layer layer : layers){
            outputs = layer.calculateOutputs(outputs);
        }
        this.finalOutputs = outputs;
        return finalOutputs;
    }
    
    void calculateMSE(double[] expectedOutputs){
        /* calcula o erro quadrático médio
         * percorre os valores esperados e os valores de saída
         * calcula o erro para cada saída e soma os quadrados
         * divide a soma pelo número de saídas
         */
        if (expectedOutputs.length != finalOutputs.length){
            throw new IllegalArgumentException("Number of expected outputs must be equal to number of neurons in output-layer");
        }
        double sumSquaredErrors = 0.0;
        for (int i = 0; i < finalOutputs.length; i++){
            double error = expectedOutputs[i] - finalOutputs[i];
            sumSquaredErrors += Math.pow(error, 2);
        }
        this.MSE = sumSquaredErrors / finalOutputs.length;
    }

    public void runBackpropagation(double[] expectedOutputs, double learningRate){
        if(expectedOutputs.length != layerInfo[layerInfo.length - 1]){
            throw new IllegalArgumentException("Number of expected outputs must be equal to number of neurons in output-layer");
        }
        backpropagation(expectedOutputs, learningRate);
    }
    
    public double[] runFeedForward(double[] inputs){
        if (inputs.length != layerInfo[0]){
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

    public void printLayers() {
        for (int i = 0; i < layers.length; i++) {
            System.out.println("LAYER " + i);
            Layer currentLayer = layers[i];
            System.out.println("Neurons: " + currentLayer.getNeurons().length);
            System.out.println("Previous Layer: " + (currentLayer.getPreviousLayer() != null ? currentLayer.getPreviousLayer().getLayerIndex() : "null"));
            System.out.println("Next Layer: " + (currentLayer.getNextLayer() != null ? currentLayer.getNextLayer().getLayerIndex() : "null"));
            System.out.println();
        }
    }

    public Layer[] getLayers(){
        return layers;
    }

    public double getMSE(){
        return MSE;
    }
}
