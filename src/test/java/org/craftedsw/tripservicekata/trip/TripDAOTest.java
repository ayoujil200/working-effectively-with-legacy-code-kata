package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripDAOTest {
    @Mock
    TripDAO tripDAO;
    User loggedUser;

    @BeforeEach
    public void init() {
        loggedUser = new User();
    }

    @Test
    public void should_return_user_trips() {
        when(tripDAO.findTripsByUser(loggedUser)).thenReturn(List.of(new Trip(), new Trip()));
        assertNotEquals(0, tripDAO.findTripsByUser(loggedUser).size());
    }

    @Test
    public void should_return_no_user_trips() {
        when(tripDAO.findTripsByUser(loggedUser)).thenReturn(List.of());
        assertEquals(0, tripDAO.findTripsByUser(loggedUser).size());
    }
}
