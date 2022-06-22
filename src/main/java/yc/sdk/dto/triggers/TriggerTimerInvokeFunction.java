package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TriggerTimerInvokeFunction {
    private String functionId;
    private String functionTag;
    private String serviceAccountId;
}
