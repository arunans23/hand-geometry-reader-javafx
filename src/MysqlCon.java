import java.sql.*;

public class MysqlCon{
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practise_project","root","");

            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from practise_user");

            while(rs.next())
                System.out.println(rs.getString(2)+"  "+rs.getString(3));
            con.close();
        } catch(Exception e){ System.out.println(e);}
    }
}