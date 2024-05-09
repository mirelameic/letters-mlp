public class Neuron{
    private int neuronIndex;
    private int layerIndex;
    private double[] inWeights;
    private double output;
    
    public Neuron(int layerIndex, int neuronIndex, int numInputs){
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
        inWeights = new double[numInputs + 1]; // +1 para o bias
        /* inicializa o neurônio
         * percorre o vetor de pesos de entrada (+ bias)
         * e inicializa cada um com um valor aleatório
         * entre -1.0 e 1.0
         */
        for (int i = 0; i < numInputs + 1; i++){
            inWeights[i] = Math.random() - 1.0;
        }
    }
    
    double calculateOutput(double[] inputs){
        double sum = inWeights[inWeights.length - 1]; // somando o bias
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
        sb.append("  NEURON ").append(neuronIndex).append("\n");
        for (int i = 0; i < inWeights.length; i++){
            if (i == inWeights.length - 1) {
                sb.append("bias: ").append(inWeights[i]).append("\n");
            } else {
                sb.append("in-weight ").append(i + 1).append(": ").append(inWeights[i]).append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
