package homework.maven.springframework.blog.security;

import homework.maven.springframework.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired // <-- ez végül mire kellett? XD
  private final UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    CharacterEncodingFilter filter = new
        CharacterEncodingFilter();
    http.addFilterBefore(filter, CsrfFilter.class)
        .authorizeRequests()
        .antMatchers("/login", "/*.css", "/images/*.jpg", "/favicon.ico", "/registration", "/index", "/home", "/registration/experiment")
        .permitAll()
        //.anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login")
            .defaultSuccessUrl("/home")
            .failureUrl("/login?error=true")
        .and()
          .logout().logoutSuccessUrl("/login");;
  }

  /* ki kellene szervezin önálló osztályba? */
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  // megadjuk a saját adatainkat a DaoAuthenticationProvider-nek, hogy azokat használja -> a mi encoderünket, és userservicet
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(encoder());
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  /*
  * @AllArgsConstructor
@Configuration
public class AuthFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                Calendar.getInstance().getTime());
        data.put(
                "Unsuccessful login",
                e.getMessage());

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }
}
  * */

}
