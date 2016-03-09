package com.yokolet.fraction;

import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

/**
 *
 * @author Yoko Harada
 */
public class FractionService implements BasicLibraryService {

    /**
     * 
     * @param runtime
     * @return
     * @throws IOException
     */
    @Override
    public boolean basicLoad(Ruby runtime) throws IOException {
        RubyModule commons = runtime.defineModule("Commons");
        RubyModule math = commons.defineModuleUnder("Math");
        RubyClass fraction = math.defineClassUnder("Fraction", runtime.getObject(), new ObjectAllocator() {
            @Override
            public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
                return new RubyFraction(runtime, klazz);
            }
        });
        fraction.defineAnnotatedMethods(RubyFraction.class);
        return true;
    }
    
//    /**
//     * Alternative since jdk8 using java lambda to replace anonymous ObjectAllocator class
//     * @param runtime
//     * @return
//     * @throws IOException
//     */
//    @Override
//    public boolean basicLoad(Ruby runtime) throws IOException {
//        RubyModule commons = runtime.defineModule("Commons");
//        RubyModule math = commons.defineModuleUnder("Math");
//        RubyClass fraction = math.defineClassUnder("Fraction", runtime.getObject(), (Ruby runtime1, RubyClass klazz) -> new RubyFraction(runtime1, klazz));
//        fraction.defineAnnotatedMethods(RubyFraction.class);
//        return true;
//    }
}
