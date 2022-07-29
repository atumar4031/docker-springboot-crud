package com.dockerspringbootv1.service.impl;

import com.dockerspringbootv1.Entity.BaseResponse;
import com.dockerspringbootv1.Entity.User;
import com.dockerspringbootv1.repository.UserRepository;
import com.dockerspringbootv1.request.UserRegistrationRequest;
import com.dockerspringbootv1.request.UserUpdatingRequest;
import com.dockerspringbootv1.response.UserResponse;
import com.dockerspringbootv1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public BaseResponse<UserResponse> createUser(UserRegistrationRequest userRegistration) {
        User user = modelMapper.map(userRegistration, User.class);
        userRepository.save(user);

        return new BaseResponse<>(
                HttpStatus.OK,
                "user registration success",
                modelMapper.map(user, UserResponse.class));
    }

    @Override
    public BaseResponse<UserResponse> getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        return new BaseResponse<>(HttpStatus.OK, "user", modelMapper.map(user, UserResponse.class));
    }

    @Override
    public List<BaseResponse<UserResponse>> getUsers() {
        List<BaseResponse<UserResponse>> userResponse = new ArrayList<>();
        List<User> response = userRepository.findAll();

        for (User user: response) {
            UserResponse userResponse1 = modelMapper.map(user, UserResponse.class);
            userResponse.add(new BaseResponse<>(HttpStatus.OK, "user", userResponse1));
        }
        return userResponse;
    }

    @Override
    public BaseResponse<UserResponse> updateUser(UserUpdatingRequest updatingRequest, Long userId) {
        log.info("update user");
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty())
            return new BaseResponse<>(HttpStatus.BAD_REQUEST, "user not found", null);

        User user = optionalUser.get();

        if (!updatingRequest.getEmail().isEmpty())
            user.setEmail(updatingRequest.getEmail());

        if (!updatingRequest.getName().isEmpty())
            user.setName(updatingRequest.getName());

        if (!updatingRequest.getPhone().isEmpty())
            user.setPhone(updatingRequest.getPhone());

        User saved = userRepository.save(user);

        return new BaseResponse<>(
                HttpStatus.OK,
                "user updated",
                modelMapper.map(saved, UserResponse.class));
    }

    @Override
    public BaseResponse<User> deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null)
            return new BaseResponse<>(HttpStatus.BAD_REQUEST, "user not found", null);

        userRepository.delete(user);
        return new BaseResponse<>(HttpStatus.OK, "user deleted", null);
    }
}
