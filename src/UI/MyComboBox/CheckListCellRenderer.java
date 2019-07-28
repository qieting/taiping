package UI.MyComboBox;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serializable;

public class CheckListCellRenderer extends JCheckBox implements ListCellRenderer,
        Serializable {
    protected static Border noFocusBorder;
    /**
     * Constructs a default renderer object for an item
     * in a list.
     */
    MyComboBox myComboBox;
    public CheckListCellRenderer( MyComboBox myComboBox) {
        super();
        this.myComboBox=myComboBox;
        if (noFocusBorder == null) {
            noFocusBorder = new EmptyBorder(1, 1, 1, 1);
        }
        setOpaque(false);
        setBorder(noFocusBorder);

    }
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());
        if (isSelected) {
            setBackground(Color.WHITE);
            setForeground(Color.RED);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        if (value instanceof CheckValue) {
            if(index!=-1) {
                CheckValue ckValue = (CheckValue) value;
                this.setText(ckValue.value == null ? "" : ckValue.value.daima + ckValue.value.mingzi);
                this.setSelected(ckValue.bolValue);
            }else {
                this.setText(myComboBox.getComboid());
                this.setSelected(true);
            }
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setBorder((cellHasFocus) ?
                UIManager.getBorder("List.focusCellHighlightBorder") :
                noFocusBorder);
        return this;
    }


}

