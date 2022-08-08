package attributes;

import global.Global;
import log.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AttributeMgr {
    private static final String path = Global.PROJECT_PATH + "src/attributes/attribute";//对应的文件夹
    public static final Map<attributes.EAttribute, attributes.Attribute> type2attribute = new HashMap<>();

    public static void init(){
        type2attribute.clear();
        File file = new File(path);
        if(file.listFiles() == null){
            Log.error("路径未找到。尝试修改下Global中的路径");
        }
        readFile(file);
        Log.debug("attribute init over. total:{}",type2attribute.size());
        if(EAttribute.values().length != type2attribute.size()){
            Log.error("属性属性不足。需要：{}，实际：{}",EAttribute.values().length,type2attribute.size());
        }
        showAllAtt();
    }

    public static void showAllAtt(){
        type2attribute.forEach((type,att)->{
            Log.debug("属性信息：{}",att);
        });
    }

    private static void readFile(File file){
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if(files == null){
            return;
        }
        for(File f:files){
            if(f.isFile()){
                tryLoadFile(f);
            }else if(f.isDirectory()){
                readFile(f);
            }
        }
    }

    private static void tryLoadFile(File file){
        if(isCfgFile(file)){
            FileInputStream pInStream = null;
            Properties p = new Properties();
            try {
                pInStream = new FileInputStream(file);
                BufferedReader bf = new BufferedReader(new InputStreamReader(pInStream, StandardCharsets.UTF_8));//解决读取properties文件中产生中文乱码的问题
                p.load(bf);
                String fileNme = file.getName();
                String attribute = p.getProperty("attribute",null);
                EAttribute eAttribute = EAttribute.getAttribute(attribute);
                if(eAttribute == null){
                    Log.error("未定义属性。文件名:{}",fileNme);
                    return;
                }
                String strongString = p.getProperty("strong",";");
                Set<EAttribute> strongAtt = new HashSet<>();
                for (String s : strongString.split(";")) {
                    EAttribute att = EAttribute.getAttribute(s);
                    if(att == null){
                        Log.error("strong出现错误。文件名:{}",fileNme);
                        return;
                    }else{
                        strongAtt.add(att);
                    }
                }

                String weekString = p.getProperty("week",";");
                Set<EAttribute> weekAtt = new HashSet<>();
                for (String s : weekString.split(";")) {
                    EAttribute att = EAttribute.getAttribute(s);
                    if(att == null){
                        Log.error("week出现错误。文件名:{}",fileNme);
                        return;
                    }else{
                        weekAtt.add(att);
                    }
                }

                String invalidString = p.getProperty("invalid",";");
                Set<EAttribute> invalidAtt = new HashSet<>();
                for (String s : invalidString.split(";")) {
                    EAttribute att = EAttribute.getAttribute(s);
                    if(att == null){
                        Log.error("invalid出现错误。文件名:{}",fileNme);
                        return;
                    }else{
                        invalidAtt.add(att);
                    }
                }

                Attribute att = new Attribute(fileNme,eAttribute,strongAtt,weekAtt,invalidAtt);
                Attribute curAtt = type2attribute.get(eAttribute);
                if(null == curAtt){
                    if(att.checkData()){
                        type2attribute.put(eAttribute,att);
                    }
                }else {
                    Log.error("重复的属性:{} {} {}",eAttribute,att.toString(),curAtt.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pInStream != null) {
                        pInStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isCfgFile(File file){
        String name = file.getName();
        String ext = name.substring(name.lastIndexOf(".") + 1);
        if("properties".equalsIgnoreCase(ext)){
            return true;
        }
        return false;
    }
}
