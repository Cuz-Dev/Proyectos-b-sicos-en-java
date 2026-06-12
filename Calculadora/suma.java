public class suma {
    static int pedir_numero1(){
        while (true) {
            try{
                System.out.print("Digita el primer numero a sumar: ");
                int numero_1 = configuracion.entrada_us();
                return numero_1;

            }catch(Exception e){
                System.out.print("Debes escribir un numero no una letra");
            }
        }
    }
    static int pedir_numero2(){
        while(true){
            try{
                System.out.print("Digita el segundo numero a sumar: ");
                int numero_2 = configuracion.entrada_us();
                return numero_2;
            }catch(Exception e){
                System.out.print("Debes escribir un numero no una letra");
            }
        }
    }
    static void resultado(){
        int numero_1 = pedir_numero1();
        int numero_2 = pedir_numero2();
        int resultado = suma_operacion.operacion(numero_1,numero_2);
        System.out.println("Resultado de suma: " + resultado);
    }

}

class suma_operacion {
    static int operacion(int numero_1, int numero_2){
        int resultado = numero_1 + numero_2;
        return resultado;
    }
}