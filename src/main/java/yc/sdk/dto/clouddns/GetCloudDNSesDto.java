package yc.sdk.dto.clouddns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCloudDNSesDto {
    private List<GetCloudDNSDto> dnsZones;
}
