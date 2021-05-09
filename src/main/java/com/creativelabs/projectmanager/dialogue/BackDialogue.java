package com.creativelabs.projectmanager.dialogue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BackDialogue {

    public String readNpcFile(String line) {
        String npcName = "";
        int npcNameIndex = 0;
        String findStrInstance = "= \"";
        while (npcNameIndex != -1) {
            npcNameIndex = line.indexOf(findStrInstance, npcNameIndex);
            if (npcNameIndex != -1) {
                npcNameIndex += findStrInstance.length();
                npcName = line.substring(npcNameIndex, line.length()-2);
                System.out.println(npcName);
            }
        }
        return npcName;
    }

    public static void main(String[] args) throws IOException {
        String folderPath = "D:/Program Files (x86)/JoWood/Gothic2ZlotaEdycja/_Work/Data/Scripts/Content/Story/NPC";
        BackDialogue backDialogue = new BackDialogue();
        String dialoguePath = "D:/Program Files (x86)/JoWood/Gothic2ZlotaEdycja/_Work/Data/Scripts/Content/Story/Dialoge/DIA_Back.d";

        List<File> filesInFolder = Files.list(Paths.get(folderPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        List<String> filteredFilesName = filesInFolder.stream()
                .map(s -> s.getName())
                .filter(name -> !name.equals("PC_Hero.d"))
                .collect(Collectors.toList());
        String path = "D:/Program Files (x86)/JoWood/Gothic2ZlotaEdycja/_Work/Data/Scripts/Content/Story/NPC/LUMBERJACK_1_Alrik_MainCamp.d";
        //String path = "C:/input.d";
        File file = new File(path);
        String npcName = "";
        String test = "// ************************************************************\n" +
                "INSTANCE DIA_Hagen_EXIT(C_INFO)\n" +
                "{\n" +
                "\tnpc\t\t\t= PAL_200_Hagen;\n" +
                "\tnr\t\t\t= 999;\n" +
                "\tcondition\t= DIA_Hagen_EXIT_Condition;\n" +
                "\tinformation\t= DIA_Hagen_EXIT_Info;\n" +
                "\tpermanent\t= TRUE;\n" +
                "\tdescription = DIALOG_ENDE;\n" +
                "};                       \n" +
                "FUNC INT DIA_Hagen_EXIT_Condition()\n" +
                "{\n" +
                "\treturn TRUE;\n" +
                "};\n" +
                "FUNC VOID DIA_Hagen_EXIT_Info()\n" +
                "{\t\n" +
                "\tAI_StopProcessInfos\t(self);\n" +
                "};";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            try {

                FileWriter writeDialogue = new FileWriter(dialoguePath);

                while (line != null) {

                    if (line.contains("name")) {
                        npcName = backDialogue.readNpcFile(line);
                        writeDialogue.write(npcName);
                    }

                    line = reader.readLine();
                }
                reader.close();
                writeDialogue.close();
                System.out.println("Dialogue successfully wrote to the file.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error with dialogue occurred.");
            e.printStackTrace();
        }
    }


}


