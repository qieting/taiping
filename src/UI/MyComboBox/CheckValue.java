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

    public boolean equals(Object o){
        if(o==null)
            return false;
        if(o instanceof  CheckValue){
            CheckValue checkValue =(CheckValue)o;
            return  checkValue.value.equals(this.value);
        }
        return false;
    }
}