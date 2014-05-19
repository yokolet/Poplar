# -*- ruby -*-
require 'java'
require 'rake/javaextensiontask'

Rake::JavaExtensionTask.new('commons/math') do |ext|
  jars = FileList['lib/*.jar']
  ext.classpath = jars.map {|x| File.expand_path x}.join ':'
  ext.name = 'commons/math/poplar'
  ext.debug=true
  ext.source_version='1.7'
  ext.target_version='1.7'
end




