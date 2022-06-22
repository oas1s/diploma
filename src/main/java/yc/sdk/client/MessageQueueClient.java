package yc.sdk.client;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesResult;
import lombok.SneakyThrows;

import java.util.Map;

public class MessageQueueClient {
    private AmazonSQS amazonSQS;

    @SneakyThrows
    public MessageQueueClient(BasicAWSCredentials basicAWSCredentials){
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(
                                basicAWSCredentials))
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                                "https://message-queue.api.cloud.yandex.net",
                                "ru-central1"
                        ))
        );
        SQSConnection sqsConnection = connectionFactory.createConnection();
        this.amazonSQS = sqsConnection.getAmazonSQSClient();
    }

    public CreateQueueResult createQueue(String queueName){
        return amazonSQS.createQueue(queueName);
    }

    public DeleteQueueResult deleteQueue(String queueUrl){
        return amazonSQS.deleteQueue(queueUrl);
    }

    public ListQueuesResult listQueues(){
        return amazonSQS.listQueues();
    }

    public SetQueueAttributesResult setQueueAttributes(String queueUrl, Map<String,String> attributes){
        return amazonSQS.setQueueAttributes(queueUrl,attributes);
    }

    public String getQueueUrl(String queueName){
        return amazonSQS.getQueueUrl(queueName).getQueueUrl();
    }


}
