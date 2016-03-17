package com.yokolet.fraction;

import org.jruby.Ruby;
import org.jruby.RubyBoolean;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Arity;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.apache.commons.math3.fraction.Fraction;
import org.jruby.RubyFixnum;

/**
 *
 * @author Yoko Harada
 */
@JRubyClass(name = "Fraction")
public class RubyFraction extends RubyObject {

    private static final long serialVersionUID = 4127515380048748616L;
    private final Ruby runtime;
    private Fraction j_fraction = null;

    /**
     *
     * @param context ThreadContext
     * @param klazz IRubyObject
     * @param args IRubyObject[]
     * @return
     */
    @JRubyMethod(name = "new", meta = true, rest = true)
    public static IRubyObject rbNew(ThreadContext context, IRubyObject klazz, IRubyObject[] args) {
        RubyFraction fraction = (RubyFraction) ((RubyClass) klazz).allocate();
        fraction.init(context, args);
        return fraction;
    }

    /**
     *
     * @param runtime Ruby
     * @param klass RubyClass
     */
    public RubyFraction(Ruby runtime, RubyClass klass) {
        super(runtime, klass);
        this.runtime = runtime;
    }

    void init(ThreadContext context, IRubyObject[] args) {
        Arity.checkArgumentCount(context.getRuntime(), args, 2, 2);
        int numerator = (Integer) args[0].toJava(Integer.class);
        int denominator = (Integer) args[1].toJava(Integer.class);
        j_fraction = new Fraction(numerator, denominator);
    }

    Fraction getJFraction() {
        return j_fraction;
    }

    /**
     *
     * @param other IRubyObject
     * @return
     */
    @JRubyMethod(name = "add!")
    public IRubyObject add_bang(IRubyObject other) {
        if (other instanceof RubyFraction) {
            Fraction other_fraction = ((RubyFraction) other).getJFraction();
            j_fraction = j_fraction.add(other_fraction);
            return (RubyObject) this;
        } else {
            throw runtime.newArgumentError("argument should be Commons::Math::Fraction type");
        }
    }

    /**
     * This method uses java name as ruby name.
     *
     * @param context ThreadContext
     * @param other IRubyObject
     * @return
     */
    @JRubyMethod
    public IRubyObject add(ThreadContext context, IRubyObject other) {
        if (other instanceof RubyFraction) {
            Fraction other_fraction = ((RubyFraction) other).getJFraction();
            Fraction result = j_fraction.add(other_fraction);
            return (RubyObject) RubyFraction.rbNew(context, other.getMetaClass(), new IRubyObject[]{
                runtime.newFixnum(result.getNumerator()),
                runtime.newFixnum(result.getDenominator())
            });
        } else {
            throw runtime.newArgumentError("argument should be Commons::Math::Fraction type");
        }
    }

    /**
     * This method uses '*' ruby name. It is not really an operator in ruby
     * But we preserve the convention in the java method
     * @param context ThreadContext
     * @param other IRubyObject should be Fixnum or Fraction
     * @return
     */
    @JRubyMethod(name = "*")
    public IRubyObject op_multiply(ThreadContext context, IRubyObject other) {
        if (other instanceof RubyFraction) {
            Fraction other_fraction = ((RubyFraction) other).getJFraction();
            j_fraction = j_fraction.multiply(other_fraction);
            return (RubyObject) this;
        } else if (other instanceof RubyFixnum) {
            int other_fixnum = (int)((RubyFixnum) other).getIntValue();
            j_fraction = j_fraction.multiply(other_fixnum);
            return (RubyObject) this;
        } else {
            throw runtime.newArgumentError("argument should be Commons::Math::Fraction type or Fixnum");
        }
    }

    /**
     *
     * @param context
     * @param other
     * @return
     */
    @JRubyMethod(name = "==")
    @Override
    public IRubyObject op_equal(ThreadContext context, IRubyObject other) {
        boolean result = false;
        if (other instanceof RubyFraction) {
            Fraction other_fraction = ((RubyFraction) other).getJFraction();
            result = j_fraction.equals(other_fraction);
        }
        return RubyBoolean.newBoolean(runtime, result);
    }

    /**
     *
     * @param context
     * @param other
     * @return
     */
    @JRubyMethod(name = "eql?")
    @Override
    public IRubyObject equal_p(ThreadContext context, IRubyObject other) {
        boolean result = false;
        if (other instanceof RubyFraction) {
            Fraction other_fraction = ((RubyFraction) other).getJFraction();
            result = j_fraction.equals(other_fraction);
        }
        return RubyBoolean.newBoolean(runtime, result);
    }

    /**
     *
     * @return
     */
    @JRubyMethod(name = {"to_s", "inspect"})
    @Override
    public IRubyObject to_s() {
        return runtime.newString(j_fraction.toString());
    }

}
