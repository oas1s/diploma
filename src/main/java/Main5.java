import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import yc.sdk.client.ApiGatewayWebClient;
import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.dto.apigateway.CreateApiGatewayDto;
import yc.sdk.dto.apigateway.GetApiGatewaysDto;
import yc.sdk.dto.apigateway.UpdateApiGatewayDto;

public class Main5 {
    public static void main(String[] args) throws JsonProcessingException {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        ApiGatewayWebClient apiGatewayWebClient = new ApiGatewayWebClient();
        CreateApiGatewayDto createApiGatewayDto = CreateApiGatewayDto.builder().name("tapess").description("dsds")
                .openapiSpec("openapi: 3.0.0\n" +
                        "info:\n" +
                        "  title: Sample API\n" +
                        "  version: 1.0.0\n" +
                        "servers:\n" +
                        "- url: https://d5d9etraplafvoqo10fh.apigw.yandexcloud.net\n" +
                        "paths:\n" +
                        "  /:\n" +
                        "    get:\n" +
                        "      x-yc-apigateway-integration:\n" +
                        "        type: dummy\n" +
                        "        content:\n" +
                        "          '*': Hello, World!ssss\n" +
                        "        http_code: 200\n" +
                        "        http_headers:\n" +
                        "          Content-Type: text/plain").folderId(folderId).build();
        System.out.println(new ObjectMapper().writeValueAsString(createApiGatewayDto));
        apiGatewayWebClient.createApiGateway(iamToken,createApiGatewayDto);
        GetApiGatewaysDto getApiGatewaysDto = apiGatewayWebClient.getApiGateways(iamToken,folderId);
        UpdateApiGatewayDto updateApiGatewayDto = UpdateApiGatewayDto.builder().name("newname")
                .openapiSpec("openapi: 3.0.0\n" +
                        "info:\n" +
                        "  title: Sample API\n" +
                        "  version: 1.0.0\n" +
                        "servers:\n" +
                        "- url: https://d5d9etraplafvoqo10fh.apigw.yandexcloud.net\n" +
                        "paths:\n" +
                        "  /:\n" +
                        "    get:\n" +
                        "      x-yc-apigateway-integration:\n" +
                        "        type: dummy\n" +
                        "        content:\n" +
                        "          '*': Hello, World!ssss\n" +
                        "        http_code: 200\n" +
                        "        http_headers:\n" +
                        "          Content-Type: text/plain").build();
        System.out.println(new ObjectMapper().writeValueAsString(updateApiGatewayDto));
        apiGatewayWebClient.updateApiGateway(iamToken,getApiGatewaysDto.getApiGateways().get(0).getId(),updateApiGatewayDto);
        apiGatewayWebClient.deleteApiGateway(iamToken,getApiGatewaysDto.getApiGateways().get(0).getId());
    }
}
