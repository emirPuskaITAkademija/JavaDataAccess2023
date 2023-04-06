package org.example.dao.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.example.dao.DaoException;
import org.example.dao.PersonDao;
import org.example.model.Person;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PersonJSONDao extends PersonDao {
    public PersonJSONDao() {
        super("person.json");
    }

    @Override
    public Person readFirst() throws DaoException {
        return null;
    }

    @Override
    public List<Person> readAll() throws DaoException {
        //List<Person> personList = new ArrayList<>();
        String filename = getMediaFileName();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();

        try (FileReader fileReader = new FileReader(filename)) {
            Type type = new TypeToken<ArrayList<Person>>() {}.getType();
            List<Person> personList = gson.fromJson(fileReader, type);
            return personList;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
//        try (FileReader fileReader = new FileReader(filename)) {
//            JSONParser jsonParser = new JSONParser();
//            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                Person person = new Person(
//                        (String)jsonObject.get("nationalIdentificationNumber"),
//                        (String)jsonObject.get("name"),
//                        (String)jsonObject.get("surname"),
//                        fromString((String) jsonObject.get("birthday")));
//                personList.add(person);
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        return new ArrayList<>();
    }

    private LocalDate fromString(String birthday) {
        StringTokenizer tokenizer = new StringTokenizer(birthday, "-");
        int year = Integer.parseInt(tokenizer.nextToken());
        int month = Integer.parseInt(tokenizer.nextToken());
        int day = Integer.parseInt(tokenizer.nextToken());
        return LocalDate.of(year, month, day);
    }

    @Override
    public void write(Person entity) throws DaoException {

    }


    //   [   ]
    //  {    }
    // [ {}, {}, {} ]

    /**
     * {
     * "name":"Mirsad",
     * "surname":"Škandro",
     * "nationalIdentificationNumber": "212121",ž
     * "birthday":"1990-01-01"
     * <p>
     * }
     *
     * @param personList
     * @throws DaoException
     */
    @Override
    public void writeAll(List<Person> personList) throws DaoException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String filename = getMediaFileName();
        try (FileWriter fileWriter = new FileWriter(filename)) {
            String jsonString = gson.toJson(personList);
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
//        JSONArray jsonArray = new JSONArray();
//        for (Person person : personList) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("name", person.getName());
//            jsonObject.put("surname", person.getSurname());
//            jsonObject.put("nationalIdentificationNumber", person.getNationalIdentificationNumber());
//            jsonObject.put("birthday", person.getBirthday() + "");
//            jsonArray.add(jsonObject);
//        }
//        String jsonString = jsonArray.toJSONString();
//        String filename = getMediaFileName();
//        try (FileWriter fileWriter = new FileWriter(filename)) {
//            fileWriter.write(jsonString);
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
    }

    class LocalDateSerializer implements JsonSerializer<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    class LocalDateDeserializer implements JsonDeserializer<LocalDate>{

        @Override
        public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String stringBirthday = jsonElement.getAsString();
            System.out.println(stringBirthday+"     <-----");
            StringTokenizer tokenizer = new StringTokenizer(stringBirthday, "-");
            int year = Integer.parseInt(tokenizer.nextToken());
            int month = Integer.parseInt(tokenizer.nextToken());
            int day = Integer.parseInt(tokenizer.nextToken());
            return LocalDate.of(year, month, day);
        }
    }
}
