/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbd;

import java.awt.HeadlessException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author RLCR
 */
public class Gestionbd {
    
    Connection conexion= null;
    Statement sentencia=null;
    ResultSet resultado=null;
    String NOMBREBD="bibliotecaShoe.sqlite";
    String URL="jdbc:sqlite:"+NOMBREBD;
    String DRIVER="org.sqlite.JDBC";
    
public void connect(){
 try {
     conexion= DriverManager.getConnection(URL);
     if (conexion!=null) {
         System.out.println("Conectado");
     }
 }catch (SQLException ex) {
     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
 }
}


    public void crearBD(){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
        System.out.println("Base de datos creada con exito!");
    }

    
    public void crearTablaCliente(){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="CREATE TABLE CLIENTE"+
                   "(RUT TEXT PRIMARY KEY NOT NULL, "+
                   "NOMBRE TEXT NOT NULL, "+
                   "APELLIDO TEXT NOT NULL, "+
                   "EDAD INT NOT NULL, "+
                   "DIRECCION TEXT NOT NULL)";
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
        System.out.println("Tabla creada con exito!");
    }
    
    
public void crearTablaLibros(){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="CREATE TABLE LIBROS"+
                   "(ID_LIBRO INT PRIMARY KEY NOT NULL, "+
                   "NOMBRE_LIBRO TEXT NOT NULL, "+
                   "AUTOR_LIBRO TEXT NOT NULL, "+
                   "TIPO_LIBRO TEXT NOT NULL, "+
                   "STOCK INT NOT NULL)";
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
        System.out.println("Tabla creada con exito!");
    }
    

    public void crearTablaPrestamos(){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="CREATE TABLE PRESTAMOS"+
                   "(ID_PRESTAMO INT PRIMARY KEY NOT NULL, "+
                   "ID_LIBRO INT NOT NULL ,"+
                   "FECHA_PRESTAMO TEXT NOT NULL, "+
                   "FECHA_DEVOLUCION TEXT NOT NULL, "+
                   "RUT TEXT NOT NULL, " +
                 "FOREIGN KEY (ID_LIBRO) REFERENCES LIBROS(ID_LIBROS),"+
                 " FOREIGN KEY (RUT) REFERENCES CLIENTE(RUT))";
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
         JOptionPane.showMessageDialog(null,"Tabla creada con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);
    }
    
public void insertarCliente(String rut,String nombre,String apellido,int edad,String direccion){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="INSERT INTO CLIENTE "
                   + "(RUT,NOMBRE,APELLIDO,EDAD,DIRECCION)"+
                   "VALUES('"+rut+"','"+nombre+"','"+apellido+"','"+edad+"','"+direccion+"')";
        sentencia.executeUpdate(sql);
        JOptionPane.showMessageDialog(null,"Datos ingresados","EXITO!",JOptionPane.INFORMATION_MESSAGE);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos o bien esta ingresando un rut repetido","ERROR",JOptionPane.ERROR_MESSAGE);
    }
    
   
}

public void eliminarCliente(String rut){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="DELETE FROM CLIENTE WHERE RUT ='"+rut+"'";
        sentencia.executeUpdate(sql);
        System.out.println("Datos  Eliminados");
        JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO","ELIMINADO",JOptionPane.INFORMATION_MESSAGE);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
   
}

public void eliminarPrestamo(int id){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="DELETE FROM PRESTAMOS WHERE ID_PRESTAMO ='"+id+"'";
        sentencia.executeUpdate(sql);
        JOptionPane.showMessageDialog(null, "PRESTAMO ELIMINADO","ELIMINADO",JOptionPane.INFORMATION_MESSAGE);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
       
}

public void eliminarlibro(int li){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="DELETE FROM LIBROS WHERE ID_LIBRO ='"+li+"'";
        sentencia.executeUpdate(sql);
        System.out.println("Datos  Eliminados");
        JOptionPane.showMessageDialog(null, "LIBRO ELIMINADO","ELIMINADO",JOptionPane.INFORMATION_MESSAGE);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        System.out.println("ERROR:"+e.getMessage());
    }
    
     
}
   /*public static void main(String[] args) {
        Gestionbd g=new Gestionbd();
        g.crearTablaLibros();
       
        
    }*/

