import java.util.Scanner;
public class configuracion {
    static int entrada_us(){
        Scanner input = new Scanner(System.in);
        while (true){
            try {
                int entrada = input.nextInt();
                return entrada;
                
            }catch(Exception e){
                System.out.println("Debes escribir un numero no una letra");
            }
        }
    }

}
