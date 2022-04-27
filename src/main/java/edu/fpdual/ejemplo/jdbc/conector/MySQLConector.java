package edu.fpdual.ejemplo.jdbc.conector;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.Properties;

/**
 * Clase que crea la conexion con la base de datos
 * @author Ismael Orellana Bello
 *
 */
public class MySQLConector {


    @Getter
    @Setter
    Properties prop = new Properties();

    public MySQLConector(){
        try{
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Creates the connection object for a MySQL DDBB
     * @return a {@link Connection}
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {

            //Indicates which driver is going to be used.
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));
            //Creates the connection based on the obtained URL.
            return  DriverManager.getConnection(getUrl());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    private String getUrl(){
        return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
                .append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstants.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstants.URL_SSL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstants.SERVER_TIMEZONE)).toString();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConector connector = new MySQLConector();
        Connection connection = connector.getMySQLConnection();
        System.out.println(connection.getCatalog());
        Statement st = connection.createStatement();
        try {
            ResultSet resultSet = st.executeQuery("Select * from city where CountryCode like 'ESP'");
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));

            }
            st.close();
            resultSet.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }


    }

}


