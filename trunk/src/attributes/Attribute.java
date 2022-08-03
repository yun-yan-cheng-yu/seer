package attributes;

public enum Attribute {
    NULL("无") ,
    GRASS("草") ,
    WATER("水") ,
    FIRE("火"),
    THUNDER("电"),
    DRAGON("龙"),
    LIGHT("光"),
    ICE("冰"),
    LAND("地面"),
    FLY("飞行"),
    DARK("暗影"),
    SUPER("超能"),
    FIGHT("战斗"),
    MYSTIC("神秘"),
    HALLOW("圣灵"),
    MECHANICAL("机械"),
    NORMAL("普通"),
    EVIL("邪灵"),
    NATURE("自然"),
    KING("王"),
    CHAOS("混沌"),
    DIMENSION("次元"),
    ROTATE("轮回"),
    INSECT("虫"),
    VOID("虚空"),
    ANCIENT("远古"),
    GOD("神灵"),

    ;


    private String comment;

    Attribute(String comment){
        this.comment = comment;
    }
}
