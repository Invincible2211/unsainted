package de.prog2.dungeontop.utils;

import com.google.gson.GsonBuilder;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;

import java.io.*;
import java.util.*;


public class MinionHandler
{

    private String inPath = "";
    private String outPath = "";
    private BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(MinionHandler.class.getResourceAsStream(
                    inPath)));


    public MinionHandler (String path, String outPath)
    {
        this.inPath = path;
        this.outPath = outPath;
    }

    /**
     * turning a String from Json format into a Minion instance.
     *
     * @param minionJson String from minion
     * @return Minion instance
     */
    public Minion createMinionFromJson (String minionJson)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create().fromJson(minionJson, Minion.class);
    }

    /**
     * Saves Deck of minions
     */
    public void saveMinions (Minion[] miniondeck) {
        for (Minion minion :miniondeck)
        {
            saveMinion(minion);
        }
    }

    /**
     * Gets a minionInstance from a UUID
     * @param uuid
     * @return minion Instance
     */
    //TODO Exception oder logger einbinden
    public Minion getMinionFromID(UUID uuid)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
    List<Minion> minionList = Arrays.stream(gsonBuilder.create().fromJson(getAllMinions(), Minion[].class)).toList();
        for (Minion minion : minionList) {
            if (minion.getUuid() == uuid) {return minion;}
        }
        System.out.println(ExceptionMessagesKeys.MINION_UUID_NOT_FOUND_EXCEPTION);
        return null;
    }

    /**
     * hilfsmethode um txt mit allen minionJSON zu String zu lesen
     * @return JSON formatted String with all saved minions
     */
    private String getAllMinions ()
    {
        StringBuilder sb = new StringBuilder();
        File file = new File(inPath);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(ExceptionMessagesKeys.CAN_NOT_LOAD_FILE);
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        return sb.toString();
    }

    /**
     * turning a Minion into a String formatted as JSON using GSON lib
     *
     * @param minion the Instance of Minion to be turned to JSON string format
     * @return
     */
    private String minionToJson (Minion minion)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create().toJson(minion);
    }


    /**
     * Useses minionToJson to create a JSON format and write that String to outpath.
     * Will create file andOr path if either doesnt exist
     *
     * @param minion minion to be written in file
     * @throws IOException if current user does not have permission to write in that directory.
     */
    public void saveMinion (Minion minion)
    {

        String minionjson = minionToJson(minion);
        File file = new File(outPath);
        file.getParentFile().mkdirs(); // Will create parent directories if not exists

        try {
            file.createNewFile(); // Will create File if it does not exist
            FileOutputStream fileOutputStream = new FileOutputStream(file, true); //will append on end of file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file)); // Writer to write in said file
            writer.write(minionjson);
            writer.flush();
            writer.close();
            fileOutputStream.close();
        } catch(IOException e) {
            e.printStackTrace(); //TODO THIS BETTER
        }

    }

}