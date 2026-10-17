public class Gato extends Paciente {
    private int nivelEstres;

    public Gato(String nombre, double peso, int edad) {
        super(nombre, peso, edad, "INGRESADO", 6.0);
        this.nivelEstres = 0;
    }

    public int getNivelEstres() { return nivelEstres; }
    public void setNivelEstres(int nivelEstres) { this.nivelEstres = nivelEstres; }
}
