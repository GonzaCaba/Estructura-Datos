package Estructuras_Conjuntistas.TDA_Arbol_AVL;

import Estructuras_Conjuntistas.TDA_Arbol_AVL.ArbolAVL;

public class TestAVL {
    
    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";
    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String RESET = "\u001B[0m";
    public static void main (String args[]){
        System.out.println(ANSI_CYAN_BACKGROUND + ROJO + "**************************************************************");
        System.out.println(ANSI_CYAN_BACKGROUND + ROJO + "*                      Test Arbol AVL                        *");
        System.out.println(ANSI_CYAN_BACKGROUND + ROJO + "**************************************************************" + "\n\n" + RESET);

        ArbolAVL a = new ArbolAVL();
        ArbolAVL b = new ArbolAVL();

        System.out.println(ANSI_YELLOW_BACKGROUND + "--------------------------------------------------------------------------------------------------------"
                + "------------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n");

        System.out.println("********************************");
        System.out.println("*      Insercion basica        *");
        System.out.println("********************************");

        System.out.println("Checkeo si es vacio " + ((a.esVacio()) ? sOk : sErr));
        System.out.println("Intento vaciar arbol vacio ");
        a.vaciar();
        System.out.println("Altura de arbol vacio:  " + a.altura());
        System.out.println("Busco elemento inexistente en arbol vacio con pertenece."
                + "Tiene que dar " + sOk + " ---> " + ((!b.pertenece(20)) ? sOk : sErr));

        System.out.println("Inserto el 10" + ((a.insertar(10)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar:\n"
                + "\n        10"
                + "\n");
        System.out.println(a.toString());
        System.out.println("");

        System.out.println("Altura de arbol solo con raiz:  " + a.altura());
        System.out.println("Inserto el 9, debe quedar como hijo izquierdo de 10 " + ((a.insertar(9)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar:\n"
                + "\n        10 "
                + "\n    +---+  "
                + "\n    |      "
                + "\n    9      "
                + "\n");
        System.out.println(a.toString());
        System.out.println("");
        System.out.println("Inserto el 7" + ((a.insertar(7)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                9"
                + "\n                +---------------+---------------+"
                + "\n                |                               |"
                + "\n                7                              10"
                + "\n" + a.toString());
        System.out.println("Inserto el 3" + ((a.insertar(3)) ? sOk : sErr));
        System.out.println("Altura de arbol deberia dar 2:  " + a.altura());
        System.out.println("Inserto el 15" + ((a.insertar(15)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                           9"
                + "\n                +----------+----------+"
                + "\n                |                     |"
                + "\n                7                     10"
                + "\n          +-----+                     +-----+"
                + "\n          |                                 |"
                + "\n          3                                 15"
                + "\n" + a.toString());
        System.out.println("Inserto el 12" + ((a.insertar(12)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                           9"
                + "\n                +----------+----------+"
                + "\n                |                     |"
                + "\n                7                     12"
                + "\n          +-----+               +-----+-----+"
                + "\n          |                     |           |"
                + "\n          3                    10           15"
                + "\n" + a.toString());
        System.out.println("Inserto el 20" + ((a.insertar(20)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                               9"
                + "\n                +--------------+--------------+"
                + "\n                |                             |"
                + "\n                7                             12"
                + "\n          +-----+                    +--------+--------+"
                + "\n          |                          |                 |"
                + "\n          3                         10                 15"
                + "\n                                                       +-----+"
                + "\n                                                             |"
                + "\n                                                             20"
                + "\n" + a.toString());
        System.out.println("Inserto el 22" + ((a.insertar(22)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                               9"
                + "\n                +--------------+--------------+"
                + "\n                |                             |"
                + "\n                7                             12"
                + "\n          +-----+                    +--------+--------+"
                + "\n          |                          |                 |"
                + "\n          3                         10                 20"
                + "\n                                                 +-----+-----+"
                + "\n                                                 |           |"
                + "\n                                                15           22"
                + "\n" + a.toString());
        System.out.println("Inserto el 30" + ((a.insertar(30)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                               9"
                + "\n                +--------------+--------------+"
                + "\n                |                             |"
                + "\n                7                             20"
                + "\n          +-----+                    +--------+--------+"
                + "\n          |                          |                 |"
                + "\n          3                         12                 22"
                + "\n                                +---+---+              +-----+"
                + "\n                                |       |                    |"
                + "\n                               10       15                   30"
                + "\n" + a.toString());
        System.out.println("Inserto el 40" + ((a.insertar(40)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                               9"
                + "\n                +--------------+--------------+"
                + "\n                |                             |"
                + "\n                7                             20"
                + "\n          +-----+                    +--------+--------+"
                + "\n          |                          |                 |"
                + "\n          3                         12                 30"
                + "\n                                +---+---+        +-----+-----+"
                + "\n                                |       |        |           |"
                + "\n                               10       15      22           40"
                + "\n" + a.toString());
        System.out.println("Inserto el 45" + ((a.insertar(45)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                20"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            30"
                + "\n          +-----+-----+                 +----+----+"
                + "\n          |           |                 |         |"
                + "\n          7           12                22        40"
                + "\n      +---+       +---+---+                       +----+"
                + "\n      |           |       |                            |"
                + "\n      3          10       15                           45"
                + "\n" + a.toString());
        System.out.println("Inserto el 55" + ((a.insertar(55)) ? sOk : sErr));
        System.out.println("Se produce un desbalance, por lo que se rebalancea el arbol");
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                20"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            30"
                + "\n          +-----+-----+                 +----+----+"
                + "\n          |           |                 |         |"
                + "\n          7           12                22        45"
                + "\n      +---+       +---+---+                  +----+----+"
                + "\n      |           |       |                  |         |"
                + "\n      3          10       15                40         55"
                + "\n" + a.toString());
        System.out.println("\n");
        System.out.println("Checkeo si es vacio. Tiene que dar " + sErr + " --> " + ((a.esVacio()) ? sOk : sErr));
        System.out.println("Altura de arbol deberia dar 3:  " + a.altura());
        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);

        System.out.println("\n\n********************************");
        System.out.println("*      Test de clonado         *");
        System.out.println("********************************\n");

        b = a.clone();
        System.out.println("Altura de arbol clon:  " + b.altura());
        System.out.println("\n toString()  deberia dar: \n"
        + "\n                                20"
        + "\n                +---------------+------------+"
        + "\n                |                            |"
        + "\n                9                            30"
        + "\n          +-----+-----+                 +----+----+"
        + "\n          |           |                 |         |"
        + "\n          7           12                22        45"
        + "\n      +---+       +---+---+                  +----+----+"
        + "\n      |           |       |                  |         |"
        + "\n      3          10       15                40         55"
        + "\n" + b.toString());
        System.out.println("\n");
        System.out.println("Inserto el 25 en CLON" + ((b.insertar(25)) ? sOk : sErr));
        System.out.println("Inserto el 27 en CLON" + ((b.insertar(27)) ? sOk : sErr));
        System.out.println("Inserto el 35 en CLON" + ((b.insertar(35)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
        + "\n                                20"
        + "\n                +---------------+--------------+"
        + "\n                |                              |"
        + "\n                9                              30"
        + "\n          +-----+-----+                +-------+----------+"
        + "\n          |           |                |                  |"
        + "\n          7           12               25                 45"
        + "\n      +---+       +---+---+       +----+----+        +----+----+"
        + "\n      |           |       |       |         |        |         |"
        + "\n      3          10       15      22        27      40         55"
        + "\n                                               +----+"
        + "\n                                               |"
        + "\n                                               35"
        + "\n" + b.toString());
        System.out.println("\n" + AZUL + "CLON toString() \t\t\n" + b.toString() + "\n\n");
        System.out.println(VERDE + "ORIGINAL toString()\t\t\n " + a.toString() + "\n\n");

        System.out.println("Vacio el CLON");
        b.vaciar();
        System.out.println("Busco al padre 20 en Arbol vacio. Tiene que dar " + sOk + " --> " + ((b.padre(20) == null) ? sOk : sErr));
        System.out.println("Busco a 25 con pertenece. Tiene que dar " + sOk + " ---> " + ((!b.pertenece(20)) ? sOk : sErr));

        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "----------------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n********************************");
        System.out.println("*      Test de Busqueda         *");
        System.out.println("********************************\n");
        System.out.println("Busco al padre de 55. Tiene que dar " + sOk + " --> " + (((int) a.padre(55) == 45) ? sOk : sErr));
        System.out.println("Busco al padre de 20 (raiz). Tiene que dar " + sOk + " --> " + ((a.padre(20) == null) ? sOk : sErr));
        System.out.println("Busco al padre de 10. Tiene que dar " + sOk + " --> " + (((int) a.padre(10) == 12) ? sOk : sErr));
        System.out.println("Busco al padre de elemento inexistente. Tiene que dar " + sErr + " --> " + ((a.padre(1011) != null) ? sOk : sErr));
        System.out.println("Busco a raiz con pertenece. Tiene que dar " + sOk + " ---> " + ((a.pertenece(20)) ? sOk : sErr));
        System.out.println("Busco a 55 con pertenece. Tiene que dar " + sOk + " ---> " + ((a.pertenece(55)) ? sOk : sErr));
        System.out.println("Busco a 7 con pertenece. Tiene que dar " + sOk + " ---> " + ((a.pertenece(7)) ? sOk : sErr));
        System.out.println("Busco elemento inexistente con pertenece. Tiene que dar " + sOk + " ---> " + ((!a.pertenece(200)) ? sOk : sErr));

        System.out.println("Busco ancentros de raiz deberia dar vacio: " + a.obtenerAncestros(20).toString());
        System.out.println("Busco ancentros de 3 deberia dar 7 - 9 - 20: " + a.obtenerAncestros(3).toString());
        System.out.println("Busco ancentros de 40 deberia dar 45 - 30 - 20: " + a.obtenerAncestros(40).toString());
        System.out.println("Busco ancentros de elemento inexistente deberia dar vacio: " + a.obtenerAncestros(3333).toString());

        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "----------------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n**********************************");
        System.out.println("*        Test de Borrado         *");
        System.out.println("**********************************\n");

        System.out.println("Eliminamos por caso 1, es decir eliminacion con nodo sin hijos. Eliminamos nodo 40: " + sOk + " --> " + ((a.eliminar(40)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                20"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            30"
                + "\n          +-----+-----+                 +----+----+"
                + "\n          |           |                 |         |"
                + "\n          7           12                22        45"
                + "\n      +---+       +---+---+                       +----+"
                + "\n      |           |       |                            |"
                + "\n      3          10       15                           55"
                + "\n" + a.toString());
        System.out.println("\n\n");
        System.out.println("Eliminamos por caso 2, es decir eliminacion con nodo con un solo hijo. Eliminamos nodo 7: " + sOk + " --> " + ((a.eliminar(7)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                20"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            30"
                + "\n          +-----+-----+                 +----+----+"
                + "\n          |           |                 |         |"
                + "\n          3           12                22        45"
                + "\n                  +---+---+                       +----+"
                + "\n                  |       |                            |"
                + "\n                 10       15                           55"
                + "\n" + a.toString());
        System.out.println("\n\n");
        System.out.println("Eliminamos por caso 3, es decir eliminacion con nodo con dos hijos. Eliminamos nodo 20: " + sOk + " --> " + ((a.eliminar(20)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                15"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                9                            30"
                + "\n          +-----+-----+                 +----+----+"
                + "\n          |           |                 |         |"
                + "\n          3           12                22        45"
                + "\n                  +---+                           +----+"
                + "\n                  |                                    |"
                + "\n                 10                                   55"
                + "\n" + a.toString());
        System.out.println("\n\n");
        System.out.println("Eliminamos por caso 3 nuevamente, para forzar desbalanceo. Eliminamos nodo 9: " + sOk + " --> " + ((a.eliminar(9)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n"
                + "\n                                15"
                + "\n                +---------------+------------+"
                + "\n                |                            |"
                + "\n                10                            30"
                + "\n          +-----+-----+                 +----+----+"
                + "\n          |           |                 |         |"
                + "\n          3           12                22        45"
                + "\n                                                  +----+"
                + "\n                                                       |"
                + "\n                                                       55"
                + "\n" + a.toString());
        System.out.println("\n\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "----------------------------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------" + RESET);
        System.out.println("\n\n**********************************");
        System.out.println("*      Test de Recorridos        *");
        System.out.println("**********************************\n");
        System.out.println("\n");
        System.out.println("Recorrido en lista in orden .\n Tiene que dar: "
                + "\n [ 3 - 10 - 12 - 15 - 22 - 30 - 45 - 55 ]"
                + "\n --> " + a.listar().toString());
        System.out.println("\n");
        System.out.println("Recorrido en lista in orden con rango de 1 a 20.\n Tiene que dar: "
                + "\n [ 3 - 10 - 12 - 15 ]"
                + "\n --> " + a.listarRango(1,20).toString());
        System.out.println("\n");
        System.out.println("Recorrido en lista in orden con rango de 20 a 40.\n Tiene que dar: "
                + "\n [ 22 - 30 ]"
                + "\n --> " + a.listarRango(20,40).toString());
        System.out.println("\n");
        System.out.println("Recorrido en lista in orden con rango de 40 a 60.\n Tiene que dar: "
                + "\n [ 45 - 55 ]"
                + "\n --> " + a.listarRango(40,60).toString());
        System.out.println("\n");
        System.out.println("Recorrido por Niveles.\n Tiene que dar: "
                + "\n [ 15 - 10 - 30 - 3 - 12 - 22 - 45 - 55 ]  "
                + "\n --> " + a.listarPorNiveles().toString());
        System.out.println("\n");
        System.out.println(ANSI_YELLOW_BACKGROUND + "----------------------------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------" + RESET);
    }
    
}
