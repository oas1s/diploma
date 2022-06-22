package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerTimer {
    private String cronExpression;
    private TriggerTimerInvokeFunction invokeFunction;
    private TriggerTimerInvokeFunctionWithRetry invokeFunctionWithRetry;
}
