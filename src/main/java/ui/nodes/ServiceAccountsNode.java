package ui.nodes;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.childs.CloudTriggerNodeChild;
import ui.nodes.childs.ServiceAccountNodeChild;

import java.awt.*;

public class ServiceAccountsNode extends NamedNode{
    public ServiceAccountsNode(SimpleNode aParent, String name) {
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
        SimpleNode[] childs = new SimpleNode[2];
        childs[0] = new ServiceAccountNodeChild(this,"adminn");
        childs[1] = new ServiceAccountNodeChild(this,"yc-assistant-plugin");
        return childs;
    }
}
