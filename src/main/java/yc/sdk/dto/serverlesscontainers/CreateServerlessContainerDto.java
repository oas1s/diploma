package yc.sdk.dto.serverlesscontainers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateServerlessContainerDto {
    private String folderId;
    private String name;
    private String description;
}
