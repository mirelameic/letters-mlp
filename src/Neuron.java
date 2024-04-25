public class Neuron{
    private int neuronIndex;
    private int layerIndex;
    private double[] inWeights;
    private double output;
    
    public Neuron(int layerIndex, int neuronIndex, int numInputs){
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
        inWeights = new double[numInputs];
        /* inicializa o neurônio
         * percorre o vetor de pesos de entrada e inicializa cada peso com um valor aleatório
         * entre -1.0 e 1.0
         */
        for (int i = 0; i < numInputs; i++){
            inWeights[i] = Math.random() - 1.0;
        }
    }
    
    double calculateOutput(double[] inputs){
        double sum = 0;
        /* calcula a saída do neurônio
         * percorre o vetor de pesos de entrada e multiplica cada peso pelo respectivo input
         * soma o resultado
         * aplica a função de ativação (sigmoid) ao resultado da soma
         */
        for (int i = 0; i < inputs.length; i++){
            sum += inputs[i] * inWeights[i];
        }
        output = sigmoid(sum);
        return output;
    }
    
    /* função de ativação (sigmoid) */
    double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public double[] getWeights(){
        return inWeights;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("  Neuron ").append(neuronIndex).append(" in-weights: ");
        for (double weight : inWeights){
            sb.append(weight).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
