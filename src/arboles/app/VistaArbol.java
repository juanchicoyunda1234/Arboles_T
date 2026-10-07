package arboles.app;

import arboles.modelo.Nodo;
import arboles.negocio.ArbolBinario;

public class VistaArbol {

    private static final boolean UNICODE = false;

    private static final String RAMA = UNICODE ? "├─ " : "+-- ";
    private static final String ULTIMA = UNICODE ? "└─ " : "`-- ";
    private static final String BARRA = UNICODE ? "│  " : "|   ";
    private static final String ESPACIO = UNICODE ? "   " : "    ";

    public static <T> void mostrar(ArbolBinario<T> arbol) {
        if (arbol.esVacio()) {
            System.out.println("(árbol vacío)");
            return;
        }
        System.out.println(arbol.getRaiz().getDato());
        dibujarHijos(arbol.getRaiz(), "");
    }

    private static <T> void dibujarHijos(Nodo<T> nodo, String prefijo) {
        Nodo<T> izquierdo = nodo.getIzquierdo();
        Nodo<T> derecho = nodo.getDerecho();
        if (izquierdo != null) {
            boolean ultimo = derecho == null;
            System.out.println(prefijo + (ultimo ? ULTIMA : RAMA) + "I: " + izquierdo.getDato());
            dibujarHijos(izquierdo, prefijo + (ultimo ? ESPACIO : BARRA));
        }
        if (derecho != null) {
            System.out.println(prefijo + ULTIMA + "D: " + derecho.getDato());
            dibujarHijos(derecho, prefijo + ESPACIO);
        }
    }
}
