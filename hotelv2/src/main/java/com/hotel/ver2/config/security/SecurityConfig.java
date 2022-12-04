package com.hotel.ver2.config.security;

import com.hotel.ver2.config.security.JWT.JwtTokenFilter;
import com.hotel.ver2.config.security.JWT.JwtTokenProvider;
import com.hotel.ver2.config.security.JWT.JwtTokenProvider;
import com.hotel.ver2.service.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
@ComponentScan("com.hotel")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    DataSource dataSource;
@Autowired
    PasswordEncoder passwordEncoder;

   @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        //httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //http.authorizeRequests().antMatchers("/").permitAll().and().formLogin().loginPage("/login");
        http.httpBasic().disable()
                .csrf().disable()
              //  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               // .and()
                .authorizeRequests()
                //.antMatchers("/login").permitAll()
                //.antMatchers("/register").permitAll()
                // .antMatchers("/greetings-with-response-body").permitAll()
                // .antMatchers(" /admin/**").hasRole("ADMIN")
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/registration").permitAll()
                .anyRequest().authenticated()
                //.antMatchers("/").authenticated()
                .and().formLogin()
                .loginPage("/admin/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/admin");



        /*http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers("/login").permitAll()
                //.antMatchers("/register").permitAll()
               // .antMatchers("/greetings-with-response-body").permitAll()
               // .antMatchers(" /admin/**").hasRole("ADMIN")
                .antMatchers("/").permitAll().and().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/admin/home")
                .usernameParameter("username")
                .passwordParameter("password")

        ;
                //.anyRequest().authenticated()
               // .and().addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //.apply(new JwtConfigurer(jwtTokenProvider));.antMatchers(" /admin/**").hasRole("ADMIN")*/
    }

}