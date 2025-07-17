package Estructuras_Grafos.TDA_DiGrafo_Etiquetado;

import java.util.HashMap;
import java.util.Map;

import TDA_Cola.Dinamica.Cola;
import TDA_Lista.Dinamica.Lista;

public class Grafo {
    private NodoVert inicio;

    // Constructor
    public Grafo() {
        this.inicio = null;
    }

    // De Implementacion
    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        // Si el grafo esta vacio...
        if (this.inicio == null) {
            this.inicio = new NodoVert(nuevoVertice, null, null);
            exito = true;
        } else {
            NodoVert aux = this.inicio;
            while (aux.getSigVertice() != null && !aux.getSigVertice().getElem().equals(nuevoVertice)) {
                aux = aux.getSigVertice();
            }
            if (aux.getSigVertice() == null) {
                aux.setSigVertice(new NodoVert(nuevoVertice, null, null));
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarVertice(Object vertice) {
        boolean exito = false;
        // Primero verificamos la existencia del vertice, si existe lo eliminamos.
        NodoVert auxVert = this.inicio;
        // Si el grafo no esta vacio.
        if (auxVert != null) {
            // Caso especial: el nodo a eliminar es el primero de todos
            if (auxVert.getElem().equals(vertice)) {
                auxVert.setPrimerAdy(null);
                this.inicio = auxVert.getSigVertice();
                exito = true;
            } else {
                while (auxVert.getSigVertice() != null) {
                    if (auxVert.getSigVertice().getElem().equals(vertice)) {
                        // Elimina el vertice la lista de vertices.
                        auxVert.getSigVertice().setPrimerAdy(null);
                        auxVert.setSigVertice((auxVert.getSigVertice()).getSigVertice());
                        exito = true;
                        break;
                    }
                    auxVert = auxVert.getSigVertice();
                }
            }

            // Segundo, tenemos que recorrer todos los arcos para ver cual tenia una
            // conexion con
            // el nodo que eliminamos.
            if (exito) {
                auxVert = this.inicio;
                while (auxVert != null) {
                    NodoAdy auxAdy = auxVert.getPrimerAdy();
                    // Si el nodo adyacente a eliminar es el primero:
                    while (auxAdy != null && auxAdy.getVertice().getElem().equals(vertice)) {
                        auxVert.setPrimerAdy(auxAdy.getSigAdyacente());
                        auxAdy = auxVert.getPrimerAdy();
                    }
                    while (auxAdy != null && auxAdy.getSigAdyacente() != null) {
                        if ((auxAdy.getSigAdyacente()).getVertice().getElem().equals(vertice)) {
                            // Si el siguiente nodo adyacente es un arco al nodo a eliminar, lo borra
                            auxAdy.setSigAdyacente((auxAdy.getSigAdyacente()).getSigAdyacente());
                        }
                        auxAdy = auxAdy.getSigAdyacente();
                    }
                    auxVert = auxVert.getSigVertice();
                }
            }
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean exito = false;
        // Verifica si existen ambos vertices.
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        // Podriamos llamar a ubicar vertice, pero seria recorrer dos veces todos los
        // vertices
        // para encontrar tanto el origen como el destino.
        while ((auxOrigen == null || auxDestino == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxOrigen = aux;
            if (aux.getElem().equals(destino))
                auxDestino = aux;
            aux = aux.getSigVertice();
        }

        if (auxOrigen != null && auxDestino != null) {
            // Si ambos vertices existen, busca si existen caminos entre ambos
            NodoAdy auxAdy = auxOrigen.getPrimerAdy();
            if (auxAdy != null) {
                while (auxAdy.getSigAdyacente() != null) {
                    // Recorre todos los adyacentes
                    auxAdy = auxAdy.getSigAdyacente();
                }
                auxAdy.setSigAdyacente(new NodoAdy(auxDestino, null, etiqueta));
                exito = true;
            } else {
                auxOrigen.setPrimerAdy(new NodoAdy(auxDestino, null, etiqueta));
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false;
        // Verifica si existen ambos vertices.
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        // Podriamos llamar a ubicar vertice, pero seria recorrer dos veces todos los
        // vertices
        // para encontrar tanto el origen como el destino.
        while ((auxOrigen == null || auxDestino == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxOrigen = aux;
            if (aux.getElem().equals(destino))
                auxDestino = aux;
            aux = aux.getSigVertice();
        }
        if (auxOrigen != null && auxDestino != null) {
            // Si ambos vertices existen, busca si existen caminos entre ambos
            NodoAdy auxAdy = auxOrigen.getPrimerAdy();
            if (auxAdy != null) {
                // Primer caso: el primer arco es el que debe ser eliminado
                if (auxAdy.getVertice().getElem().equals(destino)) {
                    auxOrigen.setPrimerAdy(auxAdy.getSigAdyacente());
                    exito = true;
                } else {
                    while (auxAdy.getSigAdyacente() != null) {
                        // Recorre todos los adyacentes hasta encontrar el arco
                        if ((auxAdy.getSigAdyacente()).getVertice().getElem().equals(destino)) {
                            auxAdy.setSigAdyacente(auxAdy.getSigAdyacente().getSigAdyacente());
                            exito = true;
                            break;
                        }
                        auxAdy = auxAdy.getSigAdyacente();
                    }
                }
            }
        }
        return exito;
    }

    public boolean existeVertice(Object vertice) {
        // Recorre todos los vertices hasta al final para ubicar el vertice
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(vertice)) {
            aux = aux.getSigVertice();
        }
        return (aux != null);
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean exito = false;
        // Verifica si existen ambos vertices.
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        // Podriamos llamar a ubicar vertice, pero seria recorrer dos veces todos los
        // vertices
        // para encontrar tanto el origen como el destino.
        while ((auxOrigen == null || auxDestino == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxOrigen = aux;
            if (aux.getElem().equals(destino))
                auxDestino = aux;
            aux = aux.getSigVertice();
        }
        if (auxOrigen != null && auxDestino != null) {
            // Si ambos vertices existen, busca si existen caminos entre ambos
            NodoAdy auxAdy = auxOrigen.getPrimerAdy();
            if (auxAdy != null) {
                if (auxAdy.getVertice().getElem().equals(destino)) {
                    exito = true;
                } else {
                    while (auxAdy.getSigAdyacente() != null) {
                        // Recorre todos los adyacentes hasta encontrar el arco
                        if ((auxAdy.getSigAdyacente()).getVertice().getElem().equals(destino)) {
                            exito = true;
                            break;
                        }
                        auxAdy = auxAdy.getSigAdyacente();
                    }
                }
            }
        }
        return exito;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        // Verifica si existen ambos vertices.
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        // Podriamos llamar a ubicar vertice, pero seria recorrer dos veces todos los
        // vertices
        // para encontrar tanto el origen como el destino.
        while ((auxOrigen == null || auxDestino == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxOrigen = aux;
            if (aux.getElem().equals(destino))
                auxDestino = aux;
            aux = aux.getSigVertice();
        }

        if (auxOrigen != null && auxDestino != null) {
            // Si ambos vertices existen, busca si existen caminos entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxOrigen, destino, visitados);
        }
        return exito;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoMasCorto = new Lista();
        // Verifica si existen ambos vertices.
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        // Podriamos llamar a ubicar vertice, pero seria recorrer dos veces todos los
        // vertices
        // para encontrar tanto el origen como el destino.
        while ((auxOrigen == null || auxDestino == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxOrigen = aux;
            if (aux.getElem().equals(destino))
                auxDestino = aux;
            aux = aux.getSigVertice();
        }

        if (auxOrigen != null && auxDestino != null) {
            // Si ambos vertices existen, busca si existen caminos entre ambos
            Lista visitados = new Lista();
            caminoMasCorto = this.caminoMasCortoAux(auxOrigen, auxDestino, visitados);
        }
        return caminoMasCorto;
    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        Lista caminoMasLargo = new Lista();
        // Verifica si existen ambos vertices.
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        // Podriamos llamar a ubicar vertice, pero seria recorrer dos veces todos los
        // vertices
        // para encontrar tanto el origen como el destino.
        while ((auxOrigen == null || auxDestino == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxOrigen = aux;
            if (aux.getElem().equals(destino))
                auxDestino = aux;
            aux = aux.getSigVertice();
        }

        if (auxOrigen != null && auxDestino != null) {
            // Si ambos vertices existen, busca si existen caminos entre ambos
            Lista visitados = new Lista();
            caminoMasLargo = this.caminoMasLargoAux(auxOrigen, auxDestino, visitados);
        }
        return caminoMasLargo;
    }

    public boolean esVacio() {
        return (this.inicio == null);
    }

    public Grafo clone() {
        Grafo grafoClone = new Grafo();
        grafoClone.inicio = this.cloneAux(this.inicio, new HashMap<>());
        return grafoClone;
    }

    public void vaciar() {
        this.inicio = null;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        // Define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                // Si el vertice no fue visitado aun, avanza en profundidad
                this.listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    public String toString() {
        String cadena = "";
        NodoVert vert = this.inicio;
        while (vert != null) {
            cadena += vert.getElem() + " -> ";
            NodoAdy ady = vert.getPrimerAdy();
            if (ady == null) {
                cadena += "∅";
            }
            while (ady != null) {
                cadena += ady.getVertice().getElem();
                ady = ady.getSigAdyacente();
                if (ady != null) {
                    cadena += ", ";
                }
            }
            cadena += "\n";
            vert = vert.getSigVertice();
        }
        return cadena;
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        // Define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                // Si el vertice no se recorrio todavia...
                this.listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    // Privadas & Auxiliares
    private void listarEnProfundidadAux(NodoVert unVert, Lista vis) {
        if (unVert != null) {
            // Marca el vertice unVert como visitado
            vis.insertar(unVert.getElem(), vis.longitud() + 1);
            NodoAdy ady = unVert.getPrimerAdy();
            while (ady != null) {
                // Visita en profundad los adyacentes de unVert aun no visitados
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    private void listarEnAnchuraAux(NodoVert unVert, Lista vis) {
        NodoVert nodoVert;
        NodoAdy nodoAdy;
        Cola nodosRecorrer = new Cola();
        vis.insertar(unVert.getElem(), vis.longitud() + 1);
        nodosRecorrer.poner(unVert);
        while (!nodosRecorrer.esVacia()) {
            nodoVert = (NodoVert) nodosRecorrer.obtenerFrente();
            nodosRecorrer.sacar();
            nodoAdy = nodoVert.getPrimerAdy();
            // Recorremos todos los nodos adyacentes de nodoVert
            while (nodoAdy != null) {
                // Si el nodo adyacente no se encuentra en la lista, lo colocamos
                if (vis.localizar(nodoAdy.getVertice().getElem()) < 0) {
                    vis.insertar(nodoAdy.getVertice().getElem(), vis.longitud() + 1);
                    nodosRecorrer.poner(nodoAdy.getVertice());
                }
                nodoAdy = nodoAdy.getSigAdyacente();
            }
        }
    }

    private boolean existeCaminoAux(NodoVert unVert, Object destino, Lista vis) {
        boolean exito = false;
        if (unVert != null) {
            // Si vertice unVert es el destino: HAY CAMINO!
            if (unVert.getElem().equals(destino)) {
                exito = true;
            } else {
                // Si no es el destino verifica si hay camino entre unVert y destino
                vis.insertar(unVert.getElem(), vis.longitud() + 1);
                NodoAdy ady = unVert.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), destino, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    private Lista caminoMasCortoAux(NodoVert nodoOrigen, NodoVert nodoDestino, Lista vis) {
        // Devuelve una lista con el camino mas corto desde un NodoOrigen hasta un
        // nodoDestino
        // utilizando BFS, si no encuentra el camino devuelve la lista vacia.

        Lista camino = new Lista();
        Cola nodosVisitar = new Cola();
        Map<NodoVert, NodoVert> predecesores = new HashMap<>();

        nodosVisitar.poner(nodoOrigen);
        vis.insertar(nodoOrigen.getElem(), vis.longitud() + 1);
        predecesores.put(nodoOrigen, null);

        boolean encontrado = false;

        while (!nodosVisitar.esVacia() && !encontrado) {
            NodoVert nodoActual = (NodoVert) nodosVisitar.obtenerFrente();
            nodosVisitar.sacar();
            NodoAdy ady = nodoActual.getPrimerAdy();

            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    nodosVisitar.poner(ady.getVertice());
                    vis.insertar(ady.getVertice().getElem(), vis.longitud() + 1);
                    predecesores.put(ady.getVertice(), nodoActual);

                    if (ady.getVertice().getElem().equals(nodoDestino.getElem())) {
                        encontrado = true;
                        break;
                    }
                }
                ady = ady.getSigAdyacente();
            }
        }

        if (encontrado) {
            // Si se encontro un camino, se reconstruye con el HashMap
            camino.insertar(nodoDestino.getElem(), 1);
            NodoVert nodoAux = predecesores.get(nodoDestino);
            while (nodoAux != null) {
                camino.insertar(nodoAux.getElem(), 1);
                nodoAux = predecesores.get(nodoAux);
            }
            // Si llega a null significa que llego al nodo origen,
            // por ende el camino esta completo
        }

        return camino;
    }

    private Lista caminoMasLargoAux(NodoVert actual, NodoVert destino, Lista visitados) {
        Lista mejor = new Lista();

        if (actual.getElem().equals(destino.getElem())) {
            mejor.insertar(actual.getElem(), 1);
        } else {
            /* Marco el nodo como visitado */
            visitados.insertar(actual.getElem(), visitados.longitud() + 1);
            /* Exploro vecinos no visitados */
            NodoAdy ady = actual.getPrimerAdy();
            while (ady != null) {
                NodoVert vecino = ady.getVertice();
                if (visitados.localizar(vecino.getElem()) < 0) {
                    Lista candidato = caminoMasLargoAux(vecino, destino, visitados);
                    /* Si el candidato llega al destino y es mas largo, lo guardo */
                    if (!candidato.esVacia() && candidato.longitud() > mejor.longitud()) {
                        mejor = candidato;
                    }
                }
                ady = ady.getSigAdyacente();
            }

            /* Desmarco para back‑tracking */
            visitados.eliminar(visitados.longitud());

            /* Si encontre al menos un camino, antepongo mi nodo */
            if (!mejor.esVacia()) {
                mejor.insertar(actual.getElem(), 1);
            }
        }

        return mejor; 
    }

    private NodoVert cloneAux(NodoVert unVertice, Map<NodoVert, NodoVert> mapa) {
        NodoVert nodoClon = null;
        if (unVertice != null) {
            // Si el vertice ya fue clonado, se devuelve el clon.
            if (mapa.containsKey(unVertice)) {
                nodoClon = mapa.get(unVertice);
            } else {
                // Se clona el vertice
                nodoClon = new NodoVert(unVertice.getElem(), null, null);
                mapa.put(unVertice, nodoClon);

                // Se clona el siguiente vertice
                nodoClon.setSigVertice(this.cloneAux(unVertice.getSigVertice(), mapa));

                // Se clona los nodos adyacentes
                NodoAdy adyOrig = unVertice.getPrimerAdy();
                NodoAdy adyClonPrimero = null;
                NodoAdy adyClonPrevio = null;
                while (adyOrig != null) {
                    // Buscamos el clon del vertice destino
                    NodoVert destinoClon = this.cloneAux(adyOrig.getVertice(), mapa);
                    NodoAdy adyClon = new NodoAdy(destinoClon, null,adyOrig.getEtiqueta());

                    if (adyClonPrimero == null) {
                        adyClonPrimero = adyClon;
                    } else {
                        // Con esto se logra que el nodo que clonamos en la iteracion anterior
                        // enlace su "sigAdyacente" con el nodo creado en la iteracion actual
                        adyClonPrevio.setSigAdyacente(adyClon);
                    }
                    adyClonPrevio = adyClon;
                    // Avanzamos al siguiente adyacente de el nodo original.
                    adyOrig = adyOrig.getSigAdyacente();
                }
                nodoClon.setPrimerAdy(adyClonPrimero);
            }
        }
        return nodoClon;
    }
}
