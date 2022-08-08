package attributes;

import log.Log;

public enum EAttribute {
    GRASS("草",0),
    WATER("水",1) ,
    FIRE("火",2),
    THUNDER("电",3),
    DRAGON("龙",4),
    LIGHT("光",5),
    ICE("冰",6),
    LAND("地面",7),
    FLY("飞行",8),
    DARK("暗影",9),
    SUPER("超能",10),
    FIGHT("战斗",11),
    MYSTIC("神秘",12),
    HALLOW("圣灵",13),
    MECHANICAL("机械",14),
    NORMAL("普通",15),
    EVIL("邪灵",16),
    NATURE("自然",17),
    KING("王",18),
    CHAOS("混沌",19),
    DIMENSION("次元",20),
    ROTATE("轮回",21),
    INSECT("虫",22),
    VOID("虚空",23),
    ANCIENT("远古",24),
    GOD("神灵",25),
    ;

    private final String comment;
    private final int value;

    EAttribute(String comment,int value){
        this.value = value;
        this.comment = comment;
    }

    public static EAttribute getAttribute(String name){
        for (EAttribute eAttribute : EAttribute.values()) {
            if(eAttribute.comment.equals(name)){
                return eAttribute;
            }
        }
        Log.error("未找到属性。{}",name);
        return null;
    }

    @Override
    public String toString() {
        return comment;
    }
}
