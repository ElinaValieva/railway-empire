package com.elina.railwayApp.DAO;

import com.elina.railwayApp.model.User;

import java.util.List;

/**
 * скопируй в проводник C:\Users\Elina\IdeaProjects\RailwayStationV2\web
 * жто он перезапустилсяя?
 * дэ
 * ахаха
 * боль
 * он тупой
 * :(
 * мне кажется, он не то собирал
 * вроде помник должен лежать на уровне с src
 */
public interface UserDAO {

    List<User> getUsers();

    void addUser(User user);

    void removeUser(User user);
}
