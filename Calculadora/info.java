import java.io.File;
import java.util.Scanner;
public class info {
    static void ver_txt(){
        try{
            Scanner lector = new Scanner(new File("info.txt"));
            while (lector.hasNextLine()) {
                System.out.println(lector.nextLine());
            }
        }catch(Exception e){
            System.out.print("No se encontro el archivo");
        }
    
    }

}
