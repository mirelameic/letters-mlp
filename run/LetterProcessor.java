import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterProcessor{
    private NeuralNetwork neuralNetwork;

    public static void main(String[] args){
        // Input layer size = 120, hidden layer = 60, output layer = 26
        int[] layerInfo = {120, 60, 26};
        int[] folds = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        int testFold = 1;
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerInfo);
        LetterProcessor letterProcessor = new LetterProcessor(neuralNetwork);
        letterProcessor.crossValidation(folds, testFold);

        // String filePath = System.getProperty("user.dir") + "/data/cross-validation/1-fold-x.txt";
        // letterProcessor.processImages(filePath);
    }

    public void crossValidation(int folds[], int testFold){
        for (int fold : folds){
            // System.out.println("Fold: " + fold);
            String filePath = setFilePathWithFoldNumber(fold);
            processImages(filePath, false);
        }


        processImages(setFilePathWithFoldNumber(testFold), true);
        // System.out.println(setFilePathWithFoldNumber(testFold));
    }

    public void processImages(String filePath, boolean isTestFold){
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("File path is not set. Use setFilePathWithFoldNumber() to set the path to the fold file.");
            return;
        }

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
                if (!isTestFold){
                    System.out.println("oi");
                    neuralNetwork.runBackpropagation(expectedOutputs, 0.5);
                }
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

        public String setFilePathWithFoldNumber(int foldNumber){
            return System.getProperty("user.dir") + "/data/cross-validation/" + foldNumber + "-fold-x.txt";
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
