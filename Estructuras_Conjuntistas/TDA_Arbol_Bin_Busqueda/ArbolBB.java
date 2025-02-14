package Estructuras_Conjuntistas.TDA_Arbol_Bin_Busqueda;
/**
 *
 * @author Cabanne, Gonzalo Heber
 */
import TDA_Lista.Dinamica.Lista;

public class ArbolBB {
    private NodoABB raiz;

    public ArbolBB(){
        this.raiz = null;
    }

    public boolean insertar(Comparable unElem){
        boolean exito = false;
        //Caso unico, el arbol esta vacio.
        if(this.raiz == null){
            this.raiz = new NodoABB(unElem, null, null);
        } else{
            exito = this.insertarAux(this.raiz, unElem);
        }
        return exito;
    }

    public boolean eliminar(Comparable unElem){
        boolean exito = false;
        if(this.raiz != null){
            if((this.raiz.getElemento().compareTo(unElem))==0){
                //Consideramos caso especial para raiz.
                boolean tieneHijoIzq = (this.raiz.getHijoIzq()!=null);
                boolean tieneHijoDer = (this.raiz.getHijoDer()!=null);
                //Caso 1: El nodo es una hoja.
                if(!tieneHijoDer && !tieneHijoIzq){
                    this.raiz = null;
                    exito = true;
                } else if(tieneHijoIzq && tieneHijoDer){
                    //Caso 3: El nodo tiene dos hijos.
                    exito = this.eliminarCaso3(this.raiz);
                } else{
                    //Caso 2: El nodo tiene un solo hijo.
                    if(tieneHijoDer)
                        this.raiz = this.raiz.getHijoDer();
                    else
                        this.raiz = this.raiz.getHijoIzq();
                }
            } else{
                exito = this.eliminarAux(this.raiz, null, unElem);
            }
        }
        return exito;
    }

    public boolean pertenece(Comparable unElem){
        boolean exito = false;
        exito = this.perteneceAux(this.raiz, unElem);
        return exito;
    }

    public boolean esVacio(){
        return (this.raiz == null);
    }

    public ArbolBB clone(){
        ArbolBB arbolClone = new ArbolBB();
        arbolClone.raiz = this.cloneAux(this.raiz);
        return arbolClone;
    }

