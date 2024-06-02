public class CrossValidation{
    public static void main(String[] args){
        /* CrossValidation com 10 folds
         * onde fold[] é o conjunto de folds de treinamento
         * e testFold é o fold de teste (que foi excluído do conjunto de treinamento)
         * O fold 11 é separado para o teste final
         */
        LetterProcessor letterProcessor = new LetterProcessor();

        int[] folds1 = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        int testFold1 = 1;
        letterProcessor.runCrossValidation(folds1, testFold1);

        int[] folds2 = {1, 3, 4, 5, 6, 7, 8, 9, 10};
        int testFold2 = 2;
        letterProcessor.runCrossValidation(folds2, testFold2);

        int[] folds3 = {1, 2, 4, 5, 6, 7, 8, 9, 10};
        int testFold3 = 3;
        letterProcessor.runCrossValidation(folds3, testFold3);

        int[] folds4 = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        int testFold4 = 4;
        letterProcessor.runCrossValidation(folds4, testFold4);

        int[] folds5 = {1, 2, 3, 4, 6, 7, 8, 9, 10};
        int testFold5 = 5;
        letterProcessor.runCrossValidation(folds5, testFold5);
        
        int[] folds6 = {1, 2, 3, 4, 5, 7, 8, 9, 10};
        int testFold6 = 6;
        letterProcessor.runCrossValidation(folds6, testFold6);

        int[] folds7 = {1, 2, 3, 4, 5, 6, 8, 9, 10};
        int testFold7 = 7;
        letterProcessor.runCrossValidation(folds7, testFold7);

        int[] folds8 = {1, 2, 3, 4, 5, 6, 7, 9, 10};
        int testFold8 = 8;
        letterProcessor.runCrossValidation(folds8, testFold8);

        int[] folds9 = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        int testFold9 = 9;
        letterProcessor.runCrossValidation(folds9, testFold9);

        int[] folds10 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int testFold10 = 10;
        letterProcessor.runCrossValidation(folds10, testFold10);
    }
}
