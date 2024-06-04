public class NormalValidation{
    public static void main(String[] args){
        /* validação normal com 1000 épocas
         * (os dados foram divididos em 80% treinamento e 20% teste)
         */
        LetterProcessor letterProcessor = new LetterProcessor();
        int epocas = 1000;
        letterProcessor.runNormalValidation(epocas);

        letterProcessor.runNormalValidationEarlyStopping(epocas);
    }    
}
