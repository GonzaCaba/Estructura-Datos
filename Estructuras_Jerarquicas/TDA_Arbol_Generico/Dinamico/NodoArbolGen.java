package Estructuras_Jerarquicas.TDA_Arbol_Generico.Dinamico;
/**
 *
 * @author Cabanne, Gonzalo Heber
 */
class NodoArbolGen {
    private Object elemento;
    private NodoArbolGen hijoExtremoIzquierdo;
    private NodoArbolGen hermanoDerecho;

    public NodoArbolGen(Object unElem, NodoArbolGen HEI, NodoArbolGen HD){
        this.elemento = unElem;
        this.hijoExtremoIzquierdo = HEI;
        this.hermanoDerecho = HD;
    }

    //Getters
    public Object getElemento(){
        return this.elemento;
    }

    public NodoArbolGen getHEI(){
        return this.hijoExtremoIzquierdo;
    }

    public NodoArbolGen getHD(){
        return this.hermanoDerecho;
    }

    //Setters
    public void setElemento(Object unElemento){
        this.elemento = unElemento;
    }

    public void setHEI(NodoArbolGen unNodo){
        this.hijoExtremoIzquierdo = unNodo;
    }

    public void setHD(NodoArbolGen unNodo){
        this.hermanoDerecho = unNodo;
    }
}
