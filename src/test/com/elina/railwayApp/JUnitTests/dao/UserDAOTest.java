package com.elina.railwayApp.JUnitTests.dao;

import com.elina.railwayApp.model.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UserDAOTest {


    public List<User> findUserByEmailTest2(String s) {
        List<User> users = findUserByEmailTest2(s);
        return users;
    }

    @Test
    public void findUserByEmailTest(String s) {
        assertNotNull(findUserByEmailTest2("elina@mail.ru"));
    }


}