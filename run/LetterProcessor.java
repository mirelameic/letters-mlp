import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterProcessor{
    private NeuralNetwork neuralNetwork;
    private int[] layerInfo;

    public LetterProcessor(){
        this.layerInfo = new int[]{120, 60, 26};
    }

    public void runCrossValidation(int[] folds, int testFold){
        this.neuralNetwork = new NeuralNetwork(this.layerInfo);
        crossValidation(folds, testFold);
    }

    private void crossValidation(int folds[], int testFold){
        for (int fold : folds){
            String filePath = setFilePathWithFoldNumber(fold);
            processImages(filePath, false);
        }

        processImages(setFilePathWithFoldNumber(testFold), true);
    }

    private void processImages(String filePath, boolean isTestFold){
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("File path is not set. Use setFilePathWithFoldNumber() to set the path to the fold file.");
            return;
        }

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
                neuralNetwork.calculateMSE(expectedOutputs);
                currentMSE = neuralNetwork.getMSE();
                System.out.println("MSE: " + currentMSE);
                linha++;
            }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        private String setFilePathWithFoldNumber(int foldNumber){
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
}
