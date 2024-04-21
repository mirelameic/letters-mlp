public class NeuralNetwork{
    private Layer[] layers;

    public NeuralNetwork(int[] layerSizes){
        layers = new Layer[layerSizes.length];
        for (int i = 0; i < layerSizes.length; i++){
            int numNeurons = layerSizes[i];
            int numInputsPerNeuron = (i == 0) ? 1 : layerSizes[i - 1]; // número de entradas por neurônio
            layers[i] = new Layer(i, numNeurons, numInputsPerNeuron);
        }
    }
    
    public double[] feedForward(double[] inputs){
        double[] outputs = null;
        for (Layer layer : layers){
            outputs = layer.calculateOutputs(inputs);
        }
        return outputs;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Neural Network:\n");
        for (int i = 0; i < layers.length; i++){
            sb.append(layers[i].toString());
        }
        return sb.toString();
    }
}
