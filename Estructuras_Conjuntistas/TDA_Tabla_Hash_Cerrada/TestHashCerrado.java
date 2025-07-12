package Estructuras_Conjuntistas.TDA_Tabla_Hash_Cerrada;

import TDA_Lista.Dinamica.Lista;

public class TestHashCerrado {

    static String sOk  = "\u001B[32m OK! \u001B[0m";
    static String sErr = "\u001B[31m ERROR \u001B[0m";

    public static void main(String[] args) {
        ejecutarTests();
    }

    public static void ejecutarTests() {
        System.out.println("***** Test HashCerrado *****\n");

        final int SIZE = 10;
        HashCerrado hash = new HashCerrado(SIZE);

        // 1. Vacio inicial
        System.out.println("Esta vacia inicialmente: "
            + (hash.esVacia() ? sOk : sErr));

        // 2. Insertar y pertenece (usar Integer para rehashing)
        Integer e1 = 1;
        System.out.println("Insertar 1: "
            + (hash.insertar(e1) ? sOk : sErr));
        System.out.println("Pertenece 1: "
            + (hash.pertenece(e1) ? sOk : sErr));

        // 3. Duplicado
        System.out.println("Insertar duplicado 1: "
            + (!hash.insertar(e1) ? sOk : sErr));

        // 4. Colisiones (1 y 6 colisionan en posicion 1)
        Integer e2 = 6;
        System.out.println("Insertar colision 6: "
            + (hash.insertar(e2) ? sOk : sErr));
        System.out.println("Pertenece 6: "
            + (hash.pertenece(e2) ? sOk : sErr));

        // 5. Eliminacion
        System.out.println("Eliminar inexistente 9: "
            + (!hash.eliminar(9) ? sOk : sErr));
        System.out.println("Eliminar 6: "
            + (hash.eliminar(e2) ? sOk : sErr));
        System.out.println("Eliminar 1: "
            + (hash.eliminar(e1) ? sOk : sErr));
        System.out.println("Esta vacia tras eliminar todo: "
            + (hash.esVacia() ? sOk : sErr));

        // 6. Vaciar y comprobar
        hash.insertar(2);
        hash.insertar(3);
        hash.vaciar();
        System.out.println("Vaciar tabla: "
            + (hash.esVacia() ? sOk : sErr));

        // 7. Clone profundo e independencia
        hash.insertar(4);
        hash.insertar(5);
        HashCerrado copia = hash.clone();
        System.out.println("Clone pertenece 4: "
            + (copia.pertenece(4) ? sOk : sErr));
        // Modificar original
        hash.eliminar(4);
        System.out.println("Original no tiene 4: "
            + (!hash.pertenece(4) ? sOk : sErr));
        System.out.println("Copia aun tiene 4: "
            + (copia.pertenece(4) ? sOk : sErr));

        // 8. Listar
        Lista lista = copia.listar();
        System.out.println("Listar longitud debe ser " + 2 + ": "
            + (lista.longitud() == 2 ? sOk : sErr));
        System.out.println("Contenido lista: " + lista.toString());

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
}
