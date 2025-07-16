package taskmanagersystem;

import java.io.*;
import java.util.List;

public class TaskRepository {
    public static <T> void save(String filePath, List<T> list) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> load(String filePath) {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) in.readObject();
        }catch (EOFException e) {
            return new java.util.ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            return new java.util.ArrayList<>();
        }
    }
}
