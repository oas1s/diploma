package yc.sdk.dto.serviceaccounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateServiceAccountDto {
    private String updateMask;
    private String name;
    private String description;
}
