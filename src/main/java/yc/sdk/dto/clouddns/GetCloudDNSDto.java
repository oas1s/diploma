package yc.sdk.dto.clouddns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCloudDNSDto {
    private String id;
    private String createdAt;
    private String folderId;
    private String name;
    private String description;
    private String zone;
    private CloudDnsPrivateVisibility privateVisibility;
    private CloudDnsPublicVisibility publicVisibility;
}

