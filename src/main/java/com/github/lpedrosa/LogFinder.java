package com.github.lpedrosa;

import java.util.Collection;

public interface LogFinder {

    public Collection<FinderInstance> instances() throws LogFinderException;

}

