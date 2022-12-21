package ba.unsa.etf.rpr.dao;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Adna Herak
 */
public class DaoFactory {
    public static final PlaysDao playsDao;

    static {
        try {
            playsDao = new PlaysDaoSQLimpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final plays_inDao playsin_Dao=new plays_inDaoSQLimpl();
    public static final ArtistDao artistDao=new ArtistDaoSQLimpl();
    public static final WritersDao writersDao=new WritersDaoSQLimpl();
    public static final DirectorsDao directorsDao=new DirectorsDaoSQLimpl();

    public DaoFactory() {
    }
    public static PlaysDao playsDao(){
        return playsDao;
    }
    public static plays_inDao plays_inDao(){
        return playsin_Dao;
    }
    public static ArtistDao artistDao(){
        return artistDao;
    }
    public static WritersDao writersDao(){
        return writersDao;
    }
    public static DirectorsDao directorsDao(){
        return directorsDao;
    }
}
