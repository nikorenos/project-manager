package com.creativelabs.projectmanager.dialogue;

import com.creativelabs.projectmanager.tasks.User;
import com.creativelabs.projectmanager.tasks.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScriptToDialogue {

    public String fileIntoString (File obj) {
        System.out.println("scripteToDialogue");
        String data = "";
        Scanner myReader = null;
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error scripteToDialogue.");
            e.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            data = data +  myReader.nextLine() + "\n";
        }
        myReader.close();

        return data;
    }

    public String scriptToText(File obj) {
        System.out.println("scripteToDialogue");
        String data = "";
        Scanner myReader = null;
        try {
            myReader = new Scanner(obj);
        } catch (FileNotFoundException e) {
            System.out.println("An error scripteToDialogue.");
            e.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            if (myReader.nextLine().contains("instance")) {
                data = data +  myReader.nextLine() + "\n";
            }

            if (myReader.nextLine().contains("AI_Output(")) {
                data = data +  myReader.nextLine() + "\n";
            }
        }
        myReader.close();

        return data;
    }

    public void stringIntoDialogue(String script) {
        int startDialogueNameIndex = 0;
        int endDialogueNameIndex = 0;
        int startAI_OutputIndex = 0;
        int startAI_OutputIndexPrevious = 0;
        int startDialogueIndex = 0;
        int startDialogueIndexPrevious = 0;
        int emptyLineIndex = 0;
        int count = 0;
        String findStrInstance = "instance ";
        String findStrC_INFO = " (C_INFO)";
        String findStrAI_Output = "AI_Output(";
        String findStrDialogueStart = "); //";
        String findStrEmptyLine = "    \t\n";
        String textInput = script + findStrAI_Output;
        String dialogueName = "";
        String speaker = "";
        String text = "";

        String dialoguePath = "C:/dialogue.d";

        try {

            FileWriter writeDialogue = new FileWriter(dialoguePath);

        while (startDialogueNameIndex != -1) {
            startDialogueNameIndex = textInput.indexOf(findStrInstance, startDialogueNameIndex);
            //System.out.println("Znalazłem instance w indeksie: " + startDialogueNameIndex);

            endDialogueNameIndex = textInput.indexOf(findStrC_INFO, endDialogueNameIndex);
            //System.out.println("Znalazłem (C_INFO) w indeksie: " + endDialogueNameIndex);

            if (startDialogueNameIndex != -1) {
                startDialogueNameIndex += findStrInstance.length();
                //System.out.println("Ustawiam startDialogueNameIndex na: " + startDialogueNameIndex);
                endDialogueNameIndex += findStrC_INFO.length();
                /*endDialogueNameIndex = textInput.indexOf(findStrC_INFO, endDialogueNameIndex);
                endDialogueNameIndex -= findStrC_INFO.length();*/
                dialogueName = textInput.substring(startDialogueNameIndex + 4, endDialogueNameIndex - findStrC_INFO.length());
                //dialogueName = textInput.substring(endDialogueNameIndex -5, endDialogueNameIndex + 15);

                String[] dialogueparts = dialogueName.split("_");
                writeDialogue.write("\n");
                writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                writeDialogue.write("////////////////  " + dialogueparts[0] + " " + dialogueparts[1] + "\n");
                writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                writeDialogue.write("A: " + dialogueparts[0] + " D: " + dialogueparts[1] + ":");
                writeDialogue.write("\n");
                writeDialogue.write("\n");
            }

            while (startAI_OutputIndex != -1) {
                startAI_OutputIndex = textInput.indexOf(findStrAI_Output, startAI_OutputIndex);
                startDialogueIndex = textInput.indexOf(findStrDialogueStart, startDialogueIndex);
                //emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);

                if (startAI_OutputIndex != -1) {
                    count++;
                    startAI_OutputIndex += findStrAI_Output.length();
                    startDialogueIndex += findStrDialogueStart.length();
                    //emptyLineIndex += findStrEmptyLine.length();

                    if (count == 1) {

                    } else {
                        speaker = textInput.substring(startAI_OutputIndexPrevious, startAI_OutputIndexPrevious + 5);
                        text = textInput.substring(startDialogueIndexPrevious, startAI_OutputIndex - 13);
                        if (text.contains("    \t\n")) {
                            findStrEmptyLine = "    \t\n";
                            emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);
                            text = textInput.substring(startDialogueIndexPrevious, emptyLineIndex - 2);
                        }
                        if (text.contains("\t\n")) {
                            findStrEmptyLine = "\t\n";
                            emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);
                            text = textInput.substring(startDialogueIndexPrevious, emptyLineIndex - 2);
                        }
                        //emptyLine = textInput.substring(emptyLineIndex, emptyLineIndex + 5);

                        if (speaker.equals("other")) {
                            writeDialogue.write("H: ");
                        } else {
                            writeDialogue.write("N: ");
                        }

                        //writeDialogue.write("!" + text + "!");
                        writeDialogue.write(text + "\n");
                    }
                }
                startAI_OutputIndexPrevious = startAI_OutputIndex;
                startDialogueIndexPrevious = startDialogueIndex;
            }
        }
        endDialogueNameIndex = startAI_OutputIndex;

            writeDialogue.close();
            System.out.println("Dialogue successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error with dialogue occurred.");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        String script = "\n" +
                "instance Dia_Alrik_Aspis (C_INFO)\n" +
                "{\n" +
                "  npc          =  Alrik;\n" +
                "  nr           =  100;\n" +
                "  condition    =  Dia_Alrik_Aspis_condition;\n" +
                "  information  =  Dia_Alrik_Aspis_info;\n" +
                "  important    =  TRUE;\n" +
                "  permanent    =  FALSE;\n" +
                "};\n" +
                "\n" +
                "func int Dia_Alrik_Aspis_condition ()\n" +
                "{\n" +
                "if (Npc_GetDistToWP (self, \"DUCHY\") < 400) // && (Npc_IsInState (self, ZS_Talk)) && (Camera_Hatiret == TRUE)\n" +
                "\n" +
                "  { return TRUE; };\n" +
                "};\n" +
                "\n" +
                "func void Dia_Alrik_Aspis_info ()\n" +
                "{\n" +
                "  AI_Output(self,other,\"Alrik_Aspis_12_00\"); //Niechaj wszystkie umęczone dusze tych, którzy w bólu, strachu i cierpieniu zakończyli tutaj swój żywot zaznają wiecznego spokoju w zaświatach.\n" +
                "  AI_Output(other,self,\"Alrik_Aspis_15_01\"); //Zbierajmy się stąd jak najszybciej, w przeciwnym wypadku dołączymy do tych nieszczęśników.\n" +
                "  AI_Output(self,other,\"Alrik_Aspis_12_02\"); //Zaczekaj chwilę. Widzisz, o tam... leży jakieś ciało. Przyjrzyjmy się mu.  \n" +
                "    \t\n" +
                "\tAlrik_Go = TRUE;\n" +
                "\tAI_StopProcessInfos (self);\n" +
                "\tNpc_ExchangeRoutine (self, \"DEADBODY\");\n" +
                "\n" +
                "};";

        String path = "C:/input.d";
        File scriptInput = new File(path);
        ScriptToDialogue scriptToDialogue = new ScriptToDialogue();
        String text = scriptToDialogue.fileIntoString(scriptInput);
        System.out.println("<" + text + ">");
        scriptToDialogue.stringIntoDialogue(text);


        /*int startDialogueNameIndex = 0;
        int endDialogueNameIndex = 0;
        int startAI_OutputIndex = 0;
        int startAI_OutputIndexPrevious = 0;
        int startDialogueIndex = 0;
        int startDialogueIndexPrevious = 0;
        int emptyLineIndex = 0;
        int count = 0;
        String findStrInstance = "instance ";
        String findStrC_INFO = " (C_INFO)";
        String findStrAI_Output = "AI_Output(";
        String findStrDialogueStart = "); //";
        String findStrEmptyLine = "    \t\n";
        String textInput = script + findStrAI_Output;
        String dialogueName = "";
        String speaker = "";
        String text = "";
        String emptyLine = "";

        while (startDialogueNameIndex != -1) {
            startDialogueNameIndex = textInput.indexOf(findStrInstance, startDialogueNameIndex);
            //System.out.println("Znalazłem instance w indeksie: " + startDialogueNameIndex);

            endDialogueNameIndex = textInput.indexOf(findStrC_INFO, endDialogueNameIndex);
            //System.out.println("Znalazłem (C_INFO) w indeksie: " + endDialogueNameIndex);

            if(startDialogueNameIndex != -1){
                count ++;
                startDialogueNameIndex += findStrInstance.length();
                //System.out.println("Ustawiam startDialogueNameIndex na: " + startDialogueNameIndex);
                endDialogueNameIndex += findStrC_INFO.length();
                *//*endDialogueNameIndex = textInput.indexOf(findStrC_INFO, endDialogueNameIndex);
                endDialogueNameIndex -= findStrC_INFO.length();*//*
                dialogueName = textInput.substring(startDialogueNameIndex + 4, endDialogueNameIndex - findStrC_INFO.length());
                //dialogueName = textInput.substring(endDialogueNameIndex -5, endDialogueNameIndex + 15);

                String[] dialogueparts = dialogueName.split("_");
                System.out.println();
                System.out.print("///////////////////////////////////////////////////////////////////////" + "\n");
                System.out.print("////////////////  " + dialogueparts[0] + " " + dialogueparts[1]  + "\n");
                System.out.println("///////////////////////////////////////////////////////////////////////" + "\n");
                System.out.println("A: " + dialogueparts[0] + " D: " + dialogueparts[1] + ":");
                System.out.println();
                }

                while (startAI_OutputIndex != -1) {
                    startAI_OutputIndex = textInput.indexOf(findStrAI_Output, startAI_OutputIndex);
                    startDialogueIndex = textInput.indexOf(findStrDialogueStart, startDialogueIndex);
                    //emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);

                    if (startAI_OutputIndex != -1) {
                        count++;
                        startAI_OutputIndex += findStrAI_Output.length();
                        startDialogueIndex += findStrDialogueStart.length();
                        //emptyLineIndex += findStrEmptyLine.length();

                        if (count == 1) {

                        } else {
                            speaker = textInput.substring(startAI_OutputIndexPrevious, startAI_OutputIndexPrevious + 5);
                            text = textInput.substring(startDialogueIndexPrevious, startAI_OutputIndex -13);
                            if (text.contains("    \t\n")) {
                                findStrEmptyLine = "    \t\n";
                                emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);
                                text = textInput.substring(startDialogueIndexPrevious, emptyLineIndex - 2);
                            }
                            if (text.contains("\t\n")) {
                                findStrEmptyLine = "\t\n";
                                emptyLineIndex = textInput.indexOf(findStrEmptyLine, emptyLineIndex);
                                text = textInput.substring(startDialogueIndexPrevious, emptyLineIndex - 2);
                            }
                            //emptyLine = textInput.substring(emptyLineIndex, emptyLineIndex + 5);

                            if (speaker.equals("other")) {
                                System.out.print("H: ");
                            } else {
                                System.out.print("N: ");
                            }

                            System.out.println("!" + text + "!");
                            //System.out.println("<" + emptyLine + ">");
                        }
                    }
                    startAI_OutputIndexPrevious = startAI_OutputIndex;
                    startDialogueIndexPrevious = startDialogueIndex;
                }
            }
            endDialogueNameIndex = startAI_OutputIndex;
        */

    }
}

