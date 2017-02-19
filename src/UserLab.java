import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arunan on 2/18/2017.
 */
public class UserLab {
    private static UserLab userLab;
    private Connection databaseConnection;
    private int oS;  //OffSet

    private List<User> users;

    public static UserLab get(){
        if (userLab == null){
            userLab = new UserLab();
        }
        return userLab;
    }

    private UserLab(){
        databaseConnection = MysqlCon.getConnection();
        getAllUsers();
    }

    public String[] getUsers(int tL, int iFL, int mFL, int rFL, int pL, int tW, int iFW, int mFW, int rFW, int pFW){

        return null;
    }

    public List<User> getAllUsers(){
        try{
            Statement stmt = databaseConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * from " + constants.databaseUserTableName + " JOIN " +
                    constants.databaseMeasurementName + " WHERE " + "users.id = hand_measurement.id;");
            System.out.println(result.toString());
            this.users = new ArrayList<User>();
            while(result.next()) {
                users.add(new User(result.getString(1), result.getString(2), result.getInt(4), result.getInt(5),
                         result.getInt(6), result.getInt(7), result.getInt(8), result.getInt(9), result.getInt(10), result.getInt(11),
                                result.getInt(12), result.getInt(13)));
            }

        } catch (Exception e){
            System.out.println(e);
        }

        return users;
    }

    public void addUser(User user){
        try{
            Statement stmt1 = databaseConnection.createStatement();
            stmt1.executeUpdate("insert into " + constants.databaseUserTableName + " (id, name) " + " VALUES ( " +
                                "'" + user.getUserId() + "'" + ", " + "'" + user.getName() +  "'" + ");" );
            Statement stmt2 = databaseConnection.createStatement();
            stmt2.executeUpdate("insert into " + constants.databaseMeasurementName + " (id, thumb_length, index_finger_length, middle_finger_length, ring_finger_length, " +
                    "pinkie_length, thumb_width, index_finger_width, middle_finger_width, ring_finger_width, pinkie_width)" + " VALUES( " +
                    "'" + user.getUserId()+ "'" + ", " +
                                user.getThumbLength() + ", " + user.getIndexFingerLength() + ", " +
                                user.getMiddleFingerLength() + ", " + user.getRingFingerLength() + ", " +
                                user.getPinkieFingerLength() + ", " + user.getThumbWidth() + ", " +
                                user.getIndexFingerWidth() + ", " + user.getMiddleFingerWidth() + ", " +
                                user.getRingFingerWidth() + ", " + user.getPinkieFingerWidth() + ");");

        } catch (Exception e){
            System.out.println(e);
        }

        users = getAllUsers();

//
//            ResultSet rs=stmt.executeQuery("select * from practise_user");
    }
}
