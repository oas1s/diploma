import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.ContainerRegistryWebClient;
import yc.sdk.dto.containerregistry.CreateRegistryDto;
import yc.sdk.dto.containerregistry.UpdateRegistryDto;

public class Main8 {
    public static void main(String[] args) {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        ContainerRegistryWebClient client = new ContainerRegistryWebClient();
        client.createRegistry(iamToken,CreateRegistryDto.builder().name("test").folderId(folderId).build());
        String id = client.getRegistrys(iamToken,folderId).getRegistries().stream().filter(r -> r.getName().equals("test")).findFirst().get().getId();
        client.updateRegistry(iamToken,id, UpdateRegistryDto.builder().name("test2").build());
        client.deleteRegistry(iamToken,id);
    }
}
