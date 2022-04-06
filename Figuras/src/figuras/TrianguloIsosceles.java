package figuras;

import abstracts.Triangulo;
import interfaces.IMedidas;

public class TrianguloIsosceles extends Triangulo implements IMedidas {

    private double lado;
    private double base;

    public TrianguloIsosceles(double lado, double base) {
        this.lado = lado;
        this.base = base;
        super.altura = this.calcularAltura();
    }

    @Override
    public double calcularAltura() {
        super.altura = Math.sqrt(Math.pow(this.lado, 2) - (Math.pow(this.base, 2) / 4));
        return this.altura;
    }

    @Override
    public double calcularPerimetro() {
        super.perimetro = (this.lado * 2) + this.base;
        return super.perimetro;
    }

    @Override
    public double calcularArea() {
        super.area = super.calcularArea(this.base);
        return super.area;
    }
}
