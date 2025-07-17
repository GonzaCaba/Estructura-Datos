package Estructuras_Grafos.TDA_DiGrafo_NoEtiquetado;

public class TestGrafo {

    /* ===== Colores para consola ===== */
    static final String OK = "\u001B[32m OK! \u001B[0m";
    static final String ERR = "\u001B[31m ERROR \u001B[0m";
    static final String CY = "\u001B[36m";
    static final String BG_Y = "\u001B[43m";
    static final String BG_C = "\u001B[46m";
    static final String BG_R = "\u001B[41m";
    static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println(BG_C + CY + "**************************************************************");
        System.out.println(BG_C + CY + "*                       Test Grafo                           *");
        System.out
                .println(BG_C + CY + "**************************************************************" + "\n\n" + RESET);

        /*--------------------------------------------------------------------
         * 1.  Construccion e insercion de vertices y arcos
         *------------------------------------------------------------------*/
        Grafo g = new Grafo();
        System.out.println("El grafo comienza vacio: " + (g.esVacio() ? OK : ERR));

        /* ===== Vertices ==================================================== */
        String[] ciudades = {
                "Coruna", "Vigo", "Valladolid", "Bilbao", "Oviedo",
                "Madrid", "Zaragoza", "Barcelona", "Gerona", "Valencia",
                "Albacete", "Murcia", "Granada", "Jaen", "Sevilla",
                "Cadiz", "Badajoz"
        };
        for (String c : ciudades) {
            g.insertarVertice(c);
        }

        /* ===== Aristas NO etiquetadas – sin repetidos ====================== */
        String[][] arcos = {
                { "Coruna", "Vigo" },
                { "Coruna", "Valladolid" },
                { "Vigo", "Valladolid" },
                { "Valladolid", "Bilbao" },
                { "Valladolid", "Madrid" },
                { "Oviedo", "Bilbao" },
                { "Bilbao", "Zaragoza" },
                { "Madrid", "Zaragoza" },
                { "Madrid", "Badajoz" },
                { "Madrid", "Albacete" },
                { "Madrid", "Jaen" },
                { "Zaragoza", "Barcelona" },
                { "Barcelona", "Gerona" },
                { "Barcelona", "Valencia" },
                { "Valencia", "Albacete" },
                { "Valencia", "Murcia" },
                { "Murcia", "Granada" },
                { "Murcia", "Albacete" },
                { "Granada", "Jaen" },
                { "Jaen", "Sevilla" },
                { "Sevilla", "Cadiz" }
        };

        /* Inserto cada arista en ambos sentidos para simular grafo no etiquetado */
        for (String[] a : arcos) {
            g.insertarArco(a[0], a[1]);
            g.insertarArco(a[1], a[0]);
        }

        System.out.println("** toString() real **");
        System.out.println(g.toString());

        /*--------------------------------------------------------------------
         * 2.  Busquedas y existencia
         *------------------------------------------------------------------*/
        System.out.println("existeVertice(\"Girona\"): " + (g.existeVertice("Gerona") ? OK : ERR));
        System.out.println("existeVertice(\"Cataluña\"): " + (!g.existeVertice("Cataluña") ? OK : ERR));

        System.out.println("existeArco(Coruña->Valladolid): " + (g.existeArco("Coruna", "Valladolid") ? OK : ERR));
        System.out.println("existeArco(Coruña->Oviedo): " + (!g.existeArco("Coruna", "Oviedo") ? OK : ERR));

        System.out.println("existeCamino(Valencia->Oviedo): " + (g.existeCamino("Valencia", "Oviedo") ? OK : ERR));
        System.out.println("existeCamino(Granada->Zaragoza): " + (!g.existeCamino("Granada", "Zaragoza") ? OK : ERR));

        /*--------------------------------------------------------------------
         * 3.  Caminos
         *------------------------------------------------------------------*/
        System.out.println("\nCamino mas corto Coruña->Murcia (BFS): " + g.caminoMasCorto("Coruna", "Murcia").toString());
        System.out.println("Camino mas largo Coruña->Murcia (DFS): " + g.caminoMasLargo("Coruna", "Murcia").toString());

        /*--------------------------------------------------------------------
         * 4.  Recorridos
         *------------------------------------------------------------------*/
        System.out.println("\nRecorrido en profundidad: " + g.listarEnProfundidad().toString());
        System.out.println("Recorrido en anchura    : " + g.listarEnAnchura().toString());

        /*--------------------------------------------------------------------
         * 5.  Clonado
         *------------------------------------------------------------------*/
        Grafo gClon = g.clone();
        System.out.println("\nClono el grafo. toString() del clon debe coincidir:");
        System.out.println(gClon.toString().equals(g.toString()) ? OK : ERR);

        gClon.eliminarArco("Valladolid", "Bilbao");
        boolean modOk = !gClon.toString().equals(g.toString())
                && g.existeArco("Valladolid", "Bilbao");
        System.out.println("Elimine arco Valladolid->Bilbao en CLON; original intacto: " + (modOk ? OK : ERR));

        /*--------------------------------------------------------------------
         * 6.  Eliminaciones
         *------------------------------------------------------------------*/
        g.eliminarArco("Granada", "Sevilla");
        System.out.println("\nElimino arco Granada->Sevilla; existeArco(Granada->Sevilla) debe ser false -> "
                + (!g.existeArco("Granada", "Sevilla") ? OK : ERR));

        g.eliminarVertice("Madrid");
        System.out.println("\nElimino vertice Madrid; existeVertice(Madrid) debe ser false -> "
                + (!g.existeVertice("Madrid") ? OK : ERR));

        System.out.println("\n** toString() real **");
        System.out.println(g.toString());

        System.out.println("existeCamino(Badajoz->Sevilla): " + (!g.existeCamino("Badajoz", "Sevilla") ? OK : ERR));

        /*--------------------------------------------------------------------
         * 3.  Caminos - 2da parte
         *------------------------------------------------------------------*/
        System.out.println("\nCamino mas corto Vigo->Cadiz (BFS): " + g.caminoMasCorto("Vigo", "Cadiz").toString());
        System.out.println("Camino mas largo Vigo->Cadiz (DFS): " + g.caminoMasLargo("Vigo", "Cadiz").toString());

        /*--------------------------------------------------------------------
         * 7.  Vaciar
         *------------------------------------------------------------------*/
        g.vaciar();
        System.out.println("\nTras vaciar, esVacio(): " + (g.esVacio() ? OK : ERR));

        System.out.println(BG_Y + CY + "\n************  Fin de TestGrafo  ************" + RESET);
    }
}