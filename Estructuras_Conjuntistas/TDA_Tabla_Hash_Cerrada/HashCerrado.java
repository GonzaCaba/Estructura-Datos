package Estructuras_Conjuntistas.TDA_Tabla_Hash_Cerrada;

import TDA_Lista.Dinamica.Lista;

public class HashCerrado {
    private final int TAMANIO;
    private CeldaHash[] tablaHash;
    private int cant;
    private static final int VACIO = 0;
    private static final int OCUPADO = 1;
    private static final int BORRADO = -1;

    public HashCerrado(int tam){
        this.TAMANIO = tam;
        this.tablaHash = new CeldaHash[this.TAMANIO];
        this.cant = 0;
        for(int i = 0; i<this.TAMANIO;i++){
            this.tablaHash[i] = new CeldaHash(null, HashCerrado.VACIO);
        }
    }

    public boolean pertenece(Object elemento){
        //Primero calcula posicion inicial e incremento
        int pos = elemento.hashCode() % this.TAMANIO;
        int incremento = this.rehashing(elemento); 

        boolean pertenece = false;
        int intento = 1;

        // Busca el elemento hasta encontrarlo o encontrar una celda vacia
        // o para despues de TAM intentos
        while(!pertenece && intento < this.TAMANIO && this.tablaHash[pos].getEstado() != HashCerrado.VACIO){
            if(this.tablaHash[pos].getEstado() == HashCerrado.OCUPADO){
                pertenece = (this.tablaHash[pos].getElemento() == elemento);
            }
            pos = (pos+ intento*incremento) % this.TAMANIO;
            intento++;
        }
        
        return pertenece;
    }

    public boolean insertar(Object nuevoElem){
        //Primero calcula posicion inicial e incremento
        int pos = nuevoElem.hashCode() % this.TAMANIO;
        int incremento = this.rehashing(nuevoElem); 

        boolean seInserto = false;
        boolean encontrado = this.pertenece(nuevoElem);
        int intento = 1;

        while(!encontrado && !seInserto && intento < this.TAMANIO){
            if(this.tablaHash[pos].getEstado() != HashCerrado.OCUPADO){
                this.tablaHash[pos].setElemento(nuevoElem);
                this.tablaHash[pos].setEstado(HashCerrado.OCUPADO);
                seInserto = true;
                this.cant++;
            } 
            pos = (pos+ intento*incremento) % this.TAMANIO;
            intento++;
        }

        return seInserto;
    }

    public boolean eliminar(Object elemento){
        //Primero calcula posicion inicial e incremento
        int pos = elemento.hashCode() % this.TAMANIO;
        int incremento = this.rehashing(elemento); 

        boolean encontrado = false;
        int intento = 1;

        // Busca el elemento hasta encontrarlo o encontrar una celda vacia
        // o para despues de TAM intentos
        while(!encontrado && intento < this.TAMANIO && this.tablaHash[pos].getEstado() != HashCerrado.VACIO){
            if(this.tablaHash[pos].getEstado() == HashCerrado.OCUPADO){
                encontrado = (this.tablaHash[pos].getElemento() == elemento);
                if(encontrado){
                    //Si lo encuentra lo marca y para el ciclo
                    this.tablaHash[pos].setEstado(HashCerrado.BORRADO);
                    this.cant--;
                }
            }
            pos = (pos+ intento*incremento) % this.TAMANIO;
            intento++;
        }

        return encontrado;
    }

    public boolean esVacia(){
        return (this.cant == 0);
    }

    public void vaciar(){
        for(int i=0; i<this.TAMANIO; i++){
            this.tablaHash[i].setElemento(null);
            this.tablaHash[i].setEstado(HashCerrado.VACIO);
        }
        this.cant = 0;
    }

    public HashCerrado clone(){
        HashCerrado hashClone = new HashCerrado(this.TAMANIO);
        for(int i=0; i<this.TAMANIO; i++){
            hashClone.tablaHash[i].setElemento(this.tablaHash[i].getElemento());
            hashClone.tablaHash[i].setEstado(this.tablaHash[i].getEstado());
        }
        return hashClone;
    }

    public Lista listar(){
        Lista listaElementos = new Lista();
        for(int i=0; i<this.TAMANIO; i++){
            listaElementos.insertar(this.tablaHash[i].getElemento(), listaElementos.longitud()+1);
        }
        return listaElementos;
    }

    private int rehashing(Object unElemento){
        //Devuelve un incremento
        return ((int)unElemento%10);
    }
}
