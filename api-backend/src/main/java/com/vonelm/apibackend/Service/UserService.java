package com.vonelm.apibackend.Service;

import com.vonelm.apibackend.Model.Event;
import com.vonelm.apibackend.Model.User;
import com.vonelm.apibackend.Repository.EventRepository;
import com.vonelm.apibackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User assignUserToEvent(Integer userId, Integer eventId) {
        Set<Event> assignedEvents = null;
        Set<User> usersAssigned = null;
        User foundUser = userRepository.findById(userId).get();
        Event foundEvent = eventRepository.findById(eventId).get();
        //Add event to user
        assignedEvents = foundUser.getEvents();
        assignedEvents.add(foundEvent);
        foundUser.setEvents(assignedEvents);
        userRepository.save(foundUser);
        //Add user to event
        usersAssigned = foundEvent.getUsers();
        usersAssigned.add(foundUser);
        foundEvent.setUsers(usersAssigned);
        eventRepository.save(foundEvent);
        return foundUser;
    }
}
