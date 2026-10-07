# Diagrama de clases

Un diagrama de clases muestra las clases del programa, sus atributos, sus métodos y cómo se relacionan. Este documento describe el código real de la microclase: el paquete `arboles`, organizado en las capas modelo, negocio y app, y el paquete `reto`, que usa esas clases sin modificarlas.

Los tres diagramas usan la sintaxis de Mermaid. GitHub los dibuja al abrir este archivo.

## Vista general de paquetes

Solo aparecen las clases y las flechas de uso. Los atributos y los métodos están en los diagramas de cada paquete.

```mermaid
classDiagram
    direction TB
    namespace arboles_modelo {
        class Nodo
    }
    namespace arboles_negocio {
        class ArbolBinario
    }
    namespace arboles_app {
        class Main
        class VistaArbol
    }
    namespace reto {
        class MainReto
    }
    namespace reto_solucion {
        class MainRetoSolucion
    }
    namespace reto_pruebas {
        class PruebasArbol
    }
    ArbolBinario ..> Nodo : usa
    Main ..> ArbolBinario : usa
    Main ..> Nodo : usa
    Main ..> VistaArbol : usa
    VistaArbol ..> ArbolBinario : usa
    VistaArbol ..> Nodo : usa
    MainReto ..> ArbolBinario : usa
    MainReto ..> Nodo : usa
    MainReto ..> VistaArbol : usa
    MainRetoSolucion ..> ArbolBinario : usa
    MainRetoSolucion ..> Nodo : usa
    MainRetoSolucion ..> VistaArbol : usa
    PruebasArbol ..> MainRetoSolucion : usa
    PruebasArbol ..> ArbolBinario : usa
    PruebasArbol ..> Nodo : usa
    PruebasArbol ..> VistaArbol : usa
```

`arboles_modelo` es el paquete `arboles.modelo`, `arboles_negocio` es `arboles.negocio` y `arboles_app` es `arboles.app`. `reto_solucion` es `reto.solucion` y `reto_pruebas` es `reto.pruebas`.

La flecha punteada significa que la clase de origen usa a la clase de destino. `ArbolBinario` usa a `Nodo`. `Main` y `VistaArbol` usan a `ArbolBinario`, a `Nodo` y, en el caso de `Main`, también a `VistaArbol`. Las tres clases del paquete `reto` usan el paquete `arboles` y no lo modifican. `PruebasArbol` además usa a `MainRetoSolucion` para comprobar el árbol A, el árbol B y `esCadena`. `MainReto` importa `VistaArbol` para que el estudiante pueda dibujar su árbol, aunque la plantilla todavía no lo llama.

## Paquete arboles

Clases de la clase, con atributos, métodos y visibilidad. `+` es público y `-` es privado. `Nodo~T~` y `ArbolBinario~T~` son las clases genéricas `Nodo<T>` y `ArbolBinario<T>`.

```mermaid
classDiagram
    direction TB
    namespace modelo {
        class Nodo~T~ {
            -T dato
            -Nodo~T~ izquierdo
            -Nodo~T~ derecho
            +Nodo(T dato)
            +T getDato()
            +void setDato(T dato)
            +Nodo~T~ getIzquierdo()
            +void setIzquierdo(Nodo~T~ izquierdo)
            +Nodo~T~ getDerecho()
            +void setDerecho(Nodo~T~ derecho)
            +boolean esHoja()
            +int grado()
        }
    }
    namespace negocio {
        class ArbolBinario~T~ {
            -Nodo~T~ raiz
            +ArbolBinario()
            +boolean esVacio()
            +Nodo~T~ getRaiz()
            +Nodo~T~ crearRaiz(T dato)
            +Nodo~T~ agregarIzquierdo(Nodo~T~ padre, T dato)
            +Nodo~T~ agregarDerecho(Nodo~T~ padre, T dato)
            +int contarNodos()
            +int contarHojas()
            +int altura()
            -int contarNodos(Nodo~T~ nodo)
            -int contarHojas(Nodo~T~ nodo)
            -int altura(Nodo~T~ nodo)
        }
    }
    namespace app {
        class Main {
            +void main(String[] args)
            -void titulo(String texto)
        }
        class VistaArbol {
            -boolean UNICODE
            -String RAMA
            -String ULTIMA
            -String BARRA
            -String ESPACIO
            +void mostrar(ArbolBinario~T~ arbol)
            -void dibujarHijos(Nodo~T~ nodo, String prefijo)
        }
    }
    Nodo~T~ "1" --> "0..1" Nodo~T~ : izquierdo
    Nodo~T~ "1" --> "0..1" Nodo~T~ : derecho
    ArbolBinario~T~ "1" --> "0..1" Nodo~T~ : raiz
    Main ..> ArbolBinario~T~ : usa
    Main ..> Nodo~T~ : usa
    Main ..> VistaArbol : usa
    VistaArbol ..> ArbolBinario~T~ : usa
    VistaArbol ..> Nodo~T~ : usa
```

La capa modelo es `Nodo`. Guarda un dato y dos referencias, `izquierdo` y `derecho`, cada una con multiplicidad 0..1 porque un hijo puede no existir. La capa negocio es `ArbolBinario`. Tiene una raíz, también 0..1, y ofrece las operaciones de construcción y de consulta. `contarNodos`, `contarHojas` y `altura` tienen un método público sin parámetros y un método privado recursivo que recibe el nodo. La capa app es `Main`, que arma el ejemplo UIO, GYE, MEC, CUE, LOH y ESM, y `VistaArbol`, que lo dibuja en consola.

