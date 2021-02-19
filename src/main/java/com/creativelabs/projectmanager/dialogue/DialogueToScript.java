package com.creativelabs.projectmanager.dialogue;

import java.io.FileWriter;
import java.io.IOException;

public class DialogueToScript {

    public void convertDialogueToScript (String dialogueName, String text, String dialoguesPath) {

        String findStr = ": ";
        String npcName = "";
        String previousNpcName = "Alrik";
        String diagoueName = "";
        String previousDialogueName = "Hello";
        String questName = "";
        String entryText = "";
        String choiceText = "";
        String textInput = text + findStr;
        int lastIndex = 0;
        int previousIndex = 0;
        int count = 0;
        int choiceCount = 1;
        int choiceSection = 1;
        int dialogueCount = 1;

        String dialoguePath = dialoguesPath +  "/" + dialogueName;

        try {

            FileWriter writeDialogue = new FileWriter(dialoguePath);

            while(lastIndex != -1){
                lastIndex = textInput.indexOf(findStr,lastIndex);

                if(lastIndex != -1){
                    count ++;
                    lastIndex += findStr.length();
                    if (count > 1) {

                        if (textInput.charAt(previousIndex-3) == 'A') {
                            npcName = textInput.substring(previousIndex, lastIndex - 4);
                            choiceSection = 1; choiceCount = 1;
                        }

                        if (textInput.charAt(previousIndex-3) == 'D') {

                            diagoueName = textInput.substring(previousIndex, lastIndex-2); //previousIndex + currentIndex);
                            dialogueCount = dialogueCount + 1;

                            if (count > 4) {
                                writeDialogue.write("};");
                                writeDialogue.write("\n");
                            }

                            count = 0;

                            writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                            writeDialogue.write("////////////////  " + npcName + " " + diagoueName  + "\n");
                            writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                            writeDialogue.write("INSTANCE DIA_" + npcName + "_" + diagoueName + " (C_INFO)" + "\n");
                            writeDialogue.write("{" + "\n");
                            writeDialogue.write("\tnpc\t\t\t = \t" + npcName+ ";" + "\n");
                            writeDialogue.write("\tnr\t\t\t = \t2;" + "\n");
                            writeDialogue.write("\tcondition\t = \tDIA_" + npcName + "_" + diagoueName + "_Condition;" + "\n");
                            writeDialogue.write("\tinformation\t = \tDIA_" + npcName + "_" + diagoueName + "_Info;" + "\n");
                            writeDialogue.write("\timportant\t = \tFALSE;" + "\n");
                            writeDialogue.write("\tpermanent\t = \tFALSE;" + "\n");
                            writeDialogue.write("\tdescription\t = \t\"(to do)\";" + "\n");
                            writeDialogue.write("};" + "\n");
                            writeDialogue.write("func int DIA_" + npcName + "_" + diagoueName + "_Condition ()" + "\n");
                            writeDialogue.write("{" + "\n");
                            writeDialogue.write("if (Npc_KnowsInfo (other, Dia_" + previousNpcName + "_"+ previousDialogueName + "))" + "\n");
                            writeDialogue.write("\t{ return TRUE; };\t" + "\n");
                            writeDialogue.write("};" + "\n");
                            writeDialogue.write("func void DIA_" + npcName + "_" + diagoueName + "_Info ()" + "\n");
                            writeDialogue.write("{" + "\n");
                            previousNpcName = npcName;
                            previousDialogueName = diagoueName;
                        }

                        if (textInput.charAt(previousIndex-3) == 'H') {
                            if (count < 12) {
                                writeDialogue.write("\tAI_Output (other, self, \"DIA_" + npcName + "_" + diagoueName + "_15_0" + (count - 2) + "\"); //" + textInput.substring(previousIndex, lastIndex - 3));
                            }
                            else {
                                writeDialogue.write("\tAI_Output (other, self, \"DIA_" + npcName + "_" + diagoueName + "_15_" + (count - 2) + "\"); //" + textInput.substring(previousIndex, lastIndex - 3));
                            }
                        }

                        if (textInput.charAt(previousIndex-3) == 'N') {
                            if (count < 12) {
                                writeDialogue.write("\tAI_Output (self, other, \"DIA_" + npcName + "_" + diagoueName + "_12_0" + (count - 2) + "\"); //" + textInput.substring(previousIndex, lastIndex - 3));
                            }
                            else {
                                writeDialogue.write("\tAI_Output (self, other, \"DIA_" + npcName + "_" + diagoueName + "_12_" + (count - 2) + "\"); //" + textInput.substring(previousIndex, lastIndex - 3));
                            }

                        }

                        if (textInput.charAt(previousIndex-3) == 'Q') {
                            questName = textInput.substring(previousIndex, lastIndex-2);
                        }
                        if (textInput.charAt(previousIndex-3) == 'E') {
                            entryText = textInput.substring(previousIndex, lastIndex-2);
                            writeDialogue.write("\n");
                            writeDialogue.write("\tNOTE (\"" + questName + "\", \"" + entryText + ".\");");
                            writeDialogue.write("\n");
                        }

                        if (count > 3) {

                            if (textInput.charAt(previousIndex - 3) == 'C') {
                                choiceText = textInput.substring(previousIndex, lastIndex - 4);
                                writeDialogue.write("\n");
                                if (choiceCount == 1) {
                                    writeDialogue.write("\tInfo_ClearChoices(Dia_" + npcName + "_" + diagoueName + ");" + "\n");
                                }

                                writeDialogue.write("\tInfo_AddChoice(Dia_" + npcName + "_" + diagoueName + ",\"" + choiceText + "\",Dia_" + npcName + "_" + diagoueName + "_" + choiceCount + ");");
                                choiceCount = choiceCount+1;
                            }
                        }

                        if (textInput.charAt(previousIndex-3) == 'S') {

                            writeDialogue.write("\n");
                            if (choiceSection == 1) {
                                writeDialogue.write("};");
                                count = count - 4;
                            }
                            if (choiceSection == 2) {
                                writeDialogue.write("};");
                                count = count - 1;
                            }
                            writeDialogue.write("\n");
                            writeDialogue.write("FUNC VOID DIA_" + npcName + "_" + diagoueName + "_" + choiceSection + " ()" + "\n");
                            writeDialogue.write("{");
                            writeDialogue.write("\tInfo_ClearChoices(Dia_" + npcName + "_" + diagoueName + ");" + "\n");

                            choiceSection = choiceSection+1;
                        }
                    }
                }
                previousIndex = lastIndex;
            }

            writeDialogue.write("\n");
            writeDialogue.write("\n");
            writeDialogue.write("};");
            writeDialogue.write("\n");

            writeDialogue.close();
            System.out.println("Dialogue successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error with dialogue occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

    }
}