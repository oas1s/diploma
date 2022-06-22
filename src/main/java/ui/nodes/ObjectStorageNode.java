package ui.nodes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
//import ui.nodes.childs.CloudFunctionNodeChild;
import ui.nodes.childs.ObjectStorageNodeChild;
import yc.sdk.client.ObjectStorageWebClient;
import yc.sdk.dto.functions.GetFunctionDto;
import yc.sdk.dto.objectstorage.GetBucketDto;

import java.awt.*;
import java.util.stream.Collectors;

public class ObjectStorageNode extends NamedNode{
    private String folderId;
    private String IAMToken;
    private ObjectStorageWebClient objectStorageWebClient = new ObjectStorageWebClient();

    public ObjectStorageNode(SimpleNode aParent, String name,String folderId,String IAMToken) {
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

    private ObjectStorageNodeChild convertFuncToNode(GetBucketDto getFunctionDto){
        return new ObjectStorageNodeChild(this, getFunctionDto.getName());
    }

    @Override
    protected SimpleNode[] buildChildren() {
        return objectStorageWebClient.getBuckets(IAMToken,folderId).getBuckets().stream().map(this::convertFuncToNode)
                .collect(Collectors.toList()).toArray(new SimpleNode[0]);
    }
}
