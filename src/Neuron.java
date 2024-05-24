public class Neuron{
    private int neuronIndex;
    private int layerIndex;
    private double[] inWeights;
    private double bias;
    private double[] inputs;
    private double output;
    private double[] errorGradients;
    
    public Neuron(int layerIndex, int neuronIndex, int numInputs){
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
        this.inWeights = new double[numInputs];
        this.errorGradients = new double[numInputs];
        /* caso não seja a camada de entrada,
        * inicializa o neurônio
        * percorre o vetor de pesos de entrada (+ bias)
        * e inicializa cada um com um valor aleatório
        * entre -1.0 e 1.0
        */
        if(layerIndex != 0){
            this.bias = Math.random() - 1.0;
            for (int i = 0; i < numInputs; i++){
                inWeights[i] = Math.random() - 1.0;
            }
        }
    }
    
    double calculateOutput(double[] inputs){
        this.inputs = inputs;
        double sum =  this.bias; // somando o bias antes da multiplicação dos pesos
        /* se for a camada de entrada,
         * a saída do neurônio é o input, sem cálculos
         * se não, calcula a saída do neurônio
         * percorrendo o vetor de pesos de entrada e multiplica cada peso pelo respectivo input
         * soma o resultado
         * aplica a função de ativação (sigmoid) ao resultado da soma
         */
        if(layerIndex == 0){
            this.output = inputs[0];
            printOutputInfo(output);
            return output;
        }else{
            for (int i = 0; i < inputs.length; i++){
                sum += inputs[i] * inWeights[i];
            }
            this.output = sigmoid(sum);
            printOutputInfo(sum);
            return output;
        }
    }

    public void calculateErrorGradients(double expectedOutput){
        System.out.println("Neuron " + neuronIndex);
        for(int i = 0; i < inWeights.length; i++){
            this.errorGradients[i] = outputGradient(expectedOutput) * sigmoidDerivative() * inputs[i];
            System.out.println("Error gradient " + i + ": " + errorGradients[i] + " input: " + inputs[i] + " inWeight: " + inWeights[i]);
        }
    }

    /* derivada do erro em relação a saída já calculada */
    public double outputGradient(double expectedOutput){
        return (- (expectedOutput - output));
    }

    /* derivada da Sigmoid em relação a saída já calculada */
    public double sigmoidDerivative(){
        return output * (1 - output);
    }
    
    /* função de ativação Sigmoid */
    double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public int getNeuronIndex(){
        return neuronIndex;
    }

    public double[] getInWeights(){
        return inWeights;
    }

    public void updateWeight(int index, double weight){
        this.inWeights[index] = weight;
    }

    public double getBias(){
        return bias;
    }

    public void setBias(double bias){
        this.bias = bias;
    }

    public double getOutput(){
        return output;
    }

    public double[] getErrorGradients(){
        return errorGradients;
    }

    public void setErrorGradients(double[] errorGradients){
        this.errorGradients = errorGradients;
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

    private void printOutputInfo(double sum){
        System.out.println(" LAYER " + layerIndex + " NEURON " + neuronIndex);
        System.out.println("bias: " + bias);
        System.out.println("sum: " + sum);
        System.out.println("output: " + output);
        System.out.println();
        System.out.println();
    }
}
