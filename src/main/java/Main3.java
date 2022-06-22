import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudTriggersWebClient;
import yc.sdk.dto.common.BatchSettings;
import yc.sdk.dto.triggers.*;

import java.util.Collections;

public class Main3 {
    public static void main(String[] args) throws JsonProcessingException {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        CloudTriggersWebClient cloudTriggersWebClient = new CloudTriggersWebClient();
        CreateTriggerDto createTriggerDto = CreateTriggerDto.builder().name("test1").description("desc")
                .folderId(folderId).rule(TriggerRule.builder().objectStorage(TriggerObjectStorage.builder()
                        .bucketId("asdasddddd").eventType(Collections.singletonList("OBJECT_STORAGE_EVENT_TYPE_CREATE_OBJECT")).invokeFunction(TriggerTimerInvokeFunctionWithRetry.builder()
                                .functionId("d4ebbihsimdso1nvqmkn").functionTag("$latest").serviceAccountId("aje4jac533dg8dm1hccp")
                                .build()).build()).build()).build();
        CreateTriggerDto createTriggerDto2 = CreateTriggerDto.builder().name("test2").description("desc")
                .folderId(folderId).rule(TriggerRule.builder().messageQueue(TriggerMessageQueue.builder()
                        .queueId("yrn:yc:ymq:ru-central1:b1g06rqs3rbeik9nd2n0:asasdsdaadad").serviceAccountId("ajed4rbg33detc5rtvjn").batchSettings(BatchSettings.builder()
                                .cutoff("0s").size("1").build()).invokeFunction(TriggerMessageQueueInvokeFunction.builder()
                                .functionId("d4ebbihsimdso1nvqmkn").functionTag("$latest").serviceAccountId("aje4jac533dg8dm1hccp").build()).build()).build())
                .build();
        CreateTriggerDto createTriggerDto3 = CreateTriggerDto.builder().name("test3").description("desc")
                .folderId(folderId).rule(TriggerRule.builder().timer(TriggerTimer.builder()
                        .cronExpression("0 * ? * * *")
                        .invokeFunction(TriggerTimerInvokeFunction.builder().functionId("d4ebbihsimdso1nvqmkn").functionTag("$latest").serviceAccountId("aje4jac533dg8dm1hccp").build())
                        .build()).build())
                .build();
//        System.out.println(new ObjectMapper().writeValueAsString(createTriggerDto));
//        cloudTriggersWebClient.createTrigger(iamToken,createTriggerDto);
//        System.out.println(new ObjectMapper().writeValueAsString(createTriggerDto2));
//        cloudTriggersWebClient.createTrigger(iamToken,createTriggerDto2);
//        System.out.println(new ObjectMapper().writeValueAsString(createTriggerDto3));
//        cloudTriggersWebClient.createTrigger(iamToken,createTriggerDto3);

        cloudTriggersWebClient.deleteTrigger(iamToken,"a1s8ol6ido1a8ujui8be");
        GetTriggersDto getTriggersDto = cloudTriggersWebClient.getTriggers(iamToken,"b1g06rqs3rbeik9nd2n0");
        cloudTriggersWebClient.updateTrigger(iamToken,"a1smsp3871649712mb3h",UpdateTriggerDto.builder().name("test222").build());

    }
}
