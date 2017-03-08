import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.*;
import java.io.*;

import static java.lang.Math.abs;


/**
 * Created by Arunan on 2/18/2017.
 */
public class UserLab {
    private static UserLab userLab;
    private int oS;  //OffSet

    private List<User> users;

    String saveFileLocation = constants.saveFileLocation;
    String lineSeperator = System.getProperty("line.separator");
    Scanner reader = new Scanner(System.in);

    public static UserLab get(){
        if (userLab == null){
            userLab = new UserLab();
        }
        return userLab;
    }

    private UserLab(){
        getAllUsers();
    }

    //method to get users close to the given search input
    /*
      Get a weighted average from all the measurements and return the matched user values with a threshold value.
    */
    public String getUsers(int tL, int iFL, int mFL, int rFL, int pL, int tW, int iFW, int mFW, int rFW, int pFW){
        String resultString = "Result\n";
        String resultString1 = "";
        for (int i = 0; i < this.users.size(); i++){
            float sumAve = 0;


            sumAve += (1.0-((float)(abs(users.get(i).getThumbLength() - tL))/users.get(i).getThumbLength()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getIndexFingerLength() - iFL))/users.get(i).getIndexFingerLength()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getMiddleFingerLength() - mFL))/users.get(i).getMiddleFingerLength()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getRingFingerLength() - rFL))/users.get(i).getRingFingerLength()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getPinkieFingerLength() - pL))/users.get(i).getPinkieFingerLength()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getThumbWidth() - tW))/users.get(i).getThumbWidth()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getIndexFingerWidth() - iFW))/users.get(i).getIndexFingerWidth()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getMiddleFingerWidth() - mFW))/users.get(i).getMiddleFingerWidth()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getRingFingerWidth() - rFW))/users.get(i).getRingFingerWidth()))*100;
            sumAve += (1.0-((float)(abs(users.get(i).getPinkieFingerWidth() - pFW))/users.get(i).getPinkieFingerWidth()))*100;

            double averagePerc = sumAve/10.0;

            if (averagePerc > 98.0){
                resultString1 += "" + users.get(i).getName() + "\t \t " + averagePerc + "\n";
            }

        }
        if (resultString1.equals("")){
          resultString1 += "No Results Found\n";
        }
        return resultString + resultString1;
    }

    //method to get all users from the savedFileLocation
    public List<User> getAllUsers(){
      this.users = new ArrayList<User>();
      String line = "";
      try {
          FileReader fileReader =
                  new FileReader(saveFileLocation);

          BufferedReader bufferedReader =
                  new BufferedReader(fileReader);

          while ((line = bufferedReader.readLine()) != null) {
              String[] lineArray = line.split(" ");
              User newUser = new User(lineArray[0],
                      lineArray[1],
                      Integer.parseInt(lineArray[2]),
                      Integer.parseInt(lineArray[3]),
                      Integer.parseInt(lineArray[4]),
                      Integer.parseInt(lineArray[5]),
                      Integer.parseInt(lineArray[6]),
                      Integer.parseInt(lineArray[7]),
                      Integer.parseInt(lineArray[8]),
                      Integer.parseInt(lineArray[9]),
                      Integer.parseInt(lineArray[10]),
                      Integer.parseInt(lineArray[11]) );
              users.add(newUser);
          }

          // Always close files.
          bufferedReader.close();
      } catch (FileNotFoundException ex) {
          System.out.println("Unable to open file '" + saveFileLocation + "'");
      } catch (IOException ex) {
          System.out.println("Error reading file '" + saveFileLocation + "'");
      }

        return users;
    }


    //method to add new user
    public void addUser(User user){
      try {
          FileWriter fileWriter = new FileWriter(saveFileLocation, true);

          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
          String writeLine = "" + user.getName() + " " + user.getUserId() + " " + user.getThumbLength() + " " +
                              user.getIndexFingerLength() + " " + user.getMiddleFingerLength() + " " +
                              user.getRingFingerLength() + " " + user.getPinkieFingerLength() + " " +
                              user.getThumbWidth() + " " + user.getIndexFingerWidth() + " " + user.getMiddleFingerWidth() + " " +
                              user.getRingFingerWidth() +" " + user.getPinkieFingerWidth();
          bufferedWriter.write(writeLine );
          bufferedWriter.write(lineSeperator);

          bufferedWriter.close();
      } catch (IOException ex) {
          System.out.println(
                  "Error writing to file '"
                          + saveFileLocation + "'");
      }

        users = getAllUsers();

//
//            ResultSet rs=stmt.executeQuery("select * from practise_user");
    }
}
