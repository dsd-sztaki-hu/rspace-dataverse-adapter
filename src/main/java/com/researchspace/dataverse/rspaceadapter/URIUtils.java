package com.researchspace.dataverse.rspaceadapter;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for manipulating URIs and URLs generated from Dataverse
 * deposit.
 */
public class URIUtils {

	static final Pattern DOI = Pattern.compile(("^/([^/]+)(/.+)"));

	/**
	 * Converts from a persistent DOI or other PID to a Dataverse Web URL
	 * 
	 * @param serverUrl
	 * @param persistentURL
	 * @param protocol
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	static URL persistentIDToWebUrl(URL serverUrl, URL persistentURL, String protocol)
			throws MalformedURLException, URISyntaxException {

		String path = persistentURL.getPath();
		Matcher m = DOI.matcher(path);
		m.find();
		String prefix = m.group(1);
		String suffix = m.group(2);
		String doi = protocol + ":" + prefix + suffix;

		String newUrl = serverUrl + "/" + "dataset.xhtml?persistentId=" + doi;
		return new URL(newUrl);
	}

}
