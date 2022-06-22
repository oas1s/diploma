package yc.sdk.dto.clouddns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCloudDNSDto {
    private String updateMask;
    private String name;
    private String description;
    private CloudDnsPrivateVisibility privateVisibility;
    private CloudDnsPublicVisibility publicVisibility;
}
