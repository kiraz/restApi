package com.renobidz.common.utils;

import java.util.UUID;

public class RandomGenerator {
	
	public static String randomUniqueGenerator(){
		return UUID.randomUUID().toString();
	}
}
