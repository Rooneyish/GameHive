package com.gamehive.util;

import java.util.Arrays;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Ronish Prajapati LUM-ID 23048584
 */

public class CookieUtil {
	/**
	 * Adds a cookie to the HTTP response with specified name, value, and maximum
	 * age. The cookie path is set to "/" to make it accessible across the entire
	 * site.
	 * 
	 * @param response The HttpServletResponse to add the cookie to
	 * @param name     The name of the cookie
	 * @param value    The value of the cookie
	 * @param maxAge   The maximum age in seconds; after this time, cookie expires
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * Retrieves a cookie from the HTTP request by its name. Returns null if no
	 * cookies are present or the named cookie is not found.
	 * 
	 * @param request The HttpServletRequest to get cookies from
	 * @param name    The name of the cookie to retrieve
	 * @return The Cookie object if found, otherwise null
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		if (request.getCookies() != null) {
			return Arrays.stream(request.getCookies()).filter(cookie -> name.equals(cookie.getName())).findFirst()
					.orElse(null);
		}
		return null;
	}

	/**
	 * Deletes a cookie by adding a new cookie with the same name and maxAge 0,
	 * which instructs the browser to remove the cookie.
	 * 
	 * @param response The HttpServletResponse to add the deletion cookie to
	 * @param name     The name of the cookie to delete
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
