package nl.surfnet.oda.schedule;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import nl.surfnet.oda.EntityDeserializer;
import nl.surfnet.oda.ISO8601;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Converts a JSON to a Lesson object
 *
 * @author Daniel Zolnai
 *
 */
public class LessonDeserializer extends EntityDeserializer<Lesson> {

    @Override
    public Lesson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || json.isJsonNull()) {
            return null;
        }
        JsonObject jsonLesson = json.getAsJsonObject();
        Lesson lesson = new Lesson();
        lesson.setId(getAsStringNoNull(jsonLesson.get("id")));
        lesson.setCourseUrl(getAsStringNoNull(jsonLesson.get("url")));
        lesson.setDescription(getAsStringNoNull(jsonLesson.get("description")));
        lesson.setRoomUrl(getAsStringNoNull(jsonLesson.get("room")));
        // start date
        String startDateString = getAsStringNoNull(jsonLesson.get("start"));
        if (startDateString == null) {
            lesson.setStartDate(null);
        } else {
            // convert the date
            try {
                Date startDate = ISO8601.toDate(startDateString);
                lesson.setStartDate(startDate);
            } catch (ParseException e) {
                lesson.setStartDate(null);
            }
        }
        // end date
        String endDateString = getAsStringNoNull(jsonLesson.get("end"));
        if (endDateString == null) {
            lesson.setEndDate(null);
        } else {
            // convert the date
            try {
                Date endDate = ISO8601.toDate(endDateString);
                lesson.setEndDate(endDate);
            } catch (ParseException e) {
                lesson.setEndDate(null);
            }
        }
        return lesson;
    }

}
