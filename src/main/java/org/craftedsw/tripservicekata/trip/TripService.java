package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {
    private final UserSession userSession;
    private final TripDAO tripDAO;

    public TripService(UserSession userSession, TripDAO tripDAO) {
        this.userSession = userSession;
        this.tripDAO = tripDAO;
    }

    public User getLoggedUser() {
        return userSession.getLoggedUser();
    }

    public boolean checkIfUserIsFriend(User user, User loggedUser) {
        return user.getFriends().stream().anyMatch(friend -> friend.equals(loggedUser));
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (getLoggedUser() != null) {
            return checkIfUserIsFriend(user, getLoggedUser()) ? tripDAO.findTripsByUser(user) : List.of();
        } else {
            throw new UserNotLoggedInException();
        }
    }
}