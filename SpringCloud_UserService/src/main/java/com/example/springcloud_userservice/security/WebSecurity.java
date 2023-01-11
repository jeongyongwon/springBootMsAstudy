package com.example.springcloud_userservice.security;

import com.example.springcloud_userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Environment env;

    private String IP_ADDRESS = "192.168.1.114";

    //권한에 관련된 내용
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //이제는 모든 작업에 대해서 모두 허가하지 않음
        //http.authorizeRequests().antMatchers("/users/**").permitAll(); // 인증작업없이 해당 uri는 사용할 수 있음

        //모든 코드에 관해서 통과시키지 않을것
        //특정ip만 허용
        String IP_ADDRESS = "192.168.1.114";

        http.authorizeRequests()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/**")
                .access("hasIpAddress('"+IP_ADDRESS+"')")
                .and()
                .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }


    //인증에 관련된 작업
    // db에는 암호화된 패스워드가 있으니 사용자가 입력하는 패스워드를 암호화 시켜 비교함
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)  //select pwd from user where user_no = ''  이런기능을 하는 부분이라 생각하면됨
                .passwordEncoder(bCryptPasswordEncoder);
    }
}


