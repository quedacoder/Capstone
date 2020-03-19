package com.quedacoder.WorkTaskApplication.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class CustomAuditorAware implements AuditorAware {

	@Override
	public Optional getCurrentAuditor() {
		return Optional.of(System.getProperty("user.name"));
	}

}
