/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides 33 da aula do dia 22/09/2017  
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
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {
    
    /**
     * Retorna o menor valor entre dois elementos
     * @param a primeiro valor
     * @param b segundo valor
     * @return  o menor valor entre os dois elementos
     */    
    public static int min(int a, int b){
        if (a < b){
            return a;
        } else {
            return b;
        }
    }
    
    /**
     * Realiza a busca de x no vetor A no intervalo de p a r
     * @param A vetor que contêm os dados
     * @param p inicio do vetor
     * @param r final do vetor
     * @param x elemento a ser procurado
     * @return 
     */
    public static int busca(int A[], int p, int r, int x){
        while ((p <=r) && (A[p] != x)){
            p = p + 1;
        }
        if (p<=r){
            return p;
        } else {
            return -1;
        }
    }
    
     /**
     * Seleciona um numero aleatorio no intervalo de inicio a fim
     * @param inicio Inicio do intervalodo número aleatório
     * @param fim Fim do intervalo do número aleatório
     */
    public static int aleatorio(int inicio, int fim) {
        return (int) Math.ceil(Math.random() * (fim - inicio + 1)) - 1 + inicio;
    }   
    
    /**
     * Realiza a troca de posição de dois elementos do vetor.
     * @param A Vetor que contem os dados
     * @param i Primeira posição de troca
     * @param j Segunda posição de troca
     */
    public static void troca(int[] A, int i, int j) {
        int aux = A[i];
        A[i] = A[j];
        A[j] = aux;
    }

     /**
     * Particione encontra o pivo.
     * Complexidade de tempo Theta(n).
     * T(n) = Theta(2n + 4) + O(2n) = Theta(n) 
     * Slide 68.     
     * 
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     * @return o pivo da troca
     */
    public static int particione(int A[], int p, int r) {
        //x é o "pivô"
        int x = A[r];                       //Theta(1)
        int i = p - 1;                      //Theta(1)
        for (int j = p; j <= r - 1; j++) {  //Theta(n)
            if (A[j] <= x) {                //Theta(n)
                i = i + 1;                  //O(n)
                troca(A, i, j);             //O(n)
            }
        }
        troca(A, i + 1, r);                 //Theta(1)
        return i + 1;                       //Theta(1)
    }
        
    /**
     * Ordenação por Insercao.
     * 
     * Inicialmente, pega os dois primeiros elementos de um vetor, ou seja, posições 0 e 1
     * 1. Atribui o elemento na posição 1 como chave
     * Compara a chave com todos os elementos cujos respectivos índices sejam inferiores
     *    - Até que encontre um menor valor menor que o da chave; OU
     *    - O início do vetor seja atingido.
     * 2. Repete o processo até o último elemento do vetor     
     * 
     * Complexidade no pior caso Theta(n^2)
     * Complexidade no caso m~edio/esperado Theta(n log n)
     * Slide 11
     * @param A Vetor a ser ordenado
     * @param n Quantidade de elementos do vetor
     */
    public static void ordenaPorInsercao(int A[], int p, int r) {
        for (int j = p+1; j <= r; j++) {                            //c1 * n
            int chave = A[j];                                       //c2 * n-1
            //Inserir A[j] dentro da sequencia ordenada A[1..j-1]
            //Compara chave com elementos posicionados antes no vetor(j-1)
            //Para se pelo menos uma das situações ocorrer:
            //Alcançar o início do vetor(i>=0) ou a elemento anterior for maior que a chave
            int i = j - 1;                                          //c3 * n-1
            while ((i >= 0) && (A[i] > chave)) {                    //c4 * Somatorio(2 até n)tj
                //Desloca os elementos para abrir espa;o
                A[i + 1] = A[i];                                    //c5 * Somatorio(2 até n)tj-1
                //Decrementa o contador até o inicio do vetor
                i = i - 1;                                          //c6 * Somatorio(2 até n)tj-1
            } 
            // posicao de insercao
            A[i + 1] = chave;                                       //c7 * n - 1
        }
    }
     
    private static int mediana(int A[], int p, int r) {		
	int n = r;
        int m = n/2;
        ordenaPorInsercao(A, p, r); //Ordena todo o vetor para encontrar a mediana       
        if (n%2 == 1) {				
             return (p + m);
        } else {  				
             return ((p + m) + (p + m - 1))/2;
        }	
    }	
    
    /**
     * Recebe um vetor A[1...n] e devolve o valor do i-ésimo menor elemento de A.
     * A complexidade de tempo no pior caso 
     * n = r - p + 1    
     * T(n) = max{T(k-1), T(n-k)} + Theta(n)
     * T(n) = Theta(n^2)
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
        if (p==r) {                                     
            //System.out.println("Achei p:"+p);
            return A[p];                               
        }  else {
            //Como em java vetor inicia em 0 retire a soma de + 1
            int n = (r-p)/5;           
            
            System.out.println("1n:"+n);
            int B[] = new int[n];
            for(int j = 0; j < n; j++){                
                //System.out.println("j:"+j+" / p:"+p+" /r:"+r);
                B[j] = mediana(A, 
                        p + 5*(j), 
                        min((p+5*j),r));
            }           
            int x = selectMediana(B, 0, n, n/2);
            //System.out.println("x:"+ x + "/ n:"+ n + "/ n/2:"+n/2);
            int j = busca(A, p, r, x);
            troca(A, j, r);
            int q = particione(A, p, r);                
            //Como em java vetor inicia em 0 retire a soma de + 1
            //int k = p - q + 1
            int k = q - p;                                  
            if (i==k){ //Pivô é o i-ésimo menor!            
                return x;                                   
            } else {                                
                if (i < k){                                 
                    return selectMediana(A, p, q-1, i);     
                } else {
                    return selectMediana(A, q+1, r, i-k);   
                }
            }       
        }
        
    }

    public static void main(String[] args) {
        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};

        //Quantidade de elementos
        int r = A.length-1;

        //Posição do i-ésimo termo
        int i = 0;
        int menor = selectMediana(A, 0, r, i);
        
        System.out.println("Menor:" + menor);        
    }
    
}