package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Adna Herak
 */
public class DaoFactory {
    public static final PlaysDao playsDao=new PlaysDaoSQLimpl();
    public static final plays_inDao playsin_Dao=new plays_inDaoSQLimpl();
    public static final ArtistsDao ARTISTS_DAO =new ArtistsDaoSQLimpl();
    public static final WritersDao writersDao=new WritersDaoSQLimpl();
    public static final DirectorsDao directorsDao=new DirectorsDaoSQLimpl();
public static final UsersDao usersDao=new UserDaoSQLimpl();
    public DaoFactory() {
    }
    public static PlaysDao playsDao(){
        return playsDao;
    }
    public static plays_inDao plays_inDao(){
        return playsin_Dao;
    }
    public static ArtistsDao artistDao(){
        return ARTISTS_DAO;
    }
    public static WritersDao writersDao(){
        return writersDao;
    }
    public static DirectorsDao directorsDao(){
        return directorsDao;
    }
    public static UsersDao usersDao(){return usersDao;}

    public static plays_inDao playsin_Dao() {
        return playsin_Dao;
    }
}
