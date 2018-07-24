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
import java.text.ParseException;
import java.util.Date;
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
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO userDTO, User user) throws ParseException, BusinessLogicException {
        Date birthDay = Utils.parseToDate(userDTO.getBirthDay());

        if (!birthDay.before(new Date()))
            throw new BusinessLogicException(ErrorCode.WRONG_BIRTHDAY.getMessage());

        if (findByEmail(userDTO.getLogin()) != null && !findByEmail(userDTO.getLogin()).getLogin().equals(user.getLogin()))
            throw new BusinessLogicException(ErrorCode.WRONG_LOGIN.getMessage());

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setLogin(userDTO.getLogin());
        user.setBirthDay(userDTO.getBirthDay());
        user.setSex(userDTO.getSex());
        userDAO.updateProfile(user);
    }

    @Override
    @Transactional
    public void registration(UserDTO userDTO) throws IOException, BusinessLogicException, MessagingException {
        if (userDTO == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        if (findByEmail(userDTO.getLogin()) != null)
            throw new BusinessLogicException(ErrorCode.USER_ALREADY_EXIST.getMessage());

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