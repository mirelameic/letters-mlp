public class Evaluator {
    public static int[][] confusionMatrix = new int[26][26];
    public static void main(String[] args){

    }

    public static void generateConfusionMatrix(char[] finalResponses, char[] expectedResponses, int numTestEntrance){
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
        int correctPredictions = 0;
        for(int i=0; i<26; i++){
            correctPredictions += confusionMatrix[i][i];

        }
        double accuracy = (double) correctPredictions / numTestEntrance;
        return accuracy;
    }

    public static double calculateError(double accuracy){
        return (double) 1 - accuracy;
    }
}
