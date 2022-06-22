package ui;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.IndexComparator;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.treeStructure.SimpleNode;
import com.intellij.ui.treeStructure.SimpleTree;
import com.intellij.ui.treeStructure.SimpleTreeBuilder;
import com.intellij.ui.treeStructure.SimpleTreeStructure;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ui.nodes.*;
import yc.sdk.client.AuthenticationWebClient;
import yc.sdk.client.CloudFunctionsWebClient;
import yc.sdk.dto.functions.CloudFunctionVersionResources;
import yc.sdk.dto.functions.CreateCloudFunctionVersionDto;
import yc.sdk.dto.functions.CreateFunctionDto;
import yc.sdk.dto.functions.GetFunctionDto;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static stub.Util.NOTIFICATIONS_TOPIC;

public class MySimpleTreeStructure extends SimpleTreeStructure {
    private final Project myProject;
    private final RootNode myRoot;
    private final SimpleTreeBuilder myTreeBuilder;
    private final CloudFunctionsWebClient cloudFunctionsWebClient = new CloudFunctionsWebClient();
    private String folderId = "b1g06rqs3rbeik9nd2n0";
    private String token = "";
    private SimpleNode cloudFunc;


    public MySimpleTreeStructure(Project project, JTree tree) {
        this.myRoot = new RootNode();
        this.myProject = project;
        myTreeBuilder = new SimpleTreeBuilder(tree, (DefaultTreeModel) tree.getModel(), this, new Comparator<SimpleNode>() {
            @Override
            public int compare(SimpleNode o1, SimpleNode o2) {
                if(o1 instanceof NamedNode && o2 instanceof NamedNode)
                   return o1.getName().compareTo(o2.getName());
                else
                    return IndexComparator.INSTANCE.compare(o1, o2);
            }
        });
        Disposer.register(project, myTreeBuilder);
        myTreeBuilder.initRoot();
        myTreeBuilder.expand(myRoot, null);
    }

    public void updateFromRoot(){
        myTreeBuilder.addSubtreeToUpdateByElement(myRoot);
    }

    @Override
    public Object getRootElement() {
        return myRoot;
    }

    public class RootNode extends NamedNode {

        public RootNode() {
            super(null,"Root");
        }

        @Override
        protected SimpleNode[] buildChildren() {
            if (token.isEmpty()) {
                SimpleNode[] childs = new SimpleNode[1];
                Level2Node node = new Level2Node(this,"SET YOUR OAUTH TOKEN PLEASE");
                node.buildChildren();
                childs[0] = node;
                return childs;
            }
            else {
                SimpleNode[] childs = new SimpleNode[1];
                FolderNode folderNode = new FolderNode(this,"default",folderId,token);
                cloudFunc = folderNode.getChildren()[0];
                folderNode.buildChildren();
                childs[0] = folderNode;
                return childs;
            }
        }
    }

    public class Level1Node extends NamedNode {

        public Level1Node(SimpleNode aParent, String name) {
            super(aParent, name);
            myClosedIcon = AllIcons.General.BalloonInformation;
            updatePresentation();
        }

        private void updatePresentation() {
            PresentationData presentation = getPresentation();
            presentation.clear();
            presentation.addText(myName, SimpleTextAttributes.REGULAR_ATTRIBUTES);
            presentation.addText(" Red", new SimpleTextAttributes(Font.PLAIN, Color.RED));
            presentation.addText(" Level1Node", SimpleTextAttributes.GRAYED_BOLD_ATTRIBUTES);
            update(presentation);
        }

        private Level1Node mapFuncToNode(GetFunctionDto getFunctionDto){
            return new Level1Node(this,getFunctionDto.getName());
        }

        @Override
        protected SimpleNode[] buildChildren() {
//            List<GetFunctionDto> functions = cloudFunctionsWebClient.getFunctions(token,"b1g06rqs3rbeik9nd2n0").getFunctions();
//            List<SimpleNode> childrens = functions.stream().map(this::mapFuncToNode).collect(Collectors.toList());
//            SimpleNode[] childs = new SimpleNode[childrens.size()];
//            return childrens.toArray(childs);
            return NO_CHILDREN;
        }
    }

    public class Level2Node extends NamedNode {
        private Color myColor = Color.RED;

        public Level2Node(SimpleNode aParent, String name) {
            super(aParent, name);
            myClosedIcon = AllIcons.General.BalloonWarning;
            updatePresentation();
        }

