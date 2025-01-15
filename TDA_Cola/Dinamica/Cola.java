package TDA_Cola.Dinamica;

public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola(){
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object unElemento){
        boolean exito = true;
        Nodo nodoNuevo = new Nodo(unElemento, null);
        //Caso 1: La cola esta vacia
        if(this.esVacia()){    
            this.fin = nodoNuevo;
            this.frente = nodoNuevo;
        } else{
            //Caso 2: La cola NO esta vacia
            //Enlazamos el ultimo nodo de la Cola con el nuevo nodo
            this.fin.setEnlace(nodoNuevo);
            //Redefinimos el final de la Cola.
            this.fin = nodoNuevo;
        }
        return exito;
    }

    public boolean sacar(){
        boolean exito;
        //Verificamos que la Cola NO este vacia
        if(this.esVacia())
            //ERROR: Cola vacia
            exito = false;
        else{
            //Caso 1: El elemento a sacar es el unico que queda en la Cola
            if(this.frente == this.fin){
                this.frente = null;
                this.fin = null;
            } else{
                //Caso 2: Hay mas elemento en la Cola
                this.frente = this.frente.getEnlace();
            }
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente(){
        //Precondicion: La Cola no debe estar vacia, caso contrario devuelve NULL
        Object aux = null;
        if (!this.esVacia()) {
            aux = this.frente.getElemento(); 
        }
        return aux;
    }

    public boolean esVacia(){
        boolean esVacia = false;
        if(this.frente == null && this.fin == null)
            esVacia = true;
        return esVacia;    
    }

    public void vaciar(){
        this.frente = null;
        this.fin = null;
    }

    public Cola clone(){
        Cola colaClone = new Cola();
        //Verificamos si no esta vacia
        if(!this.esVacia())
            colaClone.frente = cloneAux(this.frente, colaClone);    
        return colaClone;
    }

    private Nodo cloneAux(Nodo unNodo, Cola colaClone){
        Nodo nuevoNodo;
        if(unNodo.getEnlace() != null){
            nuevoNodo = new Nodo(unNodo.getElemento(),cloneAux(unNodo.getEnlace(), colaClone));
        }
        else{
            nuevoNodo = new Nodo(unNodo.getElemento(), null);
            colaClone.fin = nuevoNodo;
        }
        return nuevoNodo;
    }

    public String toString(){
        String s = "";
        if(this.esVacia())
            s = "Cola Vacia";
        else{
            Nodo auxRecorrido = this.frente;
            s =  "Frente --> [";
            s += auxRecorrido.getElemento().toString();
            auxRecorrido = auxRecorrido.getEnlace();
            while (auxRecorrido != null) {
                 s += "," + auxRecorrido.getElemento().toString(); 
                auxRecorrido = auxRecorrido.getEnlace();
            }
            s += "] <-- Fin";
        }
        return s;
    }
}
