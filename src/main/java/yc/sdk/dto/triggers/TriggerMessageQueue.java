package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.sdk.dto.common.BatchSettings;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerMessageQueue {
    private String queueId;
    private String serviceAccountId;
    private BatchSettings batchSettings;
    private TriggerMessageQueueInvokeFunction invokeFunction;
}
