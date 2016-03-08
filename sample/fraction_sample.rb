require 'java'

require_relative '../lib/commons_math_fraction'

f = Commons::Math::Fraction.new(1, 1)

(2..4).each do |i|
  f.add!(Commons::Math::Fraction.new(1, i))
  puts f
end
