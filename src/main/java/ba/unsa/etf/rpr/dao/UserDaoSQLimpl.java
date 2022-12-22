package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UserDaoSQLimpl extends AbstractDao1<Users> implements UsersDao{

    public UserDaoSQLimpl() {
        super("Users");
    }

    @Override
    public Users row2object(ResultSet rs) throws Exception {
        try {
            Users user = new Users();
          user.setId(rs.getInt("user_id"));
          user.setUsername(rs.getString("username"));
          user.setPassword(rs.getString("password"));
          user.setFirst_name(rs.getString("first_name"));
          user.setLast_name(rs.getString("last_name"));
          user.setLocation(rs.getString("location"));
          user.setGender(rs.getString("gender"));
          user.setDate_of_birth(rs.getDate("date_of_birth"));
            return user;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @Override
    public Map<String, Object> object2row(Users object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("user_id", object.getId());
        row.put("username", object.getUsername());
        row.put("first_name",object.getFirst_name());
        row.put("last_name",object.getLast_name());
        row.put("date_of_birth",object.getDate_of_birth());
        row.put("password",object.getPassword());
        row.put("gender",object.getGender());
        row.put("location",object.getLocation());
        return row;
    }
    public Users searchByUsername(String user){
        String query = "SELECT * FROM Users WHERE username = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
return row2object(rs);
            } else {
                return null; // if there is no elements in the result set return null
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return null;
    }
}