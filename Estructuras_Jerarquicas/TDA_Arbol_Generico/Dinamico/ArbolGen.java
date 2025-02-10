package Estructuras_Jerarquicas.TDA_Arbol_Generico.Dinamico;

import TDA_Cola.Dinamica.Cola;
import TDA_Lista.Dinamica.Lista;

public class ArbolGen {
    private NodoArbolGen raiz;

    public ArbolGen(){
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre){
        boolean exito = true;
        //Considerar caso que el arbol este vacio, en cuyo caso inserta si o si independientemente de lo que se introdujo en el campo padre.
        if(this.raiz == null){
            this.raiz = new NodoArbolGen(elemNuevo, null, null);
        } else{
            //Busca si existe el nodo en el cual se desea insertar un nuevo hijo.
            NodoArbolGen nodoPadre = buscarNodo(this.raiz, elemPadre);
            if(nodoPadre != null){
                //Se añade el nodo nuevo en la primer posicion disponible, es decir a la derecha de todos sus hijos.
                NodoArbolGen nodoHijo = nodoPadre.getHEI();
                //Comprueba que el nodo padre tenga al menos un hijo...
                if(nodoHijo != null){
                    while(nodoHijo.getHD() != null){
                        nodoHijo = nodoHijo.getHD();
                    }
                    //Añadimos el nuevo nodo como hermano derecho del ultimo hijo.
                    nodoHijo.setHD(new NodoArbolGen(elemNuevo, null, null));
                } else{
                    //Si no tiene hijos, lo añade como el primer hijo extremo izquierdo.
                    nodoPadre.setHEI(new NodoArbolGen(elemNuevo, null, null));
                }
            } else{
                exito = false;
            }
        }
        return exito;
    }

    public boolean pertenece(Object unElemento){
        boolean exito = false;
        NodoArbolGen nodoBuscado = buscarNodo(this.raiz, unElemento);
        if(nodoBuscado != null)
            exito = true;
        return exito;
    }

    public Lista obtenerAncestros(Object unElemento){
        Lista listaAncestros = new Lista();
        this.obtenerAncestrosAux(this.raiz, listaAncestros, unElemento);
        return listaAncestros;
    }

    public boolean esVacio(){
        boolean esVacio = false;
        if(this.raiz == null){
            esVacio = true;
        }
        return esVacio;
    }

    public int altura(){
        int alturaArbol = this.alturaAux(this.raiz);
        return alturaArbol;
    }

    public int nivel(Object unElemento){
        int nivelNodo = this.nivelAux(unElemento, this.raiz);
        return nivelNodo;
    }

    public Object padre(Object unElem){
        //Se debe considerar en particular el caso de que el elemento solicitado sea la raiz
        Object elemPadre = null;
        NodoArbolGen nodoPadre = null;
        if(!this.esVacio() && !this.raiz.getElemento().equals(unElem)){
            nodoPadre = this.buscarNodoPadre(this.raiz, unElem);
            if(nodoPadre != null){
                elemPadre = nodoPadre.getElemento(); 
            }
        }
        return elemPadre;
    }

    public void vaciar(){
        this.raiz = null;
    }

    public int grado(){
        return this.gradoAux(this.raiz);
    }

    public int gradoSubarbol(Object unElemento){
        int grado = this.gradoSubarbolAux(this.buscarNodo(this.raiz, unElemento));
        return grado;
    }

    public Lista listarPreorden(){
        Lista listaPreorden = new Lista();
        this.listarPreordenAux(this.raiz, listaPreorden);
        return listaPreorden;
    }

    public Lista listarInorden(){
        Lista listaInorden = new Lista();
        this.listarInordenAux(this.raiz, listaInorden);
        return listaInorden;
    }

    public Lista listarPosorden(){
        Lista listaPosorden = new Lista();
        this.listarPosordenAux(this.raiz, listaPosorden);
        return listaPosorden;
    }

