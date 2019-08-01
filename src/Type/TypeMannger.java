package Type;


import UI.TemplateJFrame;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class TypeMannger {
    public static HashMap<String, Mytype> types;

    static boolean validDate(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(s);
            date = new Date();
            if (sdf.format(date).compareTo(s) > 0) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    static {
        types = new HashMap<>();
        List<Chioce> chioces;
        Mytype mytype = new Mytype.Builder().setType(Mytype.mytupe.Date).setMust(true).setTitle("生效日期").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;
                return validDate(s);
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.Date).setMust(true).setTitle("失效日期").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;
                return validDate(s);
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("OA号").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;
                return Pattern.matches("\\d+", s);
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", ""));
//        chioces.add(new Chioce("1", ""));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(true).setTitle("保费是否含税").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("备注").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("销售及服务成本").setShorthand("XSJFWCB").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("可用变动费用率").setShorthand("KYBDFL").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();


        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("可用变动费用额").setShorthand("KYBDFYE").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0801", "交强险"));
        chioces.add(new Chioce("0802", "机动车商业险"));
        chioces.add(new Chioce("0803", "摩托车、拖拉机商业险"));
        chioces.add(new Chioce("0804", "单程提车商业险"));
        chioces.add(new Chioce("0805", "两地机动车商业险"));
        chioces.add(new Chioce("0808", "机动车电销商业险"));
        chioces.add(new Chioce("0833", "出镜车商业险"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(true).setTitle("险种").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("代理手续费率").setShorthand("DLSXFL").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                if (text == null || text.equals(""))
                    return true;
                try {
                    int q = Integer.parseInt((String) text);
                    if (q <= TemplateJFrame.limit) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("月度提奖率").setShorthand("YDTJL").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                if (text == null || text.equals(""))
                    return true;
                try {
                    int q = Integer.parseInt((String) text);
                    if (q <= TemplateJFrame.limit) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("可联动费用率").setShorthand("KLDFYL").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("组织利益(分)").setShorthand("ZZLY(F)").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("组织利益(总)").setShorthand("ZZLY(Z)").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("保单服务费").setShorthand("BDFWF").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(true).setTitle("固定绩效").setShorthand("GDJX").setMOren("0").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.intt).setMust(false).setTitle("代理手续费率2").setShorthand("DLSXFL2").setMOren("").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("代理手续费率2支付人").setShorthand("DLSXFL2ZFR").setMOren("").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("1", "险种配置"));
        mytype = new Mytype.Builder().setChioces(chioces).setType(Mytype.mytupe.dan).setMust(true).setTitle("政策差异化方式").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("KO1", "车损及其不计免赔"));
        chioces.add(new Chioce("KO2", "三者及其不计免赔"));
        chioces.add(new Chioce("KO3", "盗抢及其不计免赔"));
        chioces.add(new Chioce("KO4", "车上人员（含司机和乘客）及其不计免赔"));
        chioces.add(new Chioce("KO5", "划痕险"));
        chioces.add(new Chioce("KO6", "其他险别"));
        chioces.add(new Chioce("KO7", "机动车损失保险（IACJQL0001）及新增设备损失险（IACJQL0001）"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("费用险别").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("归属机构").setMOren("0367").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;

                return s.startsWith("0367") && s.length() <= 10;
            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("T", "团单"));
        chioces.add(new Chioce("C", "车商"));
        chioces.add(new Chioce("G", "个单"));
        chioces.add(new Chioce("D", "代理"));
        chioces.add(new Chioce("G2", "个单2"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(true).setTitle("决策单元大类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("T01", "招标业务"));
        chioces.add(new Chioce("T02", "机关事业单位及央企"));
        chioces.add(new Chioce("T03", "企业"));
        chioces.add(new Chioce("T04", "运输公司"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("决策单元小类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("决策单元代码").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;

                return s.length() == 10;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0203", "个代"));
        chioces.add(new Chioce("0601", "电销"));
        chioces.add(new Chioce("2201", "综合开拓"));
        chioces.add(new Chioce("0701", "网销"));
        chioces.add(new Chioce("0304", "银保"));
        chioces.add(new Chioce("0301", "战略客户（重客）"));
        chioces.add(new Chioce("0202", "车商"));
        chioces.add(new Chioce("0101", "团队渠道"));
        chioces.add(new Chioce("0201", "经代"));
        chioces.add(new Chioce("2206", "中石化渠道"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(true).setTitle("渠道类型").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("00000000", "其他"));
        chioces.add(new Chioce("01010100", "直销"));
        chioces.add(new Chioce("01010101", "团队车商"));
        chioces.add(new Chioce("01010102", "团队其他"));
        chioces.add(new Chioce("01010200", "团队代理"));
        chioces.add(new Chioce("01010201", "车商"));
        chioces.add(new Chioce("01010202", "临分分入"));
        chioces.add(new Chioce("01010203", "团队个代"));
        chioces.add(new Chioce("01010204", "团队经纪"));
        chioces.add(new Chioce("01010205", "团队银保"));
        chioces.add(new Chioce("02010100", "专业经纪"));
        chioces.add(new Chioce("02010101", "专业代理"));
        chioces.add(new Chioce("02010102", "兼业代理"));
        chioces.add(new Chioce("02010201", "经代直销"));
        chioces.add(new Chioce("02020101", "4S店"));
        chioces.add(new Chioce("02020102", "一般分销商（带维修）"));
        chioces.add(new Chioce("02020103", "一般分销商（不带维修）"));
        chioces.add(new Chioce("02020104", "汽车维修商"));
        chioces.add(new Chioce("02020105", "新车共保中心"));
        chioces.add(new Chioce("02020106", "专业性汽车服务机构"));
        chioces.add(new Chioce("02020107", "制造商/集团分销商"));
        chioces.add(new Chioce("02020201", "车商直销"));
        chioces.add(new Chioce("02030100", "个人代理"));


        chioces.add(new Chioce("03010100", "重客直销"));
        chioces.add(new Chioce("03010101", "重客专业代理"));
        chioces.add(new Chioce("03010202", "重客养老销产"));
        chioces.add(new Chioce("03010203", "重客兼业代理"));
        chioces.add(new Chioce("03010204", "重客统保（中铁快运）"));
        chioces.add(new Chioce("03010300", "重客经纪"));
        chioces.add(new Chioce("03010301", "重客统保（工行）"));
        chioces.add(new Chioce("03010302", "重客统保（农行）"));
        chioces.add(new Chioce("03010303", "重客统保（招行）"));
        chioces.add(new Chioce("03010304", "重客统保（民生行）"));
        chioces.add(new Chioce("03010400", "重客分入"));
        chioces.add(new Chioce("03010401", "重客出面（再保回分）"));
        chioces.add(new Chioce("03010500", "香港来源业务"));
        chioces.add(new Chioce("03040101", "工行(银保)"));
        chioces.add(new Chioce("03040102", "农行(银保)"));
        chioces.add(new Chioce("03040103", "中行(银保)"));
        chioces.add(new Chioce("03040104", "建行(银保)"));
        chioces.add(new Chioce("03040105", "交行(银保)"));
        chioces.add(new Chioce("03040106", "国开行(银保)"));
        chioces.add(new Chioce("03040107", "进行口银行(银保)"));
        chioces.add(new Chioce("03040108", "农发行(银保)"));
        chioces.add(new Chioce("03040109", "中信银行(银保)"));
        chioces.add(new Chioce("03040110", "光大银行(银保)"));
        chioces.add(new Chioce("03040111", "民生银行(银保)"));
        chioces.add(new Chioce("03040112", "广发银行(银保)"));
        chioces.add(new Chioce("03040113", "深发展银行(银保)"));
        chioces.add(new Chioce("03040114", "招商银行(银保)"));
        chioces.add(new Chioce("03040115", "兴业银行(银保)"));
        chioces.add(new Chioce("03040116", "浦发银行(银保)"));
        chioces.add(new Chioce("03040117", "城市商业银行(银保)"));
        chioces.add(new Chioce("03040118", "农村商业银行(银保)"));
        chioces.add(new Chioce("03040119", "城市信用社(银保)"));
        chioces.add(new Chioce("03040120", "农村信用社(银保)"));
        chioces.add(new Chioce("03040121", "邮政银行(银保)"));
        chioces.add(new Chioce("03040122", "华夏银行(银保)"));
        chioces.add(new Chioce("03040198", "其他银行(银保)"));
        chioces.add(new Chioce("03040200", "银保专业代理"));
        chioces.add(new Chioce("03040300", "银保专业经纪"));
        chioces.add(new Chioce("03040400", "银保直销"));
        chioces.add(new Chioce("03040500", "寿银销产渠道"));
        chioces.add(new Chioce("03040600", "养银销产"));
        chioces.add(new Chioce("06010100", "电销直接业务"));
        chioces.add(new Chioce("06010101", "OB呼出"));
        chioces.add(new Chioce("06010102", "IB呼入"));
        chioces.add(new Chioce("06010103", "XB"));
        chioces.add(new Chioce("06020100", "电销合作业务"));
        chioces.add(new Chioce("06030100", "互联网业务"));
        chioces.add(new Chioce("07010100", "网上商城平台"));
        chioces.add(new Chioce("07010101", "第三方网销平台"));
        chioces.add(new Chioce("07010102", "网销WT"));
        chioces.add(new Chioce("07010201", "E保"));
        chioces.add(new Chioce("22010101", "寿销产渠道一"));
        chioces.add(new Chioce("22010102", "寿销产渠道二"));
        chioces.add(new Chioce("22010103", "养销产"));
        chioces.add(new Chioce("22010201", "寿销产渠道三"));
        chioces.add(new Chioce("22010202", "直属业务渠道"));
        chioces.add(new Chioce("22010203", "车商"));
        chioces.add(new Chioce("22010302", "养老转介绍"));
        chioces.add(new Chioce("22010303", "养老项目"));
        chioces.add(new Chioce("22010401", "太平香港"));
        chioces.add(new Chioce("22010402", "太平香港转介绍"));
        chioces.add(new Chioce("22010501", "太平其他子公司"));
        chioces.add(new Chioce("22010502", "太平其他业务"));
        chioces.add(new Chioce("22010601", "中石化（寿险）"));
        chioces.add(new Chioce("22010602", "中石化（财险）"));
        chioces.add(new Chioce("22010701", "电子商务（寿险）"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setChioces(chioces).setMust(false).setTitle("渠道小类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return  ((String)text).startsWith(TemplateJFrame.jclx);
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "否"));
        chioces.add(new Chioce("1", "是"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("是否秒杀").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("1359", "机动车综合险"));
        chioces.add(new Chioce("0000", "PUB"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("产品组合").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "非双保"));
        chioces.add(new Chioce("1", "双保"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("双保标识").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("1", "直接业务"));
        chioces.add(new Chioce("2", "其他业务"));
        chioces.add(new Chioce("3", "寿/养销产业务"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("业务方式").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMOren("0367").setMust(false).setTitle("协议号").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return ((String) text).startsWith("0367") && ((String) text).length() == 18;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMOren("0367").setMust(false).setTitle("合作网点").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return ((String) text).startsWith("0367") && ((String) text).length() == 12;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMOren("03").setMust(false).setTitle("业务分类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return ((String) text).startsWith("03") && ((String) text).length() == 4;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("车损险车系分类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;
                return Pattern.matches("([123456789]|[12]\\d|31|30)|(([123456789]|[12]\\d|31|30)-([123456789]|[12]\\d|31|30))", s);

            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("决策表评分值").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;
                return Pattern.matches("([123456789]|[123456789]\\d|100|101)-([123456789]|[123456789]\\d|100|101)", s);
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("车主年龄").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                String s = (String) text;
                return Pattern.matches("(1[89]|[23456789]\\d|100)|((1[89]|[23456789]\\d|100)-(1[89]|[23456789]\\d|100))", s);

            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("1", "男"));
        chioces.add(new Chioce("2", "女"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("车主性别").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("00-00").setTitle("商业险精算折扣").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("\\d{1,2}-\\d{1,2}", (String) text);


            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("00-00").setTitle("商业险报价折扣").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("\\d{1,2}-\\d{1,2}", (String) text);


            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("00-00").setTitle("商业险签单折扣(自动)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("\\d{1,2}-\\d{1,2}", (String) text);


            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("00-00").setTitle("折扣率(%)区间").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("\\d{1,2}-\\d{1,2}", (String) text);


            }
        }).build();
        types.put(mytype.title, mytype);


        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("00-00").setTitle("无赔款优待系数区间").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("\\d{1,2}-\\d{1,2}", (String) text);


            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("O1", "过户车，不浮动"));
        chioces.add(new Chioce("O2", "脱保6个月以上，不浮动"));
        chioces.add(new Chioce("O3", "本次投保为短期单，不下浮"));
        chioces.add(new Chioce("O4", "没有上年度保单，不浮动"));
        chioces.add(new Chioce("O5", "上张保单为短期单，不下浮"));
        chioces.add(new Chioce("O6", "上张保单做过批改过户，不浮动"));
        chioces.add(new Chioce("O7", "上年和本年不在同一公司投保，不浮动"));
        chioces.add(new Chioce("O8", "脱保3个月至6个月（含），不下浮"));
        chioces.add(new Chioce("O9", "车贷投保多年，不下浮"));
        chioces.add(new Chioce("10", "对历史保单（上平台前的保单）的批改，不重新计算客户忠诚度和无赔优系数"));
        chioces.add(new Chioce("11", "不存在连续1年的保期，不浮动"));
        chioces.add(new Chioce("12", "上年没有出险，赔款金额系数不浮动"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("商业险不浮动原因(平台)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();

        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("1", "本地车"));
        chioces.add(new Chioce("2", "外地车"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("异地车标志").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("-0-0").setTitle("往年索赔记录").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("-\\d{1,2}-\\d{1,2}", (String) text);

            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("01", "个人"));
        chioces.add(new Chioce("02", "机关/团体"));
        chioces.add(new Chioce("03", "企业"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("所属性质").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("01", "家庭自用"));
        chioces.add(new Chioce("02", "机关自用"));
        chioces.add(new Chioce("03", "企业自用"));
        chioces.add(new Chioce("04", "出租客运"));
        chioces.add(new Chioce("05", "租赁客运"));
        chioces.add(new Chioce("06", "城市公交"));
        chioces.add(new Chioce("07", "公路客运"));
        chioces.add(new Chioce("08", "货物运输"));
        chioces.add(new Chioce("09", "特殊用途"));
        chioces.add(new Chioce("10", "预约出租客运"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("车辆用途").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("11", "6座以下客车"));
        chioces.add(new Chioce("12", "6-10座客车"));
        chioces.add(new Chioce("13", "10-20座客车"));
        chioces.add(new Chioce("14", "20-36座客车"));
        chioces.add(new Chioce("15", "36座以上客车"));
        chioces.add(new Chioce("21", "2吨以下货车"));
        chioces.add(new Chioce("22", "2-5吨货车"));
        chioces.add(new Chioce("23", "5-10吨货车"));
        chioces.add(new Chioce("24", "10吨以上货车"));
        chioces.add(new Chioce("25", "2吨以下挂车"));
        chioces.add(new Chioce("26", "2-5吨挂车"));
        chioces.add(new Chioce("27", "5-10吨挂车"));
        chioces.add(new Chioce("28", "10吨以上挂车"));
        chioces.add(new Chioce("44A", "三轮汽车"));
        chioces.add(new Chioce("29", "低速载货汽车"));
        chioces.add(new Chioce("30", "特种车一"));
        chioces.add(new Chioce("31", "特种车二"));
        chioces.add(new Chioce("40", "特种车三"));
        chioces.add(new Chioce("41", "特种车四"));
        chioces.add(new Chioce("42", "特种车一挂车"));
        chioces.add(new Chioce("29A", "14.7KW及以下低速载货汽车"));
        chioces.add(new Chioce("29B", "14.7KW-17.6KW(含)低速载货汽车"));
        chioces.add(new Chioce("29C", "17.6KW-50KW(含)低速载货汽车"));
        chioces.add(new Chioce("29D", "50KW-80KW(含)低速载货汽车"));
        chioces.add(new Chioce("29E", "80KW以上低速载货汽车"));
        chioces.add(new Chioce("43", "14.7KW及以下三轮汽车"));
        chioces.add(new Chioce("44", "14.7KW-17.6KW(含)三轮汽车"));
        chioces.add(new Chioce("45", "17.6KW-50KW(含)三轮汽车"));
        chioces.add(new Chioce("46", "50KW-80KW(含)三轮汽车"));
        chioces.add(new Chioce("47", "80KW以上三轮汽车"));
        chioces.add(new Chioce("50", "特种车二挂车"));
        chioces.add(new Chioce("51", "冷藏保温挂车"));
        chioces.add(new Chioce("60", "特种车三挂车"));
        chioces.add(new Chioce("71", "50CC及以下摩托车"));
        chioces.add(new Chioce("72", "50CC－250CC摩托车"));
        chioces.add(new Chioce("73", "250CC以上及侧三轮"));
        chioces.add(new Chioce("74", "电瓶助动车"));
        chioces.add(new Chioce("81", "兼用型14.7KW及以下拖拉机"));
        chioces.add(new Chioce("82", "兼用型14.7KW以上拖拉机"));
        chioces.add(new Chioce("91", "运输型14.7KW及以下拖拉机"));
        chioces.add(new Chioce("92", "运输型14.7KW以上拖拉机"));
        chioces.add(new Chioce("93", "14.7KW及以下标准变型拖拉机"));
        chioces.add(new Chioce("94", "14.7KW~17.6KW(含)标准变型拖拉机"));
        chioces.add(new Chioce("96", "14.7KW-17.6KW(含)超标变型拖拉机"));
        chioces.add(new Chioce("95", "14.7KW及以下超标变型拖拉机"));
        chioces.add(new Chioce("99", "80KW以上超标变型拖拉机"));
        chioces.add(new Chioce("98", "50KW-80KW(含)超标变型拖拉机"));
        chioces.add(new Chioce("97", "17.6KW-50KW(含)超标变型拖拉机"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("车辆种类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "旧车"));
        chioces.add(new Chioce("N", "新车"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("新旧车标志").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("02", "非营运"));
        chioces.add(new Chioce("01", "营运"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("使用性质").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();

        chioces.add(new Chioce("100", "客车"));
        chioces.add(new Chioce("320", "挂车"));
        chioces.add(new Chioce("321", "特种车一挂车"));
        chioces.add(new Chioce("322", "特种车二挂车"));
        chioces.add(new Chioce("323", "冷藏保温挂车"));
        chioces.add(new Chioce("324", "特种车三挂车"));
        chioces.add(new Chioce("200", "货车"));
        chioces.add(new Chioce("210", "低速载货汽车"));
        chioces.add(new Chioce("502", "运输型拖拉机"));
        chioces.add(new Chioce("501", "兼用型拖拉机"));
        chioces.add(new Chioce("220", "三轮汽车"));
        chioces.add(new Chioce("503", "标准变型拖拉机"));
        chioces.add(new Chioce("504", "超标变型拖拉机"));
        chioces.add(new Chioce("400", "摩托车"));
        chioces.add(new Chioce("600", "电瓶助动车"));
        chioces.add(new Chioce("301", "油罐车"));
        chioces.add(new Chioce("302", "气罐车"));
        chioces.add(new Chioce("303", "液罐车"));
        chioces.add(new Chioce("310", "冷藏车"));
        chioces.add(new Chioce("311", "清障车"));
        chioces.add(new Chioce("312", "清洁车"));
        chioces.add(new Chioce("304", "起重车"));
        chioces.add(new Chioce("306", "装卸车"));
        chioces.add(new Chioce("305", "升降车"));
        chioces.add(new Chioce("309", "搅拌车"));
        chioces.add(new Chioce("307", "挖掘车"));
        chioces.add(new Chioce("308", "推土车"));
        chioces.add(new Chioce("313", "特种车二类其他"));
        chioces.add(new Chioce("314", "消防车"));
        chioces.add(new Chioce("315", "医疗车"));
        chioces.add(new Chioce("316", "运钞车"));
        chioces.add(new Chioce("317", "特种车三类其他"));
        chioces.add(new Chioce("318", "特种车四"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("机动车种类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "国产"));
        chioces.add(new Chioce("1", "合资"));
        chioces.add(new Chioce("2", "进口"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("生产国别").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("车系代码").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return ((String) text).length() == 6;
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "非续保"));
        chioces.add(new Chioce("11", "综合续综合"));
        chioces.add(new Chioce("12", "综合续交强"));
        chioces.add(new Chioce("13", "综合续商业"));
        chioces.add(new Chioce("21", "单交续综合"));
        chioces.add(new Chioce("22", "单交续交强"));
        chioces.add(new Chioce("23", "单交续商业"));
        chioces.add(new Chioce("31", "单商续综合"));
        chioces.add(new Chioce("32", "单商续交强"));
        chioces.add(new Chioce("33", "单商续商业"));
        chioces.add(new Chioce("4", "脱保"));
        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("续保标志").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "非拆单"));
        chioces.add(new Chioce("1", "拆单"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("拆单标志").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("").setTitle("车龄区间").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("(\\d|30|[12]\\d)|((\\d|30|[12]\\d)-(\\d|30|[12]\\d))", (String) text);

            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "否"));
        chioces.add(new Chioce("1", "是"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("贷款车标志").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("0").setTitle("新车购置价").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("\\d+?-\\d+?", (String) text);

            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("0").setTitle("核定载质量(kg)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("(\\d|[123456789]//d{1,4}|100000)-(\\d|[123456789]//d{1,4}|100000)", (String) text);

            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("0-0").setTitle("签单保费").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("(\\d|[123456789]\\d+?)-(\\d|[123456789]\\d+?)", (String) text);

            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setMOren("2019-01-01#2019-01-01").setTitle("起保日期").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return Pattern.matches("(((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29))#((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)", (String) text);

            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("品牌名称").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "否"));
        chioces.add(new Chioce("1", "是"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("短期险标识").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "否"));
        chioces.add(new Chioce("1", "是"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("自卸车标识").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("01", "新车，不浮动"));
        chioces.add(new Chioce("02", "首次投保，不浮动"));
        chioces.add(new Chioce("03", "车辆所有权转移，不浮动"));
        chioces.add(new Chioce("04", "临时上道或临时入境，不浮动"));
        chioces.add(new Chioce("05", "为摩托车、拖拉机、变拖类、低速载货汽车、三轮汽车"));
        chioces.add(new Chioce("06", "未如实告知平台车辆信息，无法确定浮动比率，不浮动"));
        chioces.add(new Chioce("07", "非重复投保退保的保单续保，不浮动"));
        chioces.add(new Chioce("08", "短期保单续保不下浮"));
        chioces.add(new Chioce("09", "短期单投保不浮动"));
        chioces.add(new Chioce("10", "脱保，不下浮"));
        chioces.add(new Chioce("99", "不在浮动时间范围内"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("交强险不浮动原因(平台)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("车型新分类").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("平台车险分").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("蚂蚁车险分").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("商业险险别数量").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "保额5万"));
        chioces.add(new Chioce("2", "保额10万"));
        chioces.add(new Chioce("3", "保额15万"));
        chioces.add(new Chioce("4", "保额20万"));
        chioces.add(new Chioce("5", "保额30万"));
        chioces.add(new Chioce("6", "保额50万"));
        chioces.add(new Chioce("7", "保额100万"));
        chioces.add(new Chioce("8", "保额100万以上"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("三者险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保且同时投保不计免赔"));
        chioces.add(new Chioce("2", "投保且但未投保不计免赔"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("车上人员险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保且同时投保不计免赔"));
        chioces.add(new Chioce("2", "投保且但未投保不计免赔"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("车损险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.dan).setChioces(chioces).setMust(false).setTitle("机动车损失保险(IACJQL0001)状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保且同时投保不计免赔"));
        chioces.add(new Chioce("2", "投保且但未投保不计免赔"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("盗抢险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "国产"));
        chioces.add(new Chioce("2", "进口"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("玻璃破碎险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "保额2000元且同时投保不计免赔"));
        chioces.add(new Chioce("2", "保额5000元且同时投保不计免赔"));
        chioces.add(new Chioce("3", "保额10000元且同时投保不计免赔"));
        chioces.add(new Chioce("4", "保额20000元且同时投保不计免赔"));
        chioces.add(new Chioce("5", "保额2000元但不投保不计免赔"));
        chioces.add(new Chioce("6", "保额5000元但不投保不计免赔"));
        chioces.add(new Chioce("7", "保额10000元但不投保不计免赔"));
        chioces.add(new Chioce("8", "保额20000元但不投保不计免赔"));

        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("车身划痕险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("指定修理厂险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保且同时投保不计免赔"));
        chioces.add(new Chioce("2", "投保且但未投保不计免赔"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("自燃险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("新增设备险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("发动机损失险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("费用补偿险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保且同时投保不计免赔"));
        chioces.add(new Chioce("2", "投保且但未投保不计免赔"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("车上货物险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保且同时投保不计免赔"));
        chioces.add(new Chioce("2", "投保且但未投保不计免赔"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("精神损害险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        chioces = new ArrayList<>();
        chioces.add(new Chioce("0", "未投保"));
        chioces.add(new Chioce("1", "投保"));


        mytype = new Mytype.Builder().setType(Mytype.mytupe.shuang).setChioces(chioces).setMust(false).setTitle("第三方特约险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("三者节假日翻倍险状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("附加绝对免赔率特约(IACJQL0101)状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("附加车轮单独损坏除外特约(IACJQL0201)状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("新增设备损失险(IACJQL0001)状态").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("操作员").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("寿/养老业务员").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("业务员").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(false).setTitle("车上人员险每座保额(万元)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("预估引流成本率").setShorthand("YGYLCBL").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("预估增值服务费成本率").setShorthand("YGZZFWFCBL").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("预估业务维护成本率").setShorthand("YGYWWHCBL").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("预估间接理赔成本率").setShorthand("YGJJLPCBL").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("预估增值服务费成本率2").setShorthand("YGZZFWFCBL2").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("客户评分").setShorthand("KHPF").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("激励费率1").setShorthand("JLFL1").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("激励费额1").setShorthand("JLFE1").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("激励费率2").setShorthand("JLFL2").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("激励费额2").setShorthand("JLFE2").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);

        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("记录费用(率)").setShorthand("JLFY(L)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);
        mytype = new Mytype.Builder().setType(Mytype.mytupe.string).setMust(true).setTitle("记录费用(额)").setShorthand("JLFY(E)").setV(new Mytype.validation() {
            @Override
            public boolean changevalidation(Object text) {
                return true;
            }
        }).build();
        types.put(mytype.title, mytype);


    }


}
