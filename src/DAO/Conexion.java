package DAO;


import java.sql.*;
public class Conexion {


    protected Connection conexion;
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/transporte_masivo";
    
    private final String USER  = "postgres";
    private final String PASS  = "1234";


    public void conectar() {
        try {
            // Se carga el driver
            Class.forName(JDBC_DRIVER);
            //System.out.println( "Driver Cargado" );
        } catch (Exception e) {
            System.out.println("No se pudo cargar el driver de conexion a la BD.");
        }

        try {
                //Crear el objeto de conexion a la base de datos
                conexion = DriverManager.getConnection(DB_URL, USER, PASS);
                //System.out.println("Conexion Abierta :D");
                //return conexion;
                
            //Crear objeto Statement para realizar queries a la base de datos
        } catch (Exception e) {
            System.out.println("No se pudo abrir la bd. :'(");
            //return null;
        }

    }//end connectar

    public void cerrar() {
        try {
            if (conexion != null) {
                if(!conexion.isClosed()){
                    conexion.close();
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo cerrar. -.-'");
        }
    }
    
    public Connection getConexion() {
        if (conexion == null) {
            this.conectar();
            return conexion;
        } else {
            return conexion;
        }

    }
}
