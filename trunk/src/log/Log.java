package log;

public class Log {
    /**
     * 将message中的{}换成后面的东西
     */
    public static void error(String message, Object... args){
        for (Object arg : args) {
            message = message.replaceFirst("\\{ *}",arg.toString());
        }
        System.err.println(message);
    }

    public static void debug(String message, Object... args){
        for (Object arg : args) {
            message = message.replaceFirst("\\{ *}",arg.toString());
        }
        System.out.println(message);
    }
}
