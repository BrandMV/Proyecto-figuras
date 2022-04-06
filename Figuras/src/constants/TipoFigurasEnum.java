package constants;

public enum TipoFigurasEnum {
    CIRCULO("Circulo",1),
    CUADRADO("Cuadrado", 2),
    RECTANGULO("Rectangulo", 3),
    TRIANGULO_EQUILATERO("Triangulo equilatero", 4),
    TRIANGULO_ISOSCELES("Triangulo isosceles", 5);

    private final String nombre;
    private final int opcion;

    TipoFigurasEnum(String nombre, int opcion) {
        this.nombre = nombre;
        this.opcion = opcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getOpcion() {
        return opcion;
    }
}