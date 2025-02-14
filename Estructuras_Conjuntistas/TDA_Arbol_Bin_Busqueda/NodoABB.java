package Estructuras_Conjuntistas.TDA_Arbol_Bin_Busqueda;
/**
 *
 * @author Cabanne, Gonzalo Heber
 */
public class NodoABB {
    private Comparable elemento;
    private NodoABB hijoIzq;
    private NodoABB hijoDer;

    //Constructor
    public NodoABB(Comparable unElemento, NodoABB HI, NodoABB HD){
        this.elemento = unElemento;
        this.hijoDer = HD;
        this.hijoIzq = HI;
    }

    //Observadores
    public Comparable getElemento(){
        return this.elemento;
    }

    public NodoABB getHijoIzq(){
        return this.hijoIzq;
    }

    public NodoABB getHijoDer(){
        return this.hijoDer;
    }

    //Modificadores
    public void setElemento(Comparable unElemento){
        this.elemento = unElemento;
    }

    public void setHijoIzq(NodoABB unNodo){
        this.hijoIzq = unNodo;
    }

    public void setHijoDer(NodoABB unNodo){
        this.hijoDer = unNodo;
    }
}
