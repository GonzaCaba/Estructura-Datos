package Estructuras_Jerarquicas.TDA_Arbol_Binario.Dinamico;

class NodoArbol {
    private Object elemento;
    private NodoArbol hijoIzq;
    private NodoArbol hijoDer;

    //Constructor
    public NodoArbol(Object unElemento, NodoArbol HI, NodoArbol HD){
        this.elemento = unElemento;
        this.hijoDer = HD;
        this.hijoIzq = HI;
    }

    //Observadores
    public Object getElemento(){
        return this.elemento;
    }

    public NodoArbol getHijoIzq(){
        return this.hijoIzq;
    }

    public NodoArbol getHijoDer(){
        return this.hijoDer;
    }

    //Modificadores
    public void setElemento(Object unElemento){
        this.elemento = unElemento;
    }

    public void setHijoIzq(NodoArbol unNodo){
        this.hijoIzq = unNodo;
    }

    public void setHijoDer(NodoArbol unNodo){
        this.hijoDer = unNodo;
    }
}
