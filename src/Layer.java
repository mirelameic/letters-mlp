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
        if (nextLayer == null){
            /* se for a camada de saida, calcula o delta e o termo de erro.
             * o termo é a derivada do erro em relação ao output
             * multiplicado pela derivada da sigmoid
             * e o delta é o termo multiplicado pelo input do peso em questão
             */
            System.out.println("---- BACKPROPAGATE ----");
            for (int i = 0; i < neurons.length; i++){
                double[] inWeights = neurons[i].getInWeights();
                double[] inputs = neurons[i].getInputs();
                double[] delta = new double[inWeights.length];
                double[] termo = new double[neurons.length];
                System.out.println("Neuron " + i);
                termo[i] = neurons[i].outputGradient(expectedOutputs[i]) * neurons[i].sigmoidDerivative();
                for(int j = 0; j < inWeights.length; j++){
                    delta[j] = termo[i] * inputs[j];
                    System.out.println("Delta " + j + ": " + delta[j] + " input: " + inputs[j] + " inWeight: " + inWeights[j]);
                    System.out.println();
                }
                neurons[i].setErrorInfo(termo[i]);
                neurons[i].setDelta(delta);
                System.out.println("Termo: " + termo[i]);
                System.out.println();
            }
        }else if(previousLayer != null){
            Neuron[] nextLayerNeurons = nextLayer.getNeurons();
            double deltaIn = 0;
            for(int i = 0; i < neurons.length; i++){
                for(int j = 0; j < nextLayerNeurons.length; j++){
                    deltaIn += nextLayerNeurons[j].getErrorInfo() * nextLayerNeurons[j].getInWeights()[i];
                }
                double termo = deltaIn * neurons[i].sigmoidDerivative();
                neurons[i].setErrorInfo(termo);
            }
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
