public class Neuron{
    private int neuronIndex;
    private int layerIndex;
    private double[] weights;
    private double output;
    
    public Neuron(int layerIndex, int neuronIndex, int numInputs){
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
        weights = new double[numInputs];
        for (int i = 0; i < numInputs; i++){
            weights[i] = Math.random() - 0.5; // inicialização aleatória entre -0.5 e 0.5
        }
    }
    
    public double calculateOutput(double[] inputs){
        double sum = 0;
        for (int i = 0; i < inputs.length; i++){
            sum += inputs[i] * weights[i];
        }
        output = sigmoid(sum); // função de ativação (sigmoid)
        return output;
    }
    
    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public double[] getWeights(){
        return weights;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("  Neuron ").append(neuronIndex).append(" weights: ");
        for (double weight : weights){
            sb.append(weight).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
