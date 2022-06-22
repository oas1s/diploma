import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.ObjectStorageWebClient;
import yc.sdk.dto.objectstorage.*;

public class Main2 {
    public static void main(String[] args) throws JsonProcessingException {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        CreateBucketDto createBucketDto = CreateBucketDto.builder().name("taaapeeee").folderId(folderId)
                .anonymousAccessFlags(AnonymousAccessFlags.builder().read(false).list(false).configRead(false).build())
                .defaultStorageClass(DefaultStorageClass.STANDARD).maxSize("0").build();
        System.out.println(new ObjectMapper().writeValueAsString(createBucketDto));
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        ObjectStorageWebClient objectStorageWebClient = new ObjectStorageWebClient();
        objectStorageWebClient.createBucket(iamToken, createBucketDto);
        GetBucketsDto getBucketsDto = objectStorageWebClient.getBuckets(iamToken,folderId);
        GetBucketDto getBucketDto = objectStorageWebClient.getBucket(iamToken,getBucketsDto.getBuckets().get(0).getName());
        objectStorageWebClient.deleteBucket(iamToken,getBucketDto.getName());
    }

}
