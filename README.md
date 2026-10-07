# Árboles binarios: Grupo 1

Microclase "Introducción a los árboles" de Estructura de Datos (Unidad 3), Universidad Técnica de Ambato, Facultad de Ingeniería en Sistemas, Electrónica e Industrial (FISEI), Nivel III, Paralelo B.

Fecha de la exposición: jueves 8 de octubre de 2026. Duración: 75 minutos.

El repositorio reúne el código base en Java organizado por capas (modelo, negocio, app), el reto práctico para el curso, las pruebas, los diagramas y las evidencias de la clase.

## Equipo

| Integrante | Usuario GitHub | Responsabilidad | Entrega en el repositorio | Estado |
|---|---|---|---|---|
| Chico Yunda Juan Carlos (líder) | juanchicoyunda1234 | Liderazgo, integración final, diagramas y README | `README.md`, `.gitignore`, `docs/DIAGRAMA-CLASES.md`, `docs/DIAGRAMA-SECUENCIA.md`, `docs/DIAGRAMAS-FLUJO.md` | Pendiente |
| Torosina Armendariz Jeremy | WinoSpop | Presentación, quiz interactivo y material usado | `presentacion/` (PPTX y PDF), `docs/MATERIAL-USADO.md` | Pendiente |
| Altamirano Segovia Jullisa | jullisaaltamirano2017-boop | Pruebas e informe de ejecución | `src/reto/pruebas/PruebasArbol.java`, `src/reto/solucion/MainRetoSolucion.java`, `docs/CASOS-DE-PRUEBA.md` | Pendiente |
| Romo Nunez Joseph | wayusa25-cmyk | Backend: modelo y reto | `src/arboles/modelo/Nodo.java`, `src/reto/RETO.md` | Pendiente |
| Llamuca Abrajan Andres | llamucaandres161 | Backend: negocio y banco de preguntas | `src/arboles/negocio/ArbolBinario.java`, `docs/QUIZ.md` | Pendiente |
| Tuza Quinatoa Noemi | edithtuza15-collab | Frontend: integración y consola | `src/arboles/app/Main.java`, `src/arboles/app/VistaArbol.java`, `src/reto/MainReto.java`, `docs/img/` | Pendiente |

## Enlaces

Todos los recursos usados en la clase están reunidos en [`docs/MATERIAL-USADO.md`](docs/MATERIAL-USADO.md).

| Recurso | Enlace |
|---|---|
| Diapositivas (Canva) | _pendiente_ |
| Quiz interactivo (Quizizz) | _pendiente_ |
| Visualización de árboles (VisuAlgo) | https://visualgo.net/en/bst |

## Estructura del repositorio

El código está dividido en dos paquetes principales: `arboles`, que es lo que se explica en la clase, y `reto`, que es lo que se usa en el reto práctico.

```
README.md
.gitignore
src/
  arboles/                       Lo que se explica en la clase
    modelo/     Nodo
    negocio/    ArbolBinario
    app/        Main, VistaArbol
  reto/                          Lo que se usa en el reto
    MainReto.java                Plantilla para los estudiantes
    RETO.md                      Enunciado del reto
    solucion/   MainRetoSolucion
    pruebas/    PruebasArbol
docs/
  DIAGRAMA-CLASES.md
  DIAGRAMA-SECUENCIA.md
  DIAGRAMAS-FLUJO.md
  CASOS-DE-PRUEBA.md
  QUIZ.md
  MATERIAL-USADO.md
  img/
presentacion/
  Introduccion_a_los_arboles_Grupo_1.pptx
  Introduccion_a_los_arboles_Grupo_1.pdf
```

## Arquitectura por capas

Dentro del paquete `arboles` se aplica el patrón por capas.

| Capa | Paquete | Clases | Responsabilidad |
|---|---|---|---|
| Modelo | `arboles.modelo` | `Nodo<T>` | Guardar un dato y las referencias a sus hijos izquierdo y derecho |
| Negocio | `arboles.negocio` | `ArbolBinario<T>` | Construir el árbol y calcular nodos, hojas y altura |
| App | `arboles.app` | `Main`, `VistaArbol` | Demostración y dibujo del árbol en consola |

El paquete `reto` usa las clases de `arboles` sin modificarlas.

| Paquete | Clases | Responsabilidad |
|---|---|---|
| `reto` | `MainReto` | Plantilla donde los estudiantes resuelven el reto |
| `reto.solucion` | `MainRetoSolucion` | Solución del reto, para el docente |
| `reto.pruebas` | `PruebasArbol` | Pruebas automáticas del árbol y de la solución |

## Compilar y ejecutar

Código de la clase:

```
javac -encoding UTF-8 -d out src/arboles/modelo/*.java src/arboles/negocio/*.java src/arboles/app/*.java
java -cp out arboles.app.Main
```

