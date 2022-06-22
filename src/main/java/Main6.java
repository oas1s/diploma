import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudWebClient;

public class Main6 {
    public static void main(String[] args) {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        CloudWebClient client = new CloudWebClient();
        System.out.println(client.getClouds(iamToken));
    }
}
