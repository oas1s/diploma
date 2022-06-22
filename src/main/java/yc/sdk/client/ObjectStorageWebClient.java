package yc.sdk.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.objectstorage.CreateBucketDto;
import yc.sdk.dto.objectstorage.GetBucketDto;
import yc.sdk.dto.objectstorage.GetBucketsDto;

public class ObjectStorageWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://storage.api.cloud.yandex.net/storage/v1/buckets").filter((request, next) -> {
        return next.exchange(request).flatMap(res -> {
            if (res.rawStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                res = res.mutate().rawStatusCode(299).build();
            }
            return Mono.just(res);
        });
    }).build();

    public String createBucket(String authenticationToken, CreateBucketDto createBucketDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createBucketDto)
                .retrieve()
                .toEntity(String.class)
                .block().getBody();
    }

    public Operation deleteBucket(String authenticationToken, String bucketId) {
        return webClient
                .delete()
                .uri("/" + bucketId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetBucketDto getBucket(String authenticationToken, String bucketId) {
        return webClient
                .get()
                .uri("/" + bucketId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetBucketDto.class)
                .block().getBody();
    }

    public GetBucketsDto getBuckets(String authenticationToken, String folderId) {
        //todo make other query params
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetBucketsDto.class)
                .block().getBody();
    }
}
