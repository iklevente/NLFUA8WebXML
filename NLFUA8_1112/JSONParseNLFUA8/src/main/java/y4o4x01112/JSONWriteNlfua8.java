package nlfua8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class JSONWriteNlfua8 {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/main/resources/orarendNlfua8.json")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONObject scheduleRoot = (JSONObject) jsonObject.get("SZK_orarend");
            JSONArray lessons = (JSONArray) scheduleRoot.get("ora");

            System.out.println("Mérnökinformatikus órarend 2024/25 I. félév:\n");

            JSONObject output = new JSONObject();
            JSONArray outputArr = new JSONArray();

            for (Object lessonObj : lessons) {
                JSONObject lesson = (JSONObject) lessonObj;
                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.printf("\nTárgy: %s\n", lesson.get("targy"));
                System.out.printf("Időpont: %s %s-%s\n", time.get("nap"), time.get("tol"), time.get("ig"));
                System.out.printf("Helyszín: %s\n", lesson.get("helyszin"));
                System.out.printf("Oktató: %s\n", lesson.get("oktato"));
                System.out.printf("Szak: %s\n", lesson.get("szak"));

                JSONObject lessonDetails = new JSONObject();
                lessonDetails.put("targy", lesson.get("targy"));
                lessonDetails.put("idopont", time);
                lessonDetails.put("helyszin", lesson.get("helyszin"));
                lessonDetails.put("oktato", lesson.get("oktato"));
                lessonDetails.put("szak", lesson.get("szak"));

                outputArr.add(lessonDetails);
            }

            output.put("SZK_orarend", outputArr);

            try (FileWriter writer = new FileWriter("src/main/resources/orarendNlfua8.json")) {
                writer.write(JSONValue.toJSONString(output));
                writer.write("\n");

                // Alternative for pretty printing
                String prettyPrintedJson = JSONValue.toJSONString(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
