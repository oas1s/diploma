import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudDNSWebClient;
import yc.sdk.dto.clouddns.CloudDnsPublicVisibility;
import yc.sdk.dto.clouddns.CreateCloudDNSDto;
import yc.sdk.dto.clouddns.UpdateCloudDNSDto;

public class Main9 {
    public static void main(String[] args) {
        AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        String folderId = "b1g06rqs3rbeik9nd2n0";
        String iamToken = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
        CloudDNSWebClient client = new CloudDNSWebClient();
        client.createCloudDNS(iamToken, CreateCloudDNSDto.builder().name("tape").description("unbound")
                .folderId(folderId).publicVisibility(CloudDnsPublicVisibility.builder().build()).zone("swagg3r.sqert.").build());
        String id = client.getCloudDNSs(iamToken,folderId).getDnsZones().stream().filter(z -> z.getName().equals("tape")).findFirst().get().getId();
        client.updateCloudDNS(iamToken,id, UpdateCloudDNSDto.builder().name("swag").publicVisibility(CloudDnsPublicVisibility.builder().build()).build());
        client.deleteCloudDNS(iamToken,id);
    }
}
