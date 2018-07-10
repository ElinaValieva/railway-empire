package com.elina.railwayApp.service.Implementation;

import com.elina.railwayApp.DAO.UserDAO;
import com.elina.railwayApp.DTO.UserDTO;
import com.elina.railwayApp.configuration.common.Utils;
import com.elina.railwayApp.exception.BusinessLogicException;
import com.elina.railwayApp.exception.ErrorCode;
import com.elina.railwayApp.model.Message;
import com.elina.railwayApp.model.Role;
import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.MailService;
import com.elina.railwayApp.service.RoleService;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Log4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        userDAO.remove(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User findByEmail(User user) {
        return userDAO.findUserByEmail(user);
    }

    @Override
    @Transactional
    public User findByEmail(String login) {
        return userDAO.findUserByEmail(login);
    }

    @Override
    @Transactional
    public User findUserByForm(User user) {
        return userDAO.findUserByForm(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void updateProfile(User user) {
        String password = user.getPassword();
        user.setPassword(Utils.encodePassword(password));
        userDAO.updateProfile(user);
    }

    @Override
    @Transactional
    public void registration(UserDTO userDTO) throws IOException, BusinessLogicException, MessagingException {
        if (findByEmail(userDTO.getLogin()) != null)
            throw new BusinessLogicException(ErrorCode.USER_ALREADY_EXIST.getMessage());

        if (userDTO == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        Role role = roleService.getRole();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(Utils.encodePassword(userDTO.getPassword()));
        user.setRoles(roleSet);
        Message message = Message.createWelcomeMessage(userDTO.getLogin());
        mailService.sendMimeMessage(message);
        add(user);
    }

}