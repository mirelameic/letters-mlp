# Letters MLP
    Projeto da disciplina de Inteligência Artificial (EACH-USP)

O projeto consiste na implementação de uma rede neural artificial do tipo Multilayer Perceptron (MLP) em Java, sem o uso de bibliotecas especializadas. O objetivo é criar uma estrutura base de MLP capaz de receber imagens de letras, com ou sem ruídos, e identificar qual letra esta sendo enviada.

## Estrutura

- `src`: arquivos .java da estrutura da rede neural
- `files`: arquivos que serão usados no treinamento e processamento da rede neural
- `run`: arquivos .java que fazem o treinamento da rede
- `bin`: arquivos .class

## Classes

### Neuron
A classe `Neuron` representa um neurônio da uma rede neural. Ele armazena os pesos das conexões entre este neurônio e os neurônios da camada anterior, seu bias e a saída calculada.
- **Atributos**:
  - `neuronIndex`: Índice do neurônio na camada
  - `layerIndex`: Índice da camada a qual o neurônio pertence
  - `inWeights`: Array de pesos de entrada do neurônio
  - `bias`: Bias do neurônio
  - `output`: Saída do neurônio após o método `calculateOutput`

- **Métodos**:
  - `Neuron(int layerIndex, int neuronIndex, int numInputs)`: Construtor para inicializar os neurônios com pesos aleatórios.
  - `double calculateOutput(double[] inputs)`: Calcula a saída do neurônio com base nas entradas, pesos e bias.
  - `double sigmoid(double x)`: Função de ativação sigmoidal.

### Layer
A classe `Layer` representa uma camada da rede neural. Cada camada possui um índice próprio e um conjunto de neurônios.
- **Atributos**:
  - `layerIndex`: Índice da camada
  - `neurons`: Array de neurônios na camada

- **Métodos**:
  - `Layer(int layerIndex, int numNeurons, int numInputsPerNeuron)`: Construtor para inicializar a camada e os seus neurônios.
  - `double[] calculateOutputs(double[] inputs)`: Calcula as saídas pra cada neurônio da camada com base nas entradas.

### NeuralNetwork
A classe `NeuralNetwork` representa toda a estrutura da rede neural, composta por várias camadas e vários neurônios dentro de cada camada. Ela contém um array de `Layer`, um array com as informações de inicialização e um array com os outputs finais da rede neural. Também fornece métodos para realizar o treinamento e processamento da rede neural.
- **Atributos**:
  - `layers`: Array de camadas na rede neural
  - `layerInfo`: Array que foi utilizado pra inicializar a rede. Cada elemento `i` representa uma camada e cada valor representa a quantidade de neurônios que a camada terá.
  - `finalOutputs`: Array de outputs finais após a entrada de determinado dado na rede neural.

- **Métodos**:
  - `NeuralNetwork(int[] layerSizes)`: Construtor para inicializar a rede neural com as camadas e números de neurônios especificados.
  - `double[] feedForward(double[] inputs)`: Realiza o feedforward na rede neural com base nas entradas fornecidas.
  - `double[] getOutputs(double[] inputs)`: Devolve os outputs da camada de saída com base nas entradas fornecidas.
