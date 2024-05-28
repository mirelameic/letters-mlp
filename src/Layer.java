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
            /* se for a camada de saida,
             * o termo será a derivada do erro em relação ao output, multiplicado pela derivada da sigmoid
             * e o delta será o termo multiplicado pelo input do peso em questão (output do neuronio anterior)
             */
            for (int i = 0; i < neurons.length; i++){
                double[] inWeights = neurons[i].getInWeights();
                double[] inputs = neurons[i].getInputs();
                double[] delta = new double[inWeights.length];
                double[] termo = new double[neurons.length];
                termo[i] = neurons[i].outputGradient(expectedOutputs[i]) * neurons[i].sigmoidDerivative();
                for(int j = 0; j < inWeights.length; j++){
                    delta[j] = termo[i] * inputs[j];
                }
                neurons[i].setErrorInfo(termo[i]);
                neurons[i].setDelta(delta);
            }
        }else if(previousLayer != null){
            /* se for uma camada oculta,
             * o auxTermo será a somatória dos termos do neurônios da camada seguinte multiplicados pelo pesos do neuronio atual
             * o termo será o auxTermo multiplicado pela derivada da sigmoid
             * e o delta é o termo multiplicado pelo input do peso em questão (output do neuronio anterior)
             */
            Neuron[] nextLayerNeurons = nextLayer.getNeurons();
            double auxTermo = 0;
            for(int i = 0; i < neurons.length; i++){
                double[] inWeights = neurons[i].getInWeights();
                double[] inputs = neurons[i].getInputs();
                double[] delta = new double[inWeights.length];
                double[] termo = new double[neurons.length];
                for(int j = 0; j < nextLayerNeurons.length; j++){
                    auxTermo += nextLayerNeurons[j].getErrorInfo() * nextLayerNeurons[j].getInWeights()[i];
                }
                termo[i] = auxTermo * neurons[i].sigmoidDerivative();
                for(int k = 0; k < inWeights.length; k++){
                    delta[k] = termo[i] * inputs[k];
                }
                neurons[i].setErrorInfo(termo[i]);
                neurons[i].setDelta(delta);
            }
        }
    }

    void updateWeightsAndBiases(double learningRate){
        /* percorre cada neurônio da camada e atualiza os pesos e bias */
        //System.out.println("---- UPDATE WEIGHTS AND BIASES ----");
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
