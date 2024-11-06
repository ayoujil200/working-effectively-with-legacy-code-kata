package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    UserSession userSession;
    @Mock
    TripDAO tripDAO;
    @InjectMocks
    TripService tripService;

    User loggedUser;
    User targetUser;

    @BeforeEach
    public void init() {
        loggedUser = new User();
        targetUser = new User();
    }

    @Test
    public void should_throw_UserNotLoggedInException_exception() {
        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(null);
        });
    }

    @Test
    public void logged_user_and_target_user_should_be_friends() {
        targetUser.addFriend(loggedUser);
        assertTrue(tripService.checkIfUserIsFriend(targetUser, loggedUser));
    }

    @Test
    public void logged_user_and_target_user_should_not_be_friends() {
        assertFalse(tripService.checkIfUserIsFriend(targetUser, loggedUser));
    }

    @Test
    public void should_return_logged_user_and_is_friend_and_without_trips() {
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        when(tripDAO.findTripsByUser(targetUser)).thenReturn(List.of());

        targetUser.addFriend(loggedUser);

        assertEquals(0, tripService.getTripsByUser(targetUser).size());
    }

    @Test
    public void should_target_user_is_friend_with_trips() {
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        when(tripDAO.findTripsByUser(targetUser)).thenReturn(List.of(new Trip(), new Trip()));

        targetUser.addFriend(loggedUser);

        assertNotEquals(0, tripService.getTripsByUser(targetUser).size());
    }
}
