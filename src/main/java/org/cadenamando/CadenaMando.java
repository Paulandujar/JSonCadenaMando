package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public abstract class CadenaMando {

    protected CadenaMando next;

    public CadenaMando(CadenaMando n) {
        next = n;
    }

    public StringBuffer readCategory(JsonReader reader, String name)
            throws IOException{
        return next.readCategory(reader, name);
    }

    public StringBuffer leerComun(JsonReader reader, String name) throws IOException {
        StringBuffer data = new StringBuffer();
        reader.beginArray();
        while(reader.hasNext()) {
            reader.beginObject();
            data.append(readEntry(reader)).append("\n");
            reader.endObject();
        }

        data.append("\n");
        reader.endArray();
        return data;
    }


    public abstract String readEntry(JsonReader reader) throws IOException;
}
