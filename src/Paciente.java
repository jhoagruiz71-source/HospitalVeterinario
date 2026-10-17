public class Paciente {
    private String nombre;
    private double peso;
    private int edad;
    private String estado; // INGRESADO, CUARENTENA, EN_TRATAMIENTO
    private double factorDosis;
    private double dosisAcumulada;
    private Jaula jaulaAsignada;

    public Paciente(String nombre, double peso, int edad, String estado, double factorDosis) {
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
        this.estado = estado;
        this.factorDosis = factorDosis;
        this.dosisAcumulada = 0.0;
        this.jaulaAsignada = null;
    }

    public String getNombre() { return nombre; }
    public double getPeso() { return peso; }
    public int getEdad() { return edad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getFactorDosis() { return factorDosis; }
    public double getDosisAcumulada() { return dosisAcumulada; }
    public void setDosisAcumulada(double dosisAcumulada) { this.dosisAcumulada = dosisAcumulada; }
    public Jaula getJaulaAsignada() { return jaulaAsignada; }
    public void setJaulaAsignada(Jaula jaulaAsignada) { this.jaulaAsignada = jaulaAsignada; }
}