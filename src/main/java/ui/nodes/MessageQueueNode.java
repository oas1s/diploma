package ui.nodes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.childs.CloudTriggerNodeChild;

import java.awt.*;

public class MessageQueueNode extends NamedNode {

    public MessageQueueNode(SimpleNode aParent, String name) {
        super(aParent, name);
        myClosedIcon = AllIcons.General.BalloonInformation;
        updatePresentation();
    }

    private void updatePresentation() {
        PresentationData presentation = getPresentation();
        presentation.clear();
        presentation.addText(myName, SimpleTextAttributes.REGULAR_ATTRIBUTES);
        update(presentation);
    }


    @Override
    protected SimpleNode[] buildChildren() {
        SimpleNode[] childs = new SimpleNode[1];
        childs[0] = new CloudTriggerNodeChild(this,"tapesss");
        return childs;
    }
}
