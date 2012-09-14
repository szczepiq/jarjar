package com.tonicsystems.jarjar.resource;

import com.tonicsystems.jarjar.util.EntryStruct;

/**
 * by Szczepan Faber, created at: 9/11/12
 */
public interface LineRewriter {

    boolean accepts(EntryStruct struct);

    String replaceLine(String line);

}
