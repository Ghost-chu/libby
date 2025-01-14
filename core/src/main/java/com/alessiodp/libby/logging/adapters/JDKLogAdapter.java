package com.alessiodp.libby.logging.adapters;

import com.alessiodp.libby.logging.LogLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * Logging adapter that logs to a JDK logger.
 */
public class JDKLogAdapter implements LogAdapter {
    /**
     * JDK logger
     */
    private final Logger logger;

    /**
     * Creates a new JDK log adapter that logs to a {@link Logger}.
     *
     * @param logger the JDK logger to wrap
     */
    public JDKLogAdapter(@NotNull Logger logger) {
        this.logger = requireNonNull(logger, "logger");
    }

    /**
     * Logs a message with the provided level to the JDK logger.
     *
     * @param level   message severity level
     * @param message the message to log
     */
    @Override
    public void log(@NotNull LogLevel level, @Nullable String message) {
        switch (requireNonNull(level, "level")) {
            case DEBUG:
                logger.log(Level.FINE, message);
                break;
            case INFO:
                logger.log(Level.INFO, message);
                break;
            case WARN:
                logger.log(Level.WARNING, message);
                break;
            case ERROR:
                logger.log(Level.SEVERE, message);
                break;
        }
    }

    /**
     * Logs a message and stack trace with the provided level to the JDK
     * logger.
     *
     * @param level     message severity level
     * @param message   the message to log
     * @param throwable the throwable to print
     */
    @Override
    public void log(@NotNull LogLevel level, @Nullable String message, @Nullable Throwable throwable) {
        switch (requireNonNull(level, "level")) {
            case DEBUG:
                logger.log(Level.FINE, message, throwable);
                break;
            case INFO:
                logger.log(Level.INFO, message, throwable);
                break;
            case WARN:
                logger.log(Level.WARNING, message, throwable);
                break;
            case ERROR:
                logger.log(Level.SEVERE, message, throwable);
                break;
        }
    }
}
