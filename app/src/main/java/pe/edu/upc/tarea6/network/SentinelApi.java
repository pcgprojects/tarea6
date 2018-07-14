package pe.edu.upc.tarea6.network;

/**
 * Created by roy on 14/07/2018.
 */

public class SentinelApi {

    private static String BASE_URL = "http://178.128.155.0/apiv1/public";

    public static String getAuthUrl() {
        return BASE_URL + "/auth";
    }

    public static String getUserUrl(){
        return BASE_URL + "/user";
    }

    public static String getAssigmentsUrl() {
        return BASE_URL + "/assignments/{employeeId}";
    }

    public static String getAssignmentsByDateUrl() {
        return BASE_URL + "/assignmentsByDate/{employeeId}";
    }
    public static String changeState() {
        return BASE_URL + "/assignments/{id}";
    }

}

