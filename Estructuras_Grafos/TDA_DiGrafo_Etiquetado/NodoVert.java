package Estructuras_Grafos.TDA_DiGrafo_Etiquetado;

class NodoVert {
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    //Constructor
    public NodoVert(Object unElem, NodoVert unSigVertice, NodoAdy unPrimerAdy){
        this.elem = unElem;
        this.sigVertice = unSigVertice;
        this.primerAdy = unPrimerAdy;
    }

    //Modificadores
    public void setElem(Object unElem){
        this.elem = unElem;
    }

    public void setSigVertice(NodoVert unVert){
        this.sigVertice = unVert;
    }

    public void setPrimerAdy(NodoAdy unAdy){
        this.primerAdy = unAdy;
    }

    //Observadores
    public Object getElem(){
        return this.elem;
    }

    public NodoVert getSigVertice(){
        return this.sigVertice;
    }

    public NodoAdy getPrimerAdy(){
        return this.primerAdy;
    }
}
