# -*- encoding: utf-8 -*-
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'poplar/version'
require 'rake'

Gem::Specification.new do |gem|
  gem.name          = 'poplar'
  gem.version       = Poplar::VERSION
  gem.licenses = ['GPL-3.0']
  gem.has_rdoc = false
  gem.authors       = ['yokolet', 'monkstone']
  gem.email         = ['martin_p@lineone.net']
  gem.description   = %q{Example JRuby Extension}
  gem.summary       = %q{Just for demo purposes has been superseded by Rational.}
  gem.homepage      = 'https://github.com/monkstone/Poplar'
  gem.files         = `git ls-files`.split($/)
  gem.files << 'lib/poplar.jar'
  gem.files << 'lib/commons-math3-3.6.jar'
  gem.executables   = gem.files.grep(%r{^bin/}).map{ |f| File.basename(f) }
  gem.test_files    = gem.files.grep(%r{^(test|spec|features)/})
  gem.require_paths = ['lib']
  gem.add_development_dependency 'rake', '~> 10.0'
  gem.platform='java'
end
