import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


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

    public String getUsers(int tL, int iFL, int mFL, int rFL, int pL, int tW, int iFW, int mFW, int rFW, int pFW){
        String resultString = "";
        for (int i = 0; i < this.users.size(); i++){
            float sumAve = 0;
            sumAve += (1.0-(float)(abs(users.get(i).getThumbLength() - tL)/users.get(i).getThumbLength()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getIndexFingerLength() - iFL)/users.get(i).getIndexFingerLength()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getMiddleFingerLength() - mFL)/users.get(i).getMiddleFingerLength()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getRingFingerLength() - rFL)/users.get(i).getRingFingerLength()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getPinkieFingerLength() - pL)/users.get(i).getPinkieFingerLength()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getThumbWidth() - tW)/users.get(i).getThumbWidth()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getIndexFingerWidth() - iFW)/users.get(i).getIndexFingerWidth()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getMiddleFingerWidth() - mFW)/users.get(i).getMiddleFingerWidth()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getRingFingerWidth() - rFW)/users.get(i).getRingFingerWidth()))*100;
            sumAve += (1.0-(float)(abs(users.get(i).getPinkieFingerWidth() - pFW)/users.get(i).getPinkieFingerWidth()))*100;

            float averagePerc = sumAve/10;
            if (averagePerc > 50.0){
                resultString += "" + (i+1) + " " + users.get(i).getName() + "\t \t " + averagePerc + "\n";
            }

        }
        return resultString;
    }

    public List<User> getAllUsers(){
        try{
            Statement stmt = databaseConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * from " + constants.databaseUserTableName + " JOIN " +
                    constants.databaseMeasurementName + " WHERE " + "users.id = hand_measurement.id;");

            this.users = new ArrayList<User>();
            while(result.next()) {
                users.add(new User(result.getString(2), result.getString(1), result.getInt(4), result.getInt(5),
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
