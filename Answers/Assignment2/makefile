JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		./Pair.java \
		./MinMax.java 

default: classes

classes: $(CLASSES:.java=.class)

run:
		java MinMax


clean:
		→$(RM) *.class