        private void updatePresentation() {
            PresentationData presentation = getPresentation();
            presentation.clear();
            presentation.addText(myName, SimpleTextAttributes.REGULAR_ATTRIBUTES);
        }

        @Override
        public void handleDoubleClickOrEnter(SimpleTree tree, InputEvent inputEvent) {
            SampleDialogWrapper sampleDialogWrapper = new SampleDialogWrapper();
            sampleDialogWrapper.showAndGet();
        }

        @Override
        protected SimpleNode[] buildChildren() {
            return NO_CHILDREN;
        }
    }

    public class SampleDialogWrapper extends DialogWrapper {
        private JTextField jTextField;

        public SampleDialogWrapper() {
            super(true); // use current window as parent
            setTitle("Please Enter your OAuth token");
            init();
        }

        @Override
        protected void doOKAction() {
            token = jTextField.getText();
            MyNotifier.notifyInfo(myProject,token);
            close(OK_EXIT_CODE);
            myRoot.cleanUpCache();
            myRoot.buildChildren();
            myRoot.update();
            myTreeBuilder.updateFromRoot(true);
            MyNotifier.notifyInfo(myProject, "Authentication successful");
        }

        @Override
        protected JComponent createCenterPanel() {
            JPanel dialogPanel = new JPanel(new BorderLayout());
            dialogPanel.setPreferredSize(new Dimension(600, 50));
            jTextField = new JTextField(8);

            //textField.setBounds(5, 5, 280, 50); // to get height, set large font
            jTextField.setFont(jTextField.getFont().deriveFont(16f));
//        JLabel label = new JLabel("testing");
//        label.setPreferredSize(new Dimension(100, 100));
//        dialogPanel.add(label, BorderLayout.CENTER);
            dialogPanel.add(jTextField);
            return dialogPanel;
        }
    }

    public class FolderNode extends NamedNode {

        private String folderId;
        private String OAuthToken;
        private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        public FolderNode(SimpleNode aParent, String name,String folderId,String OAuthToken) {
            super(aParent, name);
            this.folderId = folderId;
            this.OAuthToken = OAuthToken;
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
        public SimpleNode[] buildChildren() {
            SimpleNode[] childs = new SimpleNode[10];
            String iam = authenticationWebClient.getIAMToken(OAuthToken).getIamToken();
            CloudFunctionNode cloudFunctionNode = new CloudFunctionNode(this,"CloudFunctions",folderId,OAuthToken);
            cloudFunctionNode.buildChildren();
            childs[0] = cloudFunctionNode;
            childs[1] = new APIGatewayNode(this,"APIGateway");
            childs[2] = new APIGatewayNode(this,"CloudDNS");
            childs[3] = new CloudTriggerNode(this,"CloudTriggers",folderId,iam);
            childs[4] = new MessageQueueNode(this,"MessageQueues");
            childs[5] = new ObjectStorageNode(this,"ObjectStorage",folderId,iam);
            childs[6] = new ServerlessContainersNode(this,"ServerlessContainers",folderId,iam);
            childs[7] = new ContainerRegistryNode(this,"ContainerRegistry",folderId,iam);
            childs[8] = new APIGatewayNode(this,"ResourceManager");
            childs[9] = new ServiceAccountsNode(this,"ServiceAccounts");
            return childs;
        }
    }

    public class CloudFunctionNode extends NamedNode {

        private String folderId;
        private String OAuthToken;
        private CloudFunctionsWebClient client = new CloudFunctionsWebClient();
        private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();

        public CloudFunctionNode(SimpleNode aParent, String name,String folderId,String OAuthToken) {
            super(aParent, name);
            this.folderId = folderId;
            this.OAuthToken = OAuthToken;
            myClosedIcon = AllIcons.General.BalloonInformation;
            updatePresentation();
        }

        @Override
        public void handleDoubleClickOrEnter(SimpleTree tree, InputEvent inputEvent) {
            CloudFunctionCreationWindow cloudFunctionCreationWindow = new CloudFunctionCreationWindow();
            cloudFunctionCreationWindow.showAndGet();
        }

        private void updatePresentation() {
            PresentationData presentation = getPresentation();
            presentation.clear();
            presentation.addText(myName, SimpleTextAttributes.REGULAR_ATTRIBUTES);
            update(presentation);
        }

        private CloudFunctionNodeChild convertFuncToNode(GetFunctionDto getFunctionDto){
            return new CloudFunctionNodeChild(this, getFunctionDto.getName(),getFunctionDto.getId(),getFunctionDto.getHttpInvokeUrl());
        }


