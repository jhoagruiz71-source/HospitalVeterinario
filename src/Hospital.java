public class Hospital {
    private Paciente[] pacientes;
    private int contadorPacientes;
    private Jaula[] jaulas;

    public Hospital() {
        this.pacientes = new Paciente[10]; // Máximo 10 pacientes
        this.contadorPacientes = 0;
        this.jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales() {
        jaulas[0] = new Jaula(1, 'S', 'A');
        jaulas[1] = new Jaula(2, 'M', 'A');
        jaulas[2] = new Jaula(3, 'M', 'A');
        jaulas[3] = new Jaula(4, 'L', 'A');
        jaulas[4] = new Jaula(5, 'S', 'B');
        jaulas[5] = new Jaula(6, 'M', 'B');

        pacientes[0] = new Perro("Rocky", 30.0, 4, "labrador", true);
        pacientes[1] = new Gato("Misifu", 4.0, 3);
        pacientes[2] = new AveExotica("Kiwi", 0.8, 2, 70.0);
        contadorPacientes = 3;
    }

    public Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String ingresarPerro(String nombre, double peso, int edad, String raza, String vacunaStr) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 80 || edad < 0 || edad > 30) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) return "ERROR: nombre duplicado";
        if (contadorPacientes >= 10) return "ERROR: hospital lleno";

        boolean vacunado = vacunaStr.equalsIgnoreCase("si");
        Perro nuevoPerro = new Perro(nombre, peso, edad, raza, vacunado);
        pacientes[contadorPacientes++] = nuevoPerro;

        if (!vacunado) return "OK: " + nombre + " ingresado en CUARENTENA";
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15 || edad < 0 || edad > 30) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) return "ERROR: nombre duplicado";
        if (contadorPacientes >= 10) return "ERROR: hospital lleno";

        pacientes[contadorPacientes++] = new Gato(nombre, peso, edad);
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        if (nombre == null || nombre.trim().isEmpty() || peso <= 0 || peso > 15 || edad < 0 || edad > 30 || envergadura <= 0) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) return "ERROR: nombre duplicado";
        if (contadorPacientes >= 10) return "ERROR: hospital lleno";

        pacientes[contadorPacientes++] = new AveExotica(nombre, peso, edad, envergadura);
        return "OK: " + nombre + " ingresado";
    }

    public String vacunar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) return "ERROR: paciente no encontrado";
        if (!(p instanceof Perro)) return "ERROR: solo aplica a perros";
        Perro perro = (Perro) p;
        if (perro.isVacunado()) return "ERROR: ya vacunado";

        perro.setVacunado(true);
        perro.setEstado("INGRESADO");
        return "OK: " + nombre + " vacunado (pasa a INGRESADO)";
    }

    public String asignar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) return "ERROR: paciente no encontrado";
        if (p.getEstado().equals("CUARENTENA")) return "ERROR: paciente en cuarentena";
        if (p.getJaulaAsignada() != null) return "ERROR: paciente ya tiene jaula";

        char tamanhoNecesario = 'S';
        if (p.getPeso() >= 5 && p.getPeso() <= 25) tamanhoNecesario = 'M';
        else if (p.getPeso() > 25) tamanhoNecesario = 'L';

        if (p instanceof AveExotica) {
            AveExotica ave = (AveExotica) p;
            if (ave.getEnvergadura() > 60 && tamanhoNecesario == 'S') tamanhoNecesario = 'M';
        }

        char zonaPermitida = (p instanceof AveExotica) ? 'B' : 'A';

        Jaula jaulaSeleccionada = null;
        for (Jaula j : jaulas) {
            if (j.estaLibre() && j.getZona() == zonaPermitida) {
                if (cumpleTamanho(j.getTamanho(), tamanhoNecesario)) {
                    jaulaSeleccionada = j;
                    break;
                }
            }
        }

        if (jaulaSeleccionada == null) return "ERROR: no hay jaula compatible";

        p.setJaulaAsignada(jaulaSeleccionada);
        jaulaSeleccionada.setOcupante(p);
        return "OK: " + nombre + " asignado a jaula " + jaulaSeleccionada.getNumero();
    }

    private boolean cumpleTamanho(char jaulaTam, char reqTam) {
        if (jaulaTam == reqTam) return true;
        if (jaulaTam == 'L' && (reqTam == 'M' || reqTam == 'S')) return true;
        if (jaulaTam == 'M' && reqTam == 'S') return true;
        return false;
    }

    public String medicar(String nombre, double mg) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) return "ERROR: paciente no encontrado";
        if (mg <= 0) return "ERROR: datos invalidos";
        if (p.getEstado().equals("CUARENTENA")) return "ERROR: paciente en cuarentena";
        if (p.getJaulaAsignada() == null) return "ERROR: paciente sin jaula";

        if (p instanceof Gato) {
            Gato gato = (Gato) p;
            if (gato.getNivelEstres() >= 8) return "ERROR: gato con estres alto";
        }

        double dosisMaxima = p.getPeso() * p.getFactorDosis();
        if (mg > dosisMaxima) return "ERROR: dosis excede el maximo";

        double topeAcumulado = 3 * dosisMaxima;
        if (p.getDosisAcumulada() + mg > topeAcumulado) return "ERROR: tope acumulado superado";

        p.setDosisAcumulada(p.getDosisAcumulada() + mg);
        p.setEstado("EN_TRATAMIENTO");

        if (p instanceof Gato) {
            Gato gato = (Gato) p;
            gato.setNivelEstres(Math.min(10, gato.getNivelEstres() + 2));
        }

        return "OK: " + nombre + " medicado con " + (int)mg + " mg";
    }

    public String alta(String nombre) {
        int index = -1;
        Paciente p = null;
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                p = pacientes[i];
                index = i;
                break;
            }
        }

        if (p == null) return "ERROR: paciente no encontrado";
        if (!p.getEstado().equals("EN_TRATAMIENTO")) return "ERROR: paciente no apto para alta";

        if (p.getJaulaAsignada() != null) {
            p.getJaulaAsignada().setOcupante(null);
            p.setJaulaAsignada(null);
        }

        // Compactación del vector
        for (int i = index; i < contadorPacientes - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }
        pacientes[--contadorPacientes] = null;

        return "OK: " + nombre + " dado de alta";
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE\n");
        sb.append("Pacientes: ").append(contadorPacientes).append("/10\n");
        for (int i = 0; i < contadorPacientes; i++) {
            Paciente p = pacientes[i];
            String tipo = (p instanceof Perro) ? "Perro" : (p instanceof Gato) ? "Gato" : "Ave";
            String jaulaStr = (p.getJaulaAsignada() != null) ? String.valueOf(p.getJaulaAsignada().getNumero()) : "-";
            sb.append(p.getNombre()).append(" | ").append(tipo).append(" | ")
                    .append(String.format("%.1f", p.getPeso())).append(" kg | ")
                    .append(p.getEstado()).append(" | jaula: ").append(jaulaStr).append("\n");
        }
        int jaulasLibres = 0;
        for (Jaula j : jaulas) {
            if (j.estaLibre()) jaulasLibres++;
        }
        sb.append("Jaulas libres: ").append(jaulasLibres);
        return sb.toString().trim();
    }
}
