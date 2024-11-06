package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    UserSession userSession;
    User loggedUser;

    @BeforeEach
    public void init() {
        loggedUser = new User();
    }

    @Test
    public void should_return_logged_user() {
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        assertNotNull(userSession.getLoggedUser());
    }

    @Test
    public void should_not_return_logged_user() {
        when(userSession.getLoggedUser()).thenReturn(null);
        assertNull(userSession.getLoggedUser());
    }
}
