package yc.sdk.dto.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFunctionsDto {
    private List<GetFunctionDto> functions;
    private String nextPageToken;
}