        @Override
        public SimpleNode[] buildChildren() {
            String iam = authenticationWebClient.getIAMToken(OAuthToken).getIamToken();
            List<GetFunctionDto> functions = client.getFunctions(iam,folderId).getFunctions();
            SimpleNode[] childs = functions.stream().map(this::convertFuncToNode).toArray(SimpleNode[]::new);
            return childs;
        }
    }


    public class CloudFunctionCreationWindow extends DialogWrapper {
        private JTextField jTextField;
        private JTextArea jTextArea;
        private CloudFunctionsWebClient client = new CloudFunctionsWebClient();
        private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        public CloudFunctionCreationWindow() {
            super(true); // use current window as parent
            setTitle("Please fill fields required to create function");
            init();
        }

        @Override
        protected void doOKAction() {
            client.createFunction(authenticationWebClient.getIAMToken(token).getIamToken(), CreateFunctionDto.builder().folderId(folderId).name(jTextField.getText())
                    .description(jTextArea.getText()).build());
            myTreeBuilder.updateFromRoot(true);
            super.doOKAction();
        }

        @Override
        protected JComponent createCenterPanel() {
            JPanel dialogPanel = new JPanel();
            dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
            dialogPanel.setPreferredSize(new Dimension(600, 200));
            jTextField = new JTextField();
            jTextField.setFont(jTextField.getFont().deriveFont(16f));
            jTextField.setMaximumSize(new Dimension(600, 50));
            jTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel label = new JLabel("Set cloud function name");
            label.setFont(new Font("tape",1,24));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel label2 = new JLabel("Set cloud function description");
            label2.setFont(new Font("tape",1,24));
            label2.setAlignmentX(Component.LEFT_ALIGNMENT);
            jTextArea = new JTextArea();
            jTextArea.setFont(jTextField.getFont().deriveFont(16f));
            jTextArea.setMaximumSize(new Dimension(600, 100));
            jTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(label);
            dialogPanel.add(jTextField);
            dialogPanel.add(label2);
            dialogPanel.add(jTextArea);
            return dialogPanel;
        }
    }

    public class CloudFunctionNodeChild extends NamedNode {

        private String id;
        private String httpInvokeUrl;

        public CloudFunctionNodeChild(SimpleNode aParent, String name, String id, String httpInvokeUrl) {
            super(aParent, name);
            this.id = id;
            this.httpInvokeUrl = httpInvokeUrl;
        }

        @Override
        public void handleDoubleClickOrEnter(SimpleTree tree, InputEvent inputEvent) {
            Component component = inputEvent.getComponent();
            List<String> strings = new ArrayList<>();
            strings.add("Create Version");
            strings.add("Copy Id");
            strings.add("Copy Invoke URL");
            strings.add("Edit");
            strings.add("Delete");
            CloudFuncChildStep<String> stringSteppp = new CloudFuncChildStep<>("Cloud Func actions",strings,this);
            Point p = MouseInfo.getPointerInfo().getLocation();
            JBPopupFactory.getInstance().createListPopup(stringSteppp).showInScreenCoordinates(component,p);
        }

        public String getFuncId(){
            return id;
        }

        public String getFuncHttpInvokeUrl(){
            return httpInvokeUrl;
        }

        @Override
        protected SimpleNode[] buildChildren() {
            return new SimpleNode[0];
        }
    }

    public class CloudFuncChildStep<String> extends BaseListPopupStep<String> {
        private CloudFunctionNodeChild cloudFunctionNodeChild;
        private CloudFunctionsWebClient client = new CloudFunctionsWebClient();
        private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();

        public CloudFuncChildStep(String title,List<String> strings,CloudFunctionNodeChild cloudFunctionNodeChild) {
            super((java.lang.String) title,strings);
            this.cloudFunctionNodeChild = cloudFunctionNodeChild;
        }

