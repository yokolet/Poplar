package commons.math.fastmath;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

@JRubyClass(name = "Commons::Math::FastMath")
public class FastMath extends RubyObject {

    public FastMath(final Ruby runtime, RubyClass klass) {
        super(runtime, klass);
    }

    @JRubyMethod(name = "sin")
    public static IRubyObject sin(ThreadContext context, IRubyObject rad) {
        return context.getRuntime().newFloat(org.apache.commons.math3.util.FastMath.sin((Double) rad.toJava(Double.class)));
    }

    @JRubyMethod(name = "cos")
    public static IRubyObject cos(ThreadContext context, IRubyObject rad) {
        return context.getRuntime().newFloat(org.apache.commons.math3.util.FastMath.cos((Double) rad.toJava(Double.class)));
    }
}
