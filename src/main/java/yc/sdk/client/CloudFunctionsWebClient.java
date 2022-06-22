package yc.sdk.client;

import com.amazonaws.util.Base64;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import yc.sdk.dto.functions.CreateCloudFunctionVersionDto;
import yc.sdk.dto.functions.CreateFunctionDto;
import yc.sdk.dto.common.Operation;
import yc.sdk.dto.functions.GetFunctionDto;
import yc.sdk.dto.functions.GetFunctionsDto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CloudFunctionsWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("https://serverless-functions.api.cloud.yandex.net/functions/v1/functions").build();

    public Operation createFunction(String authenticationToken, CreateFunctionDto createFunctionDto) {
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createFunctionDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation deleteFunction(String authenticationToken, String functionId) {
        return webClient
                .delete()
                .uri("/" + functionId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public GetFunctionDto getFunction(String authenticationToken, String functionId) {
        return webClient
                .get()
                .uri("/" + functionId)
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetFunctionDto.class)
                .block().getBody();
    }

    public GetFunctionsDto getFunctions(String authenticationToken,String folderId) {
        //todo make other query params
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("folderId",folderId).build())
                .header("Authorization", "Bearer " + authenticationToken)
                .retrieve()
                .toEntity(GetFunctionsDto.class)
                .block().getBody();
    }

    public Operation createVersion(String authenticationToken,CreateCloudFunctionVersionDto createCloudFunctionVersionDto) throws Exception {
        WebClient versionWebClient = WebClient.builder().baseUrl("https://serverless-functions.api.cloud.yandex.net/functions/v1/versions").build();
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("index.py"), "utf-8"));
        writer.write(createCloudFunctionVersionDto.getContent());
        writer.close();
        zipSingleFile(Path.of("index.py"), "index.zip");
        byte[] fileContent = Files.readAllBytes(Path.of("index.zip"));
        byte[] encoded = Base64.encode(fileContent);
        createCloudFunctionVersionDto.setContent(new String(encoded));
        Files.delete(Path.of("index.py"));
        Files.delete(Path.of("index.zip"));

        return versionWebClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .bodyValue(createCloudFunctionVersionDto)
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }

    public Operation makeFunctionVisible(String authenticationToken, String functionId){
        WebClient webClient = WebClient.builder().baseUrl(
                String.format("https://serverless-functions.api.cloud.yandex.net/functions/v1/functions/%s:updateAccessBindings",
                        functionId)
        ).build();
        String body = "{\n" +
                "  \"accessBindingDeltas\": [\n" +
                "    {\n" +
                "      \"action\": \"ADD\",\n" +
                "      \"accessBinding\": {\n" +
                "        \"roleId\": \"serverless.functions.invoker\",\n" +
                "        \"subject\": {\n" +
                "          \"id\": \"allUsers\",\n" +
                "          \"type\": \"system\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return webClient
                .post()
                .header("Authorization", "Bearer " + authenticationToken)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .toEntity(Operation.class)
                .block().getBody();
    }


    public static void zipSingleFile(Path source, String zipFileName) throws IOException {

        if (!Files.isRegularFile(source)) {
            System.err.println("Please provide a file.");
            return;
        }

        try (
                ZipOutputStream zos = new ZipOutputStream(
                        new FileOutputStream(zipFileName));
                FileInputStream fis = new FileInputStream(source.toFile());
        ) {

            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }

    }

}
