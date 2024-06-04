public class Layer{
    private int layerIndex;
    private Layer previousLayer;
    private Layer nextLayer;
    private Neuron[] neurons;
    private double[] outputs;
    
    public Layer(int layerIndex, int numNeurons, int numInputsPerNeuron, Layer previousLayer){
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

    double[] calculateOutputs(double[] inputs){
        this.outputs = new double[neurons.length];
        /* calcula a saída de cada neurônio da camada
         * se for a camada de entrada, o input de cada neurônio é apenas 1 valor enviado na chamada do método
         * se não, o input é o vetor de saída da camada anterior
         */
        if (layerIndex == 0){
            for (int i = 0; i < neurons.length; i++){
                outputs[i] = neurons[i].calculateOutput(new double[]{inputs[i]});
            }
        }else{
            for (int i = 0; i < neurons.length; i++){
                outputs[i] = neurons[i].calculateOutput(inputs);
            }
        }
        return outputs;
    }

    void backpropagate(double[] expectedOutputs, double learningRate){
        /* o errorInfo será um valor associado a cada neurônio
         * já o delta será um valor associado a cada peso do neurônio
         */
        if (nextLayer == null){
            /* se for a camada de saida,
             * a Informação de Erro (errorInfo) será o erro do output multiplicado pela derivada da sigmoid
             * e o delta será o errorInfo multiplicado pelo learning rate
             * e pelo input ligado ao peso em questão (output do neurônio anterior)
             */
            for (int i = 0; i < neurons.length; i++){
                double[] inWeights = neurons[i].getInWeights();
                double[] inputs = neurons[i].getInputs();
                double[] errorInfo = new double[neurons.length];
                double[] delta = new double[inWeights.length];
                double biasDelta;
                errorInfo[i] = neurons[i].outputGradient(expectedOutputs[i]) * neurons[i].sigmoidDerivative();
                for(int j = 0; j < inWeights.length; j++){
                    delta[j] = learningRate * errorInfo[i] * inputs[j];
                }
                biasDelta = learningRate * errorInfo[i];
                neurons[i].setErrorInfo(errorInfo[i]);
                neurons[i].setDelta(delta);
                neurons[i].setBiasDelta(biasDelta);
            }
        }else if(previousLayer != null){
            /* se for uma camada oculta,
             * o auxErrorInfo será a somatória (errorInfo * respectivoPeso) de todos os neuronios da camada seguinte
             * o errorInfo será o auxErrorInfo multiplicado pela derivada da sigmoid
             * e o delta será o errorInfo multiplicado pelo learning rate
             * e pelo input ligado ao peso em questão (output do neurônio anterior)
             */
            Neuron[] nextLayerNeurons = nextLayer.getNeurons();
            for(int i = 0; i < neurons.length; i++){
                double auxErrorInfo = 0;
                double[] inWeights = neurons[i].getInWeights();
                double[] inputs = neurons[i].getInputs();
                double[] errorInfo = new double[neurons.length];
                double[] delta = new double[inWeights.length];
                double biasDelta;

                for(int j = 0; j < nextLayerNeurons.length; j++){
                    auxErrorInfo += nextLayerNeurons[j].getErrorInfo() * nextLayerNeurons[j].getInWeights()[i];
                }
                errorInfo[i] = auxErrorInfo * neurons[i].sigmoidDerivative();
                for(int k = 0; k < inWeights.length; k++){
                    delta[k] = learningRate * errorInfo[i] * inputs[k];
                }
                biasDelta = learningRate * errorInfo[i];
                neurons[i].setErrorInfo(errorInfo[i]);
                neurons[i].setDelta(delta);
                neurons[i].setBiasDelta(biasDelta);
            }
        }
    }

    void updateWeightsAndBiases(){
        /* percorre cada neurônio da camada e atualiza os pesos e bias */
        for (Neuron neuron : neurons){
            neuron.updateWeightsAndBias();
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
