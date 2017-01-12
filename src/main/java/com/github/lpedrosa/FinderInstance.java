package com.github.lpedrosa;

import java.util.Collection;

public interface FinderInstance {

    public Collection<String> lines(String filename) throws LogFinderException;

    public Collection<String> lines(String filename, long limit) throws LogFinderException;

}