    public Lista listarPorNiveles(){
        Lista listaPorNiveles = new Lista();
        this.listarPorNiveles(this.raiz, listaPorNiveles);
        return listaPorNiveles;
    }

    public ArbolGen clone(){
        ArbolGen arbolClone = new ArbolGen();
        arbolClone.raiz = this.cloneAux(this.raiz);
        return arbolClone;
    }

    public String toString(){
        return this.toStringAux(this.raiz);
    }


    // Metodos Privados & Auxiliares 
    private NodoArbolGen buscarNodo(NodoArbolGen nodoRaiz, Object elem){
        NodoArbolGen nodoBuscado = null;
        if(nodoRaiz != null){
            //Comprueba si el elemento del nodo actual es el elemento buscado
            if (nodoRaiz.getElemento().equals(elem)) {
                //Si lo es, guarda el nodo este en una variable y lo devuelve enseguida
                nodoBuscado = nodoRaiz;
            } else{
                //Si no lo es, busca en sus nodos hijos.
                nodoBuscado = buscarNodo(nodoRaiz.getHEI(), elem);
                //Si no se encontro el elemento en los nodos hijos, entonces busca en sus hermanos
                if (nodoBuscado == null) {
                    NodoArbolGen nodoHermano = nodoRaiz.getHD();
                    if(nodoHermano != null){
                        nodoBuscado = buscarNodo(nodoHermano, elem);
                    }
                }
            }
        }
        return nodoBuscado;
    }

    private NodoArbolGen buscarNodoPadre(NodoArbolGen nodoRaiz, Object unElem){
        NodoArbolGen nodoBuscado = null;
        if(nodoRaiz != null){
            NodoArbolGen nodoHEI = nodoRaiz.getHEI();
            if(nodoHEI != null){
                //Comprueba si el elemento del nodo actual es el elemento buscado
                if(nodoHEI.getElemento().equals(unElem)){
                    //Si lo es, guarda el nodo este en una variable y lo devuelve enseguida
                    nodoBuscado = nodoRaiz;
                } else{
                    //Si no lo es, busca en sus nodos hijos.
                    nodoBuscado = buscarNodoPadre(nodoHEI, unElem);
                    //Si no se encontro el elemento en los nodos hijos, entonces busca en sus hermanos
                    if(nodoBuscado == null){
                        NodoArbolGen nodoHD = nodoHEI.getHD();
                        while(nodoHD != null && nodoBuscado == null){
                            if(nodoHD.getElemento().equals(unElem)){
                                nodoBuscado = nodoRaiz;
                            } else{
                                nodoBuscado = buscarNodoPadre(nodoHD, unElem);
                            }
                            nodoHD = nodoHD.getHD();
                        }
                    }
                }
            }
        }
        return nodoBuscado;
    }

    private boolean obtenerAncestrosAux(NodoArbolGen unNodo, Lista lis, Object unElem){
        boolean seEncontro = false;
        if(unNodo != null){
            //Si se encontro el elemento devuelve true y , mas importante, para la busqueda del mismo.
            if(unNodo.getElemento().equals(unElem)){
                seEncontro = true;
            } else{
                //Si no se encontró, añade el elemento a la lista y busca en los hijos
                lis.insertar(unNodo.getElemento(), lis.longitud()+1);
                seEncontro = this.obtenerAncestrosAux(unNodo.getHEI(), lis, unElem);
                if(!seEncontro){
                    //Si aun no encuentra el elemento en uno de sus hijos, busca en sus hermanos, eliminando de la lista a si mismo. 
                    lis.eliminar(lis.longitud());
                    seEncontro = this.obtenerAncestrosAux(unNodo.getHD(), lis, unElem);
                }
            }
        }
        return seEncontro;
    }

