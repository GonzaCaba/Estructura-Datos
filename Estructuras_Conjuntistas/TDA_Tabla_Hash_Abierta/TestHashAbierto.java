package Estructuras_Conjuntistas.TDA_Tabla_Hash_Abierta;

import TDA_Lista.Dinamica.Lista;

public class TestHashAbierto {

    static String sOk  = "\u001B[32m OK! \u001B[0m";
    static String sErr = "\u001B[31m ERROR \u001B[0m";

    public static void main(String[] args) {
        ejecutarTests();
    }

    public static void ejecutarTests() {
        System.out.println("***** Test HashAbierto *****\n");

        // 1. Vacio inicial
        HashAbierto hash = new HashAbierto(5);
        System.out.println("Esta vacia inicialmente: "
            + (hash.esVacia() ? sOk : sErr));

        // 2. Insertar y pertenece
        System.out.println("Insertar 'A': "
            + (hash.insertar("A") ? sOk : sErr));
        System.out.println("Pertenece 'A': "
            + (hash.pertenece("A") ? sOk : sErr));

        // 3. Duplicados
        System.out.println("Insertar duplicado 'A': "
            + (!hash.insertar("A") ? sOk : sErr));

        // 4. Colisiones
        CollidingObject o1 = new CollidingObject(1);
        CollidingObject o2 = new CollidingObject(2);
        System.out.println("Insertar colision o1: "
            + (hash.insertar(o1) ? sOk : sErr));
        System.out.println("Insertar colision o2: "
            + (hash.insertar(o2) ? sOk : sErr));
        System.out.println("Pertenece o1: "
            + (hash.pertenece(o1) ? sOk : sErr));
        System.out.println("Pertenece o2: "
            + (hash.pertenece(o2) ? sOk : sErr));

        // 5. Eliminacion
        System.out.println("Eliminar inexistente 'X': "
            + (!hash.eliminar("X") ? sOk : sErr));
        System.out.println("Eliminar o1: "
            + (hash.eliminar(o1) ? sOk : sErr));
        System.out.println("Eliminar 'A': "
            + (hash.eliminar("A") ? sOk : sErr));
        System.out.println("Eliminar o2: "
            + (hash.eliminar(o2) ? sOk : sErr));
        System.out.println("Esta vacia tras eliminar todo: "
            + (hash.esVacia() ? sOk : sErr));

        // 6. Vaciar
        hash.insertar("B");
        hash.insertar("C");
        hash.vaciar();
        System.out.println("Vaciar tabla: "
            + (hash.esVacia() ? sOk : sErr));

        // 7. Clone profundo e independencia
        hash.insertar("1");
        hash.insertar("2");
        HashAbierto copia = hash.clone();
        System.out.println("Clone pertenece '1': "
            + (copia.pertenece("1") ? sOk : sErr));
        // Modifico el original
        hash.eliminar("1");
        System.out.println("Original no tiene '1': "
            + (!hash.pertenece("1") ? sOk : sErr));
        System.out.println("Copia aun tiene '1': "
            + (copia.pertenece("1") ? sOk : sErr));

        // 8. Listar
        Lista lista = copia.listar();
        System.out.println("Listar longitud debe 2: "
            + (lista.longitud() == 2 ? sOk : sErr));
        System.out.println("Contenido lista: " + lista);

        // 9. Manejo de nulls
        try {
            hash.insertar(null);
            System.out.println("Insertar null lanza excepcion: " + sErr);
        } catch (NullPointerException e) {
            System.out.println("Insertar null lanza NullPointerException: " + sOk);
        }
        try {
            hash.pertenece(null);
            System.out.println("Pertenece null lanza excepcion: " + sErr);
        } catch (NullPointerException e) {
            System.out.println("Pertenece null lanza NullPointerException: " + sOk);
        }
        try {
            hash.eliminar(null);
            System.out.println("Eliminar null lanza excepcion: " + sErr);
        } catch (NullPointerException e) {
            System.out.println("Eliminar null lanza NullPointerException: " + sOk);
        }

        System.out.println("\n***** Fin de Tests *****");
    }

    // Auxiliar para generar colisiones
    private static class CollidingObject {
        private final int id;
        CollidingObject(int id) { this.id = id; }
        @Override public int hashCode() { return 1; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CollidingObject)) return false;
            return id == ((CollidingObject)o).id;
        }
        @Override public String toString() { return "CO"+id; }
    }
}