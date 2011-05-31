package commons.math.fraction;

import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

public class FractionService implements BasicLibraryService {

    @Override
    public boolean basicLoad(Ruby runtime) throws IOException {
        RubyModule commons = runtime.defineModule("Commons");
        RubyModule math = commons.defineModuleUnder("Math");
        RubyModule fractionModule = math.defineModuleUnder("Fraction");
        RubyClass fraction = fractionModule.defineClassUnder("Fraction", runtime.getObject(), FRACTION_ALLOCATOR);
        fraction.defineAnnotatedMethods(Fraction.class);
        return true;
    }
    
    private static ObjectAllocator FRACTION_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new Fraction(runtime, klazz);
        }
    };
}
