 package com.emat.main;
 
 import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.emat.util.CommonUtil;
import com.emat.util.DbUtil;
 
 public class StartPostServer
 {
   public static void main(String[] args)
   {
     ConfigFile config = new ConfigFile();
     config.init();
 
     String uploadPath = CommonUtil.UPLOAD_FILEPATH;
     File file = new File(uploadPath);
     String log4j_path = CommonUtil.RUN_DIR + CommonUtil.FILE_SEPARATOR + "conf" + CommonUtil.FILE_SEPARATOR + "log4j.properties";
     PropertyConfigurator.configure(log4j_path);
 
     if (!file.exists())
     {
    	 if(!file.canWrite())
    		 file.setWritable(true);
       file.mkdirs();
     }
     File backupFile = new File(CommonUtil.BACKUP_FILEPATH);
     if (!backupFile.exists())
     {
    	 if(!backupFile.canWrite())
    		 backupFile.setWritable(true);
       backupFile.mkdir();
     }
     
     File[] docList = file.listFiles();
     for (File doc : docList)
     {
       if (!doc.getName().endsWith(".trs"))continue;
       System.out.println("开始导入文件：" + doc.getName());
       DbUtil.data2Db(doc);
     }
     
     
 
   }
 }

