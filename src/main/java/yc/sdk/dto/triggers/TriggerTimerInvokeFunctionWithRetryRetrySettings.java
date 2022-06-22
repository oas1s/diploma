package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerTimerInvokeFunctionWithRetryRetrySettings {
    private String retryAttempts;
    private String interval;
}
