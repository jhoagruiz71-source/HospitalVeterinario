# Sistema de Gestión - Hospital Veterinario (POO en Java)

## Descripción del Proyecto
Este proyecto es una aplicación de consola en Java diseñada para administrar los pacientes (perros, gatos y aves exóticas) y el control de jaulas de un hospital veterinario.

## Arquitectura y Principios de Diseño
- **Herencia y Polimorfismo:** La clase abstracta/padre `Paciente` es heredada por `Perro`, `Gato` y `AveExotica`, permitiendo el almacenamiento polimórfico en un solo vector de pacientes.
- **Encapsulamiento:** Todos los atributos son privados (`private`) y se acceden mediante métodos de lectura/escritura (`getters` y `setters`).
- **Manejo Estricto de Memoria:** No se hicieron uso de colecciones dinámicas (`ArrayList`). La gestión se realiza exclusivamente mediante vectores fijos (`Paciente[10]` y `Jaula[6]`) con algoritmos de compactación manual al dar de alta.
- **Separación de Responsabilidades:** La interfaz de consola (`Scanner` y `System.out`) reside de forma aislada en la clase `Main`. La clase `Hospital` ejecuta la lógica de negocio.

## Compilación y Ejecución
Para compilar y ejecutar el proyecto manualmente desde la consola:

```bash
javac src/*.java -d bin/
java -cp bin Main