package com.tom.school.rest.core.security;

import com.tom.school.rest.core.Cache;

public class SecurityTool {

	public static boolean checkToken(String token){
		if (token == null || !Cache.Tokens.containsKey(token)) {
			return false;
		} else {
			return true;
		}
	}
	
}
