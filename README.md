## A Example JRuby extension. 

This is an example of creating a jruby extension as a github project (other version control are available), but is convenient to use github. First you create your gem project in the normal way. But you will probably want modify your `.gitignore` to exclude temporary and `binary` artifacts as follows:-

```bash
target
MANIFEST.MF
*.jar
```
### Creating the java source

The java source should live in a `src` folder (if you use Eclipse as your java ide you may wish to stick to their convention) but for others create your java packages directly under `src` it is simpler. See [wiki](https://github.com/jruby/jruby/wiki/Method-Signatures-and-Annotations-in-JRuby-extensions) for a guide to annotations and java signatures of jruby extensions

### Using a polyglot maven build

To enable polyglot maven build you should create a `.mvn` folder in the root of your project containing `extensions.xml` with the following content:-
```xml
<?xml version="1.0" encoding="UTF-8"?>
<extensions>
  <extension>
    <groupId>io.takari.polyglot</groupId>
    <artifactId>polyglot-ruby</artifactId>
    <version>0.1.15</version>
  </extension>
</extensions>
```
Update the version as appropriate, the inclusion of this file means that you are able to use `pom.rb` in place of `pom.xml` to control your maven build. Here is the `pom.rb` for this project. Which uses jruby and commons-math-library jars during the build, further it allows for the copying of the commons-math-library into the project:-

```ruby
project 'poplar' do

  model_version '4.0.0'
  id 'commons.math:poplar:1.0'
  packaging 'jar'

  description 'example JRuby extension'

  developer 'yokolet' do
    name 'Yoko Harada'
    email 'yokolet@gmail.com'
    roles 'developer'
  end

  issue_management 'https://github.com/jruby/jruby-examples/issues', 'Github'

  source_control( url: 'https://github.com/jruby/jruby-examples',
                  connection: 'scm:git:git://github.com/jruby/jruby-examples.git',
                  developer_connection: 'git@github.com:jruby/jruby-examples.git' )

  properties( 'source.directory' => 'src',
              'poplar.basedir' => '${project.basedir}',
              'polyglot.dump.pom' => 'pom.xml',
              'commons-math.api' => 'http://commons.apache.org/proper/commons-math/javadocs/api-3.6/',
              'maven.compiler.source' => '1.7',
              'project.build.sourceEncoding' => 'utf-8',
              'maven.compiler.target' => '1.7',
              'jruby.api' => 'http://jruby.org/apidocs/' )

  jar 'org.jruby:jruby:9.0.5.0'
  jar 'org.apache.commons:commons-math3:3.6'

  overrides do
    plugin :resources, '2.6'
    plugin :dependency, '2.10' do
      execute_goals( id: 'default-cli',
                     artifactItems: [ { groupId:  'org.apache.commons',
                                        artifactId:  'commons-math3',
                                        version:  '3.6',
                                        type:  'jar',
                                        outputDirectory: '${poplar.basedir}/lib'
                                      }
                                    ]
      )
    end

    plugin( :compiler, '3.3',
            source: '${maven.compiler.source}',
            target: '${maven.compiler.target}'
          )
    plugin( :javadoc, '2.10.3',
            detect_offline_links:  'false',
            links: [ '${jruby.api}',
                         '${commons-math.api}' ]
          )
    plugin( :jar, '2.6',
            archive: { manifestFile: 'MANIFEST.MF' }
          )
  end
end
```
If you are using the Eclipse folder convention be sure to specify `source.directory` directory above as `'src/main/java'` otherwise the code will not compile. This `pom.rb` requires a `MANIFEST.MF` to create the jar see the `Rakefile` below that creates it dynamically. The `mvn dependency:copy` copies the maths-commons-library jar into the into lib directory:-
```ruby
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
```
### Gemspec
The gemspec file is much as you have become used to, except you need to specifically include the jars into the gem (otherwise our `.gitignore` excludes them), and you should also specify platform as java.
```ruby
gem.files << 'lib/poplar.jar'
gem.files << 'lib/commons-math3-3.6.jar
...
gem.platform='java'
```
