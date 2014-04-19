package commons.math.fraction;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

@JRubyClass(name="Commons::Math::Fraction")
public class Fraction extends RubyObject {
    private org.apache.commons.math3.fraction.Fraction j_fraction = null;
    
    @JRubyMethod(name="new", meta = true, rest = true)
    public static IRubyObject rbNew(ThreadContext context, IRubyObject klazz, IRubyObject[] args) {
        Fraction fraction = (Fraction) ((RubyClass)klazz).allocate();
        fraction.init(context, args);
        return fraction;
    }

    public Fraction(Ruby runtime, RubyClass klass) {
        super(runtime, klass);
    }
    
    void init(ThreadContext context, IRubyObject[] args) {
        Arity.checkArgumentCount(context.getRuntime(), args, 2, 2);
        int numerator = (Integer) args[0].toJava(Integer.class);
        int denominator = (Integer) args[1].toJava(Integer.class);
        j_fraction = new org.apache.commons.math3.fraction.Fraction(numerator, denominator);
    }
    
    org.apache.commons.math3.fraction.Fraction getJFraction() {
        return j_fraction;
    }
    
    @JRubyMethod(name = "add!")
    public IRubyObject add_bang(ThreadContext context, IRubyObject other) {
        if (other instanceof Fraction) {
            org.apache.commons.math3.fraction.Fraction other_fraction = ((Fraction)other).getJFraction();
            j_fraction = j_fraction.add(other_fraction);
            return this;
        } else {
            throw context.getRuntime().newArgumentError("argument should be Commons::Math::Fraction type");
        }
    }
    
    @JRubyMethod
    public IRubyObject to_s(ThreadContext context) {
        return context.getRuntime().newString(j_fraction.toString());
    }

}
