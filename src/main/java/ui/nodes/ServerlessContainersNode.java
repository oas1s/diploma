package ui.nodes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.childs.ObjectStorageNodeChild;
import ui.nodes.childs.ServerlessContainersNodeChild;
import yc.sdk.client.ObjectStorageWebClient;
import yc.sdk.client.ServerlessContainerWebClient;
import yc.sdk.dto.objectstorage.GetBucketDto;
import yc.sdk.dto.serverlesscontainers.GetServerlessContainerDto;

import java.awt.*;
import java.util.stream.Collectors;

public class ServerlessContainersNode extends NamedNode{
    private String folderId;
    private String IAMToken;
    private ServerlessContainerWebClient serverlessContainerWebClient = new ServerlessContainerWebClient();

    public ServerlessContainersNode(SimpleNode aParent, String name,String folderId,String IAMToken) {
        super(aParent, name);
        this.folderId = folderId;
        this.IAMToken = IAMToken;
        myClosedIcon = AllIcons.General.BalloonInformation;
        updatePresentation();
    }

    private void updatePresentation() {
        PresentationData presentation = getPresentation();
        presentation.clear();
        presentation.addText(myName, SimpleTextAttributes.REGULAR_ATTRIBUTES);
        update(presentation);
    }


    private ServerlessContainersNodeChild convertFuncToNode(GetServerlessContainerDto getServerlessContainerDto){
        return new ServerlessContainersNodeChild(this, getServerlessContainerDto.getName());
    }

    @Override
    protected SimpleNode[] buildChildren() {
        return serverlessContainerWebClient.getServerlessContainers(IAMToken, folderId).getContainers().stream().map(this::convertFuncToNode).toArray(SimpleNode[]::new);
    }
}
