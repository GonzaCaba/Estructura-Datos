package Estructuras_Jerarquicas.TDA_Arbol_Binario.Dinamico;

import TDA_Lista.Dinamica.Lista;
import TDA_Cola.Dinamica.Cola;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin(){
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char pos){
        // Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo como hijo izquierdo o
        // derecho de la primer aparición de elemPadre, según lo indique el parámetro posicion. Para que la operación
        // termine con éxito debe existir un nodo en el árbol con elemento = elemPadre y ese nodo debe tener libre
        // su hijo posicion. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.
        boolean exito = false;
        //Caso Especial: el elemento a insertar, es el primero del Arbol
        if(this.esVacio()){
            this.raiz = new NodoArbol(elemNuevo, null, null);
            exito = true;
        } else{
            NodoArbol nodoPadre = this.obtenerNodo(this.raiz, elemPadre);
            if(nodoPadre != null){
                if(pos == 'I' && nodoPadre.getHijoIzq() == null){
                    nodoPadre.setHijoIzq(new NodoArbol(elemNuevo, null, null));
                    exito = true;
                } else if (pos == 'D' && nodoPadre.getHijoDer() == null){
                    nodoPadre.setHijoDer(new NodoArbol(elemNuevo, null, null));
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean esVacio(){
        return (this.raiz == null);
    }

    public int altura(){
        return this.alturaAux(this.raiz);
    }

    public int nivel(Object unElemento){
        return this.nivelAux(unElemento, this.raiz);
    }

    public Object padre(Object elemHijo){
        Object elementoPadre = null;
        if(this.raiz != null && !this.raiz.getElemento().equals(elemHijo)){
            NodoArbol nodoAux = this.obtenerNodoPadre(this.raiz, elemHijo);
            if(nodoAux != null){
                elementoPadre = nodoAux.getElemento();
            }
        }
        return elementoPadre;
    }

    public ArbolBin clone(){
        ArbolBin arbolClone = new ArbolBin();
        arbolClone.raiz = this.cloneAux(this.raiz);
        return arbolClone;
    }

    public void vaciar(){
        this.raiz = null;
    }

    public String toString(){
        String s = "";
        if(this.raiz != null){
            s = "En contruccion...";
        } else{
            s = "Arbol Vacío";
        }
        return s;
    }

    public Lista obtenerAncestros(Object unElemento){
        Lista listaAncestros = new Lista();
        this.obtenerAncestrosAux(this.raiz, listaAncestros, unElemento);
        return listaAncestros;
    }

    // De Lista
    public Lista listarPreorden(){
        Lista listaPreorden = new Lista();
        this.listarPreordenAux(this.raiz, listaPreorden);
        return listaPreorden;
    }

    public Lista listarInorden(){
        Lista listaPreorden = new Lista();
        this.listarInordenAux(this.raiz, listaPreorden);
        return listaPreorden;
    }

    public Lista listarPosorden(){
        Lista listaPosorden = new Lista();
        this.listarPosordenAux(this.raiz, listaPosorden);
        return listaPosorden;
    }

    public Lista listarPorNiveles(){
        Lista listaPorNiveles = new Lista();
        this.listarPorNivelesAux(this.raiz, listaPorNiveles);
        return listaPorNiveles;
    }

    public Lista frontera(){
        Lista listaFrontera = new Lista();
        this.fronteraAux(this.raiz, listaFrontera);
        return listaFrontera;
    }

    // Privados
    private NodoArbol cloneAux(NodoArbol unNodo){
        NodoArbol nodoClon = null;
        if(unNodo != null){
            nodoClon = new NodoArbol(unNodo.getElemento(), cloneAux(unNodo.getHijoIzq()), cloneAux(unNodo.getHijoDer()));
        }
        return nodoClon;
    }

    private boolean obtenerAncestrosAux(NodoArbol unNodo, Lista lis, Object unElem){
        boolean seEncontro = false;
        if(unNodo != null){
            //Si se encontro el elemento devuelve true
            if(unNodo.getElemento().equals(unElem)){
                seEncontro = true;
            } else{
                //Si no se encontró, añade el elemento a la lista y busca en los hijos
                lis.insertar(unNodo.getElemento(), lis.longitud()+1);
                seEncontro = this.obtenerAncestrosAux(unNodo.getHijoIzq(), lis, unElem);
                if(!seEncontro){
                    //Si aun no encuentra el elemento en el hijo izquierdo, busca en el hijo derecho
                    seEncontro = this.obtenerAncestrosAux(unNodo.getHijoDer(), lis, unElem);
                    if(!seEncontro){
                        //Y si no encuentra el elemento en el hijo derecho elimina el nodo padre de la lista y devuelve.
                        lis.eliminar(lis.longitud());
                    }
                }
            }
        }
        return seEncontro;
    }

    private void fronteraAux(NodoArbol unNodo, Lista unaLista){
        if(unNodo != null){
            //Si el nodo no tiene hijos, es una hoja por lo tanto va anotado en la lista
            if(unNodo.getHijoIzq() == null && unNodo.getHijoDer() == null){
                unaLista.insertar(unNodo.getElemento(), unaLista.longitud()+1);
            } else{
                if(unNodo.getHijoIzq() != null){
                    this.fronteraAux(unNodo.getHijoIzq(), unaLista);
                }
                if(unNodo.getHijoDer() != null){
                    this.fronteraAux(unNodo.getHijoDer(), unaLista);
                }
            }
        }
    }

    private void listarPreordenAux(NodoArbol unNodo, Lista lis){
        if(unNodo != null){
            //Visita el elemento en el nodo
            lis.insertar(unNodo.getElemento(), lis.longitud()+1);
            
            //recorre a sus hijos en preorden
            listarPreordenAux(unNodo.getHijoIzq(), lis);
            listarPreordenAux(unNodo.getHijoDer(),lis);
        }
    }

    private void listarInordenAux(NodoArbol unNodo, Lista lis){
        if(unNodo != null){
            //Recorre hijo izquierdo
            listarInordenAux(unNodo.getHijoIzq(), lis);
            //Visita el elemento en el nodo
            lis.insertar(unNodo.getElemento(), lis.longitud()+1);
            //Recorre hijo derecho
            listarInordenAux(unNodo.getHijoDer(), lis);
        }
    }

    private void listarPosordenAux(NodoArbol unNodo, Lista lis){
        if(unNodo != null){
            //Recorre hijo izquierdo
            listarPosordenAux(unNodo.getHijoIzq(), lis);
            //Recorre hijo derecho
            listarPosordenAux(unNodo.getHijoDer(), lis);
            //Visita el elemento en el nodo
            lis.insertar(unNodo.getElemento(), lis.longitud()+1);
        }
    }

    private void listarPorNivelesAux(NodoArbol unNodo, Lista lis){
        if(unNodo != null){
            NodoArbol nodoActual;
            Cola queue = new Cola();
            queue.poner(unNodo);
            while(!queue.esVacia()){
                nodoActual = (NodoArbol) queue.obtenerFrente();
                lis.insertar(nodoActual.getElemento(), lis.longitud()+1);
                queue.sacar();
                if(nodoActual.getHijoIzq() != null){
                    queue.poner(nodoActual.getHijoIzq());
                }
                if(nodoActual.getHijoDer() != null){
                    queue.poner(nodoActual.getHijoDer());
                }
            }
        }
    }

    private int nivelAux(Object unElemento, NodoArbol unNodo){
        int nivelActual = -1;
        if(unNodo != null){
            if(unNodo.getElemento().equals(unElemento)){
                nivelActual = 0;
            } else{
                NodoArbol hijoIzq = unNodo.getHijoIzq();
                NodoArbol hijoDer = unNodo.getHijoDer();
                int nivelEncontrado = -1;
                if(hijoIzq != null){
                    nivelEncontrado = nivelAux(unElemento, unNodo.getHijoIzq());
                }
                //Si ya se encontro el elemento no es necesario que siga buscando
                if(hijoDer != null && nivelEncontrado == -1){
                    nivelEncontrado = nivelAux(unElemento, unNodo.getHijoDer());
                }
                if(nivelEncontrado > -1){
                    nivelActual = nivelEncontrado + 1;
                }
            }
        }
        return nivelActual;
    }

    private int alturaAux(NodoArbol unNodo){
        int alturaArbol = -1;
        if (unNodo != null) {
            NodoArbol hijoIzq = unNodo.getHijoIzq();
            NodoArbol hijoDer = unNodo.getHijoDer();
            if(hijoIzq == null && hijoDer == null){
                //Es una hoja, la altura del arbol es 0
                alturaArbol = 0;
            } else{
                int altArbolIzq = -1;
                int altArbolDer = -1;
                if(hijoIzq != null){
                    altArbolIzq = alturaAux(unNodo.getHijoIzq());
                }
                if(hijoDer != null){
                    altArbolDer = alturaAux(unNodo.getHijoDer());
                }
                if(altArbolIzq >= altArbolDer){
                    alturaArbol = altArbolIzq + 1;
                } else{
                    alturaArbol = altArbolDer + 1;
                }
            }
        }
        return alturaArbol;
    }

    private NodoArbol obtenerNodo(NodoArbol unNodo, Object unElemento){
        //Metodo que se le pasa un elemento, y devuelve el nodo cuyo elemento coincida.
        //Si no encuentra el nodo, devuelve null.
        NodoArbol nodoBuscado = null;
        if(unNodo != null){
            if(unNodo.getElemento().equals(unElemento)){
                nodoBuscado = unNodo;
            } else{
                //Si no es el buscado, busca primero en el hijo izquierdo
                nodoBuscado = obtenerNodo(unNodo.getHijoIzq(), unElemento);
                if(nodoBuscado == null){
                    nodoBuscado = obtenerNodo(unNodo.getHijoDer(), unElemento);
                }
            }
        }
        return nodoBuscado;
    }

    private NodoArbol obtenerNodoPadre(NodoArbol unNodo, Object elemHijo){
        //Metodo que se le pasa un elemento, y devuelve el nodo padre,
        //del nodo que contiene el elemeto pasado por parametro.
        //Si no encuentra el elemento hijo o si es la raiz, devuelve null.
        NodoArbol nodoBuscado = null;
        if(unNodo != null){
            NodoArbol nodoHijoIzq = unNodo.getHijoIzq();
            NodoArbol nodoHijoDer = unNodo.getHijoDer();
            if(nodoHijoIzq != null || nodoHijoDer != null){
                if (nodoHijoIzq != null) {
                    if(nodoHijoIzq.getElemento().equals(elemHijo))
                        nodoBuscado = unNodo;
                }
                if (nodoHijoDer != null && nodoBuscado == null) {
                    if(nodoHijoDer.getElemento().equals(elemHijo))
                        nodoBuscado = unNodo;
                }
                if(nodoBuscado == null){
                    //Si no se encontró, busca primero en el hijo izquierdo y luego en el hijo derecho
                    if(nodoHijoIzq != null){
                        nodoBuscado = obtenerNodoPadre(unNodo.getHijoIzq(), elemHijo);
                    }
                    if(nodoHijoDer != null && nodoBuscado == null){
                        nodoBuscado = obtenerNodoPadre(unNodo.getHijoDer(), elemHijo);
                    }
                }
            }
        }
        return nodoBuscado;
    }
}
