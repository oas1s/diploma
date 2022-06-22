package ui.nodes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.childs.CloudTriggerNodeChild;
import ui.nodes.childs.ServerlessContainersNodeChild;
import yc.sdk.client.CloudTriggersWebClient;
import yc.sdk.client.ObjectStorageWebClient;
import yc.sdk.dto.serverlesscontainers.GetServerlessContainerDto;
import yc.sdk.dto.triggers.GetTriggerDto;

import java.awt.*;

public class CloudTriggerNode extends NamedNode {
    private String folderId;
    private String IAMToken;
    private CloudTriggersWebClient client = new CloudTriggersWebClient();

    public CloudTriggerNode(SimpleNode aParent, String name,String folderId,String IAMToken) {
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


    private CloudTriggerNodeChild convertFuncToNode(GetTriggerDto triggerDto){
        return new CloudTriggerNodeChild(this, triggerDto.getName());
    }

    @Override
    protected SimpleNode[] buildChildren() {
        return client.getTriggers(IAMToken, folderId).getTriggers().stream().map(this::convertFuncToNode).toArray(SimpleNode[]::new);
    }
}
