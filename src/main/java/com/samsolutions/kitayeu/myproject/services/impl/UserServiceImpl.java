package com.samsolutions.kitayeu.myproject.services.impl;


import com.samsolutions.kitayeu.myproject.converters.DtoToUserConverter;
import com.samsolutions.kitayeu.myproject.converters.UserToDtoConverter;
import com.samsolutions.kitayeu.myproject.dtos.UserDto;
import com.samsolutions.kitayeu.myproject.entities.User;
import com.samsolutions.kitayeu.myproject.exceptions.*;
import com.samsolutions.kitayeu.myproject.repositories.UserRepository;
import com.samsolutions.kitayeu.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Value("5")
    private int pageSize;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers(int page) {
        Pageable paging = PageRequest.of(page, pageSize);
        UserToDtoConverter userToDtoConverter = new UserToDtoConverter();
        return userRepository.findAll(paging).stream()
                .map(userToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllUsers() {
        UserToDtoConverter userToDtoConverter = new UserToDtoConverter();
        return userRepository.findAll().stream()
                .map(userToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {
        UserToDtoConverter userToDtoConverter = new UserToDtoConverter();
        if (userRepository.findById(id).isPresent()) {
            return userToDtoConverter.convert(userRepository.findById(id).get());
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean updateUser(UserDto userDto, int id) {
        DtoToUserConverter dtoToUserConverter = new DtoToUserConverter();
        User updatedUser = dtoToUserConverter.convert(userDto);
        if (id == 0) {  // userId=0 is reserved and can`t be changed
            throw new DeleteOrChangeEntityNotAllowException("2000");
        }
        if (updatedUser.getUserId() != null) {
            if (id != updatedUser.getUserId()) { // userId can`t be changed
                throw new UserIdChangeNotAllowException("2001");
            }
        }
        User readUser = userRepository.findById(id).orElse(null);
        if (readUser == null) {
            throw new UserNotFoundException("3001");
        } else {
            if (updatedUser.getUserName() != null & updatedUser.getUserMail() != null) {
                if (userRepository.existsUserByUserName(updatedUser.getUserName())) {
                    if (userRepository.findUserByUserName(updatedUser.getUserName()).getUserId() != id) {
                        throw new UserNameDuplicateException("1001");
                    } else if (userRepository.existsUserByUserMail(updatedUser.getUserMail())) {
                        if (userRepository.findUserByUserMail(updatedUser.getUserMail()).getUserId() != id) {
                            throw new UserMailDuplicateException("1002");
                        } else {
                            updatedUser.setUserId(id);
                            updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                            userRepository.saveAndFlush(updatedUser);
                            return true;
                        }
                    } else {
                        updatedUser.setUserId(id);
                        updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                        userRepository.saveAndFlush(updatedUser);
                        return true;
                    }
                } else if (userRepository.existsUserByUserMail(updatedUser.getUserMail())) {
                    if (userRepository.findUserByUserMail(updatedUser.getUserMail()).getUserId() != id) {
                        throw new UserMailDuplicateException("1002");
                    } else {
                        updatedUser.setUserId(id);
                        updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                        userRepository.saveAndFlush(updatedUser);
                        return true;
                    }
                } else {
                    updatedUser.setUserId(id);
                    updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                    userRepository.saveAndFlush(updatedUser);
                    return true;
                }
            } else if (updatedUser.getUserName() == null & updatedUser.getUserMail() != null) {
                if (userRepository.existsUserByUserMail(updatedUser.getUserMail())) {
                    if (userRepository.findUserByUserMail(updatedUser.getUserMail()).getUserId() != id) {
                        throw new UserMailDuplicateException("1002");
                    } else {
                        updatedUser.setUserId(id);
                        updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                        updatedUser.setUserName(readUser.getUserName());
                        userRepository.saveAndFlush(updatedUser);
                        return true;
                    }
                } else {
                    updatedUser.setUserId(id);
                    updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                    updatedUser.setUserName(readUser.getUserName());
                    userRepository.saveAndFlush(updatedUser);
                    return true;
                }
            } else if (updatedUser.getUserMail() == null & updatedUser.getUserName() != null) {
                if (userRepository.existsUserByUserName(updatedUser.getUserName())) {
                    if (userRepository.findUserByUserName(updatedUser.getUserName()).getUserId() != id) {
                        throw new UserNameDuplicateException("1001");
                    } else {
                        updatedUser.setUserId(id);
                        updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                        updatedUser.setUserMail(readUser.getUserMail());
                        userRepository.saveAndFlush(updatedUser);
                        return true;
                    }
                } else {
                    updatedUser.setUserId(id);
                    updatedUser.setUserPasswordHash(readUser.getUserPasswordHash());
                    updatedUser.setUserMail(readUser.getUserMail());
                    userRepository.saveAndFlush(updatedUser);
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}



















