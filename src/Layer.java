public class Layer{
    private int layerIndex;
    private Neuron[] neurons;
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

    // public double[] backpropagate(double[] outputErrors){
    //     double[] errors = new double[neurons.length];
        
    //     if (nextLayer == null){
    //         // Camada de saída
    //         for (int i = 0; i < neurons.length; i++){
    //             errors[i] = outputErrors[i] * neurons[i].sigmoidDerivative(); // Gradient
    //         }
    //     }else{
    //         // Camada oculta
    //         for (int i = 0; i < neurons.length; i++){
    //             double error = 0.0;
    //             for (Neuron neuron : nextLayer.getNeurons()){
    //                 error += neuron.getInWeights()[i] * neuron.getError();
    //             }
    //             errors[i] = error * neurons[i].sigmoidDerivative(); // Gradient
    //         }
    //     }
    //     return errors;
    // }

    // public void updateWeights(double learningRate){
    //     for (Neuron neuron : neurons){
    //         double[] inputs = previousLayer != null ? previousLayer.getOutputs() : new double[]{1.0}; // Add bias or inputs from previous layer
    //         for (int i = 0; i < neuron.getInWeights().length; i++){
    //             neuron.getInWeights()[i] += learningRate * neuron.getError() * inputs[i];
    //         }
    //         neuron.setBias(neuron.getBias() + learningRate * neuron.getError()); // Update bias
    //     }
    // }
    
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
