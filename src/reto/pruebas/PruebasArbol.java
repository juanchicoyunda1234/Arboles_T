package reto.pruebas;

import arboles.app.VistaArbol;
import arboles.modelo.Nodo;
import arboles.negocio.ArbolBinario;
import reto.solucion.MainRetoSolucion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PruebasArbol {

    private static int pasaron = 0;
    private static int fallaron = 0;

    private static void verificar(String nombre, boolean condicion) {
        if (condicion) {
            pasaron++;
        } else {
            fallaron++;
            System.out.println("FALLA: " + nombre);
        }
    }

    private static boolean lanza(Runnable accion, Class<? extends RuntimeException> tipo) {
        try {
            accion.run();
            return false;
        } catch (RuntimeException e) {
            return tipo.isInstance(e);
        }
    }

    private static String capturar(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            accion.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }

    public static void main(String[] args) {
        pruebasNodo();
        pruebasArbolVacio();
        pruebasArbolUnNodo();
        pruebasArbolDemo();
        pruebasErrores();
        pruebasVista();
        pruebasReto();
        System.out.println("pasaron: " + pasaron + ", fallaron: " + fallaron);
        if (fallaron > 0) {
            System.exit(1);
        }
    }

    private static void pruebasNodo() {
        Nodo<String> n = new Nodo<>("A");
        verificar("nodo nuevo es hoja", n.esHoja());
        verificar("nodo nuevo grado 0", n.grado() == 0);
        verificar("dato", n.getDato().equals("A"));
        n.setDato("Z");
        verificar("setDato", n.getDato().equals("Z"));
        n.setIzquierdo(new Nodo<>("B"));
        verificar("con hijo izquierdo no es hoja", !n.esHoja());
        verificar("grado 1 solo izquierdo", n.grado() == 1);
        n.setDerecho(new Nodo<>("C"));
        verificar("grado 2", n.grado() == 2);
        verificar("getIzquierdo", n.getIzquierdo().getDato().equals("B"));
        verificar("getDerecho", n.getDerecho().getDato().equals("C"));
        Nodo<String> m = new Nodo<>("M");
        m.setDerecho(new Nodo<>("N"));
        verificar("grado 1 solo derecho", m.grado() == 1);
    }

    private static void pruebasArbolVacio() {
        ArbolBinario<String> a = new ArbolBinario<>();
        verificar("vacío esVacio", a.esVacio());
        verificar("vacío raíz null", a.getRaiz() == null);
        verificar("vacío contarNodos 0", a.contarNodos() == 0);
        verificar("vacío contarHojas 0", a.contarHojas() == 0);
        verificar("vacío altura -1", a.altura() == -1);
    }

    private static void pruebasArbolUnNodo() {
        ArbolBinario<String> a = new ArbolBinario<>();
        Nodo<String> r = a.crearRaiz("UIO");
        verificar("crearRaiz devuelve la raíz", r == a.getRaiz());
        verificar("un nodo no vacío", !a.esVacio());
        verificar("un nodo contarNodos 1", a.contarNodos() == 1);
        verificar("un nodo contarHojas 1", a.contarHojas() == 1);
        verificar("un nodo altura 0", a.altura() == 0);
        verificar("raíz es hoja", r.esHoja());
    }

    private static ArbolBinario<String> arbolDemo() {
        ArbolBinario<String> a = new ArbolBinario<>();
        Nodo<String> uio = a.crearRaiz("UIO");
        Nodo<String> gye = a.agregarIzquierdo(uio, "GYE");
        Nodo<String> mec = a.agregarDerecho(uio, "MEC");
        a.agregarIzquierdo(gye, "CUE");
        a.agregarDerecho(gye, "LOH");
        a.agregarDerecho(mec, "ESM");
        return a;
    }

    private static void pruebasArbolDemo() {
        ArbolBinario<String> a = arbolDemo();
        verificar("demo contarNodos 6", a.contarNodos() == 6);
        verificar("demo contarHojas 3", a.contarHojas() == 3);
        verificar("demo altura 2", a.altura() == 2);
        verificar("demo grado raíz 2", a.getRaiz().grado() == 2);
        verificar("demo MEC grado 1", a.getRaiz().getDerecho().grado() == 1);
        verificar("demo GYE no es hoja", !a.getRaiz().getIzquierdo().esHoja());
    }

    private static void pruebasErrores() {
        ArbolBinario<String> a = new ArbolBinario<>();
        Nodo<String> r = a.crearRaiz("UIO");
        verificar("crearRaiz dos veces", lanza(() -> a.crearRaiz("OTRO"), IllegalStateException.class));
        a.agregarIzquierdo(r, "GYE");
        a.agregarDerecho(r, "MEC");
        verificar("izquierdo ocupado", lanza(() -> a.agregarIzquierdo(r, "X"), IllegalStateException.class));
        verificar("derecho ocupado", lanza(() -> a.agregarDerecho(r, "X"), IllegalStateException.class));
        verificar("padre null izquierdo", lanza(() -> a.agregarIzquierdo(null, "X"), IllegalArgumentException.class));
        verificar("padre null derecho", lanza(() -> a.agregarDerecho(null, "X"), IllegalArgumentException.class));
        verificar("el error no cambia el árbol", a.contarNodos() == 3);
        try {
            a.agregarIzquierdo(r, "X");
        } catch (IllegalStateException e) {
            verificar("mensaje del error", e.getMessage().equals("el lugar izquierdo de UIO ya está ocupado"));
        }
    }

    private static void pruebasVista() {
        String esperado = String.join("\n",
                "UIO",
                "+-- I: GYE",
                "|   +-- I: CUE",
                "|   `-- D: LOH",
                "`-- D: MEC",
                "    `-- D: ESM",
                "");
        String salida = capturar(() -> VistaArbol.mostrar(arbolDemo()));
        verificar("vista del árbol demo", salida.equals(esperado));
        String vacio = capturar(() -> VistaArbol.mostrar(new ArbolBinario<String>()));
        verificar("vista del árbol vacío", vacio.equals("(árbol vacío)\n"));
        ArbolBinario<String> soloDerecho = new ArbolBinario<>();
        Nodo<String> r = soloDerecho.crearRaiz("A");
        soloDerecho.agregarDerecho(r, "B");
        String lado = capturar(() -> VistaArbol.mostrar(soloDerecho));
        verificar("vista con solo hijo derecho", lado.equals("A\n`-- D: B\n"));
    }

    private static void pruebasReto() {
        ArbolBinario<String> a = MainRetoSolucion.construirArbolA();
        verificar("A nodos 8", a.contarNodos() == 8);
        verificar("A hojas 4", a.contarHojas() == 4);
        verificar("A altura 3", a.altura() == 3);
        verificar("A grado LTX 2", a.getRaiz().grado() == 2);
        verificar("A grado SNC 1", a.getRaiz().getDerecho().getIzquierdo().grado() == 1);
        verificar("A no es cadena", !MainRetoSolucion.esCadena(a));
        String vistaA = capturar(() -> VistaArbol.mostrar(a));
        String esperadoA = String.join("\n",
                "LTX",
                "+-- I: ATF",
                "|   +-- I: TUA",
                "|   `-- D: IBB",
                "`-- D: MCH",
                "    +-- I: SNC",
                "    |   `-- I: LGQ",
                "    `-- D: OCC",
                "");
        verificar("vista de A", vistaA.equals(esperadoA));

        ArbolBinario<String> b = MainRetoSolucion.construirArbolB();
        verificar("B nodos 5", b.contarNodos() == 5);
        verificar("B hojas 1", b.contarHojas() == 1);
        verificar("B altura 4", b.altura() == 4);
        verificar("B es cadena", MainRetoSolucion.esCadena(b));
        String vistaB = capturar(() -> VistaArbol.mostrar(b));
        String esperadoB = String.join("\n",
                "GPS",
                "`-- D: SCY",
                "    `-- D: MRR",
                "        `-- D: PTZ",
                "            `-- D: TPN",
                "");
        verificar("vista de B", vistaB.equals(esperadoB));

        verificar("vacío no es cadena", !MainRetoSolucion.esCadena(new ArbolBinario<String>()));
        ArbolBinario<String> uno = new ArbolBinario<>();
        uno.crearRaiz("UIO");
        verificar("un nodo es cadena", MainRetoSolucion.esCadena(uno));
    }
}
