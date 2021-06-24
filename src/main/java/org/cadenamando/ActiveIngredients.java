package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class ActiveIngredients extends CadenaMando {

    private static final String ACTINGREF_FIELD_TAGNAME = "activeIngredients";
    private static final String NAME_FIELD_TAGNAME = "name";

    public ActiveIngredients(CadenaMando cm) {
        super(cm);
    }

    public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
        if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
            return super.leerComun(reader, name);
        } else {
            if (next != null) {
                return super.readCategory(reader, name);
            } else {
                reader.skipValue();
                System.err.println("La categoria: '" + name + "' es incorrecta.");
                return new StringBuffer("");
            }
        }
    }

    public String readEntry(JsonReader reader)
            throws IOException {
        String acting_name = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                acting_name = reader.nextString();
            } else {
                reader.skipValue();
            }
        }

        return acting_name;
    }
}
