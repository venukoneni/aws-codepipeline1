package com.amazonaws.samples;

import java.util.List;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;

public class AwsSqsSample {

	public static void main(String[] args) throws InterruptedException {
		AmazonSNS amazonSNSClient = AmazonSNSClientBuilder.standard().withRegion("us-east-1").build();
		String topicArn = createTopic(amazonSNSClient);
		AmazonSQS amazonSQS = createQueue();
		String queueUrl = amazonSQS.getQueueUrl("venu-queue1").getQueueUrl();
		Topics.subscribeQueue(amazonSNSClient, amazonSQS, topicArn, queueUrl);

		amazonSNSClient.publish(new PublishRequest(topicArn, "Hellow Venu").withSubject("Venu-Subject"));
		Thread.sleep(5000);

		List<Message> msgs = amazonSQS.receiveMessage(queueUrl).getMessages();
		if (msgs.size() > 0) {
			System.out.println("Message->" + msgs.get(0).getBody());
		}
	}

	private static AmazonSQS createQueue() {
		AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard().withRegion("us-east-1").build();
		CreateQueueRequest createQueueRequest = new CreateQueueRequest("venu-queue1")
				.addAttributesEntry("DelaySeconds", "60").addAttributesEntry("MessageRetentionPeriod", "86400");
		amazonSQS.createQueue(createQueueRequest);

		return amazonSQS;
	}

	private static String createTopic(AmazonSNS amazonSNSClient) {
		CreateTopicRequest createTopicRequest = new CreateTopicRequest("venu-topic");
		CreateTopicResult createTopicResult = amazonSNSClient.createTopic(createTopicRequest);

		return createTopicResult.getTopicArn();
	}

}
