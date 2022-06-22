package ui.nodes.childs;

import com.intellij.ui.treeStructure.SimpleNode;
import ui.nodes.NamedNode;

public class ObjectStorageNodeChild extends NamedNode {

    public ObjectStorageNodeChild(SimpleNode aParent, String name) {
        super(aParent, name);
    }

    @Override
    protected SimpleNode[] buildChildren() {
        return new SimpleNode[0];
    }
}