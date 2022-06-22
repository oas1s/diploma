package yc.sdk.dto.serviceaccounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetServiceAccountsDto {
    private List<GetServiceAccountDto> serviceAccounts;
}
