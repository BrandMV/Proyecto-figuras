package archivos;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AperturaArchivo implements Runnable{

    private final String nombre;
    private final String pathPadre;
    public AperturaArchivo(String nombre, String pathPadre){
        this.nombre = nombre;
        this.pathPadre = pathPadre;
    }

    @Override
    public void run(){
        Desktop archivoAAbrir = Desktop.getDesktop();
        try{
            archivoAAbrir.open(new File(this.pathPadre + "/" + this.nombre));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
