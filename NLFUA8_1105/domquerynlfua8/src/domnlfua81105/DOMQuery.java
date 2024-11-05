package domnlfua81105;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMQuery {
    public static void main(String[] args) {
        try {
            File hallgatoFile = new File("nlfua8hallgato.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document hallgatoDoc = dBuilder.parse(hallgatoFile);
            hallgatoDoc.getDocumentElement().normalize();

            NodeList students = hallgatoDoc.getElementsByTagName("student");
            System.out.println("Task 2a: Student Last Names:");
            for (int i = 0; i < students.getLength(); i++) {
                Element student = (Element) students.item(i);
                String lastName = student.getElementsByTagName("lastname").item(0).getTextContent();
                System.out.println(lastName);
            }

            File orarendFile = new File("orarendNeptunkod.xml");
            Document orarendDoc = dBuilder.parse(orarendFile);
            orarendDoc.getDocumentElement().normalize();

            NodeList courses = orarendDoc.getElementsByTagName("course");
            List<String> courseNames = new ArrayList<>();
            for (int i = 0; i < courses.getLength(); i++) {
                Element course = (Element) courses.item(i);
                courseNames.add(course.getElementsByTagName("name").item(0).getTextContent());
            }
            System.out.println("Task 2b (a): Course Names: " + courseNames);

            NodeList lessons = orarendDoc.getElementsByTagName("lesson");
            if (lessons.getLength() > 0) {
                Element firstLesson = (Element) lessons.item(0);
                System.out.println("Task 2b (b): First Lesson Details:");
            }

            NodeList instructors = orarendDoc.getElementsByTagName("instructor");
            List<String> instructorNames = new ArrayList<>();
            for (int i = 0; i < instructors.getLength(); i++) {
                Element instructor = (Element) instructors.item(i);
                instructorNames.add(instructor.getTextContent());
            }
            System.out.println("Task 2b (c): Instructor Names: " + instructorNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
