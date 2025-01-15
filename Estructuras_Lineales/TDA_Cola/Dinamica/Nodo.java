package TDA_Cola.Dinamica;

class Nodo {
    private Object elemento;
    private Nodo enlace;

    public Nodo(Object unElemento, Nodo unEnlace){
        this.elemento = unElemento;
        this.enlace = unEnlace;
    }

    public Nodo getEnlace(){
        return this.enlace;
    }

    public Object getElemento(){
        return this.elemento;
    }

    public void setEnlace(Nodo unEnlace){
        this.enlace = unEnlace;
    }

    public void setElemento(Object unElemento){
        this.elemento = unElemento;
    }
}
