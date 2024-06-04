public class Evaluator {
    public static int[][] confusionMatrix = new int[26][26];

    public static void generateConfusionMatrix(char[] finalResponses, char[] expectedResponses, int numTestEntrance){
        /* gera e imprime uma matriz de confusão com base nas respostas finais e esperadas fornecidas.
         * para cada par de respostas, incrementa a contagem correspondente na matriz.
         * em seguida, imprime a matriz de confusão e o total de entradas processadas.
         */
        for(int i=0;i<numTestEntrance;i++){
            int finalResponse = AlphabetVectors.returnLetterNumber(finalResponses[i]);
            int expectedResponse = AlphabetVectors.returnLetterNumber(expectedResponses[i]);
            if(finalResponse != -1 && expectedResponse != -1){
                confusionMatrix[expectedResponse][finalResponse]++;
            }
        }

        int aux = 0;
        for(int i=0;i<26;i++){
            for(int j=0;j<26;j++){
                System.out.print(confusionMatrix[i][j] + " ");
                aux+=confusionMatrix[i][j];
            }
            System.out.println();
        }
        System.out.println("Total de entradas: " + aux);
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
