import java.io.FileWriter;
import java.io.IOException;

public class Evaluator {
    public static final int[][] confusionMatrix = new int[26][26];

    public static void generateConfusionMatrix(char[] finalResponses, char[] expectedResponses, int numTestEntrance){
        /* gera a matriz de confusão */
        updateConfusionMatrix(finalResponses, expectedResponses, numTestEntrance);
        printConfusionMatrix();
    }
    
    private static void updateConfusionMatrix(char[] finalResponses, char[] expectedResponses, int numTestEntrance){
        /* recebe as respostas finais e as esperadas
         * e atualiza a matriz de confusão
         * onde a linha é a resposta esperada e a coluna é a resposta final
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

    private static void printConfusionMatrix(){
        /* imprime a matriz de confusão no arquivo confusion_matrix.csv */
        int totalEntries = 0;
        try (FileWriter writer = new FileWriter("plot/confusion_matrix.csv")){
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
}
