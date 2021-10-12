package homework.maven.springframework.blog.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        CharacterEncodingFilter filter = new
                CharacterEncodingFilter();
        http.addFilterBefore(filter, CsrfFilter.class)
                .formLogin()
                .loginPage("/loginpage").permitAll()
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/loginpage")
                .and()
                .authorizeRequests()
                .antMatchers("/loginpage/beforelogin").permitAll()
                .anyRequest().authenticated();
    }
}
