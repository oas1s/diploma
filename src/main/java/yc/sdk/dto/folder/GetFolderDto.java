package yc.sdk.dto.folder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFolderDto {
    private String id;
    private String cloudId;
    private String createdAt;
    private String name;
    private String description;
    private String status;
}
