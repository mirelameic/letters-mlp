import java.io.FileWriter;
import java.io.IOException;

public class Evaluator{
    public static final int[][] confusionMatrix = new int[26][26];

    public static void generateConfusionMatrix(char[] finalResponses, char[] expectedResponses, int numTestEntrance, String fileNameSuffix){
        /* gera a matriz de confusão */
        clearPreviousMatrix();
        updateConfusionMatrix(finalResponses, expectedResponses, numTestEntrance);
        printConfusionMatrix(fileNameSuffix);
    }
    
    public static void clearPreviousMatrix(){
        /* limpa a matriz de confusão */
        for (int i = 0; i<26; i++){
            for (int j = 0; j < 26; j++){
                confusionMatrix[i][j] = 0;
            }
        }
    }

    private static void updateConfusionMatrix(char[] finalResponses, char[] expectedResponses, int numTestEntrance){
        /* recebe as respostas finais e as esperadas
         * e atualiza a matriz de confusão
         * onde a linha é a resposta real da rede e a coluna é a resposta esperada
         * a matriz é preenchida com a quantidade de vezes que a resposta final foi a resposta esperada
         */
        for (int i = 0; i<numTestEntrance; i++){
            int finalResponse = AlphabetVectors.returnLetterNumber(finalResponses[i]);
            int expectedResponse = AlphabetVectors.returnLetterNumber(expectedResponses[i]);
            if (finalResponse != -1 && expectedResponse != -1){
                confusionMatrix[expectedResponse][finalResponse]++;
            }
        }
    }

    private static void printConfusionMatrix(String fileNameSuffix){
        /* imprime a matriz de confusão no arquivo confusion_matrix_<suffix>.csv */
        System.out.println("entrou");
        int totalEntries = 0;
        try (FileWriter writer = new FileWriter("plot/matrix/confusion_matrix_" + fileNameSuffix + ".csv")){
            for (int i = 0; i<26; i++){
                for (int j = 0; j < 26; j++){
                    writer.append(Integer.toString(confusionMatrix[i][j]));
                    if (j<25){
                        writer.append(",");
                    }
                    totalEntries += confusionMatrix[i][j];
                }
                writer.append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Total de entradas: " + totalEntries);
    }

    public static double calculateAccuracy(int numTestEntrance){
        /* a acurácia é a soma de todas as acurácias (cross validation) / k folds */
        int correctPredictions = 0;
        for(int i=0; i<26; i++){
            correctPredictions += confusionMatrix[i][i];
        }
        return (double) correctPredictions / numTestEntrance;
    }

    public static double calculateError(double accuracy){
        /* o erro é o complemento da acurácia */
        return 1 - accuracy;
    }

    /* calcula o erro/perda de entropia cruzada com base no retorno real
     * e no retorno previsto pela rede
     */
    public static double computeCrossEntropyLoss(double[][] y_true, double[][] y_pred) {
        double loss = 0.0;
        int m = y_true.length;
        int n = y_true[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                loss += -y_true[i][j] * Math.log(y_pred[i][j] + 1e-15);
            }
        }
        return loss / m;
    }
}
