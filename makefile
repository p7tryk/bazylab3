JAVAC=javac
sources = $(wildcard src/bazylab3/*.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<
