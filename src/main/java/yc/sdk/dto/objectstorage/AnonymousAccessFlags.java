package yc.sdk.dto.objectstorage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnonymousAccessFlags {
    private Boolean read;
    private Boolean list;
    private Boolean configRead;
}
