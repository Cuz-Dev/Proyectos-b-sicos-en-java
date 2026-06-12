public class division {
    static int pedir_numero1(){
        while(true){
            try{
                System.out.print("Digita el primer numero a dividir: ");
                int numero_1 = configuracion.entrada_us();
                return numero_1;

            }catch(Exception e){
            System.out.println("Debes escribir un numero no una letra");
            }
        }
    }
    static int pedir_numero2(){
        while(true){
            try{
                System.out.print("Digita el segundo a dividir: ");
                int numero_2 = configuracion.entrada_us();
                return numero_2;
            }catch(Exception e){
                System.out.println("Debes escribir un numero no una letra");
            }
        }
    }
    static void resultado(){
        int numero_1 = pedir_numero1();
        int numero_2 = pedir_numero2();
        double resultado = operacion_division.operacion(numero_1, numero_2);
        System.out.println("Resultado division: " + resultado);
    }

}
class operacion_division{
    static double operacion(int numero_1, int numero_2){
        double resultado = numero_1 / numero_2;
        return resultado;
    }
}
