package exceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

/**
 * Base class for all custom exceptions in the banking system.
 * Provides consistent error handling, logging, and contextual details.
 */
public class GlobalExceptionHandler extends Exception {
  private final Instant timestamp;
  private final String errorCode; // e.g., "WITHDRAWAL_LIMIT", "INVALID_AMOUNT"

  public GlobalExceptionHandler(String message, String errorCode) {
    super(message);
    this.timestamp = Instant.now();
    this.errorCode = errorCode;
  }

  // Getters for contextual data
  public Instant getTimestamp() { return timestamp; }
  public String getErrorCode() { return errorCode; }

  /**
   * Formats the error for GUI display (simplifies user-facing messages).
   */
  public String getUserFriendlyMessage() {
    return String.format(
            "[%s] %s (Ref: %s)",
            errorCode,
            getMessage(),
            timestamp.toString().substring(0, 19)
    );
  }

  /**
   * Logs the exception to a file or system logger.
   */
  public void logError() {
    String logEntry = String.format(
            "%s | Code: %s | Details: %s",
            timestamp,
            errorCode,
            getMessage()
    );
    // Example: Write to transactions.csv or a dedicated error log
    try (FileWriter writer = new FileWriter("errors.log", true)) {
      writer.write(logEntry + "\n");
    } catch (IOException ignored) {}
  }
}