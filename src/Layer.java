public class Layer{
    private int layerIndex;
    private Neuron[] neurons;
    
    public Layer(int layerIndex, int numNeurons, int numInputsPerNeuron){
        this.layerIndex = layerIndex;
        neurons = new Neuron[numNeurons];
        /* inicializa as camadas
         * criando cada neurônio 
         * com o indice da camada que ele está, o indice do neuronio e o número de inputs
         */
        for (int i = 0; i < numNeurons; i++){
            neurons[i] = new Neuron(layerIndex, i, numInputsPerNeuron);
        }
    }
    
    double[] calculateOutputs(double[] inputs){
        double[] outputs = new double[neurons.length];
        /* calcula a saída de cada neurônio da camada
         * se a camada for a camada de entrada, o input é um vetor de tamanho 1
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

    public Neuron[] getNeurons(){
        return neurons;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-> Layer ").append(layerIndex).append(":\n");
        for (Neuron neuron : neurons){
            sb.append(neuron.toString());
        }
        return sb.toString();
    }
}
