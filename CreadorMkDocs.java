import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CreadorMkDocs {
    private static String usuario = "danielmi5";
    private static String proyecto = "2526_DAW_u1_action_Practica1.1";
    public static void main(String[] args) {
        File carpetaDocs = new File("docs");
        File archivoMkdocs = new File("mkdocs.yml");
        
        FileWriter writer = null;
        try {
            writer = new FileWriter(archivoMkdocs);
            String cadena = """
                site_name: Documentación del Proyecto Java
                nav:
                    - Inicio: index.html
                    - Clases:
                """;

            for (File archivo : carpetaDocs.listFiles()) {
                String nombre = archivo.getName();
                
                if (nombre.endsWith(".html") && !Arrays.asList("index.html", "package-summary.html", "allclasses-index.html").contains(nombre)) {
                    String nombreSinExtension = nombre.replace(".html", "");
                    cadena += "      - " + nombreSinExtension + ": " + nombre + "\n";
                }
            }

            cadena += """
                    - Paquete: package-summary.html
                theme:
                    name: mkdocs
                site_url: https://%s.github.io/%s/
                """.formatted(usuario, proyecto);

            writer.write(cadena);
        } catch (IOException e) {
            e.printStackTrace();

        } finally{

            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}