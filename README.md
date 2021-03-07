# Spring-boot-jwt-angular-single-unit

@Override
public void configure(WebSecurity web) throws Exception {
  web.ignoring().antMatchers(HttpMethod.GET, "/**");
}
### Adding this will make your jwt by passed all static web content and required files
  
