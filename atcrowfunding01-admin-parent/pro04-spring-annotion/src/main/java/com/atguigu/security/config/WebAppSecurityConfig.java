package com.atguigu.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

// 注意！这个类一定要放在自动扫描的包下，否则所有配置都不会生效！

// 将当前类标记为配置类
@Configuration
// 启用Web环境下权限控制功能
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private DataSource dataSource;
@Autowired
private MyUserDetial myUserDetial;
//@Autowired
//private MyEncoding myEncoding;
//@Autowired
//private BcryptPswd bcryptPswd;

@Bean
public BCryptPasswordEncoder getbCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
}



    // 授权
    // 与spring security环境下用户登录相关
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetial).passwordEncoder(getbCryptPasswordEncoder())
        ;

    }

    //  与spring security环境下请求授权相关
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        // jdbc 令牌仓库
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        security
                .authorizeRequests()			// 对请求进行授权
                .antMatchers("/index.jsp")		// 针对/index.jsp路径进行授权
                .permitAll()					// 可以无条件访问
                .antMatchers("/layui/**")		// 针对/layui目录下所有资源进行授权
                .permitAll()					// 可以无条件访问
                .antMatchers("/level1/**")
                .hasRole("ADMIN")
                .antMatchers("/level2/**")
                .hasAuthority("内门弟子")
                .and()
                .authorizeRequests()			// 对请求进行授权
                .anyRequest()					// 任意请求
                .authenticated()				// 需要登录以后才可以访问
                .and()
                .formLogin()					// 使用表单形式登录

                // 关于loginPage()方法的特殊说明
                // 指定登录页的同时会影响到：“提交登录表单的地址”、“退出登录地址”、“登录失败地址”
                // /index.jsp GET - the login form 去登录页面
                // /index.jsp POST - process the credentials and if valid authenticate the user 提交登录表单
                // /index.jsp?error GET - redirect here for failed authentication attempts 登录失败
                // /index.jsp?logout GET - redirect here after successfully logging out 退出登录
                .loginPage("/index.jsp")		// 指定登录页面（如果没有指定会访问SpringSecurity自带的登录页）

                // loginProcessingUrl()方法指定了登录地址，就会覆盖loginPage()方法中设置的默认值/index.jsp POST
                .loginProcessingUrl("/do/login.html")	// 指定提交登录表单的地址
                .permitAll()
                .usernameParameter("loginAcct").passwordParameter("userPswd")
                .defaultSuccessUrl("/main.html")// 登陆成功后去的地址。
                .and()
                .csrf().disable(). //禁用csrf功能
                logout().// 开启退出功能
                logoutUrl("/do/logut.html").// 指定处理退出请求的url地址
                logoutSuccessUrl("/index.jsp")// 退出地址


                .and()
        .exceptionHandling()// 指定 异常处理器
//
//        .accessDeniedPage("/to/no/auth/page.html")    // 访问被拒绝时去的页面
        // or
        .accessDeniedHandler(new AccessDeniedHandler() {
              @Override
              public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                  httpServletRequest.setAttribute("message","不好意思，访问出错！");
                  httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(httpServletRequest,httpServletResponse);
              }
          })
        .and()
        .rememberMe().tokenRepository(jdbcTokenRepository)

        ;

    }
    // 认证
}
