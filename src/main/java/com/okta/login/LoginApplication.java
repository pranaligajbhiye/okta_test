package com.okta.login;

import java.security.Principal;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
//@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@GetMapping("/test")
	public String welcomeToUser(Principal principal) {
		return "Hi ".concat(principal.getName().concat(" Welcome..!"));
	}

	@GetMapping("/")
	public String test() {
		return "Hi ";
	}

	@GetMapping("/profile")
	public ModelAndView userDetails(OAuth2AuthenticationToken authentication) {
		return new ModelAndView("userProfile",
				Collections.singletonMap("details", authentication.getPrincipal().getAttributes()));
	}

	@GetMapping("/user")
	public String userDetails(Principal principal) {
		return principal.toString();
	}

}
