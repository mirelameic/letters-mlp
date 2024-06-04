import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class AlphabetVectors{
    private static final int ALPHABET_SIZE = 26;
    private static final Map<Character, double[]> charToVectorMap = new HashMap<>();
    private static final Map<Integer, Character> vectorToCharMap = new HashMap<>();

    static{
        /* cria um mapa de caracteres para vetores e um mapa de vetores para caracteres
         * onde cada caractere é mapeado para um vetor de 26 posições (um para cada letra do alfabeto)
         * e cada vetor é mapeado para um caractere
         */
        for (char c = 'A'; c <= 'Z'; c++){
            double[] vector = new double[ALPHABET_SIZE];
            vector[c - 'A'] = 1.0;
            charToVectorMap.put(c, vector);
            vectorToCharMap.put(Arrays.hashCode(vector), c);
        }
    }

    public static double[] getLetter(int line){
        /* retorna o vetor correspondente a um número específico (1 para A, 2 para B, etc)
         * se a linha fornecida for menor que 1 ou maior que o tamanho do alfabeto, ele retorna null
         */
        if (line < 1 || line > ALPHABET_SIZE){
            return null;
        }
        return charToVectorMap.get((char) ('A' + line - 1));
    }

    public static char getExpectedResponse(int line){
        /* retorna o caractere correspondente a um número específico (1 para A, 2 para B, etc)
         * se a linha fornecida for menor que 1 ou maior que o tamanho do alfabeto, ele retorna '-'
         */
        if (line < 1 || line > ALPHABET_SIZE){
            return '-';
        }
        return (char) ('A' + line - 1);
    }

    public static char decodeResponse(double[] response){
        /* decodifica um vetor para retornar o caractere correspondente
         * ele utiliza o hash do vetor para encontrar a correspondência no mapa
         * se o vetor não tiver uma correspondência, ele retorna '-'
         */
        return vectorToCharMap.getOrDefault(Arrays.hashCode(response), '-');
    }

    public static int returnLetterNumber(char letter){
        /* retorna o número correspondente a uma letra específica (0 para A, 1 para B, etc)
         * se a letra fornecida estiver fora do intervalo de 'A' a 'Z', ele retorna -1
         */
        if (letter < 'A' || letter > 'Z'){
            return -1;
        }
        return letter - 'A';
    }
}