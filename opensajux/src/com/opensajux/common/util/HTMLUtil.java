package com.opensajux.common.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class HTMLUtil {
	public static String getLeadingHtml(String input, final int maxChars) {
		final StringBuilder output = new StringBuilder(Jsoup.clean(input, Whitelist.basic()));
		output.setLength(maxChars);
		output.append("&nbsp;&nbsp;...");
		return output.toString();
	}
}
