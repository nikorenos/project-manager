package com.creativelabs.projectmanager.dialogue;


import java.io.*;

public class ScriptToDialogue {

    public String[] convertInstance(String line) {
        String[] dialogueparts = null;
        String dialogueName;
        int startDialogueNameIndex = 0;
        int endDialogueNameIndex = 0;
        String findStrInstance = "instance ";
        String findStrC_INFO = " (C_INFO)";

        while (startDialogueNameIndex != -1) {
            startDialogueNameIndex = line.indexOf(findStrInstance, startDialogueNameIndex);
            endDialogueNameIndex = line.indexOf(findStrC_INFO, endDialogueNameIndex);

            if (startDialogueNameIndex != -1) {
                startDialogueNameIndex += findStrInstance.length();
                endDialogueNameIndex += findStrC_INFO.length();
                dialogueName = line.substring(startDialogueNameIndex + 4, endDialogueNameIndex - findStrC_INFO.length());
                dialogueparts = dialogueName.split("_");
            }
        }
        return dialogueparts;
    }

    public String convertMissionEntry(String line, String findString) {
        int startMissionIndex = 0;
        int startEntryIndex = 0;
        String findStrEntry = "\", \"";
        String questName = "";
        String entry = "";
        String beginning = "[b]StartQuest:[/b] ";

        while (startMissionIndex != -1) {
            startMissionIndex = line.indexOf(findString, startMissionIndex);
            startEntryIndex = line.indexOf(findStrEntry, startEntryIndex);

            if (startMissionIndex != -1) {
                startMissionIndex += findString.length();
                startEntryIndex += findStrEntry.length();
                int endIndex = line.length();
                questName = line.substring(startMissionIndex + 3, startEntryIndex - 4);
                entry = line.substring(startEntryIndex, endIndex - 3);
            }
        }
        if (findString.equals("NOTE")) {
            beginning = "[b]Quest:[/b] ";
        }
        if (findString.equals("CLOSE_MISSION")) {
            beginning = "[b]Close Quest:[/b] ";
        }
        return beginning + questName + " [b]Entry:[/b] " + entry;
    }

    public String convertAIOutput(String line) {
        int startAI_OutputIndex = 0;
        int startDialogueIndex = 0;
        String findStrAI_Output = "AI_Output(";
        String findStrDialogueStart = "); //";
        String speaker = "";
        String text = "";

        while (startAI_OutputIndex != -1) {
            startAI_OutputIndex = line.indexOf(findStrAI_Output, startAI_OutputIndex);
            startDialogueIndex = line.indexOf(findStrDialogueStart, startDialogueIndex);

            if (startAI_OutputIndex != -1) {
                startAI_OutputIndex += findStrAI_Output.length();
                startDialogueIndex += findStrDialogueStart.length();
                speaker = line.substring(startAI_OutputIndex, startAI_OutputIndex + 5);
                System.out.println(speaker);
                if (speaker.equals("other")) {
                    speaker = "H: ";
                } else {
                    speaker = "N: ";
                }
                text = line.substring(startDialogueIndex);
            }
        }
        return speaker + text;
    }

    public static void main(String[] args) {

        String path = "C:/input.d";
        String startMission = "START_MISSION";
        String entry = "NOTE";
        String closeMission = "CLOSE_MISSION";
        File file = new File(path);
        ScriptToDialogue scriptToDialogue = new ScriptToDialogue();
        String dialoguePath = "C:/dialogue.d";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            try {

                FileWriter writeDialogue = new FileWriter(dialoguePath);

            while (line != null) {
                if (line.contains("instance")) {
                    String[] dialogueparts = scriptToDialogue.convertInstance(line);
                    writeDialogue.write("\n");
                    writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                    writeDialogue.write("////////////////  " + dialogueparts[0] + " " + dialogueparts[1] + "\n");
                    writeDialogue.write("///////////////////////////////////////////////////////////////////////" + "\n");
                    writeDialogue.write("[b]A: " + dialogueparts[0] + " D: " + dialogueparts[1] + ":[/b]");
                    writeDialogue.write("\n");
                    writeDialogue.write("\n");
                }
                if (line.contains("AI_Output")) {
                    String dialogueLine = scriptToDialogue.convertAIOutput(line);
                    writeDialogue.write(dialogueLine + "\n");
                }

                if (line.contains(startMission)) {
                    String startMissionLine = scriptToDialogue.convertMissionEntry(line, startMission);
                    writeDialogue.write("\n" + startMissionLine + "\n");
                }

                if (line.contains(entry)) {
                    String entryLine = scriptToDialogue.convertMissionEntry(line, entry);
                    writeDialogue.write("\n" + entryLine + "\n");
                }
                if (line.contains(closeMission)) {
                    String entryLine = scriptToDialogue.convertMissionEntry(line, closeMission);
                    writeDialogue.write("\n" + entryLine + "\n");
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

