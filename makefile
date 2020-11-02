run:Hex.class
	@java Hex $(ARGS)
Hex.class:Hex.java
	@javac Hex.java
clean:
	rm *.class
