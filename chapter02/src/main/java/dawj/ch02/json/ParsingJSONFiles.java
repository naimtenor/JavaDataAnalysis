/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class ParsingJSONFiles {
    public static void main(String[] args) {
        File dataFile = new File("data/Books.json");
        try {
            InputStream stream = new FileInputStream(dataFile);
            JsonParser parser = Json.createParser(stream);
            Event event = parser.next();  // START_OBJECT 이후부터 분석
            HashMap<String,Object> map = getMap(parser);
            System.out.println(map);
            stream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /*  지정한 파서가 파싱한 HashMap을 반환한다.
        event.equals(event.START_OBJECT)일 때 호출 됨.
    */
    public static HashMap getMap(JsonParser parser) {
        HashMap<String,Object> map = new HashMap();
        Event event = parser.next();  // START_OBJECT 이후로 진행
        String key = parser.getString();
        event = parser.next();       // KEY_NAME 이후로 진행
        while (!event.equals(Event.END_OBJECT)) {
            if (event.equals(Event.VALUE_STRING)) {
                String value = parser.getString();
                map.put(key, value);
            } else if (event.equals(Event.VALUE_NUMBER)) {
                Integer value = parser.getInt();
                map.put(key, value);
            } else if (event.equals(Event.START_ARRAY)) {
                ArrayList<String> list = getList(parser);
                map.put(key, list);
            }
            event = parser.next();
            if (event.equals(Event.END_OBJECT)) {
                break;
            }
            key = parser.getString();
            event = parser.next();
        }
        return map;
    }
    
    /*  지정한 파서가 파싱한 ArrayList를 반환한다.
        event.equals(event.START_OBJECT)일 때 호출 됨.
    */
    public static ArrayList getList(JsonParser parser) {
        ArrayList list = new ArrayList();
        Event event = parser.next();  // START_ARRAY 이후로 진행
        while (!event.equals(Event.END_ARRAY)) {
            if (event.equals(Event.VALUE_STRING)) {
                list.add(parser.getString());
                event = parser.next();
            } else if (event.equals(Event.START_OBJECT)) {
                HashMap<String,Object> map = getMap(parser);
                list.add(map);
                event = parser.next();
            } else if (event.equals(Event.START_ARRAY)) {
                ArrayList subList = getList(parser);
                list.add(subList);
                event = parser.next();
            }
        }
        return list;
    }
}
