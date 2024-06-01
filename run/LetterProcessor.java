import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterProcessor{

    private NeuralNetwork neuralNetwork;

    public static void main(String[] args){
        // Input layer size = 120, hidden layer = 60, output layer = 26
        int[] layerInfo = {120, 60, 26};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);
        LetterProcessor letterProcessor = new LetterProcessor(neuralNetwork);
        String filePath = System.getProperty("user.dir") + "/data/cross-validation/1-fold-x.txt";
        letterProcessor.processImages(filePath);
    }
    
    public void processImages(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            // para cada arquivo de fold - roda 130x para 130 linhas
            int linha = 1;
            double currentMSE = 0;
            while ((line = br.readLine()) != null){
                linha = linha % 26;
                linha = linha == 0 ? 26 : linha;
                // System.out.println("Linha: " + linha);
                double[] expectedOutputs = AlphabetVectors.getLetter(linha);
                double[] inputs = parseInputLine(line);
                double[] outputs = neuralNetwork.runFeedForward(inputs);
                neuralNetwork.runBackpropagation(expectedOutputs, 0.5);
                // neuralNetwork.printOutputs();
                // for(double expectedOutput : expectedOutputs){
                    //     System.out.println(expectedOutput);
                    // }
                    neuralNetwork.calculateMSE(expectedOutputs);
                    currentMSE = neuralNetwork.getMSE();
                    System.out.println("MSE: " + currentMSE);
                // if(linha == 26){
                    //     break;
                    // }
                    linha++;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
        private double[] parseInputLine(String line){
            String[] values = line.trim().split(",");
            double[] inputs = new double[values.length];
            for (int i = 0; i < values.length; i++){
                inputs[i] = Double.parseDouble(values[i].trim());
            }
            return inputs;
        }

        public LetterProcessor(NeuralNetwork neuralNetwork){
            this.neuralNetwork = neuralNetwork;
        }
}
