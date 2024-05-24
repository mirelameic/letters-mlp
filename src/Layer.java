public class Layer{
    private int layerIndex;
    private Neuron[] neurons;
    private double[] recievedInputs;
    private double[] outputs;
    private Layer previousLayer;
    private Layer nextLayer;
    
    public Layer(int layerIndex, int numNeurons, int numInputsPerNeuron, Layer previousLayer, Layer nextLayer){
        this.layerIndex = layerIndex;
        this.neurons = new Neuron[numNeurons];
        this.previousLayer = previousLayer;
        /* inicializa a camada criando cada neurônio com:
         * o indice da camada que ele está
         * o indice do neuronio
         * e o número de inputs que ele vai ter
         * setta a nextLayer da camada anterior como 'this'
         */
        for (int i = 0; i < numNeurons; i++){
            neurons[i] = new Neuron(layerIndex, i, numInputsPerNeuron);
        }
        if (previousLayer != null){
            previousLayer.setNextLayer(this);
        }
    }

    void backpropagate(double[] expectedOutputs){
        /* percorre cada neurônio da camada e calcula os gradientes de erro pra todos os pesos */
        if (nextLayer == null){
            System.out.println("---- BACKPROPAGATE ----");
            for (int i = 0; i < neurons.length; i++){
                neurons[i].calculateErrorGradients(expectedOutputs[i]);
            }
        }else{
        
        }
    }

    void updateWeightsAndBiases(double learningRate){
        /* percorre cada neurônio da camada e atualiza os pesos e bias */
        System.out.println("---- UPDATE WEIGHTS AND BIASES ----");
        for (Neuron neuron : neurons){
            neuron.updateWeightsAndBias(learningRate);
        }
    }
    
    double[] calculateOutputs(double[] inputs){
        this.recievedInputs = inputs;
        this.outputs = new double[neurons.length];
        /* calcula a saída de cada neurônio da camada
         * se for a camada de entrada, o input de cada neurônio é apenas 1 valor enviado na chamada do método
         * se não, o input é o vetor de saída da camada anterior
         */
        if (layerIndex == 0){
            for (int i = 0; i < neurons.length; i++){
                outputs[i] = neurons[i].calculateOutput(new double[]{inputs[i]});
            }
            return outputs;
        }else{
            for (int i = 0; i < neurons.length; i++){
                outputs[i] = neurons[i].calculateOutput(inputs);
            }
            return outputs;
        }
    }

    public int getLayerIndex(){
        return layerIndex;
    }

    public Neuron[] getNeurons(){
        return neurons;
    }

    public double[] getOutputs(){
        return outputs;
    }

    public Layer getPreviousLayer(){
        return previousLayer;
    }

    public Layer getNextLayer(){
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer){
        this.nextLayer = nextLayer;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("------> LAYER ").append(layerIndex).append("\n");
        for (Neuron neuron : neurons){
            sb.append(neuron.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
