package com.exec.demo.majiang.controller;

import com.exec.demo.majiang.dto.AccessTokenDto;
import com.exec.demo.majiang.dto.GithubUser;
import com.exec.demo.majiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("8217344a14abe7e60cda");
        accessTokenDto.setClient_secret("1b9c45d5fc84450750489fe6de6150fe062a867c");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        String token=githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(token);
        System.out.print(user.getName());
        return  "index";
    }
}
