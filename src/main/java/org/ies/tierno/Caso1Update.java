package org.ies.tierno;

import java.sql.*;

public class Caso1Update {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String bdatos = "jdbc:mysql://localhost:3306/world";
            String user = "root";
            String password = "Sandia4you";
            Connection connection = DriverManager.getConnection(bdatos, user, password);

            // Paso previo: Insertamos 'Inventada' para que exista en la tabla
            Statement insertStmt = connection.createStatement();
            String sqlInsert = "INSERT INTO city (Name, CountryCode, District, Population) "
                    + "VALUES ('Inventada', 'ABW', 'Lum', 5)";
            insertStmt.executeUpdate(sqlInsert);
            insertStmt.close();

            // 1. Statement: UPDATE directo
            Statement sentencia1 = connection.createStatement();
            String sql1 = "UPDATE city SET District = 'AAA', Population = 25 WHERE Name = 'Inventada'";
            sentencia1.executeUpdate(sql1);
            sentencia1.close();

            // 2. PreparedStatement: UPDATE con parámetros (?)
            String sql2 = "UPDATE city SET District = ?, Population = ? WHERE Name = ?";
            PreparedStatement sentencia2 = connection.prepareStatement(sql2);
            sentencia2.setString(1, "AAA");
            sentencia2.setInt(2, 25);
            sentencia2.setString(3, "Inventada");
            sentencia2.executeUpdate();
            sentencia2.close();

            connection.close();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}