package com.creativelabs.projectmanager.dialogue;

import java.io.*;

public class DialogueToScriptByLine {

    public String[] convertDialogueStart(String line) {
        String[] dialogueparts = null;
        String dialogueName;
        int startDialogueIndex = 0;
        String findStrInstance = "A: ";

        while (startDialogueIndex != -1) {
            startDialogueIndex = line.indexOf(findStrInstance, startDialogueIndex);

            if (startDialogueIndex != -1) {
                startDialogueIndex += findStrInstance.length();
                dialogueName = line.substring(startDialogueIndex);
                dialogueparts = dialogueName.split("_");
            }
        }
        return dialogueparts;
    }


    public static void main(String[] args) {

        String path = "C:/input.d";
        String startMission = "START_MISSION";
        String entry = "NOTE";
        String closeMission = "CLOSE_MISSION";
        File file = new File(path);
        DialogueToScriptByLine dialogue = new DialogueToScriptByLine();
        String dialoguePath = "C:/dialogue.d";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            try {

                FileWriter writeDialogue = new FileWriter(dialoguePath);

                while (line != null) {
                    if (line.contains("A: ")) {
                        String[] dialogueParts = dialogue.convertDialogueStart(line);

                        writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                        writeDialogue.write("////////////////  " + dialogueParts[0] + " " + dialogueParts[1]  + "\n");
                        writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                        writeDialogue.write("INSTANCE DIA_" + dialogueParts[0] + "_" + dialogueParts[1] + " (C_INFO)" + "\n");
                        writeDialogue.write("{" + "\n");
                        writeDialogue.write("\tnpc\t\t\t = \t" + dialogueParts[0]+ ";" + "\n");
                        writeDialogue.write("\tnr\t\t\t = \t2;" + "\n");
                        writeDialogue.write("\tcondition\t = \tDIA_" + dialogueParts[0] + "_" + dialogueParts[1] + "_Condition;" + "\n");
                        writeDialogue.write("\tinformation\t = \tDIA_" + dialogueParts[0] + "_" + dialogueParts[1] + "_Info;" + "\n");
                        writeDialogue.write("\timportant\t = \tFALSE;" + "\n");
                        writeDialogue.write("\tpermanent\t = \tFALSE;" + "\n");
                        writeDialogue.write("\tdescription\t = \t\"(to do)\";" + "\n");
                        writeDialogue.write("};" + "\n");
                        writeDialogue.write("func int DIA_" + dialogueParts[0] + "_" + dialogueParts[1] + "_Condition ()" + "\n");
                        writeDialogue.write("{" + "\n");
                        writeDialogue.write("if (Npc_KnowsInfo (other, Dia_" + dialogueParts[0] + "_"+ dialogueParts[1] + "))" + "\n");
                        writeDialogue.write("\t{ return TRUE; };\t" + "\n");
                        writeDialogue.write("};" + "\n");
                        writeDialogue.write("func void DIA_" + dialogueParts[0] + "_" + dialogueParts[1] + "_Info ()" + "\n");
                        writeDialogue.write("{" + "\n");
                    }
                    /*if (line.contains("AI_Output")) {
                        String dialogueLine = scriptToDialogue.convertAIOutput(line);
                        writeDialogue.write(dialogueLine + "\n");
                    }*/


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
