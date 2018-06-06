# srf-example
Simple example of how the pom.xml needs to be set up to create a jar with dependencies for SRF to utilize.

You will need a [StormRunner Functional](https://software.microfocus.com/en-us/products/functional-testing-as-a-service/free-trial) account if you wish to use this simple test as is and upload it for execution.

If you wish to execute this locally (on your own machine) without using StormRunner Functional, you will need to add
```
<build>
  ...
  <testSourceDirectory>src/main</testSourceDirectory>
</build>
```

## Usage
To generate the jar file to upload to StormRunner Functional, execute the goal "package".  This can be done from your IDE or from the command line using:

```
mvn clean package
```
The **srf-example-0.1-jar-with-dependencies.jar** is the file you will use to upload to StormRunner Functional.
For convenience, **srf-example-0.1-jar-with-dependencies.jar** is part of the project if you don't wish to compile the test.


**NOTE:** If you wish to run the test as a SRF Remote Test, run the maven project using:
```
clean test -DrunRemote=true
```
From Intellij, you can do that using the "Execute Maven Goal" button and entering the value in the window that pops up.

If you are running from the commandline, then use:
```
mvn clean test -DrunRemote=true
```

## Background
If you change the name of the class from LeanFtTest **OR** the package net.mf, you will need to update the descriptor.json file to reflect those changes.  Instructions can be found on the [StormRunner Functional](https://admhelp.microfocus.com/srf/en/1.30/Content/Upload-asset.htm) help pages.

To have maven package up your tests correctly in to a jar file that StormRunner Functional can use, you will need to make sure your pom.xml is using the maven-assembly-plugin (or other similar).  Below is an example of the maven-assembly-plugin used in this script.

```
<build>
   <plugins>
      <plugin>
         <!-- This plugin is key to create a LFT Maven project to have the jar file
              created so SRF can use it.
               More information on the plugin itself can be found
               http://maven.apache.org/plugins/maven-assembly-plugin/ -->
         <artifactId>maven-assembly-plugin</artifactId>
         <version>2.6</version>
         <configuration>
            <descriptorRefs>
               <descriptorRef>jar-with-dependencies</descriptorRef>
            </descriptorRefs>
         </configuration>
         <executions>
            <execution>
               <id>make-assembly</id> <!-- this is used for inheritance merges -->
               <phase>package</phase> <!-- bind to the packaging phase -->
               <!--phase>compile</phase--> <!-- bind to the compiling phase -->
               <goals>
                  <goal>single</goal>
               </goals>
            </execution>
         </executions>
      </plugin>
            
      ...
   </plugins>
</build>
```

If you wish to change the name that is being appended to the jar file from "jar-with-dependencies", edit the line in the pom which has the tag \<descriptorRef\>

### Adding UFT Pro(LeanFT) jar files to local .m2
Review the commands for Mac, Linux and Window on the [Wiki](https://github.com/panama69/srf-example/wiki)
