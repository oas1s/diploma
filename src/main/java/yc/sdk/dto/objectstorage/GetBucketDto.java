package yc.sdk.dto.objectstorage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBucketDto {
    private String name;
    private String folderId;
    private DefaultStorageClass defaultStorageClass;
    private String maxSize;
    private AnonymousAccessFlags anonymousAccessFlags;
    private String versioning;
    private String createdAt;
}
