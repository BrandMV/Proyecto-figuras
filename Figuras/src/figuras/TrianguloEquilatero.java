package figuras;

import abstracts.Triangulo;
import interfaces.IMedidas;

public class TrianguloEquilatero extends Triangulo implements IMedidas {

    private double lado;

    public TrianguloEquilatero(double lado) {
        this.lado = lado;
        super.altura = this.calcularAltura();
    }

    @Override
    public double calcularAltura() {
        super.altura = (Math.sqrt(3) * this.lado) / 2;
        return super.altura;
    }

    @Override
    public double calcularPerimetro() {
        super.perimetro = this.lado * 3;
        return super.perimetro;
    }

    @Override
    public double calcularArea() {
        super.area = super.calcularArea(this.lado);
        return super.area;
    }
}
