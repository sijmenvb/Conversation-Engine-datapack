# Conversation-Engine-datapack
My attempt at making a modular conversation engine for Minecraft

See https://conversationengine.ddns.net/?page=ConversationEngine for details.

# Building with maven.
Since we are using plugins in this project, building is slightly different.

The normal maven build with the goal `clean package` will still work but will not update the plugins.

To build the plugins and the program, we need to run the `pom-plugins.xml` as well.

To do this, we need two Maven builds,
First, we have the goal `-f ./pom-plugins.xml clean package`
For the second Maven build, we have the goal `package` (without the clean).

Most IDEs have a way to automatically chain these. In eclipse, you can make a Lounch group. (Make sure to make the first task "wait untill termination".)

# Configuring the plugins.
If you want to automatically build plugins or configure which plugins should be bundled by default, you can use the `plugin_configuration.py` script to generate the `pom-plugin.xml` file (and dependent files). See `plugin_configuration.py` for further documentation.

Note that plugin files are included as packages in the program. This makes them easily available when developing. When building the programme into a jar, repositories are excluded from the build. So, do not place other classes in those packages. If you want to add a package (with plugins) to be excluded, this can be done in `./mavenFiles/main.xml`.
