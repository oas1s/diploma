package ui.nodes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.childs.ContainerRegistryNodeChild;
import ui.nodes.childs.ObjectStorageNodeChild;
import yc.sdk.client.ContainerRegistryWebClient;
import yc.sdk.client.ObjectStorageWebClient;
import yc.sdk.dto.containerregistry.GetRegistryDto;
import yc.sdk.dto.objectstorage.GetBucketDto;

import java.awt.*;
import java.util.stream.Collectors;

public class ContainerRegistryNode extends NamedNode{
    private String folderId;
    private String IAMToken;
    private ContainerRegistryWebClient client = new ContainerRegistryWebClient();

    public ContainerRegistryNode(SimpleNode aParent, String name,String folderId,String IAMToken) {
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

    private ContainerRegistryNodeChild convertFuncToNode(GetRegistryDto getRegistryDto){
        return new ContainerRegistryNodeChild(this, getRegistryDto.getName());
    }

    @Override
    protected SimpleNode[] buildChildren() {
        return client.getRegistrys(IAMToken, folderId).getRegistries().stream().map(this::convertFuncToNode).toArray(SimpleNode[]::new);
    }
}
