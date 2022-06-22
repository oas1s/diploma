package yc.sdk.client;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import yc.sdk.dto.auth.AuthResponse;
import yc.sdk.dto.auth.Authentication;

public class AuthenticationWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://iam.api.cloud.yandex.net/iam/v1/tokens").build();

    public AuthResponse getIAMToken(String oauthToken){
        return webClient
                .method(HttpMethod.POST)
                .body(Mono.just(Authentication.builder().yandexPassportOauthToken(oauthToken).build()), Authentication.class)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();
    }
}
