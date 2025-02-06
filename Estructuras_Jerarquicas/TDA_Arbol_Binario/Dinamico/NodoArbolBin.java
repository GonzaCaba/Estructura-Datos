package Estructuras_Jerarquicas.TDA_Arbol_Binario.Dinamico;

class NodoArbolBin {
    private Object elemento;
    private NodoArbolBin hijoIzq;
    private NodoArbolBin hijoDer;

    //Constructor
    public NodoArbolBin(Object unElemento, NodoArbolBin HI, NodoArbolBin HD){
        this.elemento = unElemento;
        this.hijoDer = HD;
        this.hijoIzq = HI;
    }

    //Observadores
    public Object getElemento(){
        return this.elemento;
    }

    public NodoArbolBin getHijoIzq(){
        return this.hijoIzq;
    }

    public NodoArbolBin getHijoDer(){
        return this.hijoDer;
    }

    //Modificadores
    public void setElemento(Object unElemento){
        this.elemento = unElemento;
    }

    public void setHijoIzq(NodoArbolBin unNodo){
        this.hijoIzq = unNodo;
    }

    public void setHijoDer(NodoArbolBin unNodo){
        this.hijoDer = unNodo;
    }
}
