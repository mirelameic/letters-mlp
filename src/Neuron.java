public class Neuron{
    private int neuronIndex;
    private int layerIndex;
    private double[] inWeights;
    private double bias;
    private double output;
    
    public Neuron(int layerIndex, int neuronIndex, int numInputs){
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
        inWeights = new double[numInputs];
        this.bias = Math.random() - 1.0;
        /* inicializa o neurônio
         * percorre o vetor de pesos de entrada (+ bias)
         * e inicializa cada um com um valor aleatório
         * entre -1.0 e 1.0
         */
        for (int i = 0; i < numInputs; i++){
            inWeights[i] = Math.random() - 1.0;
        }
    }
    
    double calculateOutput(double[] inputs){
        double sum =  this.bias; // somando o bias antes da multiplicação dos pesos
        /* calcula a saída do neurônio
         * percorre o vetor de pesos de entrada e multiplica cada peso pelo respectivo input
         * soma o resultado
         * aplica a função de ativação (sigmoid) ao resultado da soma
         */
        for (int i = 0; i < inputs.length; i++){
            sum += inputs[i] * inWeights[i];
        }
        System.out.println(" LAYER " + layerIndex + " NEURON " + neuronIndex);
        System.out.println("sum: " + sum);

        output = sigmoid(sum);
        System.out.println("output: " + output);
        System.out.println();
        System.out.println();
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
        sb.append("bias: ").append(bias).append("\n");
        for (int i = 0; i < inWeights.length; i++){
                sb.append("in-weight ").append(i).append(": ").append(inWeights[i]).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
