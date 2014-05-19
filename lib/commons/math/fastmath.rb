require_relative 'poplar'

module Commons
  module Math
    module FastMath
    Java::CommonsMathFastmath::FastmathService.new.basicLoad(JRuby.runtime)
    end
  end
end

FastMath = Commons::Math::Fastmath::Fastmath

