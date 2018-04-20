package md.skin.colorschema;

import md.skin.colorschema.i.IMDColorSchema;
import md.skin.colorschema.impl.DefaultMDColorSchemaBuilder;

public class ColorSchemaFactory {
    public static IMDColorSchema get(ColorSchemas schema) {
        return new DefaultMDColorSchemaBuilder().build();
    }
}
