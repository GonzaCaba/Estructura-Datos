package Estructuras_Conjuntistas.TDA_Tabla_Hash_Abierta;

class Nodo {
    private Object elemento;
    private Nodo enlace;

    public Nodo(Object nuevoElemento, Nodo nuevoEnlace){
        this.elemento = nuevoElemento;
        this.enlace = nuevoEnlace;
    }

    public Object getElemento(){
        return this.elemento;
    }

    public Nodo getEnlace(){
        return this.enlace;
    }

    public void setElemento(Object unElemento){
        this.elemento = unElemento;
    }

    public void setEnlace(Nodo unEnlace){
        this.enlace = unEnlace;
    }
}
