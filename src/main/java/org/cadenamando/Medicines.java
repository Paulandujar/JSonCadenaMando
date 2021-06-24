package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class Medicines extends CadenaMando {
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String MEDICINES_TAGNAME = "medicines";

    public Medicines(CadenaMando cm) {
        super(cm);
    }

    public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
        if(name.equals(MEDICINES_TAGNAME)) {
            return super.leerComun(reader, name);
        }
        else {
            if(next != null) {
                return super.readCategory(reader, name);
            }
            else {
                reader.skipValue();
                System.err.println("La categoria: '" + name + "' es erronea.");
                return new StringBuffer("");
            }
        }
    }

    @Override
    public String readEntry(JsonReader reader) throws IOException {
        String medName = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                medName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return medName;
    }
}
