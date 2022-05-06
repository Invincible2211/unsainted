package de.prog2.dungeontop.utils;

import de.prog2.dungeontop.model.entities.Minion;

import java.io.*;
import javax.json.bind.*;

public class MinionHandler
{

    private String inPath = "";
    private String outPath = "";
    private BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(MinionHandler.class.getResourceAsStream(
                    inPath)));
    private Jsonb jsonb = JsonbBuilder.create();

    public MinionHandler (String path, String outPath)
    {
        this.inPath = path;
        this.outPath = outPath;
    }

    public Minion createMinionFromJson ()
    {
        return jsonb.fromJson(this.bufferedReader, Minion.class);
    }

    private String minionToJson (Minion minion)
    {
        return this.jsonb.toJson(minion, Minion.class);
    }

    public void saveMinion (Minion minion, String filename) throws IOException
    {
        String minionjson = minionToJson(minion);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(minionjson);
        writer.flush();
        writer.close();
    }

}