import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class JDBCExample {
        public static void main(String[] args) {

            List<User> result = new ArrayList<>();


            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://127.0.0.1:3306/firstdatabase", "postgres", "rudy1234");
                if (conn != null) {
                    System.out.println("Connected!");
                } else {
                    System.out.println("Failed to make connection");
                }
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select custname, customerid from public.userdata");
                while(rs.next()){
                    String s = rs.getString("custname");
                    int id = rs.getInt("customerid");
                    System.out.println(s + " " + id);

                    User usr = new User(s,id);
                    result.add(usr);
                }
               //what to do with the userData
                result.forEach(u -> System.out.println(u));


            }
            catch(SQLException e){
                System.err.format("SQL State: %s\n%s",e.getSQLState(),e.getMessage());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
