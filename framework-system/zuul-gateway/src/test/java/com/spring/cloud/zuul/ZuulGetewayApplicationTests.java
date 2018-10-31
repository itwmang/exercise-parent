package com.spring.cloud.zuul;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZuulGetewayApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void contextLoads() {
		// com.wmang.exercise  Y29tLndtYW5nLmV4ZXJjaXNl
		// com.wmang.exercise:{com.wmang.exercise.888888}  Y29tLndtYW5nLmV4ZXJjaXNlOntjb20ud21hbmcuZXhlcmNpc2UuODg4ODg4fQ
		// {com.wmang.exercise.888888}  e2NvbS53bWFuZy5leGVyY2lzZS44ODg4ODh9
		// {com.wmang.exercise.888888}  $2a$10$P7AfmnpE8vSdfAtfykWTt.lJnwtSG41y291HsB6Y36V6NSeNqG25m

//		String secret = "{bcrypt}$2a$10$P7AfmnpE8vSdfAtfykWTt.lJnwtSG41y291HsB6Y36V6NSeNqG25m";
		String secret = "com.wmang.exercise";
		try {
			log.info("==========================:"+ Base64.getEncoder().encodeToString(secret.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("==========================:   "+passwordEncoder.encode(secret));
	}

}
