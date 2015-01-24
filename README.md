# Batch
Spring JMS with ActiveMQ – send and receive message example.

This code pretends to be a simple prove of concept for using ActiveMQ with JMS Spring Beans. The idea is to lookup an Spring context to deal with the JMS beans.

# Configuration
You will need to configure an environment variable named: BATCH_CONF with the path where the conf file is located on your local machine. Something like:

```$BATCH_CONF={$LOCAL_WORKSPACE}/batch/batch-conf/wc```

# Setting up ActiveMQ
You will need the ActiveMQ binaries from the Apache Software Foundation:

```http://activemq.apache.org/```

Then you have to start the ActiveMQ server, from your terminal:

```${ACTIVE_MQ_HOME}/bin/activemq start```

Now you can access to your localhost ActiveMQ admin environment:

```http://localhost:8161/admin```

The default user and password are: admin/admin

# Starting the tests
The software consist on two spring beans to create and consume messages: JmsMessageReiver.java and JmsMessageSender.java

You can try the funcionallity of the two of them on the test ProducerConsumerTest where several producers are created and the consumers listen and print the message from the corresponding queue.

Notice how the beans are created from the spring bean configuration file: spring-beans-test.xml

CAUTION

The tests on the JmsMessageReceiverTest are ignored. That is because the receiver is always waiting for new message to process, so make sure that you execute the test from some IDE like IntelliJ or Eclipse in order to debug and watch what is happening. But if you execute those test from the terminal, the maven compile goal will never end.


