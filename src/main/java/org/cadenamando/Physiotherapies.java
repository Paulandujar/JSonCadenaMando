package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class Physiotherapies extends CadenaMando {
    private static final String NAME_FIELD_TAGNAME = "name" ;
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String FIELD_SEPARATOR = ";";
    private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";


    public Physiotherapies(CadenaMando cm) {
        super(cm);
    }

    public StringBuffer readCategory(JsonReader reader, String name)
            throws IOException{
        if(name.equals(PHYSIOTHERAPIES_TAGNAME)) {
            return super.leerComun(reader, name);
        }
        else {
            if(next != null) {
                return super.readCategory(reader, name);
            }
            else {
                reader.skipValue();
                System.err.println("La categoria: '" + name + "' es incorrecta.");
                return new StringBuffer("");
            }
        }
    }

    @Override
    public String readEntry(JsonReader reader) throws IOException {
        String physioName = null;
        String physioImage = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                physioName = reader.nextString();
            } else if(name.equals(IMAGE_FIELD_TAGNAME)) {
                physioImage = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return physioName + FIELD_SEPARATOR + physioImage;
    }
}
