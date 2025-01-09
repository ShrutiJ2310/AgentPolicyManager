package operations;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import com.c2lbiz.symbiosys.systemcommon.util.FileUtil;

import DataObjects.agentDO;

public class DownloadFile {

	public void DownloadUserFile(String agentIdToDownload, LinkedHashMap<String, agentDO> lhmListOfAgents) throws Exception{
		agentDO agentToDownload = findAgentById(agentIdToDownload, lhmListOfAgents);
		
		if(agentToDownload!=null){
		String diretoryPath = "C:\\Users\\shruti.jagtap\\Desktop\\AgentDetails\\"; 
		String fileName = agentIdToDownload+".txt";
		String FileData = agentToDownload.toString();
		
		createFile(diretoryPath, fileName, FileData);
		
		System.out.println("File has beem downloaded successfully");
		}else{
			System.out.println("User does not exist");
		}
	}

	private static agentDO findAgentById(String agentIdToDownload,
			LinkedHashMap<String, agentDO> lhmListOfAgents) 
	{
		if(lhmListOfAgents.get(agentIdToDownload)!=null){
			return lhmListOfAgents.get(agentIdToDownload);
		}
		return null;
	}
	
	public static void createFile(final String dirPath, final String fileName, final String fileData) throws Exception {
        final File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        final File file = new File(dirPath + fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        final Path path = Paths.get(dirPath + fileName, new String[0]);
        Files.write(path, fileData.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
    }
}
