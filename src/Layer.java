public class Layer{
    private int layerIndex;
    private Neuron[] neurons;
    
    public Layer(int layerIndex, int numNeurons, int numInputsPerNeuron){
        this.layerIndex = layerIndex;
        neurons = new Neuron[numNeurons];
        for (int i = 0; i < numNeurons; i++){
            neurons[i] = new Neuron(layerIndex, i, numInputsPerNeuron);
        }
    }
    
    public double[] calculateOutputs(double[] inputs){
        double[] outputs = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++){
            outputs[i] = neurons[i].calculateOutput(inputs);
        }
        return outputs;
    }

    public Neuron[] getNeurons(){
        return neurons;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Layer ").append(layerIndex).append(":\n");
        for (Neuron neuron : neurons){
            sb.append(neuron.toString());
        }
        return sb.toString();
    }
}
