require 'java'

require_relative '../lib/commons_math_fraction'

f = Commons::Math::Fraction::Fraction.new(1,2)
(2..4).each do |i|
  f.add!(Commons::Math::Fraction::Fraction.new(i, 12))
end
puts f
