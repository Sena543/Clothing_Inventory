package DB_file;

import java.sql.*;

public class Cloth_dbfile {
    Connection connection;
    Statement statement;
    ResultSet resultset;
    PreparedStatement preparedStatement;

    public  Connection create_Connection(){
        String url = "jdbc:mysql://localhost:3306/clothing_shop";
        String userID = "root";
        String password = "senanu123$";
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //String db_url = String.format("user=%s&password=%s", userID, password);

             connection = DriverManager.getConnection(url, userID, password);
            return connection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void getUser(String username, String password){
        String sql = String.format("SELECT FROM userTable WHERE username = %s", username);
        try{
            preparedStatement = connection.prepareStatement(sql);
            resultset = statement.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("Exception Message:"+ e.getMessage());
            System.out.println("Error State:"+e.getSQLState());
            System.out.println("Error Code:"+e.getErrorCode());
        }
        System.out.println("Success");
    }
}
