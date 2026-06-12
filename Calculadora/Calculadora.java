public class Calculadora {
    public static void main(String[] args){
        while (true){
            Ui.Menu_principal();
            System.out.print("Digita numero de opcion: ");
            int entrada = configuracion.entrada_us();
            switch (entrada) {
                case 1:
                    suma .resultado();
                    break;
                case 2:
                    resta.resultado();
                    break;
                case 3:
                    multiplicacion.resultado();
                    break;
                case 4:
                    division.resultado();
                    break;
                
                case 5:
                    info.ver_txt();
                    break;
                    
                default:
                    break;
            }
        }

    }
}
