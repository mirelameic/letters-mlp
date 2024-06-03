import java.util.Arrays;

public class AlphabetVectors{
    /* Vetores de saída esperada para cada letra do alfabeto */
    public static final double[] A = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] B = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] C = {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] D = {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] E = {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] F = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] G = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] H = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] J = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] K = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] L = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] M = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] N = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] O = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] P = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] Q = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] R = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] S = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
    public static final double[] T = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
    public static final double[] U = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
    public static final double[] V = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    public static final double[] W = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
    public static final double[] X = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
    public static final double[] Y = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0};
    public static final double[] Z = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};


    /* getLetter considerando a numeração das linhas dos arquivos txt */
    public static double[] getLetter(int line){
        switch (line){
            case 1:
                return A;
            case 2:
                return B;
            case 3:
                return C;
            case 4:
                return D;
            case 5:
                return E;
            case 6:
                return F;
            case 7:
                return G;
            case 8:
                return H;
            case 9:
                return I;
            case 10:
                return J;
            case 11:
                return K;
            case 12:
                return L;
            case 13:
                return M;
            case 14:
                return N;
            case 15:
                return O;
            case 16:
                return P;
            case 17:
                return Q;
            case 18:
                return R;
            case 19:
                return S;
            case 20:
                return T;
            case 21:
                return U;
            case 22:
                return V;
            case 23:
                return W;
            case 24:
                return X;
            case 25:
                return Y;
            case 26:
                return Z;
            default:
                return null;
        }
    }

    public static char getExpectedResponse(int line){
        switch (line){
            case 1:
                return 'A';
            case 2:
                return 'B';
            case 3:
                return 'C';
            case 4:
                return 'D';
            case 5:
                return 'E';
            case 6:
                return 'F';
            case 7:
                return 'G';
            case 8:
                return 'H';
            case 9:
                return 'I';
            case 10:
                return 'J';
            case 11:
                return 'K';
            case 12:
                return 'L';
            case 13:
                return 'M';
            case 14:
                return 'N';
            case 15:
                return 'O';
            case 16:
                return 'P';
            case 17:
                return 'Q';
            case 18:
                return 'R';
            case 19:
                return 'S';
            case 20:
                return 'T';
            case 21:
                return 'U';
            case 22:
                return 'V';
            case 23:
                return 'W';
            case 24:
                return 'X';
            case 25:
                return 'Y';
            case 26:
                return 'Z';
            default:
                return '-';
        }
    }

    public static char decodeResponse(double[] response){
        if(Arrays.equals(response, A)){
            return 'A';
        }
        else if(Arrays.equals(response, B)){
            return 'B';
        }
        else if(Arrays.equals(response, C)){
            return 'C';
        }
        else if(Arrays.equals(response, D)){
            return 'D';
        }
        else if(Arrays.equals(response, E)){
            return 'E';
        }
        else if(Arrays.equals(response, F)){
            return 'F';
        }
        else if(Arrays.equals(response, G)){
            return 'G';
        }
        else if(Arrays.equals(response, H)){
            return 'H';
        }
        else if(Arrays.equals(response, I)){
            return 'I';
        }
        else if(Arrays.equals(response, J)){
            return 'J';
        }
        else if(Arrays.equals(response, K)){
            return 'K';
        }
        else if(Arrays.equals(response, L)){
            return 'L';
        }
        else if(Arrays.equals(response, M)){
            return 'M';
        }
        else if(Arrays.equals(response, N)){
            return 'N';
        }
        else if(Arrays.equals(response, O)){
            return 'O';
        }
        else if(Arrays.equals(response, P)){
            return 'P';
        }
        else if(Arrays.equals(response, Q)){
            return 'Q';
        }
        else if(Arrays.equals(response, R)){
            return 'R';
        }
        else if(Arrays.equals(response, S)){
            return 'S';
        }
        else if(Arrays.equals(response, T)){
            return 'T';
        }
        else if(Arrays.equals(response, U)){
            return 'U';
        }
        else if(Arrays.equals(response, V)){
            return 'V';
        }
        else if(Arrays.equals(response, W)){
            return 'W';
        }
        else if(Arrays.equals(response, X)){
            return 'X';
        }
        else if(Arrays.equals(response, Y)){
            return 'Y';
        }
        else if(Arrays.equals(response, Z)){
            return 'Z';
        }
        else{
            return '-';
        }
    }

    public static int returnLetterNumber(char letter){
        switch(letter){
            case 'A':
                return 0;
            case 'B': 
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            case 'K':
                return 10;
            case 'L':
                return 11;
            case 'M':
                return 12;
            case 'N':
                return 13;
            case 'O':
                return 14;
            case 'P':
                return 15;
            case 'Q':
                return 16;
            case 'R':
                return 17;
            case 'S':
                return 18;
            case 'T':
                return 19;
            case 'U':
                return 20;
            case 'V':
                return 21;
            case 'W':
                return 22;
            case 'X':
                return 23;
            case 'Y':
                return 24;
            case 'Z':
                return 25;
            default:
                return -1; // or some other default value

        }
    }
}
