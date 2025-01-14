package TDA_Pila.Dinamica;

class Nodo {
    //Variables
    private Object elemento;
    private Nodo enlace;

    //Constructor
    public Nodo(Object elem, Nodo enl){
        this.elemento = elem;
        this.enlace = enl;
    }

    //Modificadoras
    public void setElem(Object elem){
        this.elemento = elem;
    }

    public void setEnlace(Nodo enl){
        this.enlace = enl;
    }

    //Observadoras
    public Nodo getEnlace(){
        return this.enlace;
    }

    public Object getElem(){
        return this.elemento;
    }
}
