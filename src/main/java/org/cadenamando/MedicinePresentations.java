package org.cadenamando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class MedicinePresentations extends CadenaMando{

    private static final String MEDICINEPRESENTATIONS_TAGNAME = "medicinePresentations";
    private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
    private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
    private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
    private static final String DOSE_FIELD_TAGNAME = "dose";
    private static final String POSREF_FIELD_TAGNAME = "posologyRef";
    private static final String FIELD_SEPARATOR = ";";

    public MedicinePresentations(CadenaMando cm) {
        super(cm);
    }

    public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
        if (name.equals(MEDICINEPRESENTATIONS_TAGNAME)) {
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
        String medRef = null;
        String aiRef = null;
        String inhRef = null;
        String dose = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(MEDREF_FIELD_TAGNAME)) {
                medRef = reader.nextString();
            } else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
                aiRef = reader.nextString();
            } else if (name.equals(INHREF_FIELD_TAGNAME)) {
                StringBuffer res = new StringBuffer();
                reader.beginArray();
                while (reader.hasNext()) {
                    res.append(reader.nextString()).append(",");
                }
                reader.endArray();
                res.deleteCharAt(res.length() - 1);
                inhRef = new String(res);
            } else if (name.equals(DOSE_FIELD_TAGNAME)) {
                StringBuffer res = new StringBuffer();
                reader.beginArray();
                while (reader.hasNext()) {
                    res.append(reader.nextString()).append(",");
                }
                reader.endArray();
                res.deleteCharAt(res.length() - 1);
                dose = new String(res);
            } else {
                reader.skipValue();
            }

        }
        return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
    }
}
