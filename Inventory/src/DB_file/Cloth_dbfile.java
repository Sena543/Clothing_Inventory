package DB_file;

import java.sql.*;

public class Cloth_dbfile {
    Connection connection;
    Statement statement;
    ResultSet resultset;

    public void create_Connection(String userID, String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String db_url = String.format("user=%s&password=%s", userID, password);

            connection = DriverManager.getConnection("jdbc://localhost/Cloth_Invetory"+ db_url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet count_Clothes_Available(){
        try{
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT COUNT(*) FROM clothTable");

        }catch (SQLException e){
            System.out.println("Exception Message:"+ e.getMessage());
            System.out.println("Error State:"+e.getSQLState());
            System.out.println("Error Code:"+e.getErrorCode());
        }

        return resultset;
    }

    //subtract from items available
    public void item_Sold(String itemBought){
        String sql = String.format("DELETE FROM clothTable WHERE cloth_id = %s ", itemBought);

        try{
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("Exception Message:"+ e.getMessage());
            System.out.println("Error State:"+e.getSQLState());
            System.out.println("Error Code:"+e.getErrorCode());
        }
    }

    public void add_Stock(){
        try{
            statement = connection.createStatement();
          //  resultset = statement.execute('');
        }catch (SQLException e){
            System.out.println("Exception Message:"+ e.getMessage());
            System.out.println("Error State:"+e.getSQLState());
            System.out.println("Error Code:"+e.getErrorCode());
        }
    }
}
