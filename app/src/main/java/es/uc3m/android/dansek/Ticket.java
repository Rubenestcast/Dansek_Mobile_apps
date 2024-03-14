package es.uc3m.android.dansek;

public class Ticket {
    private String id;
    private String nombre;
    private String empresa;
    private String local;
    private String dia;
    private int numeroCopas;

    public Ticket(String id, String nombre, String empresa, String local, String dia, int numeroCopas) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.local = local;
        this.dia = dia;
        this.numeroCopas = numeroCopas;
    }

    // Métodos getter y setter para acceder a los atributos de la clase

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getNumeroCopas() {
        return numeroCopas;
    }

    public void setNumeroCopas(int numeroCopas) {
        this.numeroCopas = numeroCopas;
    }
}
