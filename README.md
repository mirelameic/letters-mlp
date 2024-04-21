# Letters MLP
    Projeto da disciplina de Inteligência Artificial (EACH-USP)

O projeto consiste na implementação de uma rede neural artificial do tipo Multilayer Perceptron (MLP) em Java, sem o uso de bibliotecas especializadas em redes neurais. O objetivo é criar uma estrutura capaz de receber imagens de letras, com ou sem ruídos, e identificar qual letra é representada.

## Estrutura

- `src`: pasta com arquivos .java
- `bin`: pasta com arquivos .class

## Classes

### Neuron
A classe Neuron representa um neurônio em uma rede neural. Cada neurônio possui um índice de camada e um índice de neurônio dentro dessa camada. Ele também armazena os pesos das conexões entre este neurônio e os neurônios da camada anterior, bem como a saída calculada após a aplicação da função de ativação.
- **Atributos**:
  - `neuronIndex`: Índice do neurônio na camada
  - `layerIndex`: Índice da camada a qual o neurônio pertence
  - `weights`: Array de pesos dos neurônios
  - `output`: Saída do neurônio após o cálculo

- **Métodos**:
  - `Neuron(layerIndex: int, neuronIndex: int, numInputs: int)`: Construtor para inicializar os neurônios com pesos aleatórios.
  - `calculateOutput(inputs: double[]): double`: Calcula a saída do neurônio com base nas entradas e nos pesos.
  - `sigmoid(x: double): double`: Função de ativação sigmoidal.

### Layer
A classe Layer representa uma camada em uma rede neural. Cada camada possui um índice próprio e um conjunto de neurônios. Os neurônios em uma camada são responsáveis por calcular as saídas com base nas entradas recebidas.
- **Atributos**:
  - `layerIndex`: Índice da camada
  - `neurons`: Array de neurônios na camada

- **Métodos**:
  - `Layer(layerIndex: int, numNeurons: int, numInputsPerNeuron: int)`: Construtor para inicializar a camada com os neurônios.
  - `calculateOutputs(inputs: double[]): double[]`: Calcula as saídas da camada com base nas entradas.

### NeuralNetwork
A classe NeuralNetwork representa toda a estrutura de uma rede neural, composta por várias camadas. Ela mantém um array de camadas e fornece métodos para realizar o feedforward.
- **Atributos**:
  - `layers`: Array de camadas na rede neural

- **Métodos**:
  - `NeuralNetwork(layerSizes: int[])`: Construtor para inicializar a rede neural com as camadas especificadas.
  - `feedForward(inputs: double[]): double[]`: Realiza o feedforward na rede neural com base nas entradas fornecidas.
