package com.unitech.classapi;

import com.unitech.classapi.config.testcontainer.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;

@SpringBootTest
@Import(TestContainerConfig.class)
class ClassApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
