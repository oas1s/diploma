package actions;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.MyNotifier;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static stub.Util.NOTIFICATIONS_TOPIC;

public class CommonAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        List<String> operations = new ArrayList<>();
        operations.add("tape");
        operations.add("inatore");
        DataContext dataContext = e.getDataContext();
        List<String> strings = new ArrayList<>();
        strings.add("create");
        strings.add("edit");
        Steppp<String> stringSteppp = new Steppp<>("cloud func",strings);
//        baseListPopupStep.onChosen()
        Notifications.Bus.notify(new Notification(NOTIFICATIONS_TOPIC, "header", "Common action", NotificationType.INFORMATION));
//        JBPopupFactory.getInstance().createActionGroupPopup("Tape", ActionGroup.EMPTY_GROUP,
//                DataContext.EMPTY_CONTEXT, JBPopupFactory.ActionSelectionAid.MNEMONICS,true).showInBestPositionFor(dataContext)
        JBPopupFactory.getInstance().createListPopup(stringSteppp).showInBestPositionFor(dataContext);
        JBPopupFactory.getInstance().createListPopup(stringSteppp).showInFocusCenter();

//        JBPopupFactory.getInstance().createListPopup(baseListPopupStep).showInBestPositionFor(dataContext);
//        JBPopupFactory.getInstance().createPopupChooserBuilder(operations).createPopup().showInBestPositionFor(dataContext);

    }

    public static class Steppp<String> extends BaseListPopupStep<String> {

        public Steppp(String title,List<String> strings) {
             super((java.lang.String) title,strings);
        }

        @Override
        public @Nullable PopupStep<?> onChosen(String selectedValue, boolean finalChoice) {
            if (selectedValue.equals("create")) {
                Notifications.Bus.notify(new Notification(NOTIFICATIONS_TOPIC, "cloud create", "cloud create", NotificationType.INFORMATION));
            }
            if (selectedValue.equals("edit")) {
                Notifications.Bus.notify(new Notification(NOTIFICATIONS_TOPIC, "cloud edit", "cloud edit", NotificationType.INFORMATION));
            }
            return FINAL_CHOICE;
        }
    }
}
