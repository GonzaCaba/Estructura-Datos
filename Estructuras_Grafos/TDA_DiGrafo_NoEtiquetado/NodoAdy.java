package Estructuras_Grafos.TDA_DiGrafo_NoEtiquetado;

class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;

    //Constructor
    public NodoAdy(NodoVert unVertice, NodoAdy unSigAdy){
        this.vertice = unVertice;
        this.sigAdyacente = unSigAdy;
    }

    //Modificadores
    public void setVertice(NodoVert unVert){
        this.vertice = unVert;
    }

    public void setSigAdyacente(NodoAdy unAdy){
        this.sigAdyacente = unAdy;
    }

    //Observadores
    public NodoVert getVertice(){
        return this.vertice;
    }

    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }
}
