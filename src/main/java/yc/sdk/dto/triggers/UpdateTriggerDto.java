package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTriggerDto {
    private String updateMask;
    private String name;
    private String description;
}
