package it.uniroma3.siw.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
//public  class WebSecurityConfig {
	public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    @Lazy
    private CustomOAuth2UserService customOAuth2UserService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .authoritiesByUsernameQuery("SELECT username, role from credentials WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity)
            throws Exception{
    	httpSecurity
        .cors().disable()
        .csrf().disable()
        .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,
                        "/", "/index", "/register", "/login",
                        "/css/**", "/images/**", "/uploads/**", "/favicon.ico",
                        "/anyone/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()
            
            // LOGIN:
            .and().formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/success",true)
            .failureUrl("/login?error=true")
            .and()
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/success", true)
                    .userInfoEndpoint(userInfo -> userInfo
                            .userService(customOAuth2UserService)
                    )
            )
          // LOGOUT:
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .clearAuthentication(true).permitAll();
    return httpSecurity.build();
            
    }
}
