package com.dockerspringbootv1.Controller;

import com.dockerspringbootv1.Entity.BaseResponse;
import com.dockerspringbootv1.Entity.User;
import com.dockerspringbootv1.request.UserRegistrationRequest;
import com.dockerspringbootv1.request.UserUpdatingRequest;
import com.dockerspringbootv1.response.UserResponse;
import com.dockerspringbootv1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    private BaseResponse<UserResponse> createUser(@RequestBody UserRegistrationRequest userRegistration){
        return userService.createUser(userRegistration);
    }

    @PutMapping("/{userId}")
    private BaseResponse<UserResponse> updateUser(@RequestBody UserUpdatingRequest userUpdatingRequest,@PathVariable Long userId){
        return userService.updateUser(userUpdatingRequest, userId);
    }

    @GetMapping("/{userId}")
    private BaseResponse<UserResponse> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @GetMapping
    private List<BaseResponse<UserResponse>> getUsers(){
        return userService.getUsers();
    }

    @DeleteMapping("/{userId}")
    private BaseResponse<User> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
