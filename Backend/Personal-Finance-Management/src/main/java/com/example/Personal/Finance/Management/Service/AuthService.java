package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.entity.LoginRequest;
import com.example.Personal.Finance.Management.entity.RegisterRequest;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public interface AuthService {
    public String register(RegisterRequest request) ;
    public String login(LoginRequest request) ;
}
