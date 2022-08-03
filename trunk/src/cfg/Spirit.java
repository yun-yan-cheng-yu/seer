package cfg;

import log.Log;

public class Spirit {
    private final String fileName;

    private final int id;
    private final String name;
    private final String description;
    private final double minAttackIndex;
    private final double maxAttackIndex;

    Spirit(String fileName, int id, String name, String description, double minAttackIndex, double maxAttackIndex){
        this.fileName = fileName;
        this.id = id;
        this.name = name;
        this.description = description;
        this.minAttackIndex = minAttackIndex;
        this.maxAttackIndex = maxAttackIndex;
    }

    public boolean checkData(){
        if(this.id <=0){
            Log.error("id错误：文件名字{}",fileName);
            return false;
        }
        if(this.name == null){
            Log.error("名字错误：文件名字{}",fileName);
            return false;
        }
        if(this.description == null){
            Log.error("描述错误：文件名字{}",fileName);
            return false;
        }
        if(this.minAttackIndex <=0){
            Log.error("最小攻击指数错误：文件名字{}",fileName);
            return false;
        }
        if(this.maxAttackIndex <=0){
            Log.error("最大攻击指数错误：文件名字{}",fileName);
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "fileName:"+this.fileName+"\t"
                +"id:"+this.id+"\t"
                +"name:"+this.name+"\t"
                +"desc:"+this.description+"\t"
                +"最小攻击指数:"+this.minAttackIndex+"\t"
                +"最大攻击指数:"+this.maxAttackIndex+"\t";
    }
}
