public class Rueda {
    private String marca;
    private int pulgadas;

    public Rueda() {}

    public Rueda(String marca, int pulgadas) {
        this.marca = marca;
        this.pulgadas = pulgadas;
    }

    // Getters y Setters
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getPulgadas() { return pulgadas; }
    public void setPulgadas(int pulgadas) { this.pulgadas = pulgadas; }

    @Override
    public String toString() {
        return "Rueda [marca=" + marca + ", pulgadas=" + pulgadas + "]";
    }
}
