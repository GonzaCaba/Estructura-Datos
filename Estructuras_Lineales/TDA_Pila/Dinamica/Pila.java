package TDA_Pila.Dinamica;

public class Pila {
    private Nodo tope;

    public Pila(){
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem){
        //Crea un nuevo nodo con el elemento pasado como parametro y lo enlaza con el tope, de esta manera
        //el nodo la que estaba apuntando tope queda por debajo del nodo nuevo.
        Nodo nodoAux = new Nodo(nuevoElem,this.tope);
        //Remplaza el tope actual con el nodo nuevo, quedando como tope de pila el nodo nuevo.
        this.tope = nodoAux;
        //Nunca hay error de pila llena, asi que devuelve true.
        return true;
    }

    public boolean desapilar(){
        boolean exito;
        //Si el nodo al que apunta el tope es nulo, significa que la pila esta vacia. Devuelve false.
        if(this.tope==null){
            exito = false;
        }
        else{
            //El tope, de apuntar al primer elemento en la pila (el de mas arriba), pasa a puntar al segundo elemento (el que esta abajo del primero)
            //Como no quedan referencias al primer nodo, este es borrado por el garbage collector.
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elemDevuelto;
        //Si la pila esta vacia, devuelve null
        if(this.tope == null){
            elemDevuelto = null;
        }
        else{
            elemDevuelto = this.tope.getElem();
        }
        return elemDevuelto;
    }

    public boolean esVacia(){
        boolean exito = false;
        if(this.tope == null){
            exito = true;
        }
        return exito;
    }

    public void vaciar(){
        //Al quitar referencia del primer nodo de la pila, automaticamente se pierden las referencias
        //de todos los demas nodos (por el Garbage Collector). 
        this.tope = null;
    }

    public Pila clone(){
        Pila pilaClonada = new Pila();
        pilaClonada.tope = cloneAux(this.tope);
        return pilaClonada;
    }

    public String toString(){
        String s = "";
        if(this.tope == null)
            s = "Pila vacia";
        else {
            s = "[";
            s += toStringAux("", this.tope);
            s += "] <--- Tope";
        }
        return s;
    }

    private String toStringAux(String cadena, Nodo nodoActual){
        if(nodoActual.getEnlace()!=null){
            cadena += toStringAux(cadena,nodoActual.getEnlace()) + "," + nodoActual.getElem().toString(); 
        }
        else{
            cadena += nodoActual.getElem().toString();
        }
        return cadena;
    }

    private Nodo cloneAux(Nodo unNodo){
        Nodo nodoClonado;
        if(unNodo.getEnlace() != null){
            nodoClonado = new Nodo(unNodo.getElem(),cloneAux(unNodo.getEnlace()));
        }
        else{
            nodoClonado = new Nodo(unNodo.getElem(),null);
        }
        return nodoClonado;
    }
}
