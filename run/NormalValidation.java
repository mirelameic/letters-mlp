public class NormalValidation{
    public static void main(String[] args){
        /* validação normal e validação normal com parada antecipada, 
         * ambas com 1000 épocas e divisão dos dados entre
         * 80% treinamento e 20% teste/validação
         */
        LetterProcessor letterProcessor = new LetterProcessor();
        int epocas = 1000;
        letterProcessor.runNormalValidation(epocas);

        letterProcessor.runNormalValidationEarlyStopping(epocas);
    }    
}