    public Lista listar(){
        //Devuelve una lista ordenada del arbol que , por la naturaleza
        //del funcionamiento del arbol de busqueda, sera una lista in orden. 
        Lista lis = new Lista();
        this.listarInordenAux(this.raiz, lis);
        return lis;
    }

    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo){
        Lista lis = new Lista();
        if(elemMaximo != null && elemMinimo != null){
            this.listarInOrdenRangoAux(this.raiz, lis, elemMinimo, elemMaximo);
        }
        return lis;
    }

    public Comparable minimoElem(){
        Comparable elementoMinimo = null;
        if(this.raiz != null){
            if(this.raiz.getHijoIzq() != null){
                NodoABB nodoMinimo = this.raiz.getHijoIzq();
                while(nodoMinimo.getHijoIzq() != null){
                    nodoMinimo = nodoMinimo.getHijoIzq();
                }
                elementoMinimo = nodoMinimo.getElemento();
            } else{
                elementoMinimo = this.raiz.getElemento();
            }
        }
        return elementoMinimo;
    }

    public Comparable maximoElem(){
        Comparable elementoMaximo = null;
        if(this.raiz != null){
            if(this.raiz.getHijoDer() != null){
                NodoABB nodoMaximo = this.raiz.getHijoDer();
                while(nodoMaximo.getHijoDer() != null){
                    nodoMaximo = nodoMaximo.getHijoDer();
                }
                elementoMaximo = nodoMaximo.getElemento();
            } else{
                elementoMaximo = this.raiz.getElemento();
            }
        }
        return elementoMaximo;
    }

    public void vaciar(){
        this.raiz = null;
    }

    // Metodos Auxiliares && Privados
    private boolean perteneceAux(NodoABB unNodo, Comparable unElemento){
        boolean seEncontro = false;
        if(unNodo!=null){
            if(unElemento.compareTo(unNodo.getElemento())==0){
                seEncontro = true;
            } else if(unElemento.compareTo(unNodo.getElemento())>0){
                seEncontro = this.perteneceAux(unNodo.getHijoDer(),unElemento);
            } else{
                seEncontro = this.perteneceAux(unNodo.getHijoIzq(), unElemento);
            }
        }
        return seEncontro;
    }

    private boolean insertarAux(NodoABB unNodo, Comparable unElemento){
        //Precondicion: unNodo es nulo
        boolean exito = true;
        if((unElemento.compareTo(unNodo.getElemento()))==0){
            // Error: el elemento esta repetido
            exito = false;
        } else if((unElemento.compareTo(unNodo.getElemento()))>0){
            // Si el elemento es mayor que la raiz, entonces buscamos en el hijo derecho
            if(unNodo.getHijoDer()!=null){
                exito = insertarAux(unNodo.getHijoDer(), unElemento);
            } else
                unNodo.setHijoDer(new NodoABB(unElemento, null, null));
        } else{
            // Si el elemento es menor que la raiz, entonces buscamos en el hijo izquierdo
            if(unNodo.getHijoIzq()!=null){
                exito = insertarAux(unNodo.getHijoIzq(), unElemento);
            } else
                unNodo.setHijoIzq(new NodoABB(unElemento, null, null));
        }
        return exito;
    }

    private boolean eliminarAux(NodoABB unNodo, NodoABB nodoPadre, Comparable unElemento){
        //Precondicion: unNodo no es nulo ni es la raiz
        boolean exito = false;
        NodoABB nodoHijo;
        if(unNodo!=null){
            if ((unElemento.compareTo(unNodo.getElemento()))==0) {
                //Una vez encontrado, se aplican los casos
                boolean tieneHijoIzq = (unNodo.getHijoIzq()!=null);
                boolean tieneHijoDer = (unNodo.getHijoDer()!=null);
                //Caso 1: El nodo es una hoja.
                if(!tieneHijoDer && !tieneHijoIzq){
                    exito = this.eliminarCaso1(nodoPadre, unNodo);
                } else if(tieneHijoIzq && tieneHijoDer){
                    //Caso 3: El nodo tiene dos hijos.
                    exito = this.eliminarCaso3(unNodo);
                } else{
                    //Caso 2: El nodo tiene un solo hijo.
                    exito = this.eliminarCaso2(nodoPadre, unNodo);
                }
            } else if(unElemento.compareTo(unNodo.getElemento())>0){
                // Si el elemento es mayor a la raiz, busca en el hijo derecho
                exito = eliminarAux(unNodo.getHijoDer(), unNodo, unElemento);
            } else{
                // Si el elemento es menor a la raiz, busca en el hijo izquierdo
                exito = eliminarAux(unNodo.getHijoIzq(), unNodo, unElemento);
            }
        }
        return exito;
    }

    private NodoABB cloneAux(NodoABB unNodo){
        NodoABB nodoClon = null;
        if(unNodo != null){
            nodoClon = new NodoABB(unNodo.getElemento(), cloneAux(unNodo.getHijoIzq()), cloneAux(unNodo.getHijoDer()));
        }
        return nodoClon;
    }

    private boolean eliminarCaso1(NodoABB nodoPadre, NodoABB nodoRaiz){
        //Caso 1: Se desconecta desde el nodo padre al nodo hijo.
        boolean exito = false;
        if(nodoPadre != null && nodoRaiz != null){
            if(nodoPadre.getHijoIzq() == nodoRaiz){
                nodoPadre.setHijoIzq(null);
                exito = true;
            } else if(nodoPadre.getHijoDer() == nodoRaiz){
                nodoPadre.setHijoDer(null);
                exito = true;
            }
        }
        return exito;
    }

    private boolean eliminarCaso2(NodoABB nodoPadre, NodoABB nodoRaiz){
        //Caso 2: Se desconecta desde el nodo padre al nodo hijo y se lo conecta
        //con el nodo hijo del, valga la redundancia, nodo hijo.
        boolean exito = false;
        if(nodoPadre != null && nodoRaiz != null){
            if(nodoPadre.getHijoIzq() == nodoRaiz){
                if(nodoRaiz.getHijoIzq() != null){
                    nodoPadre.setHijoIzq(nodoRaiz.getHijoIzq());
                    exito = true;
                } else {
                    nodoPadre.setHijoIzq(nodoRaiz.getHijoDer());
                    exito = true;
                }
            } else if (nodoPadre.getHijoDer() == nodoRaiz){
                if(nodoRaiz.getHijoIzq() != null){
                    nodoPadre.setHijoDer(nodoRaiz.getHijoIzq());
                    exito = true;
                } else{
                    nodoPadre.setHijoDer(nodoRaiz.getHijoDer());
                    exito = true;
                }
            }
        }
        return exito;
    }

    private boolean eliminarCaso3(NodoABB nodoRaiz){
        //Caso 3: Primero se busca el Candidato A, es decir el nodo mas a la derecha
        //bajando por la rama izquierda.
        boolean exito = false;
        if(nodoRaiz != null){
            NodoABB nodoCandidato = nodoRaiz.getHijoIzq();
            NodoABB nodoPadreCandidato = nodoRaiz;
            //No es necesario preguntar si es nodoCandidato es nulo, debido a que
            //si esta en el caso 3 es porque tiene dos hijos si o si.
            while(nodoCandidato.getHijoDer()!=null){
                nodoPadreCandidato = nodoCandidato;
                nodoCandidato = nodoCandidato.getHijoDer();
            }
            //Ahora que se tiene el nodoCandidato lo desconectaremos del arbol.
            boolean seElimino;
            if(nodoCandidato.getHijoIzq() != null){
                //Si tiene un hijo, se aplica caso 2.
                seElimino = this.eliminarCaso2(nodoPadreCandidato, nodoCandidato);
            } else{
                //Si no tiene hijo izq, no tiene hijos por lo tanto es una hoja.
                seElimino = this.eliminarCaso1(nodoPadreCandidato, nodoCandidato);
            }
            //Finalmente, una vez eliminado el nodo remplazamos el valor del nodo
            //por el del nodoCandidato
            if(seElimino){
                nodoRaiz.setElemento(nodoCandidato.getElemento());
                exito = true;
            }
        }
        return exito;
    }

    private void listarInordenAux(NodoABB unNodo, Lista lis){
        if(unNodo != null){
            //Recorre hijo izquierdo
            listarInordenAux(unNodo.getHijoIzq(), lis);
            //Visita el elemento en el nodo
            lis.insertar(unNodo.getElemento(), lis.longitud()+1);
            //Recorre hijo derecho
            listarInordenAux(unNodo.getHijoDer(), lis);
        }
    }

    private void listarInOrdenRangoAux(NodoABB unNodo, Lista lis, Comparable elemMinimo, Comparable elemMaximo){
        if(unNodo != null){
            //Recorre hijo izquierdo siempre y cuando el valor sea mayor al elemento minimo.
            if(unNodo.getHijoIzq()!=null && unNodo.getHijoIzq().getElemento().compareTo(elemMinimo)>=0){
                listarInOrdenRangoAux(unNodo.getHijoIzq(), lis, elemMinimo, elemMaximo);
            }
            //Si el hijo esta dentro del rango, lo lista.
            if(unNodo.getElemento().compareTo(elemMinimo)>0 && unNodo.getElemento().compareTo(elemMaximo)<0){
                lis.insertar(unNodo.getElemento(), lis.longitud()+1);
            }
            //Recorre hijo derecho siempre y cuando el valor sea menor al elemento maximo.
            if(unNodo.getHijoDer()!=null && unNodo.getHijoDer().getElemento().compareTo(elemMaximo)<=0){
                listarInOrdenRangoAux(unNodo.getHijoDer(), lis, elemMinimo, elemMaximo);
            }
        }
    }
}
