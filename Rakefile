# encoding: utf-8
# frozen_string_literal: false
require_relative 'lib/poplar/version'

def create_manifest
  title =  'Implementation-Title: poplar (java extension for JRubyArt)    '        
  version =  format('Implementation-Version: %s', Poplar::VERSION)
  file = File.open('MANIFEST.MF', 'w') do |f|
    f.puts(title)
    f.puts(version)
  end
end

task default: [:init, :compile, :test, :gem]

desc 'Create Manifest'
task :init do
  create_manifest
end

desc 'Build gem'
task :gem do
  sh 'gem build poplar.gemspec' 
end

desc 'Compile'
task :compile do
  sh 'mvn package'
  sh 'mvn dependency:copy'
  sh 'mv target/poplar.jar lib'
end

desc 'Document'
task :javadoc do
  sh 'mvn javadoc:javadoc'
end

desc 'Test'
task :test do
  sh 'jruby test/fraction_test.rb'
end

desc 'clean'
task :clean do
  Dir['./**/*.%w{jar gem}'].each do |path|
    puts 'Deleting #{path} ...'
    File.delete(path)
  end
  FileUtils.rm_rf('./target')
  FileUtils.rm('MANIFEST.MF')
end
