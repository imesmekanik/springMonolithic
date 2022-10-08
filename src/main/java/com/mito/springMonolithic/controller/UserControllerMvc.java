package com.mito.loginDeneme.controller;

import com.mito.loginDeneme.repository.entity.User;
import com.mito.loginDeneme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import static com.mito.loginDeneme.constants.EndPoints.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserControllerMvc {


    private final UserService userService;

    @GetMapping(REGISTER)
    public String getRegisterPage(Model model) {
        //model.addAttribute("registerRequest",new User());
        return "register_page";
    }

    @GetMapping(LOGIN)
    public String getLoginPage(Model model) {
        //model.addAttribute("loginRequest",new User());
        return "login_page";
    }

    @PostMapping(REGISTER)
    public String register(@ModelAttribute User user){
        System.out.println("register request:" + user);
        User registeredUser=userService.registerUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getGender().toString());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping(LOGIN)
    public String login(@ModelAttribute User user,Model model){
        System.out.println("login request:" + user);
        User authanticatedUser=userService.authanticate(user.getUsername(),user.getPassword());
        if(authanticatedUser!=null){
            if(authanticatedUser.getGender().toString().equals("ERKEK")){
                model.addAttribute("cssTemp","css/personalPageErkek.css");
            } else {
                model.addAttribute("cssTemp","css/personalPageKadin.css");
            }
            model.addAttribute("userLogin",authanticatedUser.getUsername());

            List<Integer> imgArrayId= new ArrayList<Integer>();
            int rand=(int)((Math.random()*6)+2);
            for(int i=0;i<rand;i++){
                imgArrayId.add((int)((Math.random()*52)+1));

            }
            model.addAttribute("imgArrayId",imgArrayId);

            return "personal_page";
        }else {
            return "error_page";
        }

    }
}
