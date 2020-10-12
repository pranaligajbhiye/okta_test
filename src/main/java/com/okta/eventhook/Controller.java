package com.okta.eventhook;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String test() {
		return "test";
	}
	
	@GetMapping("/eventhook")
	public Map<String, String> eventhook(@RequestHeader(value="X-Okta-Verification-Challenge", required = false) String verificationHeader ) {
		Map<String, String> verificationMap = new HashMap<String, String>();
		if(verificationHeader!=null)
			verificationMap.put("verification", verificationHeader);
		System.out.println("verificationHeader : "+ verificationHeader);
		System.out.println("verificationMap : "+ verificationMap);
		return verificationMap;
	}
	
	@PostMapping(path="/eventhook", consumes = "application/json", produces = "application/json")
	public String eventhookPost(@RequestBody String input) {
		System.out.println("Post input : "+input);
		return input;
	}
}
