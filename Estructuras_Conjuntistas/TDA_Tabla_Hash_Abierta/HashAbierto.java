package Estructuras_Conjuntistas.TDA_Tabla_Hash_Abierta;

import TDA_Lista.Dinamica.Lista;

public class HashAbierto {
    private final int TAMANIO;
    private Nodo[] tablaHash;
    private int cant;

    public HashAbierto(int tam){
        this.TAMANIO = tam;
        this.tablaHash = new Nodo[this.TAMANIO];
        this.cant = 0;
    }

    public boolean pertenece(Object elem){
        boolean pertenece = false;
        int pos = elem.hashCode() % this.TAMANIO;
        Nodo aux = this.tablaHash[pos];

        while (!pertenece && aux != null){
            pertenece = aux.getElemento().equals(elem);
            aux = aux.getEnlace();    
        }

        return pertenece;
    }

    public boolean insertar(Object nuevoElem){
        // Verifica si el elemento ya esta cargado, si no lo encuentra
        // lo pone delante del resto.
        boolean encontrado = false;
        int pos = nuevoElem.hashCode() % this.TAMANIO;
        Nodo aux = this.tablaHash[pos];
        
        while(!encontrado && aux  != null){
            encontrado = aux.getElemento().equals(nuevoElem);
            aux = aux.getEnlace();
        }

        if(!encontrado){
            this.tablaHash[pos] = new Nodo(nuevoElem, this.tablaHash[pos]);
            this.cant++;
        }

        return !encontrado;
    }

    public boolean eliminar(Object elemento){
        //Verifica si el elemento esta cargado, si lo encuentra
        //lo elimina de la lista.
        boolean encontrado = false;
        int pos = elemento.hashCode() % this.TAMANIO;
        Nodo anterior = this.tablaHash[pos];
        Nodo actual = this.tablaHash[pos];
        
        if(actual != null){
            encontrado = actual.getElemento().equals(elemento);
            while(!encontrado && actual != null){
                anterior = actual;
                actual = actual.getEnlace();
                encontrado = actual.getElemento().equals(elemento);
            }
        
            //Caso Especial: Es el primer nodo de la lista
            if(encontrado && actual.equals(anterior)){
                this.tablaHash[pos] = null;
                this.cant--;
            }  else if (encontrado){
                anterior.setEnlace(actual.getEnlace());
                this.cant--;
            }
        }

        return encontrado;
    }

    public boolean esVacia(){
        return (this.cant == 0);
    }

    public void vaciar(){
        for(int i=0; i<this.TAMANIO; i++){
            this.tablaHash[i] = null;
        }
        this.cant = 0;
    }

    public HashAbierto clone(){
        HashAbierto hashClone = new HashAbierto(this.TAMANIO);
        for(int i=0; i<this.TAMANIO; i++){
            hashClone.tablaHash[i] = cloneAux(this.tablaHash[i]);
        }
        return hashClone;
    }

    private Nodo cloneAux(Nodo unNodo){
        Nodo nodoClonado = null;
        if(unNodo != null){
            if(unNodo.getEnlace() != null){
                nodoClonado = new Nodo(unNodo.getElemento(),cloneAux(unNodo.getEnlace()));
            }
            else{
                nodoClonado = new Nodo(unNodo.getElemento(),null);
            }    
        }
        return nodoClonado;
    }

    public Lista listar(){
        Lista listaElementos = new Lista();
        for(int i=0; i<this.TAMANIO; i++){
            Nodo aux = this.tablaHash[i];
            while(aux != null){
                listaElementos.insertar(aux.getElemento(), listaElementos.longitud()+1);
                aux = aux.getEnlace();
            }
        }
        return listaElementos;
    }
}
