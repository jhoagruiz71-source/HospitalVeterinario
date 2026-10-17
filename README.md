# Sistema de Gestión - Hospital Veterinario (POO en Java)

## Descripción del Proyecto
Este proyecto consiste en una aplicación de consola desarrollada en Java puro (JDK 17 o superior) para administrar de manera integral los pacientes y las jaulas de una clínica veterinaria[cite: 1]. El sistema maneja diferentes tipos de animales (perros, gatos y aves exóticas), controla sus estados (ingresado, en cuarentena, en tratamiento), gestiona la asignación inteligente de jaulas según tamaño y zona, y aplica reglas estrictas de dosificación y altas médicas[cite: 1, 2].

---

## 🛠️ ¿Cómo se hizo? (Arquitectura y Decisiones de Diseño)

1. **Programación Orientada a Objetos (POO):**
    - **Herencia y Polimorfismo:** Se creó una clase base abstracta/general `Paciente` que agrupa los atributos comunes (nombre, peso, edad, estado, factor de dosis, dosis acumulada y jaula asignada)[cite: 1]. A partir de ella heredan las subclases `Perro`, `Gato` y `AveExotica`, las cuales inicializan sus factores de dosis particulares mediante `super()`.
    - **Encapsulamiento Estricto:** Todos los atributos de las clases de dominio se declararon como privados (`private`), permitiendo el acceso y la modificación únicamente a través de métodos de lectura y escritura (`getters` y `setters`) seguros[cite: 1].

2. **Manejo Estricto de Vectores Fijos (Sin librerías externas):**
    - Cumpliendo con los requerimientos técnicos, **no se utilizaron colecciones dinámicas** como `ArrayList`[cite: 1].
    - La persistencia y almacenamiento en memoria se implementó utilizando vectores de capacidad fija (`Paciente[10]` para los pacientes y `Jaula[6]` para las jaulas), apoyados por un contador de control de elementos.
    - Se implementaron algoritmos personalizados de búsqueda por nombre (ignorando mayúsculas) y de **compactación de vectores** al momento de procesar una baja médica, desplazando los elementos siguientes una posición a la izquierda y reduciendo el contador.

3. **Separación de Responsabilidades (Dominio vs. Consola):**
    - **Clase `Hospital`:** Concentra absolutamente toda la lógica de negocio, las validaciones de comandos (reglas R1 a R10), la carga de datos iniciales y la generación de reportes[cite: 1, 2].
    - **Clase `Main`:** Es la única clase autorizada para interactuar con la entrada y salida estándar (`Scanner` y `System.out`), leyendo los comandos ingresados por el usuario línea por línea e imprimiendo las respuestas exactas estipuladas[cite: 1, 2].

---

## 🚀 Compilación y Ejecución

Para compilar y ejecutar el proyecto manualmente desde la terminal:

```bash
javac src/*.java -d bin/
java -cp bin Main