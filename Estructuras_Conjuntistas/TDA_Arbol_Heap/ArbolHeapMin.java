package Estructuras_Conjuntistas.TDA_Arbol_Heap;
/**
 *
 * @author Cabanne, Gonzalo Heber
 */
public class ArbolHeapMin {
    private final int TAMANIO = 20;
    private int ultimo;
    private Comparable[] heap;

    public ArbolHeapMin(){
        this.heap = new Comparable[this.TAMANIO];
        this.ultimo = 0;    //La posicion 0 nunca es utilizada.
    }

    public boolean insertar(Comparable unElem){
        boolean exito = true;
        //Verifica si hay espacio en el arbol, si hay lo añadimos.
        if(this.ultimo < this.TAMANIO){
            this.ultimo++;
            this.heap[this.ultimo] = unElem;
            this.hacerSubir(this.ultimo);
        } else{
            //Si no hay falla la operacion. Devuelve false.
            exito = false;
        }
        return exito;
    }

    public boolean eliminarCima(){
        boolean exito = true;
        //Verifica si el arbol no esta vacio.
        if(this.ultimo > 0){
            //Consideramos caso de que solo haya un elemento para agilizar programacion.
            if(this.ultimo != 1){
                Comparable ultimoElemento = this.heap[this.ultimo];
                //Colocamos el ultimo elemento en la raiz.
                this.heap[1] = ultimoElemento;
                //Y ahora borramos el elemento en la ultima hoja.
                this.heap[this.ultimo] = null;
                this.ultimo--;
                this.hacerBajar(1);
            } else{
                this.heap[1] = null;
                this.ultimo = 0;
            }
        } else{
            exito = false;
        }
        return exito;
    }

    public Comparable recuperarCima(){
        //Verificamos si el arbol no esta vacio.
        Comparable cima = null;
        if(this.ultimo > 0){
            cima = this.heap[1];
        }
        return cima;
    }

    public boolean esVacio(){
        return !(this.ultimo>0);
    }

    public void vaciar(){
        if(this.ultimo > 0){
            for(int var = 1; var <= this.ultimo; var++){
                this.heap[var] = null;
            }
            this.ultimo = 0;
        }
    }

    // Metodos Privados & Auxiliares
    private void hacerSubir(int unaPos){
        //Si la posicion pasada no es la raiz y esta dentro del rango del arreglo...
        if(unaPos > 1 && unaPos <= this.ultimo){
            int posPadre = unaPos%2;
            Comparable valorPadre = this.heap[posPadre];
            Comparable valorHijo = this.heap[unaPos];
            //Verifica si el padre es mayor que el hijo.
            if(valorPadre.compareTo(valorHijo)>0){
                //Si es mayor, se intercambian padre con hijo.
                this.heap[posPadre] = valorHijo;
                this.heap[unaPos] = valorPadre;
                this.hacerSubir(posPadre);
            }
        }
    }

    private void hacerBajar(int unaPos){
        //Si la posicion pasada por parametro esta dentro del rango del arreglo...
        if(unaPos > 0 && unaPos <= this.ultimo){
            int posHijoIzq = unaPos*2;
            int posHijoDer = unaPos*2 + 1;
            int posHijoMenor;
            Comparable valorPadre = this.heap[unaPos];
            Comparable valorHijo;
            //Verifica cual de los dos hijos tiene el menor valor
            if(this.heap[posHijoIzq].compareTo(this.heap[posHijoDer])<0){
                valorHijo = this.heap[posHijoIzq];
                posHijoMenor = posHijoIzq;
            } else{
                valorHijo = this.heap[posHijoDer];
                posHijoMenor = posHijoDer;
            }
            //Ahora verifica si la nueva raiz es mayor al hijo de menor tamaño
            if(valorPadre.compareTo(valorHijo)>0){
                //Si lo es, intercambia sus valores
                this.heap[posHijoMenor] = valorPadre;
                this.heap[unaPos] = valorHijo;
                //Repite el mismo proceso.
                this.hacerBajar(posHijoMenor);
            }
        }
    }
}
