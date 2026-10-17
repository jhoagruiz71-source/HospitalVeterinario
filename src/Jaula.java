public class Jaula {
    private int numero;
    private char tamano; // 'S', 'M', 'L'
    private char zona;    // 'A', 'B'
    private Paciente ocupante;

    public Jaula(int numero, char tamano, char zona) {
        this.numero = numero;
        this.tamano = tamano;
        this.zona = zona;
        this.ocupante = null;
    }

    public int getNumero() { return numero; }
    public char getTamano() { return tamano; }
    public char getZona() { return zona; }
    public Paciente getOcupante() { return ocupante; }
    public void setOcupante(Paciente ocupante) { this.ocupante = ocupante; }

    public boolean estaLibre() {
        return this.ocupante == null;
    }
}
