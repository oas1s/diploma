package ui.nodes.childs;

import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.NamedNode;

public class CloudTriggerNodeChild extends NamedNode {
    public CloudTriggerNodeChild(SimpleNode aParent, String name) {
        super(aParent, name);
    }

    @Override
    protected SimpleNode[] buildChildren() {
        return new SimpleNode[0];
    }
}
