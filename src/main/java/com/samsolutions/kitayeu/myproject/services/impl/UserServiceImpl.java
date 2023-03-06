package com.samsolutions.kitayeu.myproject.services.impl;


import com.samsolutions.kitayeu.myproject.converters.DtoToUserConverter;
import com.samsolutions.kitayeu.myproject.converters.UserToUserDtoConverter;
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
        UserToUserDtoConverter userToDtoConverter = new UserToUserDtoConverter();
        return userRepository.findAll(paging).stream()
                .map(userToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllUsers() {
        UserToUserDtoConverter userToDtoConverter = new UserToUserDtoConverter();
        return userRepository.findAll().stream()
                .map(userToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {
        UserToUserDtoConverter userToDtoConverter = new UserToUserDtoConverter();
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
            throw new DeleteOrChangeEntityNotAllowException("1001");
        }
        if (updatedUser.getUserId() != null) {
            if (id != updatedUser.getUserId()) { // userId can`t be changed
                throw new IdMismatchException("1005");
            }
        }
        User readUser = userRepository.findById(id).orElse(null);
        if (readUser == null) {
            throw new UserNotFoundException("1008");
        } else {
            if (updatedUser.getUserName() != null & updatedUser.getUserMail() != null) {  //userName and UserMail from DTO isn`t null
                if (userRepository.existsUserByUserName(updatedUser.getUserName())) {    // check userName
                    if (userRepository.findUserByUserName(updatedUser.getUserName()).getUserId() != id) {
                        throw new UserNameDuplicateException("1007");
                    } else {
                        if (userRepository.existsUserByUserMail(updatedUser.getUserMail())) {  //check UserMail when Username is OK
                            if (userRepository.findUserByUserMail(updatedUser.getUserMail()).getUserId() != id) {
                                throw new UserMailDuplicateException("1006");
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
                    }
                } else if (userRepository.existsUserByUserMail(updatedUser.getUserMail())) {  //check UserMail when Username is OK
                    if (userRepository.findUserByUserMail(updatedUser.getUserMail()).getUserId() != id) {
                        throw new UserMailDuplicateException("1006");
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
            } else if (updatedUser.getUserName() == null & updatedUser.getUserMail() != null) {  //userName from DTO is null but UserMail isn`t null
                if (userRepository.existsUserByUserMail(updatedUser.getUserMail())) {
                    if (userRepository.findUserByUserMail(updatedUser.getUserMail()).getUserId() != id) {
                        throw new UserMailDuplicateException("1006");
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
            } else if (updatedUser.getUserMail() == null & updatedUser.getUserName() != null) { //userName from DTO isn`t null, UserMail is null
                if (userRepository.existsUserByUserName(updatedUser.getUserName())) {
                    if (userRepository.findUserByUserName(updatedUser.getUserName()).getUserId() != id) {
                        throw new UserNameDuplicateException("1007");
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
                return false;  // UserName and UserMail from DTO are null
            }
        }
    }
}