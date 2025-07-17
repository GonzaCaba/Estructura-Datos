package Estructuras_Grafos.TDA_DiGrafo_Etiquetado;

class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    //Constructor
    public NodoAdy(NodoVert unVertice, NodoAdy unSigAdy, Object unaEtiqueta){
        this.vertice = unVertice;
        this.sigAdyacente = unSigAdy;
        this.etiqueta = unaEtiqueta;
    }

    //Modificadores
    public void setVertice(NodoVert unVert){
        this.vertice = unVert;
    }

    public void setSigAdyacente(NodoAdy unAdy){
        this.sigAdyacente = unAdy;
    }

    public void setEtiqueta(Object unaEtiqueta){
        this.etiqueta = unaEtiqueta;
    }

    //Observadores
    public NodoVert getVertice(){
        return this.vertice;
    }

    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }

    public Object getEtiqueta(){
        return this.etiqueta;
    }
}
