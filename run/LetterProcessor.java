import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LetterProcessor{
    private NeuralNetwork neuralNetwork;
    private int[] layerInfo;

    public LetterProcessor(){
        /* Inicializa a rede neural com 3 camadas:
         * entrada = 120 neurônios
         * oculta = 60 neurônios
         * saída = 26 neurônios
         */
        this.layerInfo = new int[]{120, 60, 26};
    }

    public void runNormalValidation(int epocas){
        /* Cria uma nova rede neural e realiza a validação normal */
        this.neuralNetwork = new NeuralNetwork(this.layerInfo);
        normalValidation(epocas);
    }

    private void normalValidation(int epocas){
        /* Realiza a validação normal por x epocas,
         * treinando a rede neural e atualizando os pesos para os dados de treinamento
         * e testando com os dados de teste
         */
        String trainingFilePath = System.getProperty("user.dir") + "/data/normal-validation/treinamento-x.txt";
        for(int i=0; i<epocas; i++){
            processImages(trainingFilePath, false);
        }
        
        String testingFilePath = System.getProperty("user.dir") + "/data/normal-validation/teste-x.txt";
        processImages(testingFilePath, true);

        String finalTestingFilePath = System.getProperty("user.dir") + "/data/normal-validation/teste-final-x.txt";
        processImages(finalTestingFilePath, true);
    }

    public void runCrossValidation(int[] folds, int testFold, int epocas){
        /* Cria uma nova rede neural e realiza a validação cruzada */
        this.neuralNetwork = new NeuralNetwork(this.layerInfo);
        crossValidation(folds, testFold, epocas);
    }

    private void crossValidation(int[] folds, int testFold, int epocas){
        /* Realiza a validação cruzada por x epocas,
         * treinando a rede neural e atualizando os pesos para os folds de treinamento
         * e testando com o fold de teste
         */
        for(int i=0; i<epocas; i++){
            for (int fold : folds){
                String filePath = setFilePathWithFoldNumber(fold);
                processImages(filePath, false);
            }
        }

        processImages(setFilePathWithFoldNumber(testFold), true);
    }

    private void processImages(String filePath, boolean isTestFold){
        /* Processa os dados de acordo com o arquivo e a linha do fold
         * e realiza o treinamento da rede neural,
         * rodando os métodos feedforward e backpropagation (com exceção do fold de teste)
         * e calculando o erro médio quadrático
         */
        if (filePath == null || filePath.isEmpty()){
            System.err.println("File path is not set. Use setFilePathWithFoldNumber() to set the path to the fold file.");
            return;
        }
        int aux=0;
        char[] expectedResponses = new char[130];
        char actualResponse;
        char[] finalResponses = new char[130];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            int linha = 1;
            double currentMSE = 0;
            while ((line = br.readLine()) != null){
                linha = linha % 26;
                linha = linha == 0 ? 26 : linha;
                double[] expectedOutputs = AlphabetVectors.getLetter(linha);
                double[] inputs = parseInputLine(line);
                double[] outputs = neuralNetwork.runFeedForward(inputs);
                if (!isTestFold){
                    neuralNetwork.runBackpropagation(expectedOutputs, 0.5);
                }
                else{
                    double[] response = new double[26];
                    int index = findMaxIndex(outputs);
                    for(int i=0; i<outputs.length; i++){
                        if(i == index){
                            response[i] = 1;
                        }
                        else{
                            response[i] = 0;
                        }
                    }
                    actualResponse = findOutResponseLetter(response);
                    expectedResponses[aux] = AlphabetVectors.getExpectedResponse(linha);
                    finalResponses[aux] = actualResponse;
                }
                neuralNetwork.calculateMSE(expectedOutputs);
                currentMSE = neuralNetwork.getMSE();
                System.out.println("MSE: " + currentMSE);
                linha++;
                aux++;
            }
        } catch (IOException e){
                e.printStackTrace();
        }
        
        if(isTestFold){
            generateConfusionMatrix(finalResponses, expectedResponses);
        }
    }

    private String setFilePathWithFoldNumber(int foldNumber){
        /* Define o caminho do arquivo do fold */
        return System.getProperty("user.dir") + "/data/cross-validation/" + foldNumber + "-fold-x.txt";
    }
        
    private double[] parseInputLine(String line){
        /* Realiza o parse de cada linha,
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

    public void generateConfusionMatrix(char[] finalResponses, char[] expectedResponses){
        int[][] confusionMatrix = new int[26][26];

        for(int i=0;i<130;i++){
            int finalResponse = AlphabetVectors.returnLetterNumber(finalResponses[i]);
            int expectedResponse = AlphabetVectors.returnLetterNumber(expectedResponses[i]);
            if(finalResponse != -1 && expectedResponse != -1){
                confusionMatrix[expectedResponse][finalResponse]++;
            }
        }

        for(int i=0;i<26;i++){
            for(int j=0;j<26;j++){
                System.out.print(confusionMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char findOutResponseLetter(double[] response){
        return AlphabetVectors.decodeResponse(response);
    }

    public static int findMaxIndex(double[] array){
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
}
