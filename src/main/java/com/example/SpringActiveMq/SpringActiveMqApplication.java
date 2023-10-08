package com.example.SpringActiveMq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.*;
import javax.xml.soap.Text;

@SpringBootApplication
public class SpringActiveMqApplication implements CommandLineRunner {
	/*
     URL of the JMS  server. Default brocker url will just mean that jms server is on localhost
     default broker url is : tcp://localhost:61616
     * */
	private static String url = "tcp://localhost:61616";
	private static String queueName = "MESSAGE_QUEUE";

	public static void main(String[] args){
		SpringApplication.run(SpringActiveMqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//MessageSender();
		//MessageReceiver();
	}

	private void MessageSender()throws Exception {
		//Get JMS connection from the jms server and starting it.
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//make a non-transactional session to send/receive JMS message.
		Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//The queue will be created automatically on the server
		Destination destination = session.createQueue(queueName);
		/*
		 * Destination represents the queue "MESSAGE QUEUE" on the JMS server.
		 *
		 * Message Producer is used for sending messages to the queue
		 * */
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("Hi guys, Hw are you here");

		/*
		Here we are sending our message.
		 */
		producer.send(message);
		connection.close();

	}
	private void MessageReceiver()throws Exception{
		//Get JMS connection from the jms server and starting it.
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//make a non-transactional session to send/receive JMS message.
		Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//The queue will be created automatically on the server
		Destination destination = session.createQueue(queueName);
		/* MessageConsumer is used for receiving (consuming) messages*/
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = consumer.receive();

		if(message instanceof TextMessage){
			TextMessage textMessage = (TextMessage) message;
			System.out.println("Received message '"+textMessage.getText()+"'");
		}
		connection.close();

	}
}
