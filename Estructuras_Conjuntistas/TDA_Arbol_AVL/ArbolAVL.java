package Estructuras_Conjuntistas.TDA_Arbol_AVL;

import TDA_Lista.Dinamica.Lista;
/**
 *
 * @author Cabanne, Gonzalo Heber
 */
public class ArbolAVL {
    
    private NodoAVL raiz;

    public ArbolAVL(){
        this.raiz = null;
    }

    public boolean insertar(Comparable unElem){
        boolean exito = false;
        //Caso unico, el arbol esta vacio.
        if(this.raiz == null){
            this.raiz = new NodoAVL(unElem, null, null, 0);
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

    public String toString(){
        String s = this.toStringAux(this.raiz);
        return s;
    }

    public boolean esVacio(){
        return (this.raiz == null);
    }

    public int altura(){
        return this.alturaAux(this.raiz);
    }

    public ArbolAVL clone(){
        ArbolAVL arbolClone = new ArbolAVL();
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
                NodoAVL nodoMinimo = this.raiz.getHijoIzq();
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
                NodoAVL nodoMaximo = this.raiz.getHijoDer();
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


    //Metodos Privados & Auxiliares
    private boolean perteneceAux(NodoAVL unNodo, Comparable unElemento){
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

    private int alturaAux(NodoAVL unNodo){
        int alturaArbol = -1;
        if (unNodo != null) {
            NodoAVL hijoIzq = unNodo.getHijoIzq();
            NodoAVL hijoDer = unNodo.getHijoDer();
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

    private String toStringAux(NodoAVL unNodo){
        String cadena = "";
        if(unNodo != null){
            cadena = "Raiz: "+unNodo.getElemento();
            if (unNodo.getHijoIzq() != null) {
                cadena += "     H.I: "+unNodo.getHijoIzq().getElemento();
            } else{
                cadena += "     H.I: -";
            }
            if(unNodo.getHijoDer() != null){
                cadena += "     H.D: "+unNodo.getHijoDer().getElemento();
            } else{
                cadena += "     H.D: -";
            }
            if (unNodo.getHijoIzq() != null) {
                cadena += "\n" + toStringAux(unNodo.getHijoIzq());
            }
            if (unNodo.getHijoDer() != null) {
                cadena += "\n" + toStringAux(unNodo.getHijoDer());
            }
        }
        return cadena;
    }

    private boolean insertarAux(NodoAVL unNodo, Comparable unElemento){
        //Precondicion: unNodo es nulo
        boolean exito = true;
        if((unElemento.compareTo(unNodo.getElemento()))==0){
            // Error: el elemento esta repetido
            exito = false;
        } else if((unElemento.compareTo(unNodo.getElemento()))>0){
            // Si el elemento es mayor que la raiz, entonces buscamos en el hijo derecho
            if(unNodo.getHijoDer()!=null){
                exito = insertarAux(unNodo.getHijoDer(), unElemento);
                if(exito){
                    //Si se añadio un nuevo nodo al arbol se debe recalcular la altura de todos los nodos
                    //ancestros del nuevo nodo.
                    unNodo.recalcularAltura();
                    //Luego de calcular la nueva altura del nodo se debe verificar si existe un desbalance.
                    this.balancearArbol(unNodo.getHijoDer(), unNodo);
                }
            } else
                unNodo.setHijoDer(new NodoAVL(unElemento, null, null, 0));
        } else{
            // Si el elemento es menor que la raiz, entonces buscamos en el hijo izquierdo
            if(unNodo.getHijoIzq()!=null){
                exito = insertarAux(unNodo.getHijoIzq(), unElemento);
                if(exito){
                    unNodo.recalcularAltura();
                    this.balancearArbol(unNodo.getHijoIzq(), unNodo);
                }
            } else
                unNodo.setHijoIzq(new NodoAVL(unElemento, null, null, 0));
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL unNodo, NodoAVL nodoPadre, Comparable unElemento){
        //Precondicion: unNodo no es nulo ni es la raiz
        boolean exito = false;
        NodoAVL nodoHijo;
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
                if(exito){
                    //Si se elimino un nodo del arbol se debe recalcular la altura de todos los nodos
                    //ancestros del nodo eliminado.
                    unNodo.recalcularAltura();
                    //Luego de calcular la nueva altura del nodo se debe verificar si existe un desbalance.
                    this.balancearArbol(unNodo, nodoPadre);
                }
            } else{
                // Si el elemento es menor a la raiz, busca en el hijo izquierdo
                exito = eliminarAux(unNodo.getHijoIzq(), unNodo, unElemento);
                if(exito){
                    unNodo.recalcularAltura();
                    this.balancearArbol(unNodo, nodoPadre);
                }
            }
        }
        return exito;
    }

    private void balancearArbol(NodoAVL unNodo, NodoAVL nodoPadre){
        if(unNodo!=null){
            //Calcula el balance del nodo raiz.
            int balance = this.calcularBalanceArbol(unNodo);
            int balanceHijo;
            //Caso 1: El arbol esta caido para la derecha.
            if(balance < -1){
                balanceHijo = this.calcularBalanceArbol(unNodo.getHijoDer());
                //Si el subarbol hijo derecho, tambien esta caido hacia la derecha
                //se aplica rotacion simple a izq.
                if(balanceHijo == -1 || balanceHijo == 0){
                    this.rotacionSimpleIzq(nodoPadre, unNodo);
                } else{
                    //Si el subarbol hijo derecho, esta caido a la izq se debe aplicar
                    //una doble rotacion derecha-izquierda.
                    this.rotacionSimpleDer(unNodo, unNodo.getHijoDer());
                    this.rotacionSimpleIzq(nodoPadre, unNodo);
                }
            } else if(balance > 1){
                balanceHijo = this.calcularBalanceArbol(unNodo.getHijoIzq());
                //Si el subarbol hijo izquierdo, tambien esta caido hacia la izquierda
                //se aplica rotacion simple a der.
                if(balanceHijo == 1 || balanceHijo == 0){
                    this.rotacionSimpleDer(nodoPadre, unNodo);
                } else{
                    //Si el subarbol hijo izquierdo esta caido a la derecha se debe
                    //aplicar una doble rotacion izquierda-derecha.
                    this.rotacionSimpleIzq(unNodo, unNodo.getHijoIzq());
                    this.rotacionSimpleDer(nodoPadre, unNodo);
                }
            }
        }
    }

    private void rotacionSimpleIzq(NodoAVL nodoPadre, NodoAVL nodoRaiz){
        //Precondicion: el hijo derecho no es nulo (balance hijo der es -1 o 0).
        if(nodoPadre != null && nodoRaiz !=null){ 
            NodoAVL nodoHijoDer = nodoRaiz.getHijoDer();
            //Remplazamos la raiz por el nodoHijoDer
            if(nodoPadre.getHijoIzq()==nodoRaiz){
                nodoPadre.setHijoIzq(nodoHijoDer);
            } else if(nodoPadre.getHijoDer()==nodoRaiz){
                nodoPadre.setHijoDer(nodoHijoDer);
            }
            //Coloca como hijo derecho de la antigua raiz, al hijo izquierdo de la nueva raiz.
            nodoRaiz.setHijoDer(nodoHijoDer.getHijoIzq());
            //Coloca como hijo izquierdo de la nueva raiz, a la antigua raiz.
            nodoHijoDer.setHijoIzq(nodoRaiz); 
        }
    }

    private void rotacionSimpleDer(NodoAVL nodoPadre, NodoAVL nodoRaiz){
        //Precondicion: el hijo izquierdo no es nulo (balance hijo izq es 1 o 0).
        if(nodoPadre != null && nodoRaiz !=null){ 
            NodoAVL nodoHijoIzq = nodoRaiz.getHijoIzq();
            //Remplazamos la raiz por el nodoHijoIzq
            if(nodoPadre.getHijoIzq()==nodoRaiz){
                nodoPadre.setHijoIzq(nodoHijoIzq);
            } else if(nodoPadre.getHijoDer()==nodoRaiz){
                nodoPadre.setHijoDer(nodoHijoIzq);
            }
            //Coloca como hijo izquierdo de la antigua raiz, al hijo derecho de la nueva raiz.
            nodoRaiz.setHijoIzq(nodoHijoIzq.getHijoDer());
            //Coloca como hijo derecho de la nueva raiz, a la antigua raiz.
            nodoHijoIzq.setHijoDer(nodoRaiz); 
        }
    }

    private int calcularBalanceArbol(NodoAVL nodoRaiz){
        int balance = 0;
        if(nodoRaiz!=null){
            int alturaHijoIzq;
            int alturaHijoDer;
            //Verifica si los hijos no son nulos.
            if(nodoRaiz.getHijoIzq()==null)
                alturaHijoIzq = -1;
            else
                alturaHijoIzq = nodoRaiz.getHijoIzq().getAltura();
            if(nodoRaiz.getHijoDer()==null)
                alturaHijoDer = -1;
            else
                alturaHijoDer = nodoRaiz.getHijoDer().getAltura();
            //Ahora calcula el balance
            balance = alturaHijoIzq - alturaHijoDer;
        }
        return balance;
    }

    private NodoAVL cloneAux(NodoAVL unNodo){
        NodoAVL nodoClon = null;
        if(unNodo != null){
            nodoClon = new NodoAVL(unNodo.getElemento(), cloneAux(unNodo.getHijoIzq()), cloneAux(unNodo.getHijoDer()),unNodo.getAltura());
        }
        return nodoClon;
    }

    private boolean eliminarCaso1(NodoAVL nodoPadre, NodoAVL nodoRaiz){
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

    private boolean eliminarCaso2(NodoAVL nodoPadre, NodoAVL nodoRaiz){
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

    private boolean eliminarCaso3(NodoAVL nodoRaiz){
        //Caso 3: Primero se busca el Candidato A, es decir el nodo mas a la derecha
        //bajando por la rama izquierda.
        boolean exito = false;
        if(nodoRaiz != null){
            exito = this.eliminarCaso3Aux(nodoRaiz, nodoRaiz.getHijoIzq(), nodoRaiz);
        }
        return exito;
    }

    private boolean eliminarCaso3Aux(NodoAVL nodoRaiz, NodoAVL nodoCandidato, NodoAVL nodoPadreCandidato){
        boolean exito = false;
        if(nodoCandidato != null && nodoPadreCandidato != null){
            if(nodoRaiz.getHijoDer()!=null){
                exito = eliminarCaso3Aux(nodoRaiz, nodoCandidato.getHijoDer(), nodoCandidato);
                if(exito){
                    //Si se elimino el hijo para remplazar la raiz, debemos recalcular la altura
                    //de todos los ancestros y ademas re-balancear el arbol si es necesario.
                    nodoCandidato.recalcularAltura(); //Recalculamos la altura del PADRE del nodo candidato eliminado
                    this.balancearArbol(nodoCandidato, nodoPadreCandidato); //Le pasamos como parametro el PADRE del nodo candidato eliminado y su ABUELO
                }
            } else{
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
        }
        return exito;
    }

    private void listarInordenAux(NodoAVL unNodo, Lista lis){
        if(unNodo != null){
            //Recorre hijo izquierdo
            listarInordenAux(unNodo.getHijoIzq(), lis);
            //Visita el elemento en el nodo
            lis.insertar(unNodo.getElemento(), lis.longitud()+1);
            //Recorre hijo derecho
            listarInordenAux(unNodo.getHijoDer(), lis);
        }
    }

    private void listarInOrdenRangoAux(NodoAVL unNodo, Lista lis, Comparable elemMinimo, Comparable elemMaximo){
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
