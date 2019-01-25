package tools.maven;

import java.io.File;
import java.io.FilenameFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;

@Slf4j
public class DelLastUpdated {
  
    private static PropertyHelper propHelper = new PropertyHelper("config");  
    private static final String KEY_MAVEN_REPO = "maven.repo";  
    private static final String MAVEN_REPO_PATH = propHelper  
            .getValue(KEY_MAVEN_REPO);  
    private static final String FILE_SUFFIX = "lastUpdated";  

    /** 
     * @param args 
     */  
    public static void main(String[] args) {
        log.info(MAVEN_REPO_PATH);
        File mavenRep = new File(MAVEN_REPO_PATH);
        if (!mavenRep.exists()) {  
            log.warn("Maven repos is not exist.");  
            return;  
        }  
        File[] files = mavenRep.listFiles((FilenameFilter) FileFilterUtils
                .directoryFileFilter());  
        delFileRecr(files,null);  
        log.info("Clean lastUpdated files finished.");  
    }  
  
    private static void delFileRecr(File[] dirs, File[] files) {

        log.info("========{}========",dirs.length);
        if (dirs != null && dirs.length > 0) {  
            for(File dir: dirs){  
                File[] childDir = dir.listFiles((FilenameFilter) FileFilterUtils  
                .directoryFileFilter());  
                File[] childFiles = dir.listFiles((FilenameFilter) FileFilterUtils  
                .suffixFileFilter(FILE_SUFFIX));  
                delFileRecr(childDir,childFiles);  
            }  
        }  
        if(files!=null&&files.length>0){  
            for(File file: files){  
                if(file.delete()){  
                    log.info("File: ["+file.getName()+"] has been deleted.");  
                }  
            }  
        }  
    }  
  
}  