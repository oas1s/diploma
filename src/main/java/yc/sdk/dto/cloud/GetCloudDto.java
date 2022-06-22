package yc.sdk.dto.cloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCloudDto {
    private String id;
    private String createdAt;
    private String name;
    private String description;
    private String organizationId;
}
