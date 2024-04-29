import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImageProcessor{

    private NeuralNetwork neuralNetwork;

    public static void main(String[] args){
        // Input layer size = 120, hidden layer = 60, output layer = 26
        int[] layerSizes = {120, 60, 26};
        NeuralNetwork neuralNetwork = new NeuralNetwork(layerSizes);
        ImageProcessor imageProcessor = new ImageProcessor(neuralNetwork);
        String filePath = System.getProperty("user.dir") + "/files/conjunto-dados/teste-x.txt";
        imageProcessor.processImages(filePath);
    }

    public ImageProcessor(NeuralNetwork neuralNetwork){
        this.neuralNetwork = neuralNetwork;
    }

    public void processImages(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null){
                double[] inputs = parseInputLine(line);
                double[] outputs = neuralNetwork.getOutputs(inputs);
                printOutputs(outputs);
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

    private void printOutputs(double[] outputs){
        System.out.println("---- outputs ----");
        for (double output : outputs){
            System.out.println(output);
        }
        System.out.println();
    }
}
