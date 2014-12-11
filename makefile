Profile.jar: Profile.class Components/* manifest.txt
	jar cmf manifest.txt Profile.jar *.class Components/*
Profile.class: Profile.java
	javac Profile.java
clean: 
	rm *~