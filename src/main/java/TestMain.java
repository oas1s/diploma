import com.amazonaws.util.Base64;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.ui.treeStructure.SimpleNode;
//import ui.nodes.childs.CloudFunctionNodeChild;
import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudFunctionsWebClient;
import yc.sdk.dto.functions.CloudFunctionVersionResources;
import yc.sdk.dto.functions.CreateCloudFunctionVersionDto;
import yc.sdk.dto.functions.GetFunctionDto;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestMain {
    private static CloudFunctionsWebClient client = new CloudFunctionsWebClient();
    private static AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();

    public static void main(String[] args) throws Exception {
        StringSelection selection = new StringSelection("tape");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
//        String content = "def handler(event, context):\n" +
//                "    return {\n" +
//                "        'statusCode': 200,\n" +
//                "        'body': 'Hessllos Worddldd!',\n" +
//                "    }";
        String iam = authenticationWebClient.getIAMToken("AQAEA7qkBtaaAATuwdZ4h0mFa0Dfg26Lu7WsajY").getIamToken();
//        CreateCloudFunctionVersionDto createCloudFunctionVersionDto = CreateCloudFunctionVersionDto.builder().functionId("d4e4aikllvdo9m7n2opr")
//                .entrypoint("index.handler").executionTimeout("3s").resources(CloudFunctionVersionResources.builder().memory(134217728).build())
//                .runtime("python39").content(content).build();
//        client.createVersion(iam,createCloudFunctionVersionDto);
        client.makeFunctionVisible(iam,"d4etnq6h9bqhppm5f5ks");
    }
}