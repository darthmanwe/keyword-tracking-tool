package com.snovelli.seo;

import com.snovelli.seo.ktt.config.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(Constants.SPRING_PROFILE_UNIT_TESTING)
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
