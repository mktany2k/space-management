Project Setup:
1.	Open ./web/build.properties
2.	Modify project.container.path property to the web container path.
3.	Start eclipse IDE - From the menu bar, select Window > Preferences > Java > Build Path > Classpath Variables.
4.	Add new variable with name "SM_HOME", path as "[path.to.root]/space-management/".
5.	Run ant build file at ./web/build.xml