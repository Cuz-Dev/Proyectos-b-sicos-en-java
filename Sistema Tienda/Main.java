/*Sistema de tienda v2🗂
Desarrollador CUZ-DEV
BASES DE DATOS SQLITE
 */
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;

class Herramientas
{
    Scanner input = new Scanner(System.in);
    public static void limpiarPantalla()
    {
        String sistema = System.getProperty("os.name").toLowerCase();
        try{
            if(sistema.contains("win")){
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
            }
            else{
                new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
            }
        }
        catch(Exception e){
            System.out.println("Error Actual"+e.getMessage()+"]");
        }
    }
    public void pausa()
    {
        String n = input.nextLine();
        System.out.println(n);
    }
}

class Sistema{
    Scanner entradaS = new Scanner(System.in);
    Scanner entradaI = new Scanner(System.in);
    Scanner entradaD = new Scanner(System.in);
    Herramientas h = new Herramientas();
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected int id_usuario;
    protected String ciudad;
    protected String tipoP;
    protected String marcaP;
    protected String modeloP;
    protected int stockP;
    protected int id_producto;
    protected int id_pedido;
    public void agregarCliente()
    {
        Herramientas.limpiarPantalla();
        System.out.println("Nombre: ");
        nombre = entradaS.nextLine();
        System.out.println("Apellido: ");
        apellido = entradaS.nextLine();
        System.out.println("Edad: ");
        edad = entradaI.nextInt();
        System.out.println("Ciudad: ");
        ciudad = entradaS.nextLine();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "INSERT INTO clientes(nombre, apellido, edad,ciudad) VALUES(?,?,?,?)"
            );
            sql.setString(1,nombre);
            sql.setString(2,apellido);
            sql.setInt(3,edad);
            sql.setString(4,ciudad);
            sql.executeUpdate();
            conexion.close();
            System.out.println("Usuario Agregado Correctamente:");
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void mostrarClientes()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT * FROM clientes ORDER BY nombre ASC"
            );
            while(resultado.next()){
                System.out.println("================");
                System.out.println("Id: "+resultado.getInt("id"));
                System.out.println("Nombre: "+ resultado.getString("nombre"));
                System.out.println("Apellido: "+resultado.getString("apellido"));
                System.out.println("Edad: "+resultado.getInt("edad"));
                System.out.println("Ciudad: "+resultado.getString("ciudad"));
                System.out.println("================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void buscarClientes()
    {
        Herramientas.limpiarPantalla();
        System.out.println("Nombre: ");
        nombre = entradaS.nextLine();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement("SELECT * FROM clientes WHERE nombre LIKE ?");
            sql.setString(1,"%"+nombre+"%");
            ResultSet resultado = sql.executeQuery();
            System.out.println("Resultados Encontrados Para Busqueda: "+nombre);
            while(resultado.next()){
                System.out.println("==================");
                System.out.println("Nombre: "+resultado.getString("nombre"));
                System.out.println("Apellido: "+resultado.getString("apellido"));
                System.out.println("Edad: "+resultado.getInt("edad"));
                System.out.println("Ciudad: "+resultado.getString("ciudad"));
                System.out.println("==================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void eliminarCliente()
    {
        Herramientas.limpiarPantalla();
        mostrarClientes();
        System.out.println("Id Usuario: ");
        id_usuario = entradaI.nextInt();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "DELETE FROM clientes WHERE id=?"
            );
            sql.setInt(1,id_usuario);
            sql.executeUpdate();
            conexion.close();
            System.out.println("Usuario Eliminado Correctamente:");
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void agregarProducto()
    {
        Herramientas.limpiarPantalla();
        System.out.println("Tipo: ");
        System.out.println("Ejemplo; Televisor");
        tipoP = entradaS.nextLine();
        System.out.println("Marca: ");
        marcaP = entradaS.nextLine();
        Herramientas.limpiarPantalla();
        System.out.println("Modelo: ");
        modeloP = entradaS.nextLine();
        System.out.println("Precio: ");
        BigDecimal precio = entradaD.nextBigDecimal();
        System.out.println("Stock: ");
        stockP = entradaI.nextInt();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "INSERT INTO productos(tipo,marca,modelo,precio,stock) VALUES(?,?,?,?,?)");
            sql.setString(1,tipoP);
            sql.setString(2,marcaP);
            sql.setString(3,modeloP);
            sql.setBigDecimal(4, precio);
            sql.setInt(5,stockP);
            sql.executeUpdate();
            conexion.close();
            System.out.println("Producto Agregado Correctamente:");
            h.pausa();
        }
        catch(Exception e){
            System.out.println();
        }
    }
    public void mostrarProductos()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT * FROM productos ORDER BY precio DESC"
            );
            while(resultado.next()){
                System.out.println("===============================================");
                System.out.println("Id Producto: "+resultado.getInt("id"));
                System.out.println("Tipo: "+resultado.getString("tipo"));
                System.out.println("Marca: "+resultado.getString("marca"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Precio: "+resultado.getBigDecimal("precio"));
                System.out.println("Disponibilidad: "+resultado.getInt("stock"));
                System.out.println("===============================================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void buscarProductos()
    {
        Herramientas.limpiarPantalla();
        System.out.println("Tipo: ");
        tipoP = entradaS.nextLine();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "SELECT* FROM productos WHERE tipo LIKE ?"
            );
            sql.setString(1,"%"+tipoP+"%");
            ResultSet resultado = sql.executeQuery();
            System.out.println("Resultados Encontrados Para Busqueda: "+tipoP);
            while(resultado.next()){
                System.out.println("==================");
                System.out.println("ID Producto: "+resultado.getInt("id"));
                System.out.println("Tipo: "+resultado.getString("tipo"));
                System.out.println("Marca: "+resultado.getString("marca"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Precio: "+resultado.getBigDecimal("precio"));
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error actual[ "+e.getMessage()+"]");
        }
    }
    public void eliminarProducto()
    {
        Herramientas.limpiarPantalla();
        mostrarProductos();
        System.out.println("Id: ");
        id_producto = entradaI.nextInt();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "DELETE FROM productos WHERE id=?"
            );
            sql.setInt(1, id_producto);
            sql.executeUpdate();
            System.out.println("Producto Borrado Correctamente");
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Erro Actual[ "+e.getMessage()+"]");
        }
    }
    public void agregarPedido()
    {
        Herramientas.limpiarPantalla();
        mostrarClientes();
        System.out.println("Id Usuario: ");
        id_usuario = entradaI.nextInt();
        mostrarProductos();
        System.out.println("Id Producto: ");
        id_producto = entradaI.nextInt();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "INSERT INTO pedidos(cliente_id, producto_id) VALUES(?,?)"
            );
            sql.setInt(1,id_usuario);
            sql.setInt(2,id_producto);
            sql.executeUpdate();
            System.out.println("Pedido Agregado Correctamente:");
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void mostrarPedidos()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT clientes.nombre AS nc, productos.marca AS marca, productos.modelo AS modelo,productos.precio AS precio,pedidos.id AS id FROM clientes JOIN pedidos ON clientes.id=pedidos.cliente_id JOIN productos ON productos.id=pedidos.producto_id"
            );
            while(resultado.next()){
                System.out.println("======================================================");
                System.out.println("Id pedido: "+resultado.getInt("id"));
                System.out.println("Nombre Cliente: "+resultado.getString("nc"));
                System.out.println("Marca: "+resultado.getString("marca"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Precio: "+resultado.getBigDecimal("precio"));
                System.out.println("=======================================================");
            }
            conexion.close();
            h.pausa();
        }   
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void buscarPedidos()
    {
        Herramientas.limpiarPantalla();
        mostrarClientes();
        System.out.println("id: ");
        id_usuario = entradaI.nextInt();
        String url= "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "SELECT clientes.nombre AS n, productos.marca AS marca, productos.modelo AS modelo, productos.precio AS precio, pedidos.id AS id FROM clientes JOIN pedidos ON clientes.id=pedidos.cliente_id JOIN productos ON productos.id=pedidos.producto_id WHERE clientes.id=?"
            );
            sql.setInt(1,id_usuario);
            ResultSet resultado = sql.executeQuery();
            System.out.println("Resultados Encontardos Para ID: "+id_usuario);
            while(resultado.next()){
                System.out.println("=============================================");
                System.out.println("Id Pedido: "+resultado.getInt("id"));
                System.out.println("Nombre Cliente: "+resultado.getString("n"));
                System.out.println("Marca: "+resultado.getString("marca"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Precio: "+resultado.getBigDecimal("precio"));
                System.out.println("=============================================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void eliminarPedido()
    {
        Herramientas.limpiarPantalla();
        mostrarPedidos();
        System.out.println("Id: ");
        id_pedido = entradaI.nextInt();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "DELETE FROM pedidos WHERE id=?"
            );
            sql.setInt(1,id_pedido);
            sql.executeUpdate();
            conexion.close();
            System.out.println("Pedido Eliminado Correctamente");
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void productoMasCostoso()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT MAX(precio) AS pr,tipo,marca,modelo  FROM productos"
            );
            while(resultado.next()){
                System.out.println("==================================");
                System.out.println("Tipo: "+resultado.getString("tipo"));
                System.out.println("Marca: "+resultado.getString("marca"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Precio: "+resultado.getBigDecimal("pr"));
                System.out.println("==================================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void productoMasEconomico()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT MIN(precio) AS pr,tipo,marca,modelo FROM productos ORDER BY precio DESC"
            );
            while(resultado.next()){
                System.out.println("=====================================");
                System.out.println("Tipo: "+resultado.getString("tipo"));
                System.out.println("Marca: "+resultado.getString("marca"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Precio: "+resultado.getBigDecimal("pr"));
                System.out.println("=====================================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void promedioPreciosProductos()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT AVG(precio) as N FROM productos"
            );
            while(resultado.next()){
                System.out.println("Precio promedio: "+resultado.getBigDecimal("n"));
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual");
        }
    }
    public void cantidadTotalDeProductos()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT COUNT(modelo) AS modelo FROM productos"
            );
            while(resultado.next()){
                System.out.println("Cantidad De Productos: "+resultado.getInt("modelo"));
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void totalDeExistenciasProductos()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT SUM(stock) AS stock FROM productos"
            );
            while(resultado.next()){
                System.out.println("Total De Existencias De productos: "+resultado.getInt("stock"));
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void menorNumeroDeStockProductos()
    {
        Herramientas.limpiarPantalla();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            Statement sql = conexion.createStatement();
            ResultSet resultado = sql.executeQuery(
                "SELECT MIN(stock) AS stock, marca AS nombre, modelo FROM productos"
            );
            while(resultado.next()){
                System.out.println("Menor Cantidad de Existencia: "+resultado.getString("nombre"));
                System.out.println("Modelo: "+resultado.getString("modelo"));
                System.out.println("Cantidad Total: "+resultado.getInt("stock"));
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void mostrarClientesPorCiudad()
    {
        Herramientas.limpiarPantalla();
        System.out.println("Ciudad: ");
        ciudad = entradaS.nextLine();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "SELECT * FROM clientes WHERE ciudad LIKE ?"
            );
            sql.setString(1,"%"+ciudad+"%");
            ResultSet resultado = sql.executeQuery();
            System.out.println("Resultados Para Ciudad: "+ciudad);
            while(resultado.next()){
                System.out.println("=============================");
                System.out.println("Id Usuario: "+resultado.getInt("id"));
                System.out.println("Nombre: "+resultado.getString("nombre"));
                System.out.println("Apellido: "+resultado.getString("apellido"));
                System.out.println("Edad: "+resultado.getInt("edad"));
                System.out.println("Ciudad: "+resultado.getString("ciudad"));
                System.out.println("=====================================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
    public void mostrarPedidosPorCliente()
    {
        Herramientas.limpiarPantalla();
        mostrarClientes();
        System.out.println("Nombre ");
        nombre = entradaS.nextLine();
        String url = "jdbc:sqlite:tienda.db";
        try{
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement sql = conexion.prepareStatement(
                "SELECT clientes.nombre AS nombre, productos.tipo AS tipo, productos.marca AS marca, productos.modelo AS modelo, productos.precio AS pr FROM pedidos JOIN clientes ON pedidos.cliente_id=clientes.id JOIN productos ON pedidos.producto_id=productos.id WHERE clientes.nombre LIKE ?"
            );
            sql.setString(1,"%"+nombre+"%");
            ResultSet resultado = sql.executeQuery();
            System.out.println("Resultado Busqueda Pedidos Por  Nombre: "+nombre);
            while(resultado.next()){
                System.out.println("===============================================");
                System.out.println("Nombre: "+resultado.getString("nombre"));
                System.out.println("Tipo Producto: "+resultado.getString("tipo"));
                System.out.println("===============================================");
            }
            conexion.close();
            h.pausa();
        }
        catch(Exception e){
            System.out.println("Error Actual[ "+e.getMessage()+"]");
        }
    }
}

class Menu extends Sistema
{
    Scanner entrada = new Scanner(System.in);
    public void menuPrincipal()
    {
        while(true){
            Herramientas.limpiarPantalla();
            System.out.println("====================");
            System.out.println("=    TIENDA V2     =");
            System.out.println("====================");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("4. Consultas");
            System.out.println("5. Salir");
            String opcion = entrada.nextLine();
            if(opcion.equals("5")){
                break;
            }
            if(opcion.equals("1")){
                subMenuGestionarClientes();
            }
            if(opcion.equals("2")){
                subMenuGestionarProductos();
            }
            if(opcion.equals("3")){
                subMenuGestionPedidos();
            }
            if(opcion.equals("4")){
                subMenuConsultas();
            }
        }
    }
    public void subMenuGestionarClientes()
    {
        while(true){
            Herramientas.limpiarPantalla();
            System.out.println("========== CLIENTES ==========");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Buscar Clientes");
            System.out.println("4. Eliminar Clientes");
            System.out.println("5. Volver");
            String opcion = entrada.nextLine();
            if(opcion.equals("5")){
                break;
            }
            if(opcion.equals("1")){
                this.agregarCliente();
            }
            if(opcion.equals("2")){
                this.mostrarClientes();
            }
            if(opcion.equals("3")){
                this.buscarClientes();
            }
            if(opcion.equals("4")){
                this.eliminarCliente();
            }
        }
    }
    public void subMenuGestionarProductos()
    {
        while(true){
            Herramientas.limpiarPantalla();
            System.out.println("========= PRODUCTOS ==========");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Volver");
            String opcion = entrada.nextLine();
            if(opcion.equals("5")){
                break;
            }
            if(opcion.equals("1")){
                this.agregarProducto();
            }
            if(opcion.equals("2")){
                this.mostrarProductos();
            }
            if(opcion.equals("3")){
                this.buscarProductos();
            }
            if(opcion.equals("4")){
                this.eliminarProducto();
            }
        }
    }
    public void subMenuGestionPedidos()
    {
        while(true){
            Herramientas.limpiarPantalla();
            System.out.println("============= PEDIDOS =============");
            System.out.println("1. Agregar Pedido");
            System.out.println("2. Mostrar Pedidos");
            System.out.println("3. Buscar Pedido");
            System.out.println("4. Eliminar Pedido");
            System.out.println("5. Volver");
            String opcion = entrada.nextLine();
            if(opcion.equals("5")){
                break;
            }
            if(opcion.equals("1")){
                this.agregarPedido();
            }
            if(opcion.equals("2")){
                this.mostrarPedidos();
            }
            if(opcion.equals("3")){
                this.buscarPedidos();
            }
            if(opcion.equals("4")){
                this.eliminarPedido();
            }
        }
    }
    public void subMenuConsultas()
    {
        while(true){
            Herramientas.limpiarPantalla();
            System.out.println("============= CONSULTAS =============");
            System.out.println("1. Producto Mas Costoso");
            System.out.println("2. Producto Mas Barato");
            System.out.println("3. Promedio de Precios");
            System.out.println("4. Cantidad De Productos");
            System.out.println("5. Stock Total");
            System.out.println("6. Productos Con Poco Stock");
            System.out.println("7. Clientes por Ciudad");
            System.out.println("8. Pedidos Por Cliente");
            System.out.println("9. Volver");
            String opcion = entrada.nextLine();
            if(opcion.equals("9")){
                break;
            }
            if(opcion.equals("1")){
                this.productoMasCostoso();
            }
            if(opcion.equals("2")){
                this.productoMasEconomico();
            }
            if(opcion.equals("3")){
                this.promedioPreciosProductos();
            }
            if(opcion.equals("4")){
                this.cantidadTotalDeProductos();
            }
            if(opcion.equals("5")){
                this.totalDeExistenciasProductos();
            }
            if(opcion.equals("6")){
                this.menorNumeroDeStockProductos();
            }
            if(opcion.equals("7")){
                this.mostrarClientesPorCiudad();
            }
            if(opcion.equals("8")){
                this.mostrarPedidosPorCliente();
            }
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Menu obj = new Menu();
        obj.menuPrincipal();
    }
}