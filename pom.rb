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

  source_control( :url => 'https://github.com/jruby/jruby-examples',
                  :connection => 'scm:git:git://github.com/jruby/jruby-examples.git',
                  :developer_connection => 'git@github.com:jruby/jruby-examples.git' )

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
      execute_goals( :id => 'default-cli',
                     'artifactItems' => [ { 'groupId' =>  'org.apache.commons',
                                            'artifactId' =>  'commons-math3',
                                            'version' =>  '3.6',
                                            'type' =>  'jar',
                                            'outputDirectory' =>  '${poplar.basedir}/lib' } ] )
    end

    plugin( :compiler, '3.3',
            'source' =>  '${maven.compiler.source}',
            'target' =>  '${maven.compiler.target}' )
    plugin( :javadoc, '2.10.3',
            'detect_offline_links' =>  'false',
            'links' => [ '${jruby.api}',
                         '${commons-math.api}' ] )
    plugin( :jar, '2.6',
            'archive' => {
              'manifestFile' =>  'MANIFEST.MF'
            } )
  end


  build do
    default_goal 'package'
    source_directory '${source.directory}'
    final_name 'poplar'
  end

end
