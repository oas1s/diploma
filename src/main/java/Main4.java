import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudTriggersWebClient;
import yc.sdk.client.ServiceAccountsWebClient;
import yc.sdk.dto.serviceaccounts.CreateServiceAccountDto;
import yc.sdk.dto.serviceaccounts.GetServiceAccountsDto;
import yc.sdk.dto.serviceaccounts.UpdateServiceAccountDto;

public class Main4 {
    public static void main(String[] args) {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        ServiceAccountsWebClient serviceAccountsWebClient = new ServiceAccountsWebClient();
        CreateServiceAccountDto createServiceAccountDto = CreateServiceAccountDto.builder().name("tape").description("inatore")
                .folderId(folderId).build();
        serviceAccountsWebClient.createServiceAccount(iamToken,createServiceAccountDto);
        GetServiceAccountsDto getServiceAccountsDto = serviceAccountsWebClient.getServiceAccounts(iamToken,folderId);
        serviceAccountsWebClient.updateServiceAccount(iamToken,getServiceAccountsDto.getServiceAccounts().get(2).getId(),
                UpdateServiceAccountDto.builder().name("newname").build());
        serviceAccountsWebClient.deleteServiceAccount(iamToken,getServiceAccountsDto.getServiceAccounts().get(2).getId());
    }
}
