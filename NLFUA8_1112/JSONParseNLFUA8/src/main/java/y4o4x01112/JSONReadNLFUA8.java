package nlfua8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONReadNlfua8 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/main/resources/orarendNlfua8.JSON")) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONObject scheduleRoot = (JSONObject) jsonObject.get("SZK_orarend");
            JSONArray lessons = (JSONArray) scheduleRoot.get("ora");

            System.out.println("Mérnökinformatikus órarend 2024/25 I. félév:\n");

            for (Object lessonObj : lessons) {
                JSONObject lesson = (JSONObject) lessonObj;
                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.printf("\nTárgy: %s\n", lesson.get("targy"));
                System.out.printf("Időpont: %s:%s - %s\n", time.get("nap"), time.get("tol"), time.get("ig"));
                System.out.printf("Helyszín: %s\n", lesson.get("helyszin"));
                System.out.printf("Oktató: %s\n", lesson.get("oktato"));
                System.out.printf("Szak: %s\n", lesson.get("szak"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
