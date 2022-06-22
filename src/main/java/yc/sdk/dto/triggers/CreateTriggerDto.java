package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTriggerDto {
    private String folderId;
    private String name;
    private String description;
    private TriggerRule rule;
}
