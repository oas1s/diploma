import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.client.CloudFunctionsWebClient;
import yc.sdk.dto.functions.CreateFunctionDto;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.functions.GetFunctionsDto;

public class Main {
    public static void main(String[] args) {
        WebClient webClient = WebClient.builder().build();
        String token = "t1.9euelZqRjJaMkJ3OzpfOyYvKnJWXye3rnpWazpLJkYqRzZnLiZeNj4yVzsnl9Pc4LXlt-e8AGCWP3fT3eFt2bfnvABgljw.u-nRKzqqxIzoF7rgEYrE551ExD3_tYmd1heWaSaWF8Cj-MFogWlaYa80XWro9BWdfeWLsGYQVKn9Umj0Gxi9BQ";
        CloudFunctionsWebClient cloudFunctionsWebClient = new CloudFunctionsWebClient();
        CreateFunctionDto createFunctionDto = CreateFunctionDto.builder().description("sometest").folderId("b1g06rqs3rbeik9nd2n0")
                .name("tessst").build();
        CreateFunctionDto createFunctionDto1 = CreateFunctionDto.builder().description("sometest").folderId("b1g06rqs3rbeik9nd2n0")
                .name("tesssst").build();
        Operation operation = cloudFunctionsWebClient.createFunction(token,createFunctionDto);
        Operation operation1 = cloudFunctionsWebClient.createFunction(token,createFunctionDto1);
        GetFunctionsDto getFunctionsDto = cloudFunctionsWebClient.getFunctions(token,"b1g06rqs3rbeik9nd2n0");
        cloudFunctionsWebClient.deleteFunction(token,getFunctionsDto.getFunctions().get(0).getId());
        cloudFunctionsWebClient.deleteFunction(token,getFunctionsDto.getFunctions().get(1).getId());
        System.out.println(operation);
    }
}
