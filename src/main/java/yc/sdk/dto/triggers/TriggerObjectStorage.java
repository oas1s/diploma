package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerObjectStorage {
    private List<String> eventType;
    private String bucketId;
    private TriggerTimerInvokeFunctionWithRetry invokeFunction;
}
