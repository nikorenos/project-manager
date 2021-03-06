package com.creativelabs.projectmanager.dialogue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ExitDialogue {

    public String readNpcFile(String line) {
        String npcName = "";
        int npcNameIndex = 0;
        String findStrInstance = "instance ";
        while (npcNameIndex != -1) {
            npcNameIndex = line.indexOf(findStrInstance, npcNameIndex);
            if (npcNameIndex != -1) {
                npcNameIndex += findStrInstance.length();
                npcName = line.substring(npcNameIndex, line.length()-14);
            }
        }
        return npcName;
    }

    public List<String> filterNpcFiles(String folderPath) throws IOException {
        List<String> filteredFilesPaths = Files.list(Paths.get(folderPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .map(File::getName)
                .filter(name -> !name.equals("PC_Hero.d"))
                .map(name -> folderPath + "/" + name)
                .collect(Collectors.toList());

        return filteredFilesPaths;
    }

    public String prepareExitFile(List<String> filteredFilesPaths) {
        File file;
        String npcName;
        String exitDialogueText = "";

        BufferedReader reader;
        for (String fileName : filteredFilesPaths) {
            try {
                file = new File(fileName);
                reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    line = reader.readLine();
                    if ((line != null) && (line.startsWith("instance"))) {
                        npcName = readNpcFile(line);
                        exitDialogueText = exitDialogueText +
                                "// ************************************************************\n" +
                                "INSTANCE DIA_" + npcName + "_EXIT(C_INFO)\n" +
                                "{\n" +
                                "\tnpc\t\t\t= " + npcName + ";\n" +
                                "\tnr\t\t\t= 999;\n" +
                                "\tcondition\t= DIA_" + npcName + "_EXIT_Condition;\n" +
                                "\tinformation\t= DIA_" + npcName + "_EXIT_Info;\n" +
                                "\tpermanent\t= TRUE;\n" +
                                "\tdescription = DIALOG_ENDE;\n" +
                                "};                       \n" +
                                "FUNC INT DIA_" + npcName + "_EXIT_Condition()\n" +
                                "{\n" +
                                "\treturn TRUE;\n" +
                                "};\n" +
                                "FUNC VOID DIA_" + npcName + "_EXIT_Info()\n" +
                                "{\t\n" +
                                "\tAI_StopProcessInfos\t(self);\n" +
                                "};\n\n";
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return exitDialogueText;
    }
    private void saveExitDialogues(String exitDialogueText, String dialoguePath) {
        try {
            FileWriter writeDialogue = new FileWriter(dialoguePath);
            writeDialogue.write(exitDialogueText);
            writeDialogue.close();
            System.out.println("Dialogue successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ExitDialogue exitDialogue = new ExitDialogue();
        String npcFolderPath = "D:/Program Files (x86)/JoWood/Gothic2ZlotaEdycja/_Work/Data/Scripts/Content/Story/NPC";
        String dialoguePath = "D:/Program Files (x86)/JoWood/Gothic2ZlotaEdycja/_Work/Data/Scripts/Content/Story/Dialoge/DIA_Exit.d";

        List<String> filteredNpcFiles = exitDialogue.filterNpcFiles(npcFolderPath);
        String exitDialogueText = exitDialogue.prepareExitFile(filteredNpcFiles);
        exitDialogue.saveExitDialogues(exitDialogueText, dialoguePath);
    }
}


