//package ui.nodes;
//
//import com.intellij.icons.AllIcons;
//import com.intellij.ide.projectView.PresentationData;
//import com.intellij.ui.SimpleTextAttributes;
//import com.intellij.ui.treeStructure.SimpleNode;
//import yc.sdk.client.AuthenticationWebClient;
//
//public class FolderNode extends NamedNode {
//
//    private String folderId;
//    private String OAuthToken;
//    private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
//    public FolderNode(SimpleNode aParent, String name,String folderId,String OAuthToken) {
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
//
//    @Override
//    public SimpleNode[] buildChildren() {
//        SimpleNode[] childs = new SimpleNode[10];
//        String iam = authenticationWebClient.getIAMToken(OAuthToken).getIamToken();
//        CloudFunctionNode cloudFunctionNode = new CloudFunctionNode(this,"CloudFunctions",folderId,OAuthToken);
//        cloudFunctionNode.buildChildren();
//        childs[0] = cloudFunctionNode;
//        childs[1] = new APIGatewayNode(this,"APIGateway");
//        childs[2] = new APIGatewayNode(this,"CloudDNS");
//        childs[3] = new CloudTriggerNode(this,"CloudTriggers",folderId,iam);
//        childs[4] = new MessageQueueNode(this,"MessageQueues");
//        childs[5] = new ObjectStorageNode(this,"ObjectStorage",folderId,iam);
//        childs[6] = new ServerlessContainersNode(this,"ServerlessContainers",folderId,iam);
//        childs[7] = new ContainerRegistryNode(this,"ContainerRegistry",folderId,iam);
//        childs[8] = new APIGatewayNode(this,"ResourceManager");
//        childs[9] = new APIGatewayNode(this,"ServiceAccounts");
//        return childs;
//    }
//}
