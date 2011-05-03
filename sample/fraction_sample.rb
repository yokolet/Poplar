require 'java'

$: << '/Users/yoko/Documents/workspace/Poplar/lib'
require 'commons_math_fraction'

f = Commons::Math::Fraction::Fraction.new(1, 1)
(2..4).each do |i|
  f.add!(Commons::Math::Fraction::Fraction.new(1, i))
end
puts f
