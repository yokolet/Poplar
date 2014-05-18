package commons.math.fastmath;

import java.io.IOException;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

public class FastmathService implements BasicLibraryService {
  private Ruby runtime;
  
  @Override
  public boolean basicLoad(final Ruby runtime) throws IOException {
    RubyModule commons = runtime.defineModule("Commons");
    RubyModule math = commons.defineModuleUnder("Math");
    RubyModule fastMathModule = math.defineModuleUnder("Fastmath");
    RubyClass fastMath = fastMathModule.defineClassUnder(Fastmath.class.getSimpleName(), runtime.getObject(), new ObjectAllocator() {
        public IRubyObject allocate(Ruby runtime, RubyClass rubyClass) {
          return new Fastmath(runtime, rubyClass);
        }
    });
    
    fastMath.defineAnnotatedMethods(Fastmath.class);
    return true;
  }
  @JRubyClass(name = "Fastmath", parent = "Object")
  public static class Fastmath extends RubyObject {
    
    public Fastmath(final Ruby runtime, RubyClass klass) {
      super(runtime, klass);
    }
    
    @JRubyMethod(name = "sin", meta = true)
    public static IRubyObject sin(ThreadContext context, IRubyObject klazz, IRubyObject other) {
      return context.getRuntime().newFloat(org.apache.commons.math3.util.FastMath.sin((Double) other.toJava(Double.class)));
    }
    
    @JRubyMethod(name = "cos", meta = true)
    public static IRubyObject cos(ThreadContext context, IRubyObject klazz, IRubyObject other) {
      return context.getRuntime().newFloat(org.apache.commons.math3.util.FastMath.cos((Double) other.toJava(Double.class)));
    }
  }
  
}