public void insertarLibro(int id,String nombre,String autor,String tipo,int stock){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="INSERT INTO LIBROS "
                   + "(ID_LIBRO,NOMBRE_LIBRO,AUTOR_LIBRO,TIPO_LIBRO,STOCK)"+
                   "VALUES('"+id+"','"+nombre+"','"+autor+"','"+tipo+"','"+stock+"')";
        sentencia.executeUpdate(sql);
        JOptionPane.showMessageDialog(null,"Datos ingresados","EXITO!",JOptionPane.INFORMATION_MESSAGE);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos o bien esta ingresando un id repetido","ERROR",JOptionPane.ERROR_MESSAGE);
    }
    
    
}

public void insertarPrestamo(int id,int idd,String nombre,String autor,String tipo){
    try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="INSERT INTO PRESTAMOS "
                   + "(ID_PRESTAMO,ID_LIBRO,FECHA_PRESTAMO,FECHA_DEVOLUCION,RUT)"+
                   "VALUES('"+id+"','"+idd+"','"+nombre+"','"+autor+"','"+tipo+"')";
        sentencia.executeUpdate(sql);
        JOptionPane.showMessageDialog(null,"Datos ingresados","EXITO!",JOptionPane.INFORMATION_MESSAGE);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos o bien esta ingresando un id repetido","ERROR",JOptionPane.ERROR_MESSAGE);
    }
    
        JOptionPane.showMessageDialog(null,"Datos ingresados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);
}
public void actualizardatosNOMBRElibro(int id,String nombre){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE LIBROS SET NOMBRE_LIBRO ='"+nombre+"' WHERE ID_LIBRO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el nombre del libro correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizardatosautor(int id,String autor){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE LIBROS SET AUTOR_LIBRO ='"+autor+"' WHERE ID_LIBRO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el nombre del autor del libro correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizardatosSTOCK(int id,int stock){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE LIBROS SET STOCK ='"+stock+"' WHERE ID_LIBRO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el Stock del libro correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizardatostipo(int id,String tipo){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE LIBROS SET TIPO_LIBRO ='"+tipo+"' WHERE ID_LIBRO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el tipo de libro correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}

public void actualizarprestamoidlibro(int id,int IDD){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRESTAMOS SET  ID_LIBRO='"+IDD+"' WHERE ID_PRESTAMO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el id libro del prestamo correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizarprestamofechap(int id,String IDD){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRESTAMOS SET  FECHA_PRESTAMO='"+IDD+"' WHERE ID_PRESTAMO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado la fecha del prestamo correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}


public void actualizarprestamofechad(int id,String IDD){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRESTAMOS SET  FECHA_DEVOLUCION='"+IDD+"' WHERE ID_PRESTAMO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado la fecha de devolucion del prestamo correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizarprestamorut(int id,String IDD){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE PRESTAMOS SET  RUT_CLIENTE='"+IDD+"' WHERE ID_PRESTAMO = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el rut del cliente del prestamo "+id+ " correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}

public void actualizardatosNOMBREcliente(String id,String nombre){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE CLIENTE SET NOMBRE ='"+nombre+"' WHERE RUT = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el nombre del cliente correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizardatosAPELLIDOcliente(String id,String apellido){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE CLIENTE SET APELLIDO ='"+apellido+"' WHERE RUT = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado el apellido del cliente correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}

public void actualizardatosEDADcliente(String id,int edad){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE CLIENTE SET EDAD ='"+edad+"' WHERE RUT = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado la edad del cliente correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void actualizardatosDIRECCIONcliente(String id,String direccion){
 try{
    
        Class.forName(DRIVER);
        conexion=DriverManager.getConnection(URL);
        sentencia=conexion.createStatement();
        String sql="UPDATE CLIENTE SET DIRECCION ='"+direccion+"' WHERE RUT = '"+id+"'";
        JOptionPane.showMessageDialog(null,"Ha actualizado la direccion del cliente correctamente","ACTUALIZACION",JOptionPane.INFORMATION_MESSAGE);
        sentencia.executeUpdate(sql);
        sentencia.close();
        conexion.close();
        
    }catch(ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null,"ERROR: Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
        JOptionPane.showMessageDialog(null,"Datos actualizados con exito!","EXITO!",JOptionPane.INFORMATION_MESSAGE);

}
public void mostrarClientes(JTable tablaCliente){

    
    try{
 
     Class.forName(DRIVER);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="SELECT * FROM CLIENTE";
     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     tablaCliente.setValueAt(resultado.getInt("RUT"),fila,0);
     tablaCliente.setValueAt(resultado.getString("NOMBRE"),fila,1);
     tablaCliente.setValueAt(resultado.getString("APELLIDO"),fila,2);
     tablaCliente.setValueAt(resultado.getInt("EDAD"),fila,3);
     tablaCliente.setValueAt(resultado.getString("DIRECCION"),fila,4);
     fila++;
     }
     
     sentencia.close();
     conexion.close();
   
     
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}

public void mostrarLibros(JTable tablaLibros){

    
    try{
 
     Class.forName(DRIVER);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="SELECT * FROM LIBROS";
     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     tablaLibros.setValueAt(resultado.getInt("ID_LIBRO"),fila,0);
     tablaLibros.setValueAt(resultado.getString("NOMBRE_LIBRO"),fila,1);
     tablaLibros.setValueAt(resultado.getString("AUTOR_LIBRO"),fila,2);
     tablaLibros.setValueAt(resultado.getString("TIPO_LIBRO"),fila,3);
    tablaLibros.setValueAt(resultado.getString("STOCK"),fila,4);
     fila++;
     }
     
     sentencia.close();
     conexion.close();
     
     
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}


public void mostrarPrestamo(JTable tablaPrestamos){

    
    try{
 
     Class.forName(DRIVER);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="SELECT * FROM PRESTAMOS";
     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     tablaPrestamos.setValueAt(resultado.getInt("ID_PRESTAMO"),fila,0);
     tablaPrestamos.setValueAt(resultado.getString("ID_LIBRO"),fila,1);
     tablaPrestamos.setValueAt(resultado.getString("FECHA_PRESTAMO"),fila,2);
     tablaPrestamos.setValueAt(resultado.getString("FECHA_DEVOLUCION"),fila,3);
     tablaPrestamos.setValueAt(resultado.getString("RUT"),fila,4);
     fila++;
     }
    
     sentencia.close();
     conexion.close();
     
     
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}

public void CONSULTAdostablas(JTable tablaCliente){

    
    try{
 
     Class.forName(DRIVER);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="select CLIENTE.RUT,NOMBRE,APELLIDO,NOMBRE_LIBRO\n" +
"FROM CLIENTE\n" +
"JOIN PRESTAMOS\n" +
"ON CLIENTE.rowid=PRESTAMOS.rowid\n" +
"JOIN LIBROS\n" +
"ON LIBROS.rowid=PRESTAMOS.rowid";
     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     tablaCliente.setValueAt(resultado.getInt("RUT"),fila,0);
     tablaCliente.setValueAt(resultado.getString("NOMBRE"),fila,1);
     tablaCliente.setValueAt(resultado.getString("APELLIDO"),fila,2);
     tablaCliente.setValueAt(resultado.getString("NOMBRE_LIBRO"),fila,3);
     fila++;
     }
     
     sentencia.close();
     conexion.close();
   
     
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}
public void CONSULTATREStablas(JTable tablaCliente){

    
    try{
 
     Class.forName(DRIVER);
     conexion = DriverManager.getConnection(URL);
     sentencia = conexion.createStatement();
     String sql ="select CLIENTE.RUT,NOMBRE,APELLIDO,NOMBRE_LIBRO,FECHA_PRESTAMO,FECHA_DEVOLUCION\n" +
"FROM CLIENTE\n" +
"JOIN PRESTAMOS\n" +
"ON CLIENTE.rowid=PRESTAMOS.rowid\n" +
"JOIN LIBROS\n" +
"ON LIBROS.rowid=PRESTAMOS.rowid";
     resultado = sentencia.executeQuery(sql);
     int fila=0;
     while(resultado.next()){
     tablaCliente.setValueAt(resultado.getInt("RUT"),fila,0);
     tablaCliente.setValueAt(resultado.getString("NOMBRE"),fila,1);
     tablaCliente.setValueAt(resultado.getString("APELLIDO"),fila,2);
     tablaCliente.setValueAt(resultado.getString("NOMBRE_LIBRO"),fila,3);
     tablaCliente.setValueAt(resultado.getString("FECHA_PRESTAMO"),fila,4);
     tablaCliente.setValueAt(resultado.getString("FECHA_DEVOLUCION"),fila,5);
     fila++;
     }
     
     sentencia.close();
     conexion.close();
   
     
 }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);


}
}
}
