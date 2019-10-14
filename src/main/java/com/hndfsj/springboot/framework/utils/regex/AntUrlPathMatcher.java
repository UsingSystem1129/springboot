package com.hndfsj.springboot.framework.utils.regex;

import org.springframework.util.PathMatcher;
import org.springframework.util.AntPathMatcher;

/**
 * Ant path strategy for URL matching.
 *
 * @author Luke Taylor
 * @version $Id: AntUrlPathMatcher.java 2986 2008-04-22 22:27:51Z luke_t $
 */
public class AntUrlPathMatcher implements UrlMatcher {
    private boolean requiresLowerCaseUrl = true;
    private PathMatcher pathMatcher = new AntPathMatcher();

    public AntUrlPathMatcher() {
        this(true);
    }

    public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    @Override
    public Object compile(String path) {
        if (requiresLowerCaseUrl) {
            return path.toLowerCase();
        }

        return path;
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    @Override
    public boolean pathMatchesUrl(Object path, String url) {
        return pathMatcher.match((String)path, url);
    }

    @Override
    public String getUniversalMatchPattern() {
        return "/**";
    }

    @Override
    public boolean requiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[requiresLowerCase='" + requiresLowerCaseUrl + "']";
    }    
}
