package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerRule {
    private TriggerTimer timer;
    private TriggerMessageQueue messageQueue;
    private TriggerObjectStorage objectStorage;
}
