package com.example.bootiful;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class BootifulApplicationTests {

	@Test
	void contextLoads() {
		var am = ApplicationModules.of(BootifulApplication.class);
		am.verify() ;
		IO.println(am.toString());
		new Documenter(am).writeDocumentation();
	}

}
