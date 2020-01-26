package by.iba.gomel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService);

    }
    @Override
    public void configure(WebSecurity web) {
        super.configure(web);
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        SimpleUrlAuthenticationSuccessHandler s=new SimpleUrlAuthenticationSuccessHandler();
        s.setUseReferer(true);
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/**")
                    .hasRole("ADMIN")
                    .and()
                    .httpBasic();
    }
    @Bean
    public PasswordEncoder getPasswordEncorder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }

}
