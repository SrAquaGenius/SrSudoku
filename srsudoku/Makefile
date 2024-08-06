run:
	@(mvn compile exec:java)

run-d:
	@(mvn compile exec:java -Ddebug)

clean:
	@(mvn clean > /dev/null)

clean-v:
	@(mvn clean)

clean-obj:
	@(mvn exec:exec@clean-objects > /dev/null)

clean-obj-v:
	@(mvn exec:exec@clean-objects)