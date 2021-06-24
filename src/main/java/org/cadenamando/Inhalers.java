package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class Inhalers extends CadenaMando{
    private static final String INHALERS_TAGNAME = "inhalers";
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String FIELD_SEPARATOR = ";";

    public Inhalers(CadenaMando s) {
        super(s);

    }

    public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
        if(name.equals(INHALERS_TAGNAME)) {
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


    public String readEntry(JsonReader reader)
            throws IOException {
        String inha_name = null;
        String inha_ima = null;
        while(reader.hasNext()) {
            String name = reader.nextName();
            if(name.equals(NAME_FIELD_TAGNAME)) {
                inha_name = reader.nextString();
            }
            else if(name.equals(IMAGE_FIELD_TAGNAME)) {
                inha_ima = reader.nextString();
            }
            else {
                reader.skipValue();
            }
        }

        return inha_name + FIELD_SEPARATOR + inha_ima;
    }

}
