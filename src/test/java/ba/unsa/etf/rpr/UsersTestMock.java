package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.PlaysException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UsersTestMock {
    UsersManager userManager;

    @BeforeEach
    public void initializeObjectsWeNeed() {
        userManager = new UsersManager();
    }

    @Test
    void validateuserExists() throws PlaysException {
        MockedStatic<DaoFactory> dao = Mockito.mockStatic(DaoFactory.class);
        UsersDao UD = Mockito.mock(UsersDao.class);
        when(DaoFactory.usersDao()).thenReturn(UD);
        when(DaoFactory.usersDao().searchByUsername("aherak2")).thenReturn(new Users("aherak2","a123"));

        boolean x = UsersManager.validateUser("aherak2","a123");
        assertTrue(x);
        dao.close();
    }

}
