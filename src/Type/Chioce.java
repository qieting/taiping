package Type;

import UI.MyComboBox.CheckValue;

public class Chioce {

    public String daima ;
    public String mingzi;

    public Chioce(String daima,String mingzi){
        this.daima=daima;
        this.mingzi=mingzi;

    }

    @Override
    public String toString(){
        return  daima+mingzi;
    }

    @Override
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(o instanceof Chioce){
            Chioce c =(Chioce)o;
            return  (c.daima+c.mingzi).equals(this.daima+this.mingzi);
        }

        return  false;
    }
}
