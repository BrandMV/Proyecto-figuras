import archivos.AperturaArchivo;
import archivos.Archivo;
import constants.ArchivoConstants;
import constants.Mensajes;
import constants.TipoFigurasEnum;
import figuras.*;
import interfaces.IMedidas;


import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {

   public static void main(String[] args) {

       boolean salir = false;
       do {
           int operacionMenu = Integer.parseInt(JOptionPane.showInputDialog("1 - Registrar nuevo cálculo de figura\n2 - Abrir archivo con cálculos generados\n3 - Salir"));
           switch (operacionMenu){
               case 1:
                   calculoFigura();
                   break;
               case 2:
                   abrirCalculos();
                   break;
               case 3:
                   System.out.println("Saliendo del programa...");
                   salir = true;
                   break;
               default:
                   break;
           }
       }while(!salir);
    }

    public static void calculoFigura(){
        String nombre;
        String datos;
        Archivo archivo = null;
        StringBuilder sb = new StringBuilder(Mensajes.INGRESO_OPCION_FIGURA);
        TipoFigurasEnum[] figuras = TipoFigurasEnum.values();

        for(TipoFigurasEnum f : figuras) {
            sb.append(String.format(Mensajes.FORMATO_OPCIONES_FIGURAS, f.getOpcion(), f.getNombre()));
        }
        int nombreFigura = Integer.parseInt(JOptionPane.showInputDialog(sb.toString()));
        TipoFigurasEnum figura  = getNombreFigura(nombreFigura);
        IMedidas medidas = null;

        switch (figura) {
            case CIRCULO:
                double radio = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_RADIO, Mensajes.UNIDAD_MEDIDA_CM)));
                medidas = new Circulo(radio);
                double diametro = radio*2;
                datos = String.format(Mensajes.CIRCULO_DATOS,figura.getNombre(), diametro, radio, medidas.calcularArea(),medidas.calcularPerimetro());
                nombre = prepararArchivo(figura, medidas);
                archivo = new Archivo(nombre, datos);
                break;

            case CUADRADO:
                double lado = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_LADO, Mensajes.UNIDAD_MEDIDA_CM)));
                medidas = new Cuadrado(lado);
                datos = String.format(Mensajes.CUADRADO_DATOS,figura.getNombre(), lado, medidas.calcularArea(),medidas.calcularPerimetro());
                nombre = prepararArchivo(figura, medidas);
                archivo = new Archivo(nombre, datos);
                break;

            case RECTANGULO:
                double base = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_BASE, Mensajes.UNIDAD_MEDIDA_CM)));
                double altura = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_ALTURA, Mensajes.UNIDAD_MEDIDA_CM)));
                medidas = new Rectangulo(base, altura);
                datos = String.format(Mensajes.RECTANGULO_DATOS,figura.getNombre(), base, altura, medidas.calcularArea(),medidas.calcularPerimetro());
                nombre = prepararArchivo(figura, medidas);
                archivo = new Archivo(nombre, datos);
                break;

            case TRIANGULO_EQUILATERO:
                lado = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_LADO, Mensajes.UNIDAD_MEDIDA_CM)));
                altura = (Math.sqrt(3) * lado) / 2;
                medidas = new TrianguloEquilatero(lado);
                datos = String.format(Mensajes.TRIANGULO_EQUILATERO_DATOS,figura.getNombre(), lado, altura, medidas.calcularArea(),medidas.calcularPerimetro());
                nombre = prepararArchivo(figura, medidas);
                archivo = new Archivo(nombre, datos);
                break;

            case TRIANGULO_ISOSCELES:
                lado = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_LADO, Mensajes.UNIDAD_MEDIDA_CM)));
                base = Double.parseDouble(JOptionPane.showInputDialog(String.format(Mensajes.INGRESO_BASE, Mensajes.UNIDAD_MEDIDA_CM)));
                altura = Math.sqrt(Math.pow(lado, 2) - (Math.pow(base, 2) / 4));
                medidas = new TrianguloIsosceles(lado, base);
                datos = String.format(Mensajes.TRIANGULO_ISOSCELES_DATOS,figura.getNombre(), lado, base, altura, medidas.calcularArea(),medidas.calcularPerimetro());
                nombre = prepararArchivo(figura, medidas);
                archivo = new Archivo(nombre, datos);
                break;
        }
        Thread hiloArchivo = new Thread(archivo);
        String mensaje = "La figura ingresada fue " + figura.getNombre() + ", que tiene como perímetro: " + medidas.calcularPerimetro() + "cm y área: " + medidas.calcularArea() + "cm2";
        System.out.println(mensaje);
        hiloArchivo.start();
    }

    public static TipoFigurasEnum getNombreFigura(int opcion) {
        return Arrays.stream(TipoFigurasEnum.values()).filter(f -> f.getOpcion() == opcion).findFirst().orElseThrow(NoSuchElementException::new);
    }
    public static String prepararArchivo(TipoFigurasEnum figura, IMedidas medidas){
        JOptionPane.showMessageDialog( null, String.format(Mensajes.FIGURA_MENSAJE_FINAL, figura.getNombre(), medidas.calcularPerimetro(), medidas.calcularArea()));
        return JOptionPane.showInputDialog("Ingrese un nombre de archivo para guardar los calculos");
    }

    public static void abrirCalculos() {

        File fArc = new File(ArchivoConstants.PRINCIPAL_PATH);
        int dirOpt;
        File[] directorios;
        directorios = fArc.listFiles();

        StringBuilder sb = new StringBuilder(Mensajes.INGRESO_DIRECTORIO);
        formarMensaje(directorios, sb);
        dirOpt = Integer.parseInt(JOptionPane.showInputDialog(sb.toString()));
        assert directorios != null;
        mostrarArchivos(directorios[dirOpt]);
    }

    public static void mostrarArchivos(File directorio){
        File[] archivos = directorio.listFiles();
        String opciones;
        StringBuilder sb = new StringBuilder(Mensajes.INGRESO_ARCHIVO);
        formarMensaje(archivos, sb);
        opciones = JOptionPane.showInputDialog(sb.toString());
        abrirArchivos(opciones, directorio.getAbsolutePath());
    }

    public static void formarMensaje(File[] archivos, StringBuilder sb){
        final Integer[] i = {0};
        Arrays.stream(archivos).forEach(f -> sb.append(String.format(Mensajes.FORMATO_OPCIONES_ARCHIVOS, (i[0]++), f.getName() )));
    }

    public static void abrirArchivos(String opciones, String pathPadre){
       String [] opc = opciones.split(",");
       AperturaArchivo aa;
       for(String archivo : opc){
            aa = new AperturaArchivo(archivo, pathPadre);
            Thread aperturaHilo = new Thread(aa);
            aperturaHilo.start();
       }
    }
}