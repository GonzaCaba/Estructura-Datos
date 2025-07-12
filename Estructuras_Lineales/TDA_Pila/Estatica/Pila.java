package TDA_Pila.Estatica;

public class Pila {
    private static final int TAMANIO = 10;
    private Object[] arreglo;
    private int tope;

    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElemento) {
        boolean exito;
        if (this.tope+1 >= Pila.TAMANIO){
            //ERROR: pila llena
            exito = false;
        }
        else{
            //pone el elemento en el tope de la pila e incrementa tope
            this.tope++;
            arreglo[this.tope] = nuevoElemento;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar(){
        boolean exito;
        if (this.tope > -1){
            //si la pila esta vacia, retrocede el tope y elimina el elemento en el mismo
            this.arreglo[this.tope] = null;
            this.tope--;
            exito = true;
        }
        else{
            //ERROR: la pila esta vacia
            exito = false;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elem = null;
        //Precondicion: la pila no esta vacia
        if(this.tope > -1){
            elem = this.arreglo[this.tope];
        }
        return elem;
    }

    public boolean esVacia(){
        //Si la pila esta vacia devuelve true
        return (this.tope == -1);
    }

    public void vaciar(){
        for(int i = this.tope; i>=0; i--){
            this.arreglo[i] = null;
        }
    }

    public Pila clone(){
        //Creamos una pila nueva
        Pila pilaClon = new Pila();
        //Recorremos toda la pila original asignando cada elemento de la pila original en la posicion correspondiente a la clon
        for(int i = 0; i<Pila.TAMANIO;i++){
            pilaClon.arreglo[i] = this.arreglo[i];
        }
        pilaClon.tope = this.tope;
        return pilaClon;
    }

    public String toString(){
        String cadena = "\nInicio de Pila ↓ \n";
        for(int i = 0; i<Pila.TAMANIO; i++){
            cadena = cadena + this.arreglo[i] + "\n";
        }
        cadena = cadena + "Final de Pila ↑";
        return cadena;
    }
    
}
