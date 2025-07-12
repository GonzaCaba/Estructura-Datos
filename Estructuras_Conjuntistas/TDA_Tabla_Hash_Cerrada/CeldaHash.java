package Estructuras_Conjuntistas.TDA_Tabla_Hash_Cerrada;

class CeldaHash {
    private Object elem;
    private int estado;

    //Constructor
    public CeldaHash(Object unElemento, int unEstado){
        this.elem = unElemento;
        this.estado = unEstado;
    }

    //Observadores
    public Object getElemento(){
        return this.elem;
    }

    public int getEstado(){
        return this.estado;
    }

    //Modificadores
    public void setElemento(Object unElemento){
        this.elem = unElemento;
    }

    public void setEstado(int unEstado){
        this.estado = unEstado;
    }

}