        @Override
        public @Nullable PopupStep<?> onChosen(String selectedValue, boolean finalChoice) {
            if (selectedValue.equals("Create Version")) {
                CreateVersionCloudFuncWindow createVersionCloudFuncWindow = new CreateVersionCloudFuncWindow(cloudFunctionNodeChild);
                createVersionCloudFuncWindow.showAndGet();
            }
            if (selectedValue.equals("Copy Invoke URL")) {
                StringSelection selection = new StringSelection(cloudFunctionNodeChild.getFuncHttpInvokeUrl());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
            if (selectedValue.equals("Copy Id")) {
                StringSelection selection = new StringSelection(cloudFunctionNodeChild.getFuncId());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
            if (selectedValue.equals("Delete")) {
                cloudFunctionsWebClient.deleteFunction(authenticationWebClient.getIAMToken(token).getIamToken(),
                        cloudFunctionNodeChild.getFuncId());
                myTreeBuilder.updateFromRoot();
            }
            return FINAL_CHOICE;
        }
    }

    public class CreateVersionCloudFuncWindow extends DialogWrapper {
        private JTextField jTextField;
        private JTextField jTextField2;
        private JTextArea jTextArea;
        private JComboBox jComboBox;
        private JComboBox jComboBox2;
        private CloudFunctionNodeChild cloudFunctionNodeChild;
        private CloudFunctionsWebClient client = new CloudFunctionsWebClient();
        private AuthenticationWebClient authenticationWebClient = new AuthenticationWebClient();
        public CreateVersionCloudFuncWindow(CloudFunctionNodeChild cloudFunctionNodeChild) {
            super(true); // use current window as parent
            this.cloudFunctionNodeChild = cloudFunctionNodeChild;
            setTitle("Please fill fields required to create function version");
            init();
        }

        @Override
        @SneakyThrows
        protected void doOKAction() {
            client.createVersion(authenticationWebClient.getIAMToken(token).getIamToken(),
                    CreateCloudFunctionVersionDto.builder().content(jTextArea.getText()).functionId(jTextField.getText())
                            .runtime((String) jComboBox.getSelectedItem()).resources(CloudFunctionVersionResources
                                    .builder().memory(134217728).build()).executionTimeout(jTextField2.getText())
                            .entrypoint("index.handler").build());
            if (jComboBox2.getSelectedItem().equals("Yes")) {
                client.makeFunctionVisible(authenticationWebClient.getIAMToken(token).getIamToken(),jTextField.getText());
            }
            myTreeBuilder.updateFromRoot();
            super.doOKAction();
        }

        @Override
        protected JComponent createCenterPanel() {
            JPanel dialogPanel = new JPanel();
            dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
            dialogPanel.setPreferredSize(new Dimension(600, 400));

            JLabel label1 = new JLabel("Set cloud function id");
            label1.setFont(new Font("tape",1,18));
            label1.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(label1);

            jTextField = new JTextField();
            jTextField.setFont(jTextField.getFont().deriveFont(16f));
            jTextField.setMaximumSize(new Dimension(600, 50));
            jTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
            jTextField.setText(cloudFunctionNodeChild.getFuncId());
            dialogPanel.add(jTextField);

            JLabel label2 = new JLabel("Set cloud function runtime");
            label2.setFont(new Font("tape",1,18));
            label2.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(label2);

            jComboBox = new JComboBox();
            jComboBox.setEditable(true);
            jComboBox.addItem("python39");
            jComboBox.addItem("bash");
            jComboBox.addItem("go");
            jComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            jComboBox.setMaximumSize(new Dimension(600, 50));
            dialogPanel.add(jComboBox);

            JLabel label3 = new JLabel("Set execution timeout in secs");
            label3.setFont(new Font("tape",1,18));
            label3.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(label3);

            jTextField2 = new JTextField();
            jTextField2.setFont(jTextField2.getFont().deriveFont(16f));
            jTextField2.setMaximumSize(new Dimension(600, 50));
            jTextField2.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(jTextField2);

            JLabel label5 = new JLabel("Make public:");
            label5.setFont(new Font("tape",1,18));
            label5.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(label5);

            jComboBox2 = new JComboBox();
            jComboBox2.setEditable(true);
            jComboBox2.addItem("Yes");
            jComboBox2.addItem("No");
            jComboBox2.setAlignmentX(Component.LEFT_ALIGNMENT);
            jComboBox2.setMaximumSize(new Dimension(600, 50));
            dialogPanel.add(jComboBox2);

            JLabel label4 = new JLabel("Define the handler");
            label4.setFont(new Font("tape",1,18));
            label4.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(label4);

            jTextArea = new JTextArea();
            jTextArea.setFont(jTextField.getFont().deriveFont(16f));
            jTextArea.setMaximumSize(new Dimension(600, 100));
            jTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            dialogPanel.add(jTextArea);

            return dialogPanel;
        }
    }
}
