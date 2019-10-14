package com.hndfsj.springboot.framework.utils.regex;

import java.util.regex.Pattern;

/**
 * @author Luke Taylor
 * @version $Id: RegexUrlPathMatcher.java 2473 2008-01-18 16:24:35Z luke_t $
 */
public class RegexUrlPathMatcher implements UrlMatcher {

    private boolean requiresLowerCaseUrl = false;

    @Override
    public Object compile(String path) {
        return Pattern.compile(path);
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    @Override
    public boolean pathMatchesUrl(Object compiledPath, String url) {
        Pattern pattern = (Pattern)compiledPath;

        return pattern.matcher(url).matches();
    }

    @Override
    public String getUniversalMatchPattern() {
        return "/.*";
    }

    @Override
    public boolean requiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }
}
