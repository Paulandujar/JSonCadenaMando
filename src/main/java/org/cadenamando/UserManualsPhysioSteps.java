package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class UserManualsPhysioSteps extends CadenaMando{

    private static final String USERMANUALPHISIOSTEPS_TAGNAME = "userManualPhysioSteps";
    private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
    private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
    private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
    private static final String PHYREF_FIELD_TAGNAME = "physioRef";
    private static final String FIELD_SEPARATOR = ";";

    public UserManualsPhysioSteps(CadenaMando cm) {
        super(cm);
    }

    public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
        if (name.equals(USERMANUALPHISIOSTEPS_TAGNAME)) {
            return super.leerComun(reader, name);
        }
        else {
            if (next != null) {
                return super.readCategory(reader, name);
            } else {
                reader.skipValue();
                System.err.println("La categoria: '" + name + "' es incorrecta.");
                return new StringBuffer("");
            }
        }
    }

    public String readEntry(JsonReader reader) throws IOException {
        String s_title = null;
        String s_ima = null;
        String s_text = null;
        String physio_ref = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
                s_title = reader.nextString();
            } else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
                s_ima = reader.nextString();
            } else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
                s_text = reader.nextString();
            } else if (name.equals(PHYREF_FIELD_TAGNAME)) {
                physio_ref = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return s_title + FIELD_SEPARATOR + s_ima + FIELD_SEPARATOR + s_text + FIELD_SEPARATOR + physio_ref;
    }

}
