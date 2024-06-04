import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LetterProcessor{
    private NeuralNetwork neuralNetwork;
    private int[] layerInfo;
    private double learningRate;

    public LetterProcessor(){
        /* inicializa a rede neural com 3 camadas:
         * entrada = 120 neurônios
         * oculta = 60 neurônios
         * saída = 26 neurônios
         * e a taxa de aprendizado = 0.5
         */
        this.layerInfo = new int[]{120, 60, 26};
        this.learningRate = 0.5;
    }

    public void runNormalValidation(int epocas){
        /* cria uma nova rede neural e realiza a validação normal */
        this.neuralNetwork = new NeuralNetwork(this.layerInfo);
        normalValidation(epocas);
    }

    public void runNormalValidationEarlyStopping(int epocas){
        /* cria nova rede neural e realiza validação normal com parada antecipada */
        this.neuralNetwork = new NeuralNetwork(this.layerInfo);
        normalValidationEarlyStopping(epocas);
    }

    private void normalValidationEarlyStopping(int epocas){
        /* realiza a validação normal por x epocas,
         * treinando a rede neural e atualizando os pesos para os dados de treinamento
         * e testando com os dados de teste
         */
        int numTestEntrance = 260;
        String trainingFilePath = System.getProperty("user.dir") + "/data/normal-validation/treinamento-x.txt";
        String testingFilePath = System.getProperty("user.dir") + "/data/normal-validation/teste-x.txt";

        double bestValLoss = Double.MAX_VALUE;
        int patience = 100;
        int patienceCounter = 0;
        double[][] emptyMatrix = generateEmptyMatrix();
        for(int i=0; i<epocas; i++){
            // treinamento da rede neural
            processImages(trainingFilePath, false, 0, "normal-validation-early-stopping-train", false, emptyMatrix, emptyMatrix);

            // avaliação da perda no conjunto de teste
            double[][] validationLabels = new double[numTestEntrance][26];
            double[][] validationPredictions = new double[numTestEntrance][26];
            processImages(testingFilePath, true, numTestEntrance, "normal-validation-early-stopping-test", true, validationLabels, validationPredictions);

            double valLoss = Evaluator.computeCrossEntropyLoss(validationLabels, validationPredictions);
            System.out.println("Epoch " + i + " - Val Loss: " + valLoss);

            // Verificar se a perda de validação atual é melhor do que a melhor perda anterior
            if (valLoss < bestValLoss) {
                bestValLoss = valLoss; // Atualizar a melhor perda de validação
                patienceCounter = 0; // Resetar o contador de paciência
            } else {
                patienceCounter++; // Incrementar o contador de paciência
            }

            // Parar o treinamento se a perda de validação não melhorar por 'patience' épocas consecutivas
            if (patienceCounter >= patience) {
                System.out.println("Early stopping at epoch " + i);
                break;
            }
        }
        
    }

    private void normalValidation(int epocas){
        /* realiza a validação normal por x epocas,
         * treinando a rede neural e atualizando os pesos para os dados de treinamento
         * e testando com os dados de teste
         */

        double[][] emptyMatrix = generateEmptyMatrix();
        int numTestEntrance = 260;
        String trainingFilePath = System.getProperty("user.dir") + "/data/normal-validation/treinamento-x.txt";
        for(int i=0; i<epocas; i++){
            processImages(trainingFilePath, false, 0, "normal-validation-train", false, emptyMatrix, emptyMatrix);
        }
        
        String testingFilePath = System.getProperty("user.dir") + "/data/normal-validation/teste-x.txt";
        processImages(testingFilePath, true, numTestEntrance, "normal-validation-test", false, emptyMatrix, emptyMatrix);
    }

    public void runCrossValidation(int[] folds, int testFold, int epocas){
        /* cria uma nova rede neural e realiza a validação cruzada */
        this.neuralNetwork = new NeuralNetwork(this.layerInfo);
        crossValidation(folds, testFold, epocas);
    }

    private void crossValidation(int[] folds, int testFold, int epocas){
        /* realiza a validação cruzada por x epocas,
         * treinando a rede neural e atualizando os pesos para os folds de treinamento
         * e testando com o fold de teste
         */
        double[][] emptyMatrix = generateEmptyMatrix();
        int numTestEntrance = 130;
        for(int i=0; i<epocas; i++){
            for (int fold : folds){
                String filePath = setFilePathWithFoldNumber(fold);
                processImages(filePath, false, 0, "fold-" + fold, false, emptyMatrix, emptyMatrix);
            }
        }

        processImages(setFilePathWithFoldNumber(testFold), true, numTestEntrance, "fold-" + testFold, false, emptyMatrix, emptyMatrix);
    }

    private void processImages(String filePath, boolean isTestFold, int numTestEntrance, String fileNameSuffix, boolean isEarlyStopping, double[][] validationLabels, double[][] validationPredictions){
        /* processa as imagens de acordo com o arquivo de entrada
         * se for um fold de teste, armazena as respostas esperadas e as respostas finais
         * para gerar a matriz de confusão e calcular a acurácia
         */
        if (filePath == null || filePath.isEmpty()){
            System.err.println("File path is not set. Use setFilePathWithFoldNumber() to set the path to the fold file.");
            return;
        }
    
        char[] expectedResponses = new char[numTestEntrance];
        char[] finalResponses = new char[numTestEntrance];
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            processLines(br, isTestFold, expectedResponses, finalResponses, fileNameSuffix, isEarlyStopping, validationLabels, validationPredictions);
        } catch (IOException e){
            e.printStackTrace();
        }
    
        if (isTestFold){
            evaluateResults(numTestEntrance, expectedResponses, finalResponses, fileNameSuffix);
        }
    }
    
    private void processLines(BufferedReader br, boolean isTestFold, char[] expectedResponses, char[] finalResponses, String fileNameSuffix, boolean isEarlyStopping, double[][] validationLabels, double[][] validationPredictions) throws IOException{
        /* processa as linhas do arquivo de entrada
         * se for um fold de teste, armazena as respostas esperadas e as respostas finais
         * para gerar a matriz de confusão e calcular a acurácia
         */
        String line;
        int linha = 1;
        int aux = 0;
        int index = 0;
        double currentMSE = 0;
        String mseFilePath = "plot/mse/mse_values_" + fileNameSuffix + ".csv";

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(mseFilePath)))){
            while ((line = br.readLine()) != null){
                linha = linha % 26;
                linha = linha == 0 ? 26 : linha;
                double[] expectedOutputs = AlphabetVectors.getLetter(linha);
                double[] inputs = parseInputLine(line);
                double[] outputs = neuralNetwork.runFeedForward(inputs);
                
                if (!isTestFold){
                    neuralNetwork.runBackpropagation(expectedOutputs, learningRate);
                }else{
                    if(isEarlyStopping){
                        validationLabels[index] = expectedOutputs;
                        validationPredictions[index] = outputs;
                        index++;
                    }
                    handleTestFold(outputs, linha, aux, expectedResponses, finalResponses);
                }
                
                neuralNetwork.calculateMSE(expectedOutputs);
                currentMSE = neuralNetwork.getMSE();
                pw.println(aux + "," + currentMSE);
                
                linha++;
                aux++;
            }
        }
        
    }
    
    private void handleTestFold(double[] outputs, int linha, int aux, char[] expectedResponses, char[] finalResponses){
        /* armazena as respostas esperadas e as respostas finais
         * para gerar a matriz de confusão e calcular a acurácia
         */
        double[] response = new double[26];
        int index = findMaxIndex(outputs);
        
        for (int i = 0; i<outputs.length; i++){
            response[i] = (i == index) ? 1 : 0;
        }
        
        char actualResponse = findOutResponseLetter(response);
        expectedResponses[aux] = AlphabetVectors.getExpectedResponse(linha);
        finalResponses[aux] = actualResponse;
    }
    
    private void evaluateResults(int numTestEntrance, char[] expectedResponses, char[] finalResponses, String fileNameSuffix){
        /* gera a matriz de confusão e calcula a acurácia */
        Evaluator.generateConfusionMatrix(finalResponses, expectedResponses, numTestEntrance, fileNameSuffix);
        double accuracy = Evaluator.calculateAccuracy(numTestEntrance);
        double error = Evaluator.calculateError(accuracy);
    
        System.out.println("Accuracy: " + accuracy + " // error: " + error);
    }    

    private String setFilePathWithFoldNumber(int foldNumber){
        /* define o caminho do arquivo do fold */
        return System.getProperty("user.dir") + "/data/cross-validation/" + foldNumber + "-fold-x.txt";
    }
        
    private double[] parseInputLine(String line){
        /* realiza o parse de cada linha,
         * separando apenas os valores numéricos
         * e convertendo-os para double
        */
        String[] values = line.trim().split(",");
        double[] inputs = new double[values.length];
        for (int i = 0; i < values.length; i++){
            inputs[i] = Double.parseDouble(values[i].trim());
        }
        return inputs;
    }

    public char findOutResponseLetter(double[] response){
        return AlphabetVectors.decodeResponse(response);
    }

    public static int findMaxIndex(double[] array){
        /* encontra o índice do maior valor no array */
        if (array.length == 0) {
            throw new IllegalArgumentException("O vetor está vazio");
        }
        int maxIndex = 0;
        double max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static double[][] generateEmptyMatrix(){
        int linhas = 1;
        int colunas = 1;
        double[][] emptyMatrix = new double[linhas][colunas];
        return emptyMatrix;
    }
}
