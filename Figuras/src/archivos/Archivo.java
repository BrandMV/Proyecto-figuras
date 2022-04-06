package archivos;

import constants.Mensajes;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;

public class Archivo implements Runnable{

    private String nombreArchivo;
    private File archivo;
    private String rutaPadre;
    private final String datos;
    public Archivo(String nombreArchivo, String datos){
        this.nombreArchivo = nombreArchivo + ".txt";
        this.datos = datos;
    }

    @Override
    public void run() {
        crearDirectorio();
        int isSaved = 0;
        //* Menu that will be displayed till the file is saved
        while(verificarArchivo(this.nombreArchivo) && isSaved == 0){
            int opt = Integer.parseInt(JOptionPane.showInputDialog(Mensajes.ARCHIVO_EXISTE));
            switch (opt){
                case 1:
                    //*Overwriting the file
                    this.archivo.delete();
                    try {
                        this.archivo.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    guardarCalculos(this.archivo);
                    isSaved = 1;
                    break;
                case 2:
                    this.nombreArchivo = JOptionPane.showInputDialog("Ingresa nuevo nombre");
                    this.nombreArchivo = this.nombreArchivo + ".txt";
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    public void crearDirectorio() {
        String fecha = LocalDate.now().toString();
        File dir = new File("Calculos");

        if (!dir.exists()) dir.mkdir();
        String path = dir.getAbsolutePath();
        File dirPadre = new File(path+ "/" + fecha);
        if (!dirPadre.exists()) dirPadre.mkdir();
        this.rutaPadre = dirPadre.getAbsolutePath();
    }

    public Boolean verificarArchivo(String nombreAVerificar){
        boolean existe = true;
        String rutaHijo = this.rutaPadre + "/" + nombreAVerificar;
        this.archivo  = new File(rutaHijo);

        //* If file does not exist, will be created
        if(!this.archivo.exists()){
            try {
                this.archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            guardarCalculos(this.archivo);
            existe = false;
        }
        return existe;
    }
    public void guardarCalculos(File file ){
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.datos);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Datos guardados en archivo correctamente");
    }
}
