public class Layer{
    private int layerIndex;
    private Neuron[] neurons;
    private double[] recievedInputs;
    private double[] outputs;
    private Layer previousLayer;
    private Layer nextLayer;
    double[] outputSquaredErrors;
    
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

    double[] backpropagate(double[] outputErrors){
        double[] errors = new double[neurons.length];
        if (nextLayer == null){
            /* na camada de saída,
             * o erro local de cada neurônio é o erro da saída * a derivada da sigmoid
             */
            for (int i = 0; i < neurons.length; i++){
                errors[i] = outputErrors[i] * neurons[i].sigmoidDerivative();
                neurons[i].setError(errors[i]);
            }
        }else{
            /* nas camadas ocultas,
             * o erro local de cada neurônio é a soma dos erros dos neurônios da camada seguinte
             * multiplicado pelo peso da conexão entre eles * a derivada da sigmoid
             */
            for (int i = 0; i < neurons.length; i++){
                double error = 0.0;
                for (Neuron nextNeuron : nextLayer.getNeurons()){
                    error += nextNeuron.getInWeights()[i] * nextNeuron.getError();
                }
                error *= neurons[i].sigmoidDerivative();
                neurons[i].setError(error);
                errors[i] = error;
            }
        }
        return errors;
    }

    void updateWeightsAndBiases(double learningRate){
        /* atualiza os pesos e bias de cada neurônio da camada
         * com base no learning rate fornecido, no erro calculado no backpropagation
         * e nos valores de entrada da camada no feedforward
         */
        for (Neuron neuron : neurons){
            for (int i = 0; i < neuron.getInWeights().length; i++){
                double oldWeight = neuron.getInWeights()[i];
                double newWeight = oldWeight + learningRate * neuron.getError() * recievedInputs[i];
                neuron.updateWeight(i, newWeight);
            }
            neuron.setBias(neuron.getBias() + learningRate * neuron.getError());
        }
    }

    double[] calculateOutputSquaredErrors(double[] expectedOutputs){
        this.outputSquaredErrors = new double[expectedOutputs.length];
        /* calcula o erro quadrático de cada neurônio da camada de saída */
        for (int i = 0; i < neurons.length; i++){
            double error = expectedOutputs[i] - neurons[i].getOutput();
            outputSquaredErrors[i] = Math.pow(error, 2);
        }
        return outputSquaredErrors;
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

    public void printOutputSquarredErrors(){
        System.out.println("---- OUTPUT SQUARED ERRORS ----");
        for (double error : outputSquaredErrors){
            System.out.println(error);
        }
        System.out.println();
    }
}
