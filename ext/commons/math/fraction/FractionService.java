package commons.math.fraction;

import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;
import org.jruby.api.Define;

public class FractionService implements BasicLibraryService {

    @Override
    public boolean basicLoad(Ruby runtime) throws IOException {
        RubyModule commons = Define.defineModule(runtime.getCurrentContext(), "Commons");
        RubyModule math = commons.defineModuleUnder(runtime.getCurrentContext(), "Math");
        RubyModule fractionModule = math.defineModuleUnder(runtime.getCurrentContext(),"Fraction");
        RubyClass fraction = fractionModule.defineClassUnder(runtime.getCurrentContext(), "Fraction", runtime.getObject(), FRACTION_ALLOCATOR);
        fraction.defineMethods(runtime.getCurrentContext(), Fraction.class);
        return true;
    }
    
    private static ObjectAllocator FRACTION_ALLOCATOR = new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new Fraction(runtime, klazz);
        }
    };
}
