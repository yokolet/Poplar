# -*- ruby -*-

require 'rubygems'
require 'rake/javaextensiontask'

Rake::JavaExtensionTask.new('commons/math/fraction') do |ext|
  jruby_home = ENV['MY_RUBY_HOME'] # this is available of rvm
  jars = ["#{jruby_home}/lib/jruby.jar"] + FileList['lib/*.jar']
  ext.classpath = jars.map {|x| File.expand_path x}.join ':'
  ext.name = 'commons/math/poplar'
end
