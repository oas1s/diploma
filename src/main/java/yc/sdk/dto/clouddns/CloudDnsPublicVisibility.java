package yc.sdk.dto.clouddns;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class CloudDnsPublicVisibility {
}
