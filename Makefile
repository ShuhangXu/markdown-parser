<<<<<<< HEAD
Test: MarkdownParse.class  MarkdownParseTest.class
	echo "All Done!"

MarkdownParse.class: MarkdownParse.java
	javac MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.java
	javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java

=======
MarkdownParse.class: MarkdownParse.java
	javac MarkdownParse.java

MarkdownParseTest.class: MarkdownParse.java MarkdownParse.class
	javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java

Test: MarkdownParse.class MarkdownParseTest.class
>>>>>>> d7bc83e951a8e5a37de811c7c105bc4ad7e8b166
