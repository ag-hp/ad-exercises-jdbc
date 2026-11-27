package org.ies.tierno;

import java.sql.*;

public class Caso2Delete {
    public static void main(String[] args) {
        try {
            // Cargar el driver y establecer conexión con la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            String bdatos = "jdbc:mysql://localhost:3306/world";
            String user = "root";
            String password = "Sandia4you";
            Connection connection = DriverManager.getConnection(bdatos, user, password);

            // Paso previo: Insertar registro de prueba para asegurar su existencia antes del borrado
            String sqlInsert = "INSERT INTO city (Name, CountryCode) VALUES (?, ?)";
            PreparedStatement insertPrep = connection.prepareStatement(sqlInsert);
            insertPrep.setString(1, "Inventada2");
            insertPrep.setString(2, "ABW");
            insertPrep.executeUpdate();
            insertPrep.close();

            // 1. Statement: Borrado directo mediante sentencia SQL literal
            Statement sentencia1 = connection.createStatement();
            String sql1 = "DELETE FROM city WHERE Name = 'Inventada2'";
            sentencia1.executeUpdate(sql1);
            sentencia1.close();

            // Para verla en la terminal sin poner el código entero es con: INSERT INTO city (Name, CountryCode) VALUES ('Inventada2', 'ABW');


            // 2. PreparedStatement: Borrado parametrizado mediante marcador de posición (?)
            String sql2 = "DELETE FROM city WHERE Name = ?";
            PreparedStatement sentencia2 = connection.prepareStatement(sql2);
            sentencia2.setString(1, "Inventada2");
            sentencia2.executeUpdate();
            sentencia2.close();

            // Cerrar conexión con la base de datos
            connection.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}