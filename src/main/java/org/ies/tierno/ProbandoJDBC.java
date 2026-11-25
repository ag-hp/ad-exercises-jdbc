package org.ies.tierno;

import java.sql.*;

public class ProbandoJDBC {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexion con la BD
            String bdatos="jdbc:mysql://localhost:3306/world";
            String usuario="root";
            String contrasena="";

            Connection conexion = DriverManager.getConnection(bdatos, usuario, contrasena);

            // Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM city LIMIT 5";
            //String sql = "SELECT Name FROM city LIMIT 5";
            ResultSet resul = sentencia.executeQuery(sql);

            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros y se van visualizando
            while (resul.next()) {
                System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
            }

            resul.close(); // Cerrar ResultSet
            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }// fin de main
}// fin de la clase
