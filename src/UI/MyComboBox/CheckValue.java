package UI.MyComboBox;


import Type.Chioce;

public class CheckValue {
    public boolean bolValue = false;
    public Chioce value;
    public CheckValue() {
    }
    public CheckValue(boolean bolValue, Chioce value) {
        this.bolValue = bolValue;
        this.value = value;
    }

    @Override
    public String toString(){
        return value.daima+value.mingzi;
    }
}