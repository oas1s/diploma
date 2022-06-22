package yc.sdk.dto.triggers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTriggerDto {
    private String id;
    private String folderId;
    private String createdAt;
    private String name;
    private String description;
    private TriggerRule rule;
}