Reto, solución y pruebas (compila todo):

```
javac -encoding UTF-8 -d out src/arboles/modelo/*.java src/arboles/negocio/*.java src/arboles/app/*.java src/reto/*.java src/reto/solucion/*.java src/reto/pruebas/*.java
java -cp out reto.pruebas.PruebasArbol
java -cp out reto.solucion.MainRetoSolucion
java -cp out reto.MainReto
```

También se puede abrir la carpeta `src` en VS Code o IntelliJ IDEA y ejecutar `Main`.

## Dibujo del árbol en consola

`VistaArbol` dibuja con caracteres ASCII para que se vea bien en cualquier consola. Si la consola usa UTF-8 y se prefiere el estilo con líneas continuas, cambiar `UNICODE` a `true` en `VistaArbol.java`. Las pruebas esperan el estilo ASCII.

## Operaciones

| Clase | Operación |
|---|---|
| `Nodo` | `getDato`, `setDato`, `getIzquierdo`, `setIzquierdo`, `getDerecho`, `setDerecho`, `esHoja`, `grado` |
| `ArbolBinario` | `esVacio`, `getRaiz`, `crearRaiz`, `agregarIzquierdo`, `agregarDerecho`, `contarNodos`, `contarHojas`, `altura` |

La altura se cuenta en aristas: un árbol vacío tiene altura -1 y un árbol de un solo nodo tiene altura 0.

## Estructura de la clase (75 minutos)

| Fase | Minutos | Contenido |
|---|---|---|
| Activación | 5 | Pregunta gancho sobre dónde hay árboles y su relación con SmartETL_DS |
| Explicación visual | 15 | Definición, terminología, árbol general y binario, dinámica Árbol humano y demostración en VisuAlgo |
| Demostración Java | 20 | Clases `Nodo` y `ArbolBinario` por capas, un diagrama de flujo por operación y un `Main` con salida en consola |
| Reto práctico | 20 | "¿Árbol sano o árbol en cadena?" (ver `src/reto/RETO.md`) |
| Evaluación interactiva | 10 | Quiz de 5 a 8 preguntas (ver `docs/QUIZ.md`) |
| Cierre | 5 | Resumen, conexión con el Hito 4A y evidencias |

## Reto práctico

Los estudiantes construyen dos árboles con las operaciones dadas, consultan raíz, nodos, hojas y altura, y escriben `esCadena` para decidir si un árbol se comporta como una lista. No se permiten estructuras de `java.util` basadas en árboles (`TreeSet`, `TreeMap`, `PriorityQueue`, `NavigableSet`, `NavigableMap`). El enunciado completo está en `src/reto/RETO.md` y la plantilla en `src/reto/MainReto.java`. La solución está en `src/reto/solucion/MainRetoSolucion.java`.

## Documentación

| Documento | Contenido |
|---|---|
| `docs/DIAGRAMA-CLASES.md` | Diagrama de clases: vista general, paquete `arboles` (tres capas) y paquete `reto`, con explicación |
| `docs/DIAGRAMA-SECUENCIA.md` | Diagramas de secuencia de la construcción del árbol, la altura y `esCadena` |
| `docs/DIAGRAMAS-FLUJO.md` | Un diagrama de flujo por método importante, indicando capa, paquete y clase |
| `docs/CASOS-DE-PRUEBA.md` | Casos de prueba documentados y evidencia de ejecución |
| `docs/QUIZ.md` | Banco de preguntas del quiz con respuestas y explicación |
| `docs/MATERIAL-USADO.md` | Recursos de la clase reunidos en un solo lugar: Canva, VisuAlgo, Quizizz y el repositorio |

## Conexión con el proyecto final (SmartETL_DS)

En SmartETL_DS cada integrante del grupo trabajó con su propio conjunto de datos. En el caso de Juan Carlos fue el de aeropuertos, con 7.698 registros. Ese ejemplo sirve para ver la idea, que vale para cualquier dato con un identificador:

- Buscar en una lista obliga a recorrerla: en el peor caso son 7.698 comparaciones.
- En un árbol binario de búsqueda equilibrado bastan unas 13 comparaciones (el techo de log2 7.698).
- Si los identificadores se insertan en orden ascendente, el árbol se degenera en una cadena y se pierde esa ventaja.

Esa es la idea detrás del reto "¿árbol sano o árbol en cadena?" y del Hito 4A (árbol binario de búsqueda), que cada integrante aplicará a sus propios datos.

## Evidencias

- Diapositivas en `presentacion/`
- Código base en `src/`
- Ejercicio Java contextualizado con solución y casos de prueba
- Diagramas de clases, secuencia y flujo en `docs/`
- Banco de preguntas, enlace del quiz y material usado en la clase
- Commits de los seis integrantes en el historial del repositorio
