package UI;

import Type.Chioce;

import javax.swing.*;

public class MComboBox extends JComboBox {

    @Override
    public Object  getSelectedItem(){
        Object o =super.getSelectedItem();
        if(o instanceof Chioce){
            return ((Chioce)o).daima;
        } else return super.getSelectedItem();
    }
}
