package com.creativelabs.projectmanager.dialogue;

import java.io.*;

public class DialogueToScriptByLine {

    public String[] convertDialogueStart(String line) {
        String[] dialogueparts = null;
        String dialogueName;
        int startDialogueIndex = 0;
        String findStrInstance = "Dialogue: ";

        while (startDialogueIndex != -1) {
            startDialogueIndex = line.indexOf(findStrInstance, startDialogueIndex);

            if (startDialogueIndex != -1) {
                startDialogueIndex += findStrInstance.length();
                dialogueName = line.substring(startDialogueIndex);
                dialogueparts = dialogueName.split("-");
            }
        }
        return dialogueparts;
    }


    public static void main(String[] args) {

        String path = "C:/input.d";
        File file = new File(path);
        DialogueToScriptByLine dialogue = new DialogueToScriptByLine();
        String dialoguePath = "C:/dialogue.d";
        int instanceCounter = 0;
        int dialogueCounter = 0;
        int choiceCounter = 0;
        int sectionCounter = 0;
        String npcName = "null";
        String dialogueName = "null";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            try {

                FileWriter writeDialogue = new FileWriter(dialoguePath);

                while (line != null) {
                    if (line.startsWith("Dialogue: ")) {
                        instanceCounter+=1;
                        dialogueCounter = 0;
                        choiceCounter = 0;
                        sectionCounter = 0;
                        String[] dialogueParts = dialogue.convertDialogueStart(line);
                        npcName = dialogueParts[0];
                        dialogueName = dialogueParts[1];

                        if (instanceCounter > 1) {
                            writeDialogue.write("};");
                            writeDialogue.write("\n\n");
                        }
                        writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                        writeDialogue.write("////////////////  " + npcName + " " + dialogueName  + "\n");
                        writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                        writeDialogue.write("INSTANCE DIA_" + npcName + "_" + dialogueName + " (C_INFO)" + "\n");
                        writeDialogue.write("{" + "\n");
                        writeDialogue.write("\tnpc\t\t\t = \t" + npcName+ ";" + "\n");
                        writeDialogue.write("\tnr\t\t\t = \t2;" + "\n");
                        writeDialogue.write("\tcondition\t = \tDIA_" + npcName + "_" + dialogueName + "_Condition;" + "\n");
                        writeDialogue.write("\tinformation\t = \tDIA_" + npcName + "_" + dialogueName + "_Info;" + "\n");
                        writeDialogue.write("\timportant\t = \tFALSE;" + "\n");
                        writeDialogue.write("\tpermanent\t = \tFALSE;" + "\n");
                        writeDialogue.write("\tdescription\t = \t\"(to do)\";" + "\n");
                        writeDialogue.write("};" + "\n");
                        writeDialogue.write("func int DIA_" + npcName + "_" + dialogueName + "_Condition ()" + "\n");
                        writeDialogue.write("{" + "\n");
                        writeDialogue.write("if (Npc_KnowsInfo (other, Dia_" + npcName + "_"+ dialogueName + "))" + "\n");
                        writeDialogue.write("\t{ return TRUE; };\t" + "\n");
                        writeDialogue.write("};" + "\n");
                        writeDialogue.write("func void DIA_" + npcName + "_" + dialogueName + "_Info ()" + "\n");
                        writeDialogue.write("{" + "\n");
                    }
                    if (line.startsWith("H: ")) {
                        dialogueCounter+=1;
                        String dialogueLine = line.substring(3);
                        if (dialogueCounter < 10) {
                            writeDialogue.write("\tAI_Output (other, self, \"DIA_" + npcName + "_" + dialogueName + "_15_0" + dialogueCounter + "\"); //" + dialogueLine + "\n");
                        } else{
                            writeDialogue.write("\tAI_Output (other, self, \"DIA_" + npcName + "_" + dialogueName + "_15_" + dialogueCounter + "\"); //" + dialogueLine + "\n");
                        }

                    }

                    if (line.startsWith("N: ")) {
                        dialogueCounter+=1;
                        String dialogueLine = line.substring(3);
                        if (dialogueCounter < 10) {
                            writeDialogue.write("\tAI_Output (self, other, \"DIA_" + npcName + "_" + dialogueName + "_12_0" + dialogueCounter + "\"); //" + dialogueLine + "\n");
                        } else {
                            writeDialogue.write("\tAI_Output (self, other, \"DIA_" + npcName + "_" + dialogueName + "_12_" + dialogueCounter + "\"); //" + dialogueLine + "\n");
                        }
                    }

                    if (line.startsWith("C")) {
                        choiceCounter+=1;
                        System.out.println("choice: " + choiceCounter);
                        String choiceLine = line.substring(4);
                        if (choiceCounter <2) {
                            writeDialogue.write("\n\tInfo_ClearChoices(Dia_" + npcName + "_" + dialogueName + ");" + "\n");
                        }
                        writeDialogue.write("\tInfo_AddChoice(Dia_" + npcName + "_" + dialogueName + ",\"" + choiceLine + "\",Dia_" + npcName + "_" + dialogueName + "_" + choiceCounter + ");"+ "\n");
                    }

                    if (line.startsWith("S")) {
                        sectionCounter+=1;
                        writeDialogue.write("\n");
                        writeDialogue.write("};");
                        writeDialogue.write("\n");
                        writeDialogue.write("FUNC VOID DIA_" + npcName + "_" + dialogueName + "_" + sectionCounter + " ()" + "\n");
                        writeDialogue.write("{");
                        writeDialogue.write("\tInfo_ClearChoices(Dia_" + npcName + "_" + dialogueName + ");" + "\n");
                    }



                    line = reader.readLine();
                }
                writeDialogue.write("};");
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
