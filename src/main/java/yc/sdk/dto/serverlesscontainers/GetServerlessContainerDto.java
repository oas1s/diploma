package yc.sdk.dto.serverlesscontainers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetServerlessContainerDto {
    private String id;
    private String name;
    private String description;
    private String folderId;
    private String createdAt;
    private String url;
    private String status;
}