`VistaArbol` no es genérica. El parámetro de tipo está en `mostrar` y en `dibujarHijos`. `UNICODE` vale `false`, así que `RAMA`, `ULTIMA`, `BARRA` y `ESPACIO` quedan con el estilo ASCII (`+-- `, la última rama, `|   ` y cuatro espacios). `Main` y `VistaArbol` dependen de `ArbolBinario`. También usan `Nodo`, porque `Main` guarda los nodos que devuelven `crearRaiz` y `agregarIzquierdo`, y `VistaArbol` recorre los hijos.

## Paquete reto

Métodos principales de la plantilla, de la solución y de las pruebas, y su uso del paquete `arboles`.

```mermaid
classDiagram
    direction TB
    namespace reto {
        class MainReto {
            +void main(String[] args)
            ~boolean esCadena(ArbolBinario~String~ arbol)
        }
    }
    namespace solucion {
        class MainRetoSolucion {
            +ArbolBinario~String~ construirArbolA()
            +ArbolBinario~String~ construirArbolB()
            +boolean esCadena(ArbolBinario~String~ arbol)
            +void main(String[] args)
        }
    }
    namespace pruebas {
        class PruebasArbol {
            -int pasaron
            -int fallaron
            +void main(String[] args)
            -void pruebasNodo()
            -void pruebasArbolVacio()
            -void pruebasArbolUnNodo()
            -void pruebasArbolDemo()
            -void pruebasErrores()
            -void pruebasVista()
            -void pruebasReto()
        }
    }
    class ArbolBinario~T~
    class Nodo~T~
    class VistaArbol
    MainReto ..> ArbolBinario~T~ : usa
    MainReto ..> Nodo~T~ : usa
    MainReto ..> VistaArbol : usa
    MainRetoSolucion ..> ArbolBinario~T~ : usa
    MainRetoSolucion ..> Nodo~T~ : usa
    MainRetoSolucion ..> VistaArbol : usa
    PruebasArbol ..> MainRetoSolucion : usa
    PruebasArbol ..> ArbolBinario~T~ : usa
    PruebasArbol ..> Nodo~T~ : usa
    PruebasArbol ..> VistaArbol : usa
```

`MainReto` es la plantilla. Crea el árbol A y el árbol B vacíos, y su `esCadena` devuelve `false`. El método no lleva `public`: en el diagrama el `~` indica visibilidad de paquete. `MainRetoSolucion` construye los dos árboles del enunciado y en `esCadena` devuelve `false` si el árbol está vacío. Si no, compara `altura()` con `contarNodos() - 1`. `PruebasArbol` ejecuta siete grupos de pruebas y cuenta cuántas pasan y cuántas fallan. Si falla alguna, `main` termina con código 1. Los métodos privados `verificar`, `lanza`, `capturar` y `arbolDemo` apoyan esas pruebas y no cambian el diseño de las clases.

## Responsabilidad de cada clase

| Clase | Paquete principal | Capa | Responsabilidad |
|---|---|---|---|
| `Nodo` | arboles | modelo | Guardar un dato y las referencias a su hijo izquierdo y a su hijo derecho |
| `ArbolBinario` | arboles | negocio | Construir el árbol y calcular la cantidad de nodos, la cantidad de hojas y la altura |
| `Main` | arboles | app | Demostrar las operaciones con códigos de aeropuerto y mostrar los errores controlados |
| `VistaArbol` | arboles | app | Dibujar el árbol en consola, con `I` para el hijo izquierdo y `D` para el derecho |
| `MainReto` | reto | no aplica | Plantilla del reto. El estudiante construye los árboles y completa `esCadena` |
| `MainRetoSolucion` | reto | no aplica | Solución del reto: arma el árbol A, arma el árbol B y decide si un árbol es una cadena |
| `PruebasArbol` | reto | no aplica | Comprobar el nodo, el árbol, los errores, el dibujo y la solución del reto |

## Cómo leer el diagrama

El rectángulo es una clase. Si el nombre lleva `~T~`, la clase es genérica: el mismo código sirve para distintos tipos de dato. El signo `+` marca un miembro público y el signo `-` un miembro privado. La flecha continua, con multiplicidad `0..1`, es una referencia que puede no existir: un nodo hacia cada hijo, y el árbol hacia su raíz. La flecha punteada indica que una clase usa a otra, por ejemplo cuando `Main` llama a `ArbolBinario`. Conviene leer primero la vista de paquetes, después las tres capas de `arboles` y al final el paquete `reto`.

## Decisiones de diseño

Hay dos paquetes porque la clase explica `arboles` y el reto debe resolverse sin cambiarlo. `reto` solo crea árboles, consulta sus operaciones y decide si el resultado es una cadena.

Hay tres capas para separar responsabilidades. `arboles.modelo` guarda el nodo. `arboles.negocio` construye el árbol y calcula. `arboles.app` demuestra y dibuja en consola. Así la regla de negocio no depende de cómo se imprime el árbol.

El tipo `T` es genérico para no atar el árbol a `String`. En la demostración y en el reto el dato es un código de aeropuerto, y el mismo `Nodo` y el mismo `ArbolBinario` podrían guardar otro tipo.

La altura se cuenta en aristas. El método privado devuelve `-1` cuando el nodo es `null`. El árbol vacío mide `-1`. El árbol de un solo nodo mide `0`, porque `1 + max(-1, -1) = 0`.
