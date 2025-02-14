package Estructuras_Conjuntistas.TDA_Arbol_AVL;
/**
 *
 * @author Cabanne, Gonzalo Heber
 */
class NodoAVL {
    private Comparable elemento;
    private NodoAVL hijoIzq;
    private NodoAVL hijoDer;
    private int altura;

    //Constructor
    public NodoAVL(Comparable unElemento, NodoAVL HI, NodoAVL HD, int unaAltura){
        this.elemento = unElemento;
        this.hijoDer = HD;
        this.hijoIzq = HI;
        this.altura = unaAltura;
    }

    //Observadores
    public Comparable getElemento(){
        return this.elemento;
    }

    public NodoAVL getHijoIzq(){
        return this.hijoIzq;
    }

    public NodoAVL getHijoDer(){
        return this.hijoDer;
    }

    public int getAltura(){
        return this.altura;
    }

    //Modificadores
    public void setElemento(Comparable unElemento){
        this.elemento = unElemento;
    }

    public void setHijoIzq(NodoAVL unNodo){
        this.hijoIzq = unNodo;
    }

    public void setHijoDer(NodoAVL unNodo){
        this.hijoDer = unNodo;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public void recalcularAltura(){
        this.altura = calcularAltura(this);
    }

    //Auxiliares & Privados
    private int calcularAltura(NodoAVL unNodo){
        int mayorAlt = -1;
        if (unNodo != null) {
            int altArbolIzq = calcularAltura(unNodo.getHijoIzq());
            int altArbolDer = calcularAltura(unNodo.getHijoDer());
            mayorAlt = Math.max(altArbolIzq, altArbolDer) + 1;
        }
        return mayorAlt;
    }
}   
