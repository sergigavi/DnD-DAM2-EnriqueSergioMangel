package esm.dnd.DnDDAM2EnriqueSergioMangel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//sin esto no me deja hacer las POST por el csrf
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Sergio").password("s").roles("ADMIN")
                .and()
                .withUser("Enrique").password("e").roles("ADMIN")
                .and()
                .withUser("MiguelAngel").password("m").roles("ADMIN")
                .and()
                .withUser("user").password("u").roles("USUARIO");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Note: 
        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
        // Note that the CSRf token is disabled for all requests (change it as you wish...)
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Add here any custom code you need in order to get the credentials from the user...  
        /*auth.inMemoryAuthentication()
            .withUser("myUserName")
            .password("myPassword")
            .roles("USER");*/
    }
} 