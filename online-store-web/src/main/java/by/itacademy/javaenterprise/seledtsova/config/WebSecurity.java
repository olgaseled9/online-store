package by.itacademy.javaenterprise.seledtsova.config;

import by.itacademy.javaenterprise.seledtsova.config.handler.WebUrlSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(2)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public WebSecurity(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/users/add")
                .permitAll()
                .antMatchers("/users/allusers", "/users/remove", "users/update-role/{id}", "users/update-role", "reviews/get")
                .hasRole(RoleType.ADMINISTRATOR.name())
                .antMatchers("/add-item", "/delete-item-by-id", "/show/show-order-by-id", "/orders/update-status")
                .hasAnyRole(RoleType.ADMINISTRATOR.name(), RoleType.SALE_USER.name())
                .antMatchers("/profiles/get", "/get", "/add-item-to-order", "orders/show", "/show-item-by-id", "profiles/get", "profiles/update", "/items")
                .hasAnyRole(RoleType.ADMINISTRATOR.name(), RoleType.SALE_USER.name(), RoleType.CUSTOMER_USER.name())
                .antMatchers("/reviews/add", "reviews/get" )
                .hasRole(RoleType.CUSTOMER_USER.name())
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(new WebUrlSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied")
                .and()
                .csrf()
                .disable();
    }
}
