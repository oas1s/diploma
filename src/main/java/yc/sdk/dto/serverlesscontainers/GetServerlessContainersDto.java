package yc.sdk.dto.serverlesscontainers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetServerlessContainersDto {
    private List<GetServerlessContainerDto> containers;
}
