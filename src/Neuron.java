public class Neuron{
    private int neuronIndex;
    private int layerIndex;
    private double[] inWeights;
    private double bias;
    private double[] inputs;
    private double sum;
    private double output;
    private double[] delta;
    private double errorInfo;
    
    public Neuron(int layerIndex, int neuronIndex, int numInputs){
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
        this.inWeights = new double[numInputs];
        this.delta = new double[numInputs];
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
            //printOutputInfo(output);
            return output;
        }else{
            System.out.println("bias: " + bias);
            for (int i = 0; i < inputs.length; i++){
                sum += inputs[i] * inWeights[i];
                System.out.println("input " + i + ": " + inputs[i] + " weight " + i + ": " + inWeights[i]);
            }
            this.sum = sum;
            System.out.println("sum: " + sum);
            this.output = sigmoid(sum);
            System.out.println("output: " + output);
            System.out.println();
            //printOutputInfo(sum);
            return output;
        }
    }

    void updateWeightsAndBias(double learningRate){
        /* o peso novo será:
         * o peso antigo menos o valor enviado do leraning rate multiplicado pelo delta em relação ao peso */
        for (int i = 0; i < inWeights.length; i++){
            double newWeight = inWeights[i] - learningRate * delta[i];
            //System.out.println("Neuron " + neuronIndex + " Weight " + i + " old: " + inWeights[i] + " new: " + newWeight);
            //System.out.println("delta " + i + ": " + delta[i]);
            this.inWeights[i] = newWeight;
        }
        this.bias = bias - learningRate * errorInfo;
    }

    double outputGradient(double expectedOutput){
        /* valor esperado menos a saída */
        //System.out.println("Output gradient: " + (- (expectedOutput - output)));
        return (expectedOutput - output);
    }

    double sigmoidDerivative(){
        /* derivada da Sigmoid */
        //System.out.println("Sigmoid derivative: " + sum * (1 - sum));
        return sigmoid(sum) * (1 - sigmoid(sum));
    }
    
    double sigmoid(double x){
        /* função de ativação Sigmoid */
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

    public void updateBias(double bias){
        this.bias = bias;
    }

    public double getBias(){
        return bias;
    }

    public void setBias(double bias){
        this.bias = bias;
    }

    public double[] getInputs(){
        return inputs;
    }

    public double getOutput(){
        return output;
    }

    public double[] getDelta(){
        return delta;
    }

    public void setDelta(double[] delta){
        this.delta = delta;
    }

    public void setErrorInfo(double errorInfo){
        this.errorInfo = errorInfo;
    }

    public double getErrorInfo(){
        return errorInfo;
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
