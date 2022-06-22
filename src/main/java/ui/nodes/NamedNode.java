package ui.nodes;

import com.intellij.ui.treeStructure.CachingSimpleNode;
import com.intellij.ui.treeStructure.SimpleNode;

public abstract class NamedNode extends CachingSimpleNode {

    protected NamedNode(SimpleNode aParent, String name) {
        super(aParent);
        myName = name;
    }
}