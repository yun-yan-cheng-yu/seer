package cfg;

import log.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CfgMgr {
    private static final String path = "src\\spirits";//对应的文件夹

    public static final Map<Integer,Spirit> spiritMap = new HashMap<>();

    public static void init(){
        spiritMap.clear();
        File file = new File(path);
        if(file.listFiles() == null){
            Log.error("路径未找到。检查下path路径");
        }
        readFile(file);
        Log.debug("init over. total:{}",spiritMap.size());
    }

    public static void showAllCfg(){
        spiritMap.forEach((id,spirit)->{
            Log.debug("{}",spirit.toString());
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
                p.load(pInStream);
                int id = Integer.parseInt((String) p.getOrDefault("id",0));
                String fileNme = file.getName();
                String name = (String) p.getOrDefault("name",0);
                String description = (String) p.getOrDefault("description",null);
                double minAttackIndex = Double.parseDouble((String) p.getOrDefault("minAttackIndex",0));
                double maxAttackIndex = Double.parseDouble((String) p.getOrDefault("maxAttackIndex",0));
                Spirit spirit = new Spirit(fileNme,id,name,description,minAttackIndex,maxAttackIndex);
                Spirit curSpirit = spiritMap.get(id);
                if(null == curSpirit){
                    spirit.checkData();
                    spiritMap.put(id,spirit);
                }else {
                    Log.error("重复的精灵ID:{} {} {}",id,curSpirit.toString(),spirit.toString());
                }
            } catch (IOException e) {
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
