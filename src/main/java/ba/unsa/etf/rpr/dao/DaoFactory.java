package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Adna Herak
 */
public class DaoFactory {
    private static final PlaysDao playsDao=PlaysDaoSQLimpl.getInstance();
    private static final plays_inDao playsin_Dao=plays_inDaoSQLimpl.getInstance();
    private static final ArtistsDao ARTISTS_DAO =ArtistsDaoSQLimpl.getInstance();
    private static final WritersDao writersDao=WritersDaoSQLimpl.getInstance();
    private static final DirectorsDao directorsDao=DirectorsDaoSQLimpl.getInstance();
    private static final UsersDao usersDao=UserDaoSQLimpl.getInstance();
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