    private int alturaAux(NodoArbolGen nodoRaiz){
        int alturaArbol = -1;
        if(nodoRaiz != null){
            NodoArbolGen nodoHijo = nodoRaiz.getHEI();
            if(nodoHijo == null){
                //Significa que se llegó a una hoja, por lo tanto el nivel del subarbol es 0.
                alturaArbol = 0;
            } else{
                //Primero calcula el nivel del subarebol que conforma el nodo primer hijo extremo izquierdo.
                int altArbolHEI = alturaAux(nodoHijo.getHEI());
                int altArbolHD = -1;
                int maxAltura = -1; //Esta variable almacena la altura maxima entre los subarboles nodos hermanos del primer hijo extremo izquierdo.

                if(nodoHijo.getHD() != null){
                    NodoArbolGen nodoHD = nodoHijo.getHD(); //Esta variable almacena el nodo hermano actual con el cual se esta comparando la altura del primer nodo extremo izq.
                    maxAltura = alturaAux(nodoHD);  
                    while(nodoHD.getHD() != null){
                        //Mientras que el hermano derecho tenga mas hermanos, ergo haya mas hijos, entonces se comparara con todos los hijos del subarbol.
                        altArbolHD = alturaAux(nodoHD.getHD());     
                        //Si la altura del subarbol hermano derecho es mayor al hermano izquierdo anteriormente calculado, se remplaza.
                        if(altArbolHD > maxAltura)
                            maxAltura = altArbolHD;
                        //Se avanza al siguiente hermano.
                        nodoHD = nodoHD.getHD();
                    }
                }
                //Una vez que se termina de recorrer y comparar cual es la mayor altura de todos los hermanos, se compara la altura del primer hijo
                //con la mayor altura encontrada en los demas hijos.
                if(altArbolHEI >= maxAltura)
                    alturaArbol = altArbolHEI + 1;
                else
                    alturaArbol = maxAltura + 1;
            }
        }
        return alturaArbol;
    }
    
    private int nivelAux(Object unElemento, NodoArbolGen nodoRaiz){
        int nivelActual = -1;
        if(nodoRaiz != null){
            if(nodoRaiz.getElemento().equals(unElemento)){
                nivelActual = 0;
            } else{
                NodoArbolGen nodoHijo = nodoRaiz.getHEI();
                int nivelEncontrado = nivelAux(unElemento, nodoHijo);
                //Si ya se encontro el elemento no es necesario que siga buscando
                if(nivelEncontrado == -1){
                    nivelActual = nivelAux(unElemento, nodoRaiz.getHD());
                } else{
                    nivelActual = nivelEncontrado + 1;
                }
            }
        }
        return nivelActual;
    }

    private void listarPreordenAux(NodoArbolGen nodoRaiz, Lista lis){
        if(nodoRaiz != null){
            //Se lista la raiz primero
            lis.insertar(nodoRaiz.getElemento(), lis.longitud()+1);
            //Luego se recorren los hijos
            this.listarPreordenAux(nodoRaiz.getHEI(),lis);
            //Finalmente tras recorrer los hijos recorre sus hermanos.    
            this.listarPreordenAux(nodoRaiz.getHD(), lis);
        }
    }

    private void listarInordenAux(NodoArbolGen nodoRaiz, Lista lis){
        if(nodoRaiz != null){
            //Primero se visita el hijo extremos izquierdo
            if(nodoRaiz.getHEI() != null){
                this.listarInordenAux(nodoRaiz.getHEI(), lis);
            }
            //Se lista la raiz
            lis.insertar(nodoRaiz.getElemento(), lis.longitud()+1);
            //Finalmente se visitan los demas hijos
            if(nodoRaiz.getHEI() != null){
                NodoArbolGen nodoHermano = nodoRaiz.getHEI().getHD();
                while(nodoHermano != null){
                    this.listarInordenAux(nodoHermano, lis);
                    nodoHermano = nodoHermano.getHD();
                }
            }
        }
    }

    private void listarPosordenAux(NodoArbolGen nodoRaiz, Lista lis){
        if(nodoRaiz != null){
            //Primero se visitan los hijos
            if(nodoRaiz.getHEI() != null){
                this.listarPosordenAux(nodoRaiz.getHEI(), lis);
                NodoArbolGen nodoHermano = nodoRaiz.getHEI().getHD();
                while(nodoHermano != null){
                    this.listarPosordenAux(nodoHermano, lis);
                    nodoHermano = nodoHermano.getHD();
                }
            }
            //Luego se imprime la raiz
            lis.insertar(nodoRaiz.getElemento(), lis.longitud()+1);
        }
    }

