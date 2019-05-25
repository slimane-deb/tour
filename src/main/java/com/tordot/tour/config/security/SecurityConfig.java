package com.tordot.tour.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // for preauthorise
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private AchatAccessDecisionManager achatAccessDecisionManager;

    public SecurityConfig(UserDetailsService userDetailsService, AuthSuccessHandler customAuthenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
//			.passwordEncoder(bCryptPasswordEncoder)
        ;
        //You can use in memory auth
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/ng*").permitAll()
//                .anyRequest().authenticated()
//                .and().csrf().disable()
//                .formLogin().loginPage("/login")//.usernameParameter("login").passwordParameter("mdp")
//                .failureUrl("/login?error=true")
//                .successHandler(customAuthenticationSuccessHandler).and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").invalidateHttpSession(true)

//            .and().exceptionHandling()
/*        .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
            e.printStackTrace();

        })*/

//            .accessDeniedPage("/access-denied")
//        .and().authorizeRequests().accessDecisionManager(achatAccessDecisionManager)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
                .headers().frameOptions().sameOrigin(); //for X-Frame-Options denied ==> works without /birt/output? in url
        //@formatter:on
    }


    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/assets/**");
    }

}
