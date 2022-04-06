package abstracts;

public abstract class Triangulo extends FiguraGeometrica {

    protected double altura;

    public abstract double calcularAltura();

    protected double calcularArea(double lado) {
        return (lado * this.altura) / 2;
    };
}