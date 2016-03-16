require 'rubygems'
require 'poplar'

puts "Just a little demonstration that we can require and use\nour fraction gem in the normal way\n\n"

create_format = "We create two new fractions %s and %s and multiply them\nusing the \"*\" operator, except that it is a method really\n\n"

half = Commons::Math::Fraction.new(1, 2)
quarter = Commons::Math::Fraction.new(1, 4)
puts format(create_format, half, quarter)

multiply_format = "The result of %s * %s is %s\n\n"
puts format(multiply_format, half, quarter, half * quarter)

puts "Here we sum a number of fractions (changing original)\nprinting the result as we go\n\n"
f = Commons::Math::Fraction.new(1, 1)
(2..4).each do |i|
  f.add!(Commons::Math::Fraction.new(1, i))
  puts f
end

puts "\nHere we sum a number of fractions (not changing original)\nprinting the result as we go\n\n"
f = Commons::Math::Fraction.new(1, 1)
(2..4).each do |i|
  puts f.add(Commons::Math::Fraction.new(1, i))
end
