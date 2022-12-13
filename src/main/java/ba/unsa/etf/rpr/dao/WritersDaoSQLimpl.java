package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WritersDaoSQLimpl implements WritersDao {
    private Connection connection;
    public WritersDaoSQLimpl(){
        try {
            FileReader reader = new FileReader("");
            Properties p = new Properties();
            p.load(reader);
            String url = p.getProperty("url");
            String user = p.getProperty("username");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582716", "sql7582716", "yx92Ppzp5V");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Writers getById(int id) {
        String query = "SELECT * FROM Writers where writer_id=?";
        List<Writers> writers = new ArrayList<Writers>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Writers writer = new Writers();
                writer.setWriter_id(rs.getInt("writer_id"));
                writer.setFirst_name(rs.getString("first_name"));
                writer.setLast_name(rs.getString("last_name"));
                rs.close();
                return writer;
            }

        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;

    }

    @Override
    public List<Writers> getAll() {
        String query = "SELECT * FROM Writers";
        List<Writers> writers = new ArrayList<Writers>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Writers writer = new Writers();
                writer.setWriter_id(rs.getInt("writer_id"));
                writer.setFirst_name(rs.getString("first_name"));
                writer.setLast_name(rs.getString("last_name"));
                writers.add(writer);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return writers ;

    }
}
