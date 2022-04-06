package figuras;

import abstracts.FiguraGeometrica;
import interfaces.IMedidas;

public class Circulo extends FiguraGeometrica implements IMedidas {

    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularPerimetro() {
        super.perimetro = Math.PI * (this.radio * 2);
        return super.perimetro;
    }

    @Override
    public double calcularArea() {
        super.area = Math.PI * Math.pow(this.radio, 2);
        return super.area;
    }
}
