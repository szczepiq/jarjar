package com.tonicsystems.jarjar.resource;

import com.tonicsystems.jarjar.util.EntryStruct;
import com.tonicsystems.jarjar.util.JarProcessor;

import java.io.*;

/**
 * by Szczepan Faber, created at: 9/11/12
 */
public class ResourceRewriter implements JarProcessor {

    private final RewriterInput input;
    private final boolean verbose;

    public ResourceRewriter(RewriterInput input, boolean verbose) {
        this.input = input;
        this.verbose = verbose;
    }

    public boolean process(EntryStruct struct) throws IOException {
        if (input.accepts(struct)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(struct.data)));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream printer = new PrintStream(out);
            String line;
            while((line = reader.readLine()) != null) {
                String replacement = input.replaceLine(line);
                if (!replacement.equals(line) && verbose) {
                    System.out.println("Updating file: " + struct.name + ". Replacement: " + replacement);
                }
                printer.println(replacement);
            }
            reader.close();
            printer.close();
            struct.data = out.toByteArray();
        }
        return true;
    }
}
