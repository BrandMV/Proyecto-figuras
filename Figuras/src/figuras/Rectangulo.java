package figuras;

import abstracts.FiguraGeometrica;
import interfaces.IMedidas;

public class Rectangulo extends FiguraGeometrica implements IMedidas {

    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularPerimetro() {
        super.perimetro = (this.base * 2) + (this.altura * 2);
        return super.perimetro;
    }

    @Override
    public double calcularArea() {
        super.area = this.base * this.altura;
        return super.area;
    }
}
