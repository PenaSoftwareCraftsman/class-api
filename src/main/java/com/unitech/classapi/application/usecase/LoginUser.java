package com.unitech.classapi.application.usecase;

import com.unitech.classapi.application.exceptions.InvalidEmailOrPasswordException;
import com.unitech.classapi.application.port.UserPort;
import com.unitech.classapi.domain.entity.Password;
import com.unitech.classapi.domain.entity.Token;
import com.unitech.classapi.domain.entity.User;
import com.unitech.classapi.infrastructure.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUser {

    private final UserPort userPort;

    private final JwtUtil jwtUtil;

    public Token execute(String email, String password){
        User user = this.userPort.fetchByEmail(email);

        if(user == null) throw new InvalidEmailOrPasswordException();

        if(!Password.validate(password, user.getPassword())) throw new InvalidEmailOrPasswordException();

        return this.jwtUtil.generateToken(user);
    }
}
