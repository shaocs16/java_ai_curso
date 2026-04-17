public class Tapiceria {
    private String material; // ej: "cuero", "tela", "alcantara"
    private String color;

    public Tapiceria() {}

    public Tapiceria(String material, String color) {
        this.material = material;
        this.color = color;
    }

    // Getters y Setters
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return "Tapiceria [material=" + material + ", color=" + color + "]";
    }
}
