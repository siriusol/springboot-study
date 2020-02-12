package xyz.ther.boot.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("admin", "dba", "user")
                .and()
                .withUser("admin").password("123").roles("admin", "user")
                .and()
                .withUser("Ther").password("123").roles("user");
    }

    @Configuration
    @Order(1)
    public static class DBAHttpSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/db/**")
                    .authorizeRequests()
                    .anyRequest()
                    .access("hasRole('admin') and hasRole('dba')");
        }
    }

    @Configuration
    @Order(2)
    public static class AdminHttpSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("admin");
        }
    }

    @Configuration
    @Order(3)
    public static class UserHttpSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/user/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("user");
        }
    }

    @Configuration
    public static class OtherHttpSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    // .loginPage("/login_page")
                    .loginProcessingUrl("/login")
                    .usernameParameter("name")
                    .passwordParameter("passwd")
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            Object principle = authentication.getPrincipal();
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            httpServletResponse.setStatus(200);
                            Map<String, Object> map = new HashMap<>();
                            map.put("status", 200);
                            map.put("msg", principle);
                            out.write(JSONObject.toJSONString(map));
                            out.flush();
                            out.close();
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            PrintWriter out = httpServletResponse.getWriter();
                            httpServletResponse.setStatus(401);
                            Map<String, Object> map = new HashMap<>();
                            map.put("status", 401);
                            if (e instanceof LockedException) {
                                map.put("msg", "账户被锁定，登陆失败！");
                            } else if (e instanceof BadCredentialsException) {
                                map.put("msg", "账户名或密码输入错误，登陆失败！");
                            } else if (e instanceof DisabledException) {
                                map.put("msg", "账户被禁用，登陆失败！");
                            } else if (e instanceof AccountExpiredException) {
                                map.put("msg", "账户已过期，登陆失败！");
                            } else if (e instanceof CredentialsExpiredException) {
                                map.put("msg", "密码已过期，登陆失败！");
                            } else {
                                map.put("msg", "登陆失败！");
                            }
                            out.write(JSONObject.toJSONString(map));
                            out.flush();
                            out.close();
                        }
                    })
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .addLogoutHandler(new LogoutHandler() {
                        @Override
                        public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                        }
                    })
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            httpServletResponse.sendRedirect("/login");
                        }
                    });
        }
    }
}
