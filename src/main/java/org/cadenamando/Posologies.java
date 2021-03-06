package org.cadenamando;


import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class Posologies extends CadenaMando{

    private static final String POSOLOGIES_TAGNAME = "posologies";
    private static final String DESCRIP_FIELD_TAGNAME = "description";

    public Posologies(CadenaMando cm) {
        super(cm);
    }

    public StringBuffer readCategory(JsonReader reader, String name)
            throws IOException{
        if (name.equals(POSOLOGIES_TAGNAME)) {
            return super.leerComun(reader, name);
        }
        else {
            if (next != null) {
                return super.readCategory(reader, name);
            }
            else {
                reader.skipValue();
                System.err.println("La categoria: '" + name +"' es incorrecta.");
                return new StringBuffer("");
            }
        }
    }

    public String readEntry(JsonReader reader) throws IOException {
        String poso_name = null;
        while(reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(DESCRIP_FIELD_TAGNAME)) {
                poso_name = reader.nextString();
            }
            else {
                reader.skipValue();
            }
        }
        return poso_name;
    }

}
