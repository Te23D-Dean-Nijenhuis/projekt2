/*
Dean Nijenhuis TE23D
Main, detta är filen som körs, som använder andra filer.
 */


package librarysystem;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LibraryItemDeserializer implements JsonDeserializer<LibraryItem> {

    @Override
    public LibraryItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        // detect type by fields (no JSON changes needed)
        if (obj.has("author")) {
            return context.deserialize(obj, Book.class);
        }

        if (obj.has("issue")) {
            return context.deserialize(obj, Magazine.class);
        }

        throw new JsonParseException("Unknown item type");
    }
}