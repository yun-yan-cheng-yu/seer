package attributes;

import log.Log;

import java.util.HashSet;
import java.util.Set;

public class Attribute {
    private final String fileName ;
    private final EAttribute selfAttribute;//自己的属性
    private final Set<EAttribute> strong;//克制的属性
    private final Set<EAttribute> week;//微弱的属性
    private final Set<EAttribute>  invalid;//无效的属性

    Attribute(String fileName,EAttribute self,Set<EAttribute> strong,Set<EAttribute> week,Set<EAttribute> invalid){
        this.fileName = fileName;
        this.selfAttribute = self;
        this.strong = strong;
        this.week = week;
        this.invalid = invalid;
    }

    public boolean checkData() {
        Set<EAttribute> temp = new HashSet<>(strong);
        for (EAttribute eAttribute : week) {
            if(temp.contains(eAttribute)){
                Log.error("出现重复的属性{}",eAttribute);
                return false;
            }else{
                temp.add(eAttribute);
            }
        }
        for (EAttribute eAttribute : invalid) {
            if(temp.contains(eAttribute)){
                Log.error("出现重复的属性{}",eAttribute);
                return false;
            }else{
                temp.add(eAttribute);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "文件名:"+fileName+"\t"
                +"属性:"+this.selfAttribute+"\t"
                +"克制:"+this.strong+"\t"
                +"虚弱:"+this.week+"\t"
                +"无效:"+this.invalid+"\t"
                ;
    }
}
