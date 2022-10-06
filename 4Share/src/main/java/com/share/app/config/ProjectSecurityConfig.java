package com.share.app.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.share.app.constant.Auth_Person;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	private final String path="..\\\\4share\\\\src\\\\main\\\\resources\\password.properties";
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().and()
                .authorizeRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/setting").hasRole(Auth_Person.ADMIN.toString())
                .mvcMatchers("/changeLoc").hasRole(Auth_Person.ADMIN.toString())
                .mvcMatchers("/uploadFiles").authenticated()
                .mvcMatchers("/refreshDB").authenticated()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/allFiles").authenticated()
                .mvcMatchers("/document").authenticated()
                .mvcMatchers("/images").authenticated()
                .mvcMatchers("/music").authenticated()
                .mvcMatchers("/video").authenticated()
                .mvcMatchers("/downloadfile").authenticated()
                .mvcMatchers("/search").authenticated()
                .mvcMatchers("/about").authenticated()
                .mvcMatchers("/deletefileId").hasRole(Auth_Person.ADMIN.toString())
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
            

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
    	File file= new File(path);
    	if(!file.exists()) {
    		System.out.println("I am here2...");
    	auth.inMemoryAuthentication()
            .withUser("USER").password("u12345").roles("USER")
            .and()
            .withUser("ADMIN").password("a54321").roles(Auth_Person.USER.toString(),Auth_Person.ADMIN.toString())
            .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    	
    	}else {
    		
    		Properties p= new Properties();
    		FileInputStream fis= new FileInputStream(file);
            p.load(fis);
            String admin_pass=p.getProperty("ADMIN");
            String user_pass= p.getProperty("USER");
            String guest_pass= p.getProperty("GUEST");
            
            auth.inMemoryAuthentication()
            .withUser("USER").password(user_pass).roles("USER")
            .and()
            .withUser("ADMIN").password(admin_pass).roles("USER", "ADMIN")
            .and()
            .withUser("GUEST").password(guest_pass).roles("GUEST")
            .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    	}
    }

}
