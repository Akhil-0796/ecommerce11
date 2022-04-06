package com.example.ecommerce.ecommerce.config;

import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider();
         daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
         daoAuthenticationProvider.setPasswordEncoder(this.getPasswordEncoder());
         return daoAuthenticationProvider;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DtoMapper getDtoMapper(){
        return new DtoMapper();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("api/admin/**").hasRole("ADMIN")
                .antMatchers("api/user/**").hasRole("USER")
                .antMatchers(("api/supplier/**")).hasRole("SUPPLIER")
                .antMatchers("api/**").permitAll();
    }
}