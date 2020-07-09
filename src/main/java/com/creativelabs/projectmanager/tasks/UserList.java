package com.creativelabs.projectmanager.tasks;

import java.util.LinkedList;
import java.util.List;

public class UserList {

    private final List<User> usersList = new LinkedList<>();
    private final String usersListName;

    public UserList(final String usersListName) {
        this.usersListName = usersListName;
    }

    public void addUser(User user) {usersList.add(user);}

    public boolean removeUser(User user) { return usersList.remove(user);}

    public List<User> getUsersList() {
        return new LinkedList<>(usersList);
    }

    public String getUsersListName() {
        return usersListName;
    }

    @Override
    public String toString() {
        return "UserList{" +
                ", usersListName='" + usersListName + '\'' +
                "usersList=" + usersList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserList)) return false;
        UserList usersList = (UserList) o;
        return usersListName.equals(usersList.usersListName);
    }
}
