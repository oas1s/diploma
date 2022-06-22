package yc.sdk.dto.serviceaccounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetServiceAccountDto {
    private String id;
    private String folderId;
    private String createdAt;
    private String name;
    private String description;
}
