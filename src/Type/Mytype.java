package Type;

import com.sun.istack.internal.Nullable;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Mytype {


    public enum mytupe {string, intt, dan, shuang, Date}

    public boolean must;
    public String title;
    public String moren;
    public List<Chioce> chioces;
    public mytupe type;
    public validation v;
    public String shorthand;

    public interface validation {
        boolean changevalidation(Object text);
    }

    public boolean changevalidation(Object text) {
        String s = (String) text;
        if (text.equals("")) {
            if (must)
                return false;
            else return true;
        }
        if (type == mytupe.intt) {
            if (!Pattern.matches("\\d+", s)) {
                return false;
            }
        }
        return v.changevalidation(text);
    }


    public String getShorthand() {
        return shorthand;
    }

    public String getDefault() {

        if (!must) {
            return "";

        }
        if (moren != null) {
            return moren;
        }
        switch (this.type) {
            case Date:
                if (title.equals("生效日期")) {
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    return format.format(date);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    String lastDay = format.format(calendar.getTime());
                    return lastDay;
                }
            case intt:
                return "0";
            case string:
                return "0";
            case dan:
            case shuang:
                return this.chioces.get(0).daima + this.chioces.get(0).mingzi;
            default:
                return "";
        }
    }

    public static class Builder {
        String title;
        List<Chioce> chioces;
        Mytype.mytupe type;
        public String moren;
        public Mytype.validation v;
        public boolean must;
        public String shorthand;

        public Builder() {
            this.title = null;
            this.chioces = null;
            this.type = Mytype.mytupe.string;
            this.v = null;
            this.moren = null;
            this.shorthand = null;
            this.must = true;
        }

        public Builder setShorthand(String s) {
            this.shorthand = s;
            return this;
        }


        public Builder setMust(boolean b) {
            this.must = b;
            if ( chioces != null)
                chioces.add(0, new Chioce("", ""));
            return this;
        }

        public Builder setMOren(String s) {
            this.moren = s;
            return this;
        }

        public Builder setType(Mytype.mytupe type) {
            this.type = type;
            return this;
        }

        public Builder setChioces(List<Chioce> chioces) {
            this.chioces = chioces;
            return this;
        }

        public Builder setV(Mytype.validation v) {
            this.v = v;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Mytype build() {
            Mytype mytype = new Mytype();
            mytype.type = this.type;
            mytype.chioces = this.chioces;
            mytype.v = this.v;
            mytype.moren = this.moren;
            mytype.title = this.title;
            mytype.must = this.must;
            if (shorthand == null) {
                mytype.shorthand = title;
            } else {
                mytype.shorthand = this.shorthand;
            }
            return mytype;
        }

    }


}
