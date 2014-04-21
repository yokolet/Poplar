package commons.math.fastmath;

import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

public class FastMathService implements BasicLibraryService {
    private Ruby runtime;
    
    @Override
    public boolean basicLoad(Ruby runtime) throws IOException {
    RubyModule commons = runtime.defineModule("Commons");
    RubyModule math = commons.defineModuleUnder("Math");
    RubyModule fastMathModule = math.defineModuleUnder("FastMath");
    RubyClass fastMath = fastMathModule.defineClassUnder("FastMath", runtime.getObject(), new ObjectAllocator() {
      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
        return new FastMath(runtime, rubyClass);
      }
    });
    
    fastMath.defineAnnotatedMethods(FastMath.class);
    return true;
  }
}

 
