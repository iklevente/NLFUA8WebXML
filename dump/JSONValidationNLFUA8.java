public import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;

public class JSONValidationNeptunkod {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode schemaNode = mapper.readTree(new File("orarendJSONSchemaNeptunkod.json"));
            JsonNode dataNode = mapper.readTree(new File("orarendNeptunkod.json"));

            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            ProcessingReport report = schema.validate(dataNode);
            System.out.println(report.isSuccess() ? "Validáció sikeres!" : "Validáció sikertelen!");
            System.out.println(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 {
    
}