    private void listarPorNiveles(NodoArbolGen nodoRaiz, Lista lis){
        if(nodoRaiz != null){
            NodoArbolGen nodoActual;
            NodoArbolGen nodoHijo;
            NodoArbolGen nodoHermano;
            Cola queue = new Cola();
            queue.poner(nodoRaiz);
            while(!queue.esVacia()){
                nodoActual = (NodoArbolGen) queue.obtenerFrente();
                lis.insertar(nodoActual.getElemento(), lis.longitud()+1);
                queue.sacar();
                if(nodoActual.getHEI() != null){
                    queue.poner(nodoActual.getHEI());
                    nodoHermano = nodoActual.getHEI().getHD();
                    while(nodoHermano != null){
                        queue.poner(nodoHermano);
                        nodoHermano = nodoHermano.getHD();
                    }
                }
            }
        }
    }

    private NodoArbolGen cloneAux(NodoArbolGen nodoRaiz){
        NodoArbolGen nodoRaizClon = null;
        if(nodoRaiz != null){
            nodoRaizClon = new NodoArbolGen(nodoRaiz.getElemento(), cloneAux(nodoRaiz.getHEI()), cloneAux(nodoRaiz.getHD()));
        }
        return nodoRaizClon;
    }

    private String toStringAux(NodoArbolGen unNodo){
        String s = "";
        if(unNodo != null){
            s += unNodo.getElemento().toString() + " -> ";
            NodoArbolGen hijo = unNodo.getHEI();
            while(hijo != null){
                s += hijo.getElemento().toString() + ", ";
                hijo = hijo.getHD();
            }

            // comienza recorrido de los hijos del nodo llamando recursiamente
            // para que cada hijo agregue su subcadena a la general
            hijo = unNodo.getHEI();
            while(hijo != null){
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHD();
            }
        }
        return s;
    }

    private int gradoSubarbolAux(NodoArbolGen nodoRaiz){
        int cantHijos = -1;
        if(nodoRaiz != null){
            cantHijos = 0;
            if(nodoRaiz.getHEI() != null){
                cantHijos = cantHijos + 1;
                NodoArbolGen nodoHermano = nodoRaiz.getHEI().getHD();
                while(nodoHermano != null){
                    cantHijos = cantHijos + 1;
                    nodoHermano = nodoHermano.getHD();
                }
            }
        }
        return cantHijos;
    }

    private int gradoAux(NodoArbolGen nodoRaiz){
        int gradoRaiz;
        int gradoHijoMayor;
        int gradoHijoSiguiente;
        int mayorGrado = -1;
        if(nodoRaiz != null){
            gradoRaiz = this.gradoSubarbolAux(nodoRaiz);
            if(nodoRaiz.getHEI() != null){
                //Primero obtiene el grado del hijo extremo izquierdo.
                gradoHijoMayor = this.gradoAux(nodoRaiz.getHEI());
                NodoArbolGen nodoHermano = nodoRaiz.getHEI().getHD();
                while(nodoHermano != null){
                    //Luego va obteniendo uno por uno el grado de los demas hijos y los compara con quien tiene el grado mas alto.
                    gradoHijoSiguiente = this.gradoAux(nodoHermano);
                    if(gradoHijoSiguiente > gradoHijoMayor){
                        gradoHijoMayor = gradoHijoSiguiente;
                    }
                    nodoHermano = nodoHermano.getHD();
                }
                if(gradoRaiz >= gradoHijoMayor){
                    mayorGrado = gradoRaiz;
                } else{
                    mayorGrado = gradoHijoMayor;
                }
            } else
                mayorGrado = gradoRaiz;
        }
        return mayorGrado;
    }
}
