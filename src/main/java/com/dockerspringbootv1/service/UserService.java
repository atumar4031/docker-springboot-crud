package com.dockerspringbootv1.service;

import com.dockerspringbootv1.Entity.BaseResponse;
import com.dockerspringbootv1.Entity.User;
import com.dockerspringbootv1.request.UserRegistrationRequest;
import com.dockerspringbootv1.request.UserUpdatingRequest;
import com.dockerspringbootv1.response.UserResponse;

import java.util.List;

public interface UserService {

    // TODO: All ID's should be gotten from user context after login

    BaseResponse<UserResponse> createUser(UserRegistrationRequest userRegistration);
    BaseResponse<UserResponse> getUser(Long userId);
    List<BaseResponse<UserResponse>> getUsers();
    BaseResponse<User> deleteUser(Long id);
    BaseResponse<UserResponse> updateUser(UserUpdatingRequest updatingRequest, Long userId);
}
