package net.javaguide.Food_Ordering_Application.config;


import net.javaguide.Food_Ordering_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


//   @Autowired
//   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .inMemoryAuthentication()
//                .authenticationProvider(authenticationProvider());
//
//
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .securityMatcher("/secured/**")
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/registration**","/js/**","/css/**","/img/**").permitAll();
                    auth.anyRequest().authenticated();

                })
//                .formLogin(formLogin -> formLogin
//                                .loginPage("/secured/login")
//                                .loginProcessingUrl("/secured/login")
//                                .permitAll()
//                        )
//                .logout(logout -> logout
//                        .logoutUrl("/secured/logout")
//                        .logoutSuccessUrl("/secured/login?logout")
//                        .permitAll()
//                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }


//@Bean
//public SecurityFilterChain defaultFilterChai(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests((authorize -> authorize
//                        .anyRequest().denyAll()
//                        ));
//        return http.build();
//}

}
