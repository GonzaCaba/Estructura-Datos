package TDA_Cola.Estatica;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola(){
        this.arreglo = new Object[Cola.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object unElemento){
        boolean exito;
        //Si el puntero final en la siguiente posicion apunta a el puntero frente, entonces significa que la cola esta llena
        if((this.fin + 1)%this.TAMANIO == this.frente){
            //ERROR: cola llena
            exito = false;
        }
        else{
            this.arreglo[this.fin] = unElemento;
            this.fin = (this.fin + 1) % Cola.TAMANIO;
            exito = true;
        }
        return exito;
    }

    public boolean sacar(){
        boolean exito;
        //Verificamos cola vacia
        if (this.frente == this.fin) {
            //ERROR: cola vacia
            exito = false;
        } else{
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % Cola.TAMANIO;
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente(){
        //Precondicion: la cola no esta vacia
        Object aux = null;
        if(this.frente != this.fin){
            aux = this.arreglo[this.frente];
        }
        return aux; 
    }

    public boolean esVacia(){
        boolean exito = false;
        if(this.frente == this.fin)
            exito = true;
        return exito;
    }

    public void vaciar(){
        while(this.frente != this.fin){
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % Cola.TAMANIO;
        }
    }

    public Cola clone(){
        Cola colaClone = new Cola();
        for(int var = 0; var<Cola.TAMANIO; var++){
            colaClone.arreglo[var] = this.arreglo[var];
        } 
        colaClone.fin = this.fin;
        colaClone.frente = this.frente;
        return colaClone;
    }

    public String toString(){
        String s = "";
        if(this.esVacia())
            s = "Cola Vacia";
        else{
            s =  "Frente --> [";
            int auxFrente = this.frente;
            s += this.arreglo[auxFrente];
            auxFrente =  (auxFrente+1) % Cola.TAMANIO;
            while (auxFrente != this.fin) {
                s += "," + this.arreglo[auxFrente].toString(); 
                auxFrente =  (auxFrente+1) % Cola.TAMANIO;
            }
            s += "] <-- Fin";
        }
        return s;
    }



}
