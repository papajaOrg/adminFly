package org.papaja.adminify.config;

import org.papaja.adminify.service.SpringUserDetails;
import org.papaja.adminify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings({"unused"})
@Configuration
@EnableWebSecurity
public class SpringSecureConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new SpringUserDetails();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(getPasswordEncoder());

        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
            .authorizeRequests().antMatchers("/wallPage").hasAnyRole("ADMIN", "USER")
            .and()
            .authorizeRequests().antMatchers("/login", "/resource/**").permitAll()
            .and()
            .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
            .loginProcessingUrl("/doLogin")
            .successForwardUrl("/postLogin")
            .failureUrl("/loginFailed")
            .and()
            .logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
            .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
