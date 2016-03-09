gem 'minitest' # don't use bundled minitest
require 'java'
require 'minitest/autorun'
require 'minitest/pride'
require_relative '../lib/commons_math_fraction'


# Test Bar class functionality using regular 
# Minitest::Test albeit with jruby runtime

class FractionTest < Minitest::Test

  attr_reader :fraction, :half
  def setup
    @fraction = Commons::Math::Fraction.new(3, 16)
    @half = Commons::Math::Fraction.new(1, 2)
  end
  
  def test_new
    assert fraction.instance_of? Commons::Math::Fraction
  end
  
  def test_eql?
    assert_equal fraction, Commons::Math::Fraction.new(3, 16), 'failed eql? test'
  end
  
  def test_refute_eql?
    refute fraction.eql?(half), 'failed refute eql? test'
  end
  
  def test_add
    assert_equal fraction.add(Commons::Math::Fraction.new(5, 16)), half, 'failed add test'
  end
  
  def test_add_bang
    fraction.add!(Commons::Math::Fraction.new(5, 16))
    assert_equal fraction, half, 'failed add_bang test'
  end
end

