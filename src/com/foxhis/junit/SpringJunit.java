package com.foxhis.junit;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foxhis.spring.di.CompatDisc;
import com.foxhis.spring.di.JavaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class SpringJunit {
	
	@Autowired
	private CompatDisc compat;
	
	@Test
	public void test()
	{
		assertNotNull(compat);
		compat.play();
	}

}
