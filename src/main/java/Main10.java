import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import yc.sdk.client.MessageQueueClient;

import javax.jms.JMSException;
import java.util.HashMap;
import java.util.Map;

public class Main10 {
    public static void main(String[] args) throws JMSException {
        MessageQueueClient messageQueueClient = new MessageQueueClient(
                new BasicAWSCredentials("YCAJEbYlIZDcXDDan9JZdenoD","YCMmQwdAm3Uc_NZJa05F7yrChohMkmEzIOnyIygQ"));
        messageQueueClient.createQueue("testquuee");
        ListQueuesResult listQueuesResult = messageQueueClient.listQueues();
        Map<String,String> attributes = new HashMap<>();
        attributes.put("VisibilityTimeout","45");
//        attributes.put("MaximumMessageSize","1024");
        messageQueueClient.setQueueAttributes(messageQueueClient.getQueueUrl("testquuee"),attributes);
        messageQueueClient.deleteQueue(messageQueueClient.getQueueUrl("testquuee"));
    }

}
