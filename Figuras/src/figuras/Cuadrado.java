package figuras;

import abstracts.FiguraGeometrica;
import interfaces.IMedidas;

public class Cuadrado extends FiguraGeometrica implements IMedidas {

    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularPerimetro() {
        super.perimetro = this.lado * 4;
        return super.perimetro;
    }

    @Override
    public double calcularArea() {
        super.area = Math.pow(this.lado, 2);
        return super.area;
    }

}
