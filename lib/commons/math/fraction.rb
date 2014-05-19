require_relative './poplar'


module Commons
  module Math
    module Fraction
    Java::CommonsMathFraction::FractionService.new.basicLoad(JRuby.runtime)
    end
  end
end
