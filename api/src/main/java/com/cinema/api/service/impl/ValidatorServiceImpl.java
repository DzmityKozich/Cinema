package com.cinema.api.service.impl;

import com.cinema.api.model.LoginModel;
import com.cinema.api.service.ValidatorService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public boolean validator(String login, List<LoginModel> loginModelList) {
        for(LoginModel loginModel : loginModelList){
            if(login.equals(loginModel.getEmail())){
                return false;
            }
        }
        return true;
    }
}
