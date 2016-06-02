package com.garmin.parkapp.logger;

import android.content.res.Configuration;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Wrapper class over the logger to allow us to set build mode on the logger.
 *
 * @author ioana.morari on 17/02/16.
 */
public enum Logger {

    INSTANCE;

    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
    private static final int CALL_STACK_INDEX = 3;
    /**
     * The default log level (minimum value).
     */
    private int LOG_LEVEL;

    private BuildType BUILD_TYPE;

    private Configuration configuration = new Configuration();

    Logger() {
        setBuildType(BuildType.DEBUG);
    }

    /**
     * @return the default log level.
     */
    public int getDefaultLogLevel() {
        return LOG_LEVEL;
    }

    /**
     * Sets the default log level.
     *
     * @param logLevel the log level.
     */
    public void setDefaultLogLevel(int logLevel) {
        LOG_LEVEL = logLevel;
    }

    /**
     * Sets the build type.
     *
     * @param buildType the build type.
     */
    public void setBuildType(BuildType buildType) {
        BUILD_TYPE = buildType;
    }

    /**
     * Sets the build type as String. Automatically sets the log level associated with the build type.
     * RELEASE --> INFO & DEBUG --> VERBOSE
     *
     * @param buildType the String representation of a build type.
     */
    public void setBuildType(String buildType) {

        BUILD_TYPE = BuildType.getBuildTypeFromString(buildType);
        LOG_LEVEL = BUILD_TYPE.getLogLevel();
    }

    /**
     * Logs the given message using the verbose level.
     *
     * @param message the message to be logged.
     */
    public void v(String message) {
        logV(message);
    }

    /**
     * Logs the given message using the verbose level substituting the format arguments as defined in {@link
     * java.util.Formatter} and {@link String#format}.
     *
     * @param message    The message to be logged.
     * @param formatArgs The format arguments that will be used for substitution.
     */
    public void v(String message, Object... formatArgs) {
        logV(String.format(configuration.locale, message, formatArgs));
    }

    private void logV(String message) {
        if (BUILD_TYPE != BuildType.RELEASE && VERBOSE >= LOG_LEVEL) {
            Log.v(getClassName(), appendThreadInfo(message));
        }
    }

    /**
     * Logs the given message using the debug level.
     *
     * @param message the message to be logged.
     */
    public void d(String message) {
        logD(message);
    }

    /**
     * Logs the given message using the debug level substituting the format arguments as defined in {@link
     * java.util.Formatter} and {@link String#format}.
     *
     * @param message    The message to be logged.
     * @param formatArgs The format arguments that will be used for substitution.
     */
    public void d(String message, Object... formatArgs) {
        logD(String.format(configuration.locale, message, formatArgs));
    }

    private void logD(String message) {
        if (DEBUG >= LOG_LEVEL) {
            Log.d(getClassName(), appendThreadInfo(message));
        }
    }

    /**
     * Logs the given message and throwable using the debug level.
     *
     * @param message   the message to be logged.
     * @param throwable the throwable.
     */
    public void d(String message, Throwable throwable) {
        if (DEBUG >= LOG_LEVEL) {
            Log.d(getClassName(), appendThreadInfo(message), throwable);
        }
    }

    /**
     * Logs the given message using the info level.
     *
     * @param message the message to be logged.
     */
    public void i(String message) {
        logI(message);
    }

    /**
     * Logs the given message using the info level substituting the format arguments as defined in {@link
     * java.util.Formatter} and {@link String#format}.
     *
     * @param message    The message to be logged.
     * @param formatArgs The format arguments that will be used for substitution.
     */
    public void i(String message, Object... formatArgs) {
        logI(String.format(configuration.locale, message, formatArgs));
    }

    private void logI(String message) {
        if (INFO >= LOG_LEVEL) {
            Log.i(getClassName(), appendThreadInfo(message));
        }
    }

    /**
     * Logs the given message using the warning level.
     *
     * @param message the message to be logged.
     */
    public void w(String message) {
        logW(message, null);
    }

    /**
     * Logs the given message and throwable using the warning level.
     *
     * @param message   the message to be logged.
     * @param throwable the throwable.
     */
    public void w(String message, Throwable throwable) {
        logW(message, throwable);
    }

    /**
     * Logs the given message using the warning level substituting the format arguments as defined in {@link
     * java.util.Formatter} and {@link String#format}.
     *
     * @param message    The message to be logged.
     * @param formatArgs The format arguments that will be used for substitution.
     */
    public void w(String message, Object... formatArgs) {
        logW(String.format(configuration.locale, message, formatArgs), null);
    }

    private void logW(String message, Throwable throwable) {
        if (WARNING >= LOG_LEVEL) {
            if (throwable != null) {
                Log.w(getClassName(), appendThreadInfo(message), throwable);
            } else {
                Log.w(getClassName(), appendThreadInfo(message));
            }
        }
    }

    /**
     * Logs the given message using the error level.
     *
     * @param message the message to be logged.
     */
    public void e(String message) {
        logE(message, null);
    }

    /**
     * Logs the given message and throwable using the error level.
     *
     * @param message   the message to be logged.
     * @param throwable the throwable.
     */
    public void e(String message, Throwable throwable) {
        logE(message, throwable);
    }

    /**
     * Logs the given message using the error level substituting the format arguments as defined in {@link
     * java.util.Formatter} and {@link String#format}.
     *
     * @param message    The message to be logged.
     * @param formatArgs The format arguments that will be used for substitution.
     */
    public void e(String message, Object... formatArgs) {
        logE(String.format(configuration.locale, message, formatArgs), null);
    }

    private void logE(String message, Throwable throwable) {
        if (ERROR >= LOG_LEVEL) {
            if (throwable != null) {
                Log.e(getClassName(), appendThreadInfo(message), throwable);
            } else {
                Log.e(getClassName(), appendThreadInfo(message));
            }
        }
    }

    /**
     * Appends the name of the current thread to the provided message.
     *
     * @param message the log message.
     * @return a new String including the thread name and the message in the format: <code>[thread_name] message</code>
     */
    private String appendThreadInfo(String message) {
        return "[" + Thread.currentThread().getName() + "]" + " " + message;
    }

    private String createStackElementTag(StackTraceElement element) {

        String tag = element.getClassName();
        Matcher m = ANONYMOUS_CLASS.matcher(tag);
        if (m.find()) {
            tag = m.replaceAll("");
        }

        return tag.substring(tag.lastIndexOf('.') + 1);
    }

    private String getClassName() {

        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length <= CALL_STACK_INDEX) {
            throw new IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }
        return createStackElementTag(stackTrace[CALL_STACK_INDEX]);
    }

    public enum BuildType {
        DEBUG("debug", VERBOSE), RELEASE("release", ERROR);

        private final String buildTypeString;
        private final int logLevel;

        BuildType(String buildTypeString, int logLevel) {
            this.buildTypeString = buildTypeString;
            this.logLevel = logLevel;
        }

        public static BuildType getBuildTypeFromString(String buildTypeString) {
            for (BuildType buildType : values()) {
                if (buildType.buildTypeString.equalsIgnoreCase(buildTypeString)) {
                    return buildType;
                }
            }

            return DEBUG;
        }

        public int getLogLevel() {
            return logLevel;
        }
    }
}


