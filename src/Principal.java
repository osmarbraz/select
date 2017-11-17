/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 49 da aula do dia 22/09/2017  
 *
 * Problema de Seleção
 * Dado um conjunto A de n números inteiro e um inteiro i, 
 * determinar o i-ésimo menor elemento de A.
 * Executamos PARTICIONEALEATÓRIO e este rearranja o vetor e devolve um índice k tal que
 *    A[1...k−1] <= A[k] < A[k+1...n]
 * 
 * Eis a idéia do algoritmo:
 *   Se i = k, então o pivô A[k] é o i-ésimo menor! Achei!!
 *   Se i < k, então o i-ésimo menor está em A[1...k − 1];
 *   Se i > k, então o i-ésimo menor estáa em A[k+1...n].
 *
 * Atenção:
 * Vetor em java inicia em 0, os algoritmos consideram início em 1.
 * A subtraçào de -1 ocorre somente no local de acesso ao vetor ou matriz 
 * para manter a compatibilidade entre os algoritmos.
 *
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {

    /**
     * O teto (ceiling) de um número real x é o resultado do arredondamento de x
     * para cima. Em outras palavras, o teto de x é o único número inteiro j tal
     * que j−1<x<=j Ex. O teto de 3.9 é 4
     *
     * Em java pode ser utilizando Math.ceil(double)
     *
     * @param x Numero real a ser cálculado o teto.
     * @return um valor inteiro com o teto de x.
     */
    public static int teto(double x) {
        //Pego a parte inteira de x;
        int parteInteira = (int) x;
        //Pego a parte fracionária de x
        double parteFracionaria = x - parteInteira;
        //Retorno x subtraindo a parte fracionaria e adiciona 1;
        return (int) (x - parteFracionaria) + 1;
    }

    /**
     * O piso (= floor) de um número real x é o resultado do arredondamento de x
     * para baixo. Em outras palavras, o piso de x é o único número inteiro i
     * tal que i<=x<i+1. Ex. O piso de 3.9 é 3
     *
     * Em java pode ser utilizando Math.floor(double)
     *
     * @param x Numero real a ser cálculado o piso.
     * @return um valor inteiro com o piso de x.
     */
    public static int piso(double x) {
        //Pego a parte inteira de x
        int parteInteira = (int) x;
        //Pego a parte fracionária de x
        double parteFracionaria = x - parteInteira;
        //Retorno x subtraindo a parte fracionaria 
        return (int) (x - parteFracionaria);
    }

    /**
     * Retorna o menor valor entre dois elementos
     *
     * @param a primeiro valor
     * @param b segundo valor
     * @return o menor valor entre os dois elementos
     */
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Realiza a busca de x no vetor A no intervalo de p a r
     *
     * @param A vetor que contêm os dados
     * @param p inicio do vetor
     * @param r final do vetor
     * @param x elemento a ser procurado
     * @return
     */
    public static int busca(int A[], int p, int r, int x) {
        System.out.println("p:" + p + " r:" + r + " x:" + x);
        while ((p <= r) && (A[p-1] != x)) {
            p = p + 1;
        }
        System.out.println("achei x:" + x + " valor:" + A[p-1]);
        if (p <= r) {
            return p;
        } else {
            return -1;
        }
    }

    /**
     * Seleciona um numero aleatorio no intervalo de inicio a fim
     *
     * @param inicio Inicio do intervalodo número aleatório
     * @param fim Fim do intervalo do número aleatório
     */
    public static int aleatorio(int inicio, int fim) {
        return (int) Math.ceil(Math.random() * (fim - inicio + 1)) - 1 + inicio;
    }

    /**
     * Realiza a troca de posição de dois elementos do vetor.
     *
     * @param A Vetor que contem os dados
     * @param i Primeira posição de troca
     * @param j Segunda posição de troca
     */
    public static void troca(int[] A, int i, int j) {
        int aux = A[i-1];
        A[i-1] = A[j-1];
        A[j-1] = aux;
    }

    /**
     * Particione encontra o pivo. Complexidade de tempo Theta(n). T(n) =
     * Theta(2n + 4) + O(2n) = Theta(n) Slide 68.
     *
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     * @return o pivo da troca
     */
    public static int particione(int A[], int p, int r) {
        //x é o "pivô"
        int x = A[r-1];                       //Theta(1)
        int i = p - 1;                      //Theta(1)
        for (int j = p; j <= r - 1; j++) {  //Theta(n)
            if (A[j-1] <= x) {                //Theta(n)
                i = i + 1;                  //O(n)
                troca(A, i, j);             //O(n)
            }
        }
        troca(A, i + 1, r);                 //Theta(1)
        return i + 1;                       //Theta(1)
    }

    /**
     * Particione aleatório encontra o pivo de forma aleatória. Slide 91.
     *
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     * @return o pivo da troca
     */
    public static int particioneAleatorio(int A[], int p, int r) {
        //i é o "pivô"
        int i = aleatorio(p, r);             //Theta(1)            
        troca(A, i, r);                       //Theta(1)
        return particione(A, p, r);           //Theta(n)
    }

    /**
     * Executa o quicksort aleatório recursivo. Algoritmos de ordenação podem
     * ser ou não in-place ou estáveis. Um algoritmo de ordenação é in-place se
     * a memória adicional requerida é independente do tamanho do vetor que está
     * sendo ordenado. O quicksort é in-place
     *
     * Divisão: divida o vetor em dois subvetoresA[p ... q−1] e A[q+1...r]
     * Conquista: ordena os dois subvetores recursivamente usando quicksort
     * Combinação: nada a fazer, o vetor está ordenado. Slide 68 T(n) = T(k) +
     * T(n−k−1) + Theta(n+1) Complexidade de tempo no caso médio O(n log n)
     *
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     */
    public static void quicksortAleatorio(int A[], int p, int r) {
        if (p < r) {                        //Theta(1)
            int q = particioneAleatorio(A, p, r);    //Theta(n)
            quicksortAleatorio(A, p, q - 1);         //T(k)
            quicksortAleatorio(A, q + 1, r);         //T(n-k-1)    
        }
    }

    /**
     * Ordenação por Insercao.
     *
     * Inicialmente, pega os dois primeiros elementos de um vetor, ou seja,
     * posições 0 e 1 1. Atribui o elemento na posição 1 como chave Compara a
     * chave com todos os elementos cujos respectivos índices sejam inferiores -
     * Até que encontre um menor valor menor que o da chave; OU - O início do
     * vetor seja atingido. 2. Repete o processo até o último elemento do vetor
     *
     * Complexidade no pior caso Theta(n^2) Complexidade no caso m~edio/esperado
     * Theta(n log n) Slide 11
     *
     * @param A Vetor a ser ordenado
     * @param n Quantidade de elementos do vetor
     */
    public static void ordenaPorInsercao(int A[], int p, int r) {
        for (int j = p; j <= r; j++) {                            //c1 * n
            int chave = A[j-1];                                       //c2 * n-1
            //Inserir A[j] dentro da sequencia ordenada A[1..j-1]
            //Compara chave com elementos posicionados antes no vetor(j-1)
            //Para se pelo menos uma das situações ocorrer:
            //Alcançar o início do vetor(i>=0) ou a elemento anterior for maior que a chave
            int i = j - 1;                                          //c3 * n-1
            while ((i >= p) && (A[i-1] > chave)) {                    //c4 * Somatorio(2 até n)tj
                //Desloca os elementos para abrir espa;o
                A[i + 1-1] = A[i-1];                                    //c5 * Somatorio(2 até n)tj-1
                //Decrementa o contador até o inicio do vetor
                i = i - 1;                                          //c6 * Somatorio(2 até n)tj-1
            }
            // posicao de insercao
            A[i + 1 -1] = chave;                                       //c7 * n - 1
        }
    }

    private static int mediana(int A[], int p, int r) {
        int n = r;
        int m = piso(n / 2);
         System.out.println("1Mediana =>p:" + p + " r=" + (r) + " m:" + m + " n=" + (n));
        ordenaPorInsercao(A, p, n); //Ordena todo o vetor para encontrar a mediana       
        //selectAleatorio(A, p, n - 1, m); //Particiona todo o vetor coloca a media na posição m
        if (n % 2 == 1) {
            System.out.println("(p + m):"+(p + m));
            return (p + m);
        } else {
            System.out.println("(((p + m) + (p + m - 1)) / 2:"+(((p + m) + (p + m - 1)) / 2));
            return ((p + m) + (p + m - 1)) / 2;
        }
    }

    /**
     * Recebe um vetor A[1...n] e devolve o valor do i-ésimo menor elemento de
     * A. A complexidade de tempo no pior caso n = r - p + 1 T(n) = max{T(k-1),
     * T(n-k)} + Theta(n) T(n) = Theta(n^2)
     *
     * A complexidade para o caso médio é Theta(n)
     *
     * @param A Vetor com os valores
     * @param p Posição inicial do vetor
     * @param r Posição final do vetor
     * @param i Posição desejada do vetor
     * @return Um valor do elemento da posição i do vetor
     */
     public static int selectMediana(int A[], int p, int r, int i) {
        if (p == r) {                                      //Theta(1)
            return A[p-1];                                   //O(1)
        }
        int n = teto((r - p + 1) / 5.0);
        
    
        System.out.println("n:" + n + " r:"+r);
        int B[] = new int[n];
        for (int j = 1; j <= n; j++) {
            //System.out.println(">>> p + 5*(j):"+ (p + 5*(j))+" / min((p+5*j),r):"+min((p+5*j),r)+" /r:"+r); 
            int pp = p + 5 * (j-1);
            //int parte = p + 5 * (j+1-1);
            int rr = min((p + (5 * j) - 1), r);
            System.out.println(">>> Parte(j):" + j +" pp:" + (pp) + " / rr:" + (rr));
            int med = mediana(A,
                    pp,
                    rr);
            System.out.println("B[j-1]:" + (B[j-1]) + "  A[med]:" + (med-1));
            B[j-1] = A[med-1];
            //System.out.println("B[j]:" + (B[j-1]) + "  A[j]:" + (A[B[j-1]]));
            System.out.println("B[j]:" + (B[j-1]) );
        }
        System.out.println("Procurando em B:");
        int x = selectMediana(B, 1, n, teto(n / 2.0));   
        System.out.println("x0:" + x) ;
        
        int j = busca(A, p, r, x);
        //System.out.println(">>>>B[j]:" + (B[j]) + "  A[j]:" + (A[B[j]]));
        System.out.println("x:" + x + " j:" + j + "/ n:" + n + "/ n/2:" + n / 2);
        troca(A, j, r);
        int q = particione(A, p, r);
        
        int k = q - p + 1;                              //Theta(n)
        if (i == k) { //O valor do pivô é a resposta       //Theta(n)
            return A[q-1];                                //O(1)   
        } else {
            if (i < k) {                                 //O(1)   
                return selectMediana(A, p, q - 1, i);   //T(k-1)  
            } else {
                return selectMediana(A, q + 1, r, i - k); //T(n-k)
            }
        }
    }

    public static void main(String[] args) {
        //Vetor dos dados    
        //1seg 0, 
        //int A[] = {50, 70, 60, 90, 10, 30, 20, 40};
        int A[] = {50, 70, 60, 90, 10, 30, 20};
        //int A[] = {50, 70, 60, 90, 10, 30, 20, 40, 10, 20, 30, 33, 44, 55, 66,77,88,99,12,13,14};

        //Quantidade de elementos
        int r = A.length ;

        //Posição do i-ésimo termo
        //Posição do i-ésimo termo
        int i = 1; //Primeiro termo, o menor
        //int i = r+1; //Último termo, o maior
        int menor = selectMediana(A, 1, r, i);

        System.out.println("Menor:" + menor);
    }

}
