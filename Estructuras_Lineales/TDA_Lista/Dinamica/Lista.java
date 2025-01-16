package TDA_Lista.Dinamica;

public class Lista {
    private Nodo cabecera;
    private int longLista;

    public Lista(){
        this.cabecera = null;
        this.longLista = 0;
    }

    public boolean insertar(Object unElemento, int unaPosicion){
        // Agrega el elemento pasado por parámetro en la posición pos, de manera que la cantidad de elementos
        // de la lista se incrementa en 1. Para una inserción exitosa, la posición recibida debe ser 1 ≤ pos ≤
        // longitud(lista) + 1. Devuelve verdadero si se puede insertar correctamente y falso en caso contrario. 
        boolean exito = esPosValidaInsertar(unaPosicion);
        if(exito){
            //Caso 1: Se quiere insertar el nuevo elemento en la pos 1.
            if(unaPosicion == 1){
                this.cabecera = new Nodo(unElemento, this.cabecera);
            } else{
                Nodo nodoRecorrido = this.cabecera;
                int numNodo = 1;
                while((numNodo + 1) != unaPosicion){
                    nodoRecorrido = nodoRecorrido.getEnlace();
                    numNodo++;
                }
                //Setea en enlace del nodo anterior al nodo nuevo, con el nodo nuevo. 
                //Ademas setea el enlace del nodo nuevo con el enlace anterior que tenia, valga la redundancia, el nodo anterior.
                nodoRecorrido.setEnlace(new Nodo(unElemento, nodoRecorrido.getEnlace()));;
            }
            //Aumentamos la longitud de la lista en uno.
            this.longLista++;
        }
        return exito;
    }

    public boolean eliminar(int unaPosicion){
        // Borra el elemento de la posición pos, por lo que la cantidad de elementos de la lista disminuye
        // en uno. Para una eliminación exitosa, la lista no debe estar vacía y la posición recibida debe ser
        // 1 ≤ pos ≤ longitud(lista). Devuelve verdadero si se pudo eliminar correctamente y falso en caso contrario.
        boolean exito = esPosValidaRangoList(unaPosicion);
        if(exito){
            if(unaPosicion==1){
                this.cabecera = this.cabecera.getEnlace();
            }
            else{
                Nodo nodoRecorrido = this.cabecera;
                int numNodo = 1;
                while((numNodo + 1) != unaPosicion){
                    nodoRecorrido = nodoRecorrido.getEnlace();
                    numNodo++;
                }
                //Setea en enlace del nodo anterior al nodo siguiente del nodo que se quiere eliminar.
                nodoRecorrido.setEnlace((nodoRecorrido.getEnlace()).getEnlace());;  
            }
            //Disminuimos la longitud de la lista en uno.
            this.longLista--;
        }
        return exito;
    }

    public Object recuperar(int unaPosicion){
        // Devuelve el elemento de la posición pos. La precondición es que la posición sea válida.
        Object elemento = null;
        //Precondicion: La posicion debe ser valida
        boolean exito = esPosValidaRangoList(unaPosicion);
        if(exito){
            Nodo auxRecorrido = this.cabecera;
            int numNodo = 1;
            while(numNodo != unaPosicion){
                auxRecorrido = auxRecorrido.getEnlace();
                numNodo++;
            }
            elemento = auxRecorrido.getElemento();
        }
        return elemento;
    }

    public int localizar(Object unElemento){
        // Devuelve la posición en la que se encuentra la primera ocurrencia de elem dentro de la lista. 
        // En caso de no encontrarlo devuelve -1.
        int posElemento = -1;
        //Precondicion: La lista no debe estar vacia
        if(!this.esVacia()){
            int numNodo = 1;
            boolean flagEncontro = false;
            Nodo auxRecorrido = this.cabecera;
            while(auxRecorrido != null && !flagEncontro){
                if (auxRecorrido.getElemento() == unElemento) {
                    flagEncontro = true;
                    posElemento = numNodo;
                } else{
                    //Avanza hacia el siguiente nodo
                    auxRecorrido = auxRecorrido.getEnlace();
                    numNodo++;
                }
            }
        }
        return posElemento;
    }

    public void vaciar(){
        // Quita todos los elementos de la lista.
        this.longLista = 0;
        this.cabecera = null;
    }

    public boolean esVacia(){
        // Devuelve verdadero si la lista no tiene elementos y falso en caso contrario.
        return !(this.longitud() > 0);
    }

    public Lista clone(){
        // Devuelve una copia exacta de los datos en la estructura original, y respetando el orden de los mismos,
        // en otra estructura del mismo tipo
        Lista listaClone = new Lista();
        listaClone.cabecera = cloneAux(this.cabecera);
        listaClone.longLista = this.longLista;
        return listaClone;
    }

    public int longitud(){
        // Devuelve la cantidad de elementos de la lista
        return this.longLista;
    }

    public String toString(){
        // Crea y devuelve una cadena de caracteres formada por todos los elementos de la lista para poder
        // mostrarla por pantalla
        String s = "";
        if(this.esVacia()){
            s = "Lista vacia";
        } else{
            s = "\n--- Lista ---\n";
            Nodo auxRecorrido = this.cabecera;
            int numNodo = 1;
            while(auxRecorrido != null){
                s += "  " + numNodo + ".   " + auxRecorrido.getElemento().toString() + "\n";
                auxRecorrido = auxRecorrido.getEnlace();
                numNodo++;
            }
            s += "--- Fin Lista ---";
        }
        return s;
    }

    private Nodo cloneAux(Nodo unNodo){
        Nodo nodoClonado;
        if(unNodo.getEnlace() != null){
            nodoClonado = new Nodo(unNodo.getElemento(),cloneAux(unNodo.getEnlace()));
        }
        else{
            nodoClonado = new Nodo(unNodo.getElemento(),null);
        }
        return nodoClonado;
    }

    private boolean esPosValidaRangoList(int unaPosicion){
        boolean esValida = true;
        if(unaPosicion < 1 || unaPosicion > this.longitud()){
            //ERROR: posicion fuera de rango
            esValida = false;
        }
        return esValida;
    }

    private boolean esPosValidaInsertar(int unaPosicion){
        boolean esValida = true;
        if(unaPosicion < 1 || unaPosicion > this.longitud()+1){
            //ERROR: posicion fuera de rango
            esValida = false;
        }
        return esValida;
    }
}
