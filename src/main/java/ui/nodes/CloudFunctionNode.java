//package ui.nodes;
//
//import com.intellij.icons.AllIcons;
//import com.intellij.ide.projectView.PresentationData;
//import com.intellij.ui.SimpleTextAttributes;
//import com.intellij.ui.treeStructure.SimpleNode;
//import ui.nodes.childs.CloudFunctionNodeChild;
//import yc.sdk.client.AuthenticationWebClient;
//import yc.sdk.client.CloudFunctionsWebClient;
//import yc.sdk.dto.functions.GetFunctionDto;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class CloudFunctionNode extends NamedNode {
//
//    private String folderId;
//    private String OAuthToken;
//    private CloudFunctionsWebClient client = new CloudFunctionsWebClient();
//    private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
//
//    public CloudFunctionNode(SimpleNode aParent, String name,String folderId,String OAuthToken) {
//        super(aParent, name);
//        this.folderId = folderId;
//        this.OAuthToken = OAuthToken;
//        myClosedIcon = AllIcons.General.BalloonInformation;
//        updatePresentation();
//    }
//
//    private void updatePresentation() {
//        PresentationData presentation = getPresentation();
//        presentation.clear();
//        presentation.addText(myName, SimpleTextAttributes.REGULAR_ATTRIBUTES);
//        update(presentation);
//    }
//
//    private CloudFunctionNodeChild convertFuncToNode(GetFunctionDto getFunctionDto){
//        return new CloudFunctionNodeChild(this, getFunctionDto.getName(),getFunctionDto.getId());
//    }
//
//
//    @Override
//    public SimpleNode[] buildChildren() {
//        String iam = authenticationWebClient.getIAMToken(OAuthToken).getIamToken();
//        List<GetFunctionDto> functions = client.getFunctions(iam,folderId).getFunctions();
//        SimpleNode[] childs = functions.stream().map(this::convertFuncToNode).toArray(SimpleNode[]::new);
//        return childs;
//    }
//}
