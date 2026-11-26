package org.ies.tierno;

import java.sql.*;

public class ProbandoJDBC2 {
    public static void main(String[] args) {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexion con la BD
            String bdatos = "jdbc:mysql://localhost:3306/world";
            String usuario = "root";
            String contrasena = "Sandia4you";

            Connection conexion = DriverManager.getConnection(bdatos, usuario, contrasena);
            Statement sentencia = conexion.createStatement();

            // 1. Mostrar ID, nombre y código de país
            String sql = "SELECT * FROM city LIMIT 5";
            ResultSet resul = sentencia.executeQuery(sql);

            while (resul.next()) {
                System.out.printf("%d, %s, %s %n", resul.getInt(1), resul.getString(2), resul.getString(3));
            }
            resul.close();

            // 2. Mostrar solo el nombre
            sql = "SELECT Name FROM city LIMIT 5";
            resul = sentencia.executeQuery(sql);

            while (resul.next()) {
                System.out.printf("%s %n", resul.getString(1));
            }
            resul.close();

            // 3. Mostrar solo nombre
            sql = "SELECT ID, Name FROM city LIMIT 5";
            resul = sentencia.executeQuery(sql);

            while (resul.next()) {
                System.out.printf("%s %n", resul.getString(2));
            }
            resul.close();

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }// fin de main
}// fin de la clase