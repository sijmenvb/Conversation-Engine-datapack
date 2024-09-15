# This script is used to setup automatic building of plugins and define which plugins should be used by default.
import os

# Use this to set the version.
mavenVersion = "2.1.3"

# Use this configuration to specify the plugins to be build.
# Each item in this list will be a plugin.
# Each plugin is defined by a tuple containing a list and a boolean.
#       The list defines the names of the classes that will be in the plugin
#       The boolean denotes if it should be in the default installation.
# NOTE: The file names are not dependent on the folder, each folder will be searched for files with this name. (matching "**/[name].*")
configuration = [(["BuyLine"], True),
                 (["CommandLine"], True),
                 (["IfCustomLine", "ElseLine", "EndIfLine",
                  "IfLine", "IfScoreLine", "IfTagLine", "IfPurchaseSuccessfulLine"], True),
                 (["TagLine"], True),
                 (["GiveLine"], True),
                 (["PlaySound"], True),
                 (["ProfessionLine"], True),
                 (["CEScheduledCommandVillagerSetNoOffers"], False),
                 (["CETickCommandVillagerFacingPlayer"], True),
                 (["InvisibleNpc","ApplyInvisibility"], False)]


#####################################################################################

# generates the configuration files in the mavenfiles directory.
# makes sure the right files are packaged
def makePluginXml(fileNames):

    # get a formatted string with the files to include
    inclusions = ""  # accumulator
    for fileName in fileNames:
        inclusions += "\t\t\t\t<include>**/{0}.*</include>\n".format(fileName)

    # a template for the configuration missing the name and files to import.
    template = "<assembly xmlns=\"http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.3 http://maven.apache.org/xsd/assembly-1.1.3.xsd\">\n\t<!-- TODO: a jarjar format would be better -->\n\t<id>only{0}</id>\n\t<formats>\n\t\t<format>jar</format>\n\t</formats>\n\t<includeBaseDirectory>false</includeBaseDirectory>\n\t<fileSets>\n\t\t<fileSet>\n\t\t\t<outputDirectory>/</outputDirectory>\n\t\t\t<directory>${{project.build.outputDirectory}}</directory>\n\t\t\t<includes>\n{1}\t\t\t</includes>\n\t\t</fileSet>\n\t</fileSets>\n</assembly>"

    # write to the file
    file = open("./mavenFiles/only{0}.xml".format(fileNames[0]), 'w')
    file.write(template.format(fileNames[0], inclusions))
    file.close()


# makes the executon for the maven-build-plugin to build a single plugin.
# filenames is the list of file names, isDefault is a boolean definign if this plugin should be loaded by default.
def makeMavenExecution(filenames, isDefault):
    # template to make an execution leving out the name and the buid directory.
    template = "\n\t\t\t\t\t<execution>\n\t\t\t\t\t\t<id>only{0}</id>\n\t\t\t\t\t\t<configuration>{1}\n\t\t\t\t\t\t\t<descriptors>\n\t\t\t\t\t\t\t\t<descriptor>mavenFiles/only{0}.xml</descriptor>\n\t\t\t\t\t\t\t</descriptors>\n\t\t\t\t\t\t\t<finalName>{0}</finalName>\n\t\t\t\t\t\t\t<appendAssemblyId>false</appendAssemblyId>\n\t\t\t\t\t\t</configuration>\n\t\t\t\t\t\t<phase>package</phase>\n\t\t\t\t\t\t<goals>\n\t\t\t\t\t\t\t<goal>single</goal>\n\t\t\t\t\t\t</goals>\n\t\t\t\t\t</execution>"

    # get the build directory.
    directory = ""  # remains empty in case the plugin no default.
    if isDefault:
        directory = "\n\t\t\t\t\t\t\t<outputDirectory>./src/main/resources/plugins</outputDirectory>"

    # return filled in template.
    return template.format(filenames[0], directory)


def main():
    # make a configuration .xml file and get the executions for all the plugins.
    mavenExecutions = ""  # accumulator
    for plugin in configuration:
        makePluginXml(plugin[0])
        mavenExecutions += makeMavenExecution(plugin[0], plugin[1])

    # template for the pom-plugins.xml apart from the executions in the maven plugin.
    template = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\n\t<modelVersion>4.0.0</modelVersion>\n\t<groupId>org.conversationEngine.app</groupId>\n\t<artifactId>conversationEngineImporter</artifactId>\n\t<version>{0}</version>\n\t<name>conversation engine inporter </name>\n\t<description>a tool that imports dialouge trees made with yarn and converts them to a datapack for minecraft java edition</description>\n\n\t<properties>\n\t\t<maven.compiler.source>1.8</maven.compiler.source>\n\t\t<maven.compiler.target>1.8</maven.compiler.target>\n\t\t<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n\t</properties>\n\n\t<dependencies>\n\t\t<!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->\n\t\t<dependency>\n\t\t\t<groupId>com.googlecode.json-simple</groupId>\n\t\t\t<artifactId>json-simple</artifactId>\n\t\t\t<version>1.1.1</version>\n\t\t</dependency>\n\t</dependencies>\n\n\t<build>\n\t\t<plugins>\n\t\t\t<!-- plugin to clear the plugins folder while cleaning -->\n\t\t\t<plugin>\n\t\t\t\t<groupId>org.apache.maven.plugins</groupId>\n\t\t\t\t<artifactId>maven-clean-plugin</artifactId>\n\t\t\t\t<version>2.4.1</version>\n\t\t\t\t<configuration>\n\t\t\t\t\t<filesets>\n\t\t\t\t\t\t<fileset>\n\t\t\t\t\t\t\t<directory>./src/main/resources/plugins</directory>\n\t\t\t\t\t\t\t<includes>\n\t\t\t\t\t\t\t\t<include>**/*</include>\n\t\t\t\t\t\t\t</includes>\n\t\t\t\t\t\t\t<followSymlinks>false</followSymlinks>\n\t\t\t\t\t\t</fileset>\n\t\t\t\t\t</filesets>\n\t\t\t\t</configuration>\n\t\t\t</plugin>\n\t\t\t<!--define the maven jar plugin to tell it not to do anything -->\n\t\t\t<plugin>\n\t\t\t\t<artifactId>maven-jar-plugin</artifactId>\n\t\t\t\t<version>3.0.2</version>\n\t\t\t\t<executions>\n\t\t\t\t\t<execution>\n\t\t\t\t\t\t<id>default-jar</id>\n\t\t\t\t\t\t<phase>none</phase>\n\t\t\t\t\t</execution>\n\t\t\t\t</executions>\n\t\t\t</plugin>\n\t\t\t<!-- build the different plugins to the corresponding location. -->\n\t\t\t<plugin>\n\t\t\t\t<artifactId>maven-assembly-plugin</artifactId>\n\t\t\t\t<executions>{1}\n\t\t\t\t</executions>\n\t\t\t</plugin>\n\t\t</plugins>\n\t</build>\n</project>"
    # write the filled in template to the pom file.
    file = open("./pom-plugins.xml", 'w')
    file.write(template.format(mavenVersion, mavenExecutions))
    file.close()


if __name__ == "__main__":
    # check if the script is run from the right location.
    if "plugin_configuration.py" in os.listdir("./"):
        main()
        print("DONE!")
    else:
        print("\nERROR! mavenFiles folder not found!\nMake sure you are running this script from the base directory\n")
